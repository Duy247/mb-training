package com.mb.training.karate.ui

import com.intellij.execution.RunContentExecutor
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.KillableColoredProcessHandler
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import com.intellij.openapi.util.Key
import com.intellij.ui.JBColor
import com.intellij.ui.JBSplitter
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingItem
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.services.TrainingProgressSnapshot
import com.mb.training.karate.services.TrainingProjectProgressStore
import com.mb.training.karate.training.TrainingCurriculumRepository
import com.mb.training.karate.training.TrainingProgressEngine
import org.jetbrains.idea.maven.project.MavenProject
import org.jetbrains.idea.maven.project.MavenProjectsManager
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.FlowLayout
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.DefaultListCellRenderer
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.ListSelectionModel
import javax.swing.SwingUtilities
import javax.swing.Timer

class TrainingToolWindowPanel(
    private val project: Project
) : JPanel(BorderLayout()) {
    companion object {
        private const val AUTO_REFRESH_MS = 3_000
    }

    private val projectRoot = project.basePath?.let { Path.of(it) }
    private val hintPresenter = StepHintPresenter(project, projectRoot)
    private val engine = projectRoot?.let { TrainingProgressEngine(it, TrainingCurriculumRepository.program) }
    private val listModel = DefaultListModel<TrainingItem>()
    private val list = JBList(listModel)
    private val detailsScroll = JBScrollPane()
    private val progressLabel = JBLabel()
    private val validateButton = JButton("Cập nhật tiến độ")
    private val resetButton = JButton("Đặt lại")
    private val fallbackCurrentId = TrainingCurriculumRepository.items.firstOrNull()?.id.orEmpty()
    private var snapshot = runEngine(loadInitialSnapshot())
    private val shownExerciseIntroIds = mutableSetOf<String>()
    private val projectRootPathString = projectRoot?.normalize()?.toString()
    private var currentDetailsExerciseId: String? = null
    private val stepStatusLabels = linkedMapOf<String, JBLabel>()
    private val mavenSyncIds = collectMavenSyncIds()
    private var suppressProgressDialogs = false
    private val autoRefreshTimer = Timer(AUTO_REFRESH_MS) {
        if (!isShowing) return@Timer
        refreshProgressFromEngine()
        list.repaint()
        refreshStepStatusesOnly()
    }

    init {
        populateList()
        configureList()
        configureDetails()
        configureLayout()
        bindActions()
        bindAutoDetection()
        bindAutoRefreshPolling()
        bindMavenSyncDetection()
        applyCurrentSelectionFromSnapshot()
        maybeShowCurrentExerciseIntro()
        refreshProgress()
        refreshDetailsFromSelection()
    }

    private fun loadInitialSnapshot(): TrainingProgressSnapshot {
        val root = projectRoot ?: return TrainingProgressSnapshot(fallbackCurrentId, emptySet())
        return TrainingProjectProgressStore.load(root) ?: TrainingProgressSnapshot(fallbackCurrentId, emptySet())
    }

    private fun populateList() {
        TrainingCurriculumRepository.items.forEach(listModel::addElement)
    }

    private fun configureList() {
        list.selectionMode = ListSelectionModel.SINGLE_SELECTION
        list.cellRenderer = object : DefaultListCellRenderer() {
            override fun getListCellRendererComponent(
                list: javax.swing.JList<*>?,
                value: Any?,
                index: Int,
                isSelected: Boolean,
                cellHasFocus: Boolean
            ): Component {
                val component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
                val item = value as? TrainingItem
                if (item != null) {
                    val donePrefix = if (isCompleted(item.id)) "[X]" else "[ ]"
                    text = "$donePrefix ${item.title}"
                }
                return component
            }
        }
    }

    private fun configureDetails() {
        detailsScroll.border = JBUI.Borders.empty()
        detailsScroll.horizontalScrollBarPolicy = JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        detailsScroll.verticalScrollBarPolicy = JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        setDetailsView(createEmptyDetailsPanel())
    }

    private fun configureLayout() {
        val left = JPanel(BorderLayout()).apply {
            add(JBLabel("Lộ trình"), BorderLayout.NORTH)
            add(JBScrollPane(list), BorderLayout.CENTER)
        }

        val right = JPanel(BorderLayout()).apply {
            add(JBLabel("Chi tiết"), BorderLayout.NORTH)
            add(detailsScroll, BorderLayout.CENTER)
        }

        val splitter = JBSplitter(false, 0.35f).apply {
            firstComponent = left
            secondComponent = right
        }

        val actions = JPanel().apply {
            add(progressLabel)
            add(validateButton)
            add(resetButton)
        }

        add(splitter, BorderLayout.CENTER)
        add(actions, BorderLayout.SOUTH)
    }

    private fun bindActions() {
        list.addListSelectionListener {
            if (!it.valueIsAdjusting) {
                val item = list.selectedValue ?: return@addListSelectionListener
                snapshot = snapshot.copy(currentItemId = item.id)
                persistSnapshot()
                refreshDetailsFromSelection()
            }
        }

        validateButton.addActionListener {
            refreshProgressFromEngine()
            list.repaint()
            refreshStepStatusesOnly()
        }

        resetButton.addActionListener {
            val confirmed = Messages.showYesNoDialog(
                project,
                "Đặt lại sẽ xóa tiến độ đã lưu của bài hiện tại (bao gồm trạng thái run/sync). Tiếp tục?",
                "Xác nhận đặt lại",
                "Đặt lại",
                "Hủy",
                null
            )
            if (confirmed != Messages.YES) return@addActionListener

            snapshot = TrainingProgressSnapshot(
                currentItemId = snapshot.currentItemId,
                completedIds = emptySet(),
                completedStepIds = emptySet(),
                passedCommandIds = emptySet(),
                successfulMavenSyncIds = emptySet()
            )
            refreshProgressFromEngine()
            persistSnapshot()
            list.repaint()
            refreshDetailsFromSelection()
        }
    }

    private fun bindAutoDetection() {
        val rootPath = projectRootPathString ?: return
        project.messageBus.connect(project).subscribe(
            VirtualFileManager.VFS_CHANGES,
            object : BulkFileListener {
                override fun after(events: MutableList<out VFileEvent>) {
                    val hasProjectFileChanges = events.any { it.path.startsWith(rootPath, ignoreCase = true) }
                    if (!hasProjectFileChanges) return
                    SwingUtilities.invokeLater {
                        refreshProgressFromEngine()
                        list.repaint()
                        refreshStepStatusesOnly()
                    }
                }
            }
        )
    }

    private fun refreshProgressFromEngine() {
        val updated = runEngine(snapshot)
        if (updated != snapshot) {
            snapshot = updated
            persistSnapshot()
        }
        refreshProgress()
        if (!suppressProgressDialogs) {
            maybeShowCurrentExerciseIntro()
        }
        if (list.selectedIndex < 0) {
            applyCurrentSelectionFromSnapshot()
        }
    }

    private fun refreshProgress() {
        val total = TrainingCurriculumRepository.items.size
        val completed = TrainingCurriculumRepository.items.count { isCompleted(it.id) }
        progressLabel.text = "Tiến độ: $completed/$total"
    }

    private fun applyCurrentSelectionFromSnapshot() {
        val targetId = snapshot.currentItemId.ifEmpty { fallbackCurrentId }
        val idx = TrainingCurriculumRepository.items.indexOfFirst { it.id == targetId }.takeIf { it >= 0 } ?: 0
        if (listModel.size() > 0) {
            list.selectedIndex = idx
            list.ensureIndexIsVisible(idx)
        }
    }

    private fun refreshDetailsFromSelection() {
        val selected = list.selectedValue
        if (selected == null) {
            currentDetailsExerciseId = null
            stepStatusLabels.clear()
            setDetailsView(createEmptyDetailsPanel())
            return
        }
        if (currentDetailsExerciseId == selected.id) {
            refreshStepStatusesOnly()
            return
        }
        currentDetailsExerciseId = selected.id
        setDetailsView(createExerciseDetailsPanel(selected.id))
    }

    private fun createEmptyDetailsPanel(): JComponent {
        val panel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            border = JBUI.Borders.empty(10)
            alignmentX = Component.LEFT_ALIGNMENT
            add(sectionTitle("Chi tiết bài học"))
            add(Box.createVerticalStrut(6))
            add(wrappedText("Chọn một bài trong lộ trình để xem mục tiêu, các bước thực hiện và kết quả kỳ vọng."))
            add(Box.createVerticalGlue())
        }
        return panel
    }

    private fun createExerciseDetailsPanel(itemId: String): JComponent {
        val exercise = TrainingCurriculumRepository.program.exercises.firstOrNull { it.id == itemId }
            ?: return createEmptyDetailsPanel()
        stepStatusLabels.clear()

        val panel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            border = JBUI.Borders.empty(10)
            alignmentX = Component.LEFT_ALIGNMENT
        }

        panel.add(titleLabel(exercise.title))
        panel.add(Box.createVerticalStrut(4))
        panel.add(metaLabel("Độ khó: ${toVietnameseLevel(exercise.level.name)}  |  Loại: ${toVietnameseType(exercise.type.name)}"))
        panel.add(Box.createVerticalStrut(10))
        panel.add(sectionTitle("Mục tiêu"))
        panel.add(Box.createVerticalStrut(4))
        panel.add(wrappedText(exercise.objective))
        panel.add(Box.createVerticalStrut(10))
        panel.add(sectionTitle("Các bước thực hiện"))
        panel.add(Box.createVerticalStrut(6))

        exercise.steps.forEach { step ->
            panel.add(createStepCard(exercise.id, step))
            panel.add(Box.createVerticalStrut(8))
        }

        panel.add(sectionTitle("Điều kiện hoàn thành bài"))
        panel.add(Box.createVerticalStrut(4))
        panel.add(
            wrappedText(
                when (exercise.completionPolicy.name) {
                    "ALL_STEPS_DONE" -> "Hoàn thành khi tất cả các bước hoàn tất."
                    "ANY_STEP_DONE" -> "Hoàn thành khi hoàn tất ít nhất một bước."
                    else -> "Hoàn thành theo điều kiện của bài."
                }
            )
        )
        panel.add(Box.createVerticalStrut(10))
        panel.add(sectionTitle("Kết quả kỳ vọng"))
        panel.add(Box.createVerticalStrut(4))
        panel.add(wrappedText(exercise.expectedOutcome))
        val summary = exercise.knowledgeSummary
        if (summary != null) {
            panel.add(Box.createVerticalStrut(10))
            panel.add(sectionTitle("Tóm tắt"))
            panel.add(Box.createVerticalStrut(4))
            panel.add(
                JButton("Mở tóm tắt kiến thức").apply {
                    alignmentX = Component.LEFT_ALIGNMENT
                    addActionListener { showKnowledgeSummary(summary) }
                }
            )
        }
        panel.add(Box.createVerticalGlue())
        return panel
    }

    private fun createStepCard(exerciseId: String, step: TrainingStep): JComponent {
        val done = isStepCompleted(exerciseId, step.id)
        val card = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(JBColor(0xD0D7DE, 0x30363D), 1, true),
                JBUI.Borders.empty(8)
            )
            isOpaque = true
            background = JBColor(0xF6F8FA, 0x1F242D)
            alignmentX = Component.LEFT_ALIGNMENT
            maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        }

        val statusText = if (done) "Đã hoàn thành" else "Chưa hoàn thành"
        val statusColor = if (done) JBColor(0x1A7F37, 0x3FB950) else JBColor(0x9A6700, 0xD29922)
        val statusLabel = JBLabel(statusText).apply {
            foreground = statusColor
            alignmentX = Component.LEFT_ALIGNMENT
        }

        val top = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            add(JBLabel(step.title).apply {
                font = JBFont.label().asBold()
                alignmentX = Component.LEFT_ALIGNMENT
            })
            add(Box.createVerticalStrut(2))
            add(statusLabel)
        }
        stepStatusLabels[stepStatusKey(exerciseId, step.id)] = statusLabel

        card.add(top)
        card.add(Box.createVerticalStrut(4))
        card.add(wrappedText("Hướng dẫn: ${step.guidance}", italic = true))
        card.add(Box.createVerticalStrut(6))
        val actionRow = JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(6), 0)).apply {
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
            maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        }
        val hintButton = JButton("Hint").apply {
            isEnabled = step.hints.isNotEmpty()
            toolTipText = if (step.hints.isNotEmpty()) "Hiển thị gợi ý HUD cho bước này" else "Bước này chưa có gợi ý"
            addActionListener { hintPresenter.showHints(step.hints, this) }
        }
        actionRow.add(hintButton)
        extractRunnableActions(step.activities).forEach { runnableAction ->
            val actionButton = JButton(runnableActionLabel(runnableAction)).apply {
                toolTipText = runnableActionTooltip(runnableAction)
                addActionListener {
                    when (runnableAction) {
                        is RunnableStepAction.Command -> runStepCommand(
                            exerciseId = exerciseId,
                            stepId = step.id,
                            commandId = runnableAction.commandId,
                            command = runnableAction.commandHint,
                            button = this
                        )
                        is RunnableStepAction.MavenSync -> runMavenSync(
                            exerciseId = exerciseId,
                            stepId = step.id,
                            syncId = runnableAction.syncId,
                            button = this
                        )
                    }
                }
            }
            actionRow.add(actionButton)
        }

        val openSettingsActivity = step.activities
            .filterIsInstance<TrainingActivity.OpenMavenSettings>()
            .firstOrNull()
        if (openSettingsActivity != null) {
            val openSettingsButton = JButton(openSettingsActivity.buttonLabel).apply {
                toolTipText = "Mở file settings.xml Maven hiệu lực trong IntelliJ"
                addActionListener { openEffectiveMavenSettings() }
            }
            actionRow.add(openSettingsButton)
        }
        card.add(actionRow)
        card.add(Box.createVerticalStrut(6))
        card.add(JBLabel("Việc cần làm").apply { font = JBFont.label().asBold() })
        card.add(Box.createVerticalStrut(4))
        card.add(bulletedList(step.activities.map { renderActivity(it) }))
        return card
    }

    private fun titleLabel(text: String): JComponent {
        return JBLabel(text).apply {
            font = JBFont.label().deriveFont(JBFont.label().size + 3f).asBold()
            alignmentX = Component.LEFT_ALIGNMENT
        }
    }

    private fun metaLabel(text: String): JComponent {
        return JBLabel(text).apply {
            foreground = JBColor.GRAY
            font = JBFont.small()
            alignmentX = Component.LEFT_ALIGNMENT
        }
    }

    private fun sectionTitle(text: String): JComponent {
        return JBLabel(text).apply {
            font = JBFont.label().asBold()
            foreground = JBColor(0x0550AE, 0x79C0FF)
            alignmentX = Component.LEFT_ALIGNMENT
        }
    }

    private fun wrappedText(text: String, italic: Boolean = false): JComponent {
        return JBTextArea(text).apply {
            isEditable = false
            lineWrap = true
            wrapStyleWord = true
            border = null
            isOpaque = false
            columns = 48
            font = if (italic) JBFont.label().deriveFont(JBFont.label().size.toFloat()).deriveFont(java.awt.Font.ITALIC) else JBFont.label()
            alignmentX = Component.LEFT_ALIGNMENT
            maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        }
    }

    private fun bulletedList(items: List<String>): JComponent {
        val panel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
            maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        }
        items.forEach { item ->
            panel.add(wrappedText("• $item"))
            panel.add(Box.createVerticalStrut(2))
        }
        return panel
    }

    private fun setDetailsView(component: JComponent) {
        detailsScroll.setViewportView(component)
        detailsScroll.viewport.revalidate()
        detailsScroll.viewport.repaint()
    }

    private fun maybeShowCurrentExerciseIntro() {
        val currentExerciseId = snapshot.currentItemId.ifEmpty { fallbackCurrentId }
        if (currentExerciseId.isBlank()) return
        if (snapshot.completedIds.contains(currentExerciseId)) return
        val exercise = TrainingCurriculumRepository.program.exercises.firstOrNull { it.id == currentExerciseId } ?: return
        val intro = exercise.intro ?: return
        val dialog = BasicExerciseIntroDialog(project, intro)
        if (!shownExerciseIntroIds.add(currentExerciseId)) return
        dialog.show()
    }

    private fun showKnowledgeSummary(summary: com.mb.training.karate.model.TrainingKnowledgeSummary) {
        if (project.isDisposed) return
        KnowledgeSummaryDialog(project, summary).show()
    }

    private fun isCompleted(itemId: String): Boolean = snapshot.completedIds.contains(itemId)

    private fun isStepCompleted(exerciseId: String, stepId: String): Boolean {
        val key = engine?.stepKey(exerciseId, stepId) ?: "$exerciseId::$stepId"
        return snapshot.completedStepIds.contains(key)
    }

    private fun refreshStepStatusesOnly() {
        val exerciseId = currentDetailsExerciseId ?: return
        val exercise = TrainingCurriculumRepository.program.exercises.firstOrNull { it.id == exerciseId } ?: return
        exercise.steps.forEach { step ->
            val key = stepStatusKey(exercise.id, step.id)
            val label = stepStatusLabels[key] ?: return@forEach
            val done = isStepCompleted(exercise.id, step.id)
            label.text = if (done) "Đã hoàn thành" else "Chưa hoàn thành"
            label.foreground = if (done) JBColor(0x1A7F37, 0x3FB950) else JBColor(0x9A6700, 0xD29922)
        }
        detailsScroll.viewport.repaint()
    }

    private fun stepStatusKey(exerciseId: String, stepId: String): String {
        return "$exerciseId::$stepId"
    }

    private fun persistSnapshot() {
        val root = projectRoot ?: return
        TrainingProjectProgressStore.save(root, snapshot)
    }

    private fun runEngine(current: TrainingProgressSnapshot): TrainingProgressSnapshot {
        return engine?.sync(current) ?: current
    }

    private fun bindAutoRefreshPolling() {
        autoRefreshTimer.initialDelay = AUTO_REFRESH_MS
        autoRefreshTimer.isRepeats = true
        autoRefreshTimer.start()
    }

    override fun removeNotify() {
        autoRefreshTimer.stop()
        super.removeNotify()
    }

    private fun renderActivity(activity: TrainingActivity): String {
        return when (activity) {
            is TrainingActivity.CreateFolder -> "Tạo thư mục ${activity.relativePath}"
            is TrainingActivity.CreateFile -> "Tạo file ${activity.relativePath} từ mẫu"
            is TrainingActivity.CodeTask -> "Thực hiện coding: ${activity.instruction} Ctrl + S (Save) để ghi nhận tiến độ code"
            is TrainingActivity.OpenMavenSettings -> "Mở file Maven settings đang được IntelliJ sử dụng"
            is TrainingActivity.RunCommandTask -> "${activity.instruction} (${activity.commandHint})"
            is TrainingActivity.RefreshMavenProjects -> activity.actionLabel
            is TrainingActivity.CompileTask -> "Lệnh biên dịch gợi ý: ${activity.commandHint}"
            is TrainingActivity.RunTestTask -> "Lệnh chạy test gợi ý: ${activity.commandHint}"
        }
    }

    private fun extractRunnableActions(activities: List<TrainingActivity>): List<RunnableStepAction> {
        return activities.mapNotNull { activity ->
            when (activity) {
                is TrainingActivity.RunCommandTask -> RunnableStepAction.Command(activity.commandId, activity.commandHint)
                is TrainingActivity.RunTestTask -> RunnableStepAction.Command(activity.commandId, activity.commandHint)
                is TrainingActivity.CompileTask -> RunnableStepAction.Command(activity.commandId, activity.commandHint)
                is TrainingActivity.RefreshMavenProjects -> RunnableStepAction.MavenSync(activity.syncId, activity.actionLabel)
                else -> null
            }
        }
    }

    private fun runnableActionLabel(action: RunnableStepAction): String {
        return when (action) {
            is RunnableStepAction.Command -> "Run"
            is RunnableStepAction.MavenSync -> "Run"
        }
    }

    private fun runnableActionTooltip(action: RunnableStepAction): String {
        return when (action) {
            is RunnableStepAction.Command -> "Chạy: ${action.commandHint}"
            is RunnableStepAction.MavenSync -> action.actionLabel
        }
    }

    private fun runStepCommand(
        exerciseId: String,
        stepId: String,
        commandId: String,
        command: String,
        button: JButton
    ) {
        val root = projectRoot ?: run {
            Messages.showErrorDialog(project, "Không tìm thấy project root để chạy lệnh.", "MB Training")
            return
        }
        val originalText = button.text
        button.isEnabled = false
        button.text = "Running..."

        try {
            val effectiveCommand = toInteractiveCommand(command)
            val commandLine = buildCommandLine(root, effectiveCommand)
            val processHandler = KillableColoredProcessHandler(commandLine)
            val outputBuffer = StringBuilder()

            processHandler.addProcessListener(object : ProcessAdapter() {
                override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
                    outputBuffer.append(event.text)
                }

                override fun processTerminated(event: ProcessEvent) {
                    SwingUtilities.invokeLater {
                        button.isEnabled = true
                        button.text = originalText

                        val fullOutput = outputBuffer.toString()
                        val evaluation = evaluateCommandResult(
                            commandId = commandId,
                            command = effectiveCommand,
                            exitCode = event.exitCode,
                            output = fullOutput,
                            root = root
                        )
                        val result = CommandRunResult(event.exitCode, summarizeOutput(fullOutput), evaluation.passed)

                        if (result.passed) {
                            snapshot = snapshot.copy(passedCommandIds = snapshot.passedCommandIds + commandId)
                            persistSnapshot()
                            list.repaint()
                            updateStepStatus(exerciseId, stepId)
                            suppressProgressDialogs = true
                            try {
                                showRunSuccessDialog(effectiveCommand)
                            } finally {
                                suppressProgressDialogs = false
                            }
                            refreshProgressFromEngine()
                        } else {
                            Messages.showErrorDialog(
                                project,
                                evaluation.message ?: (
                                    "Lệnh đã chạy nhưng chưa đạt điều kiện pass.\n" +
                                        "Yêu cầu: exit code = 0 và có BUILD SUCCESS/BUILD PASSED trong log.\n\n${result.summary}"
                                    ),
                                "Run chưa đạt"
                            )
                        }
                    }
                }
            })

            RunContentExecutor(project, processHandler)
                .withTitle("MB Training: $effectiveCommand")
                .run()
        } catch (e: Exception) {
            button.isEnabled = true
            button.text = originalText
            Messages.showErrorDialog(project, "Không thể khởi chạy lệnh.\n${e.message}", "MB Training")
        }
    }

    private fun toInteractiveCommand(command: String): String {
        val normalized = command.trim()
        return if (Regex("""\bmvn(\.cmd)?\s+-q\s+test\b""", RegexOption.IGNORE_CASE).containsMatchIn(normalized)) {
            normalized.replace(Regex("""\s+-q\b""", RegexOption.IGNORE_CASE), "")
        } else {
            normalized
        }
    }

    private fun buildCommandLine(root: Path, command: String): GeneralCommandLine {
        val os = System.getProperty("os.name").lowercase()
        val parts = if (os.contains("win")) {
            listOf("cmd.exe", "/c", command)
        } else {
            listOf("sh", "-lc", command)
        }
        return GeneralCommandLine(parts).withWorkDirectory(root.toFile())
    }

    private fun evaluateCommandResult(
        commandId: String,
        command: String,
        exitCode: Int,
        output: String,
        root: Path
    ): CommandEvaluation {
        if (commandId == "basic-exercise-1-maven-repo-check") {
            if (exitCode != 0) {
                return CommandEvaluation(
                    passed = false,
                    message = "Không thể kiểm tra nguồn repository (exit code != 0). " +
                        "Hãy kiểm tra Maven và chạy lại.\n\n${summarizeOutput(output)}"
                )
            }
            val effectiveSettings = root.resolve("target").resolve("effective-settings.xml")
            if (!Files.exists(effectiveSettings)) {
                return CommandEvaluation(
                    passed = false,
                    message = "Không tìm thấy file target/effective-settings.xml. " +
                        "Hãy chạy lại bước kiểm tra."
                )
            }
            val content = Files.readString(effectiveSettings)
            val usesMavenCentral = content.contains("repo.maven.apache.org", ignoreCase = true) ||
                content.contains("repo1.maven.org", ignoreCase = true) ||
                content.contains("repo.maven.apache.org/maven2", ignoreCase = true)
            if (usesMavenCentral) {
                return CommandEvaluation(
                    passed = false,
                    message = "Phát hiện Maven đang pull từ Maven Central.\n\n" +
                        "Vui lòng cập nhật ~/.m2/settings.xml để dùng Nexus nội bộ, sau đó chạy lại bước này."
                )
            }
            return CommandEvaluation(
                passed = true,
                message = null
            )
        }

        if (exitCode != 0) return CommandEvaluation(false, null)
        if (!command.contains("mvn", ignoreCase = true)) return CommandEvaluation(true, null)
        val normalized = output.uppercase()
        if (normalized.contains("BUILD SUCCESS") || normalized.contains("BUILD PASSED")) {
            return CommandEvaluation(true, null)
        }
        val surefireReports = root.resolve("target").resolve("surefire-reports")
        if (!Files.isDirectory(surefireReports)) {
            return CommandEvaluation(false, null)
        }
        val hasReports = Files.list(surefireReports).use { stream -> stream.findAny().isPresent }
        return CommandEvaluation(hasReports, null)
    }

    private fun openEffectiveMavenSettings() {
        ApplicationManager.getApplication().executeOnPooledThread {
            val manager = MavenProjectsManager.getInstance(project)
            val settingsPath = resolveMavenSettingsPath(manager)
            if (settingsPath == null) {
                ApplicationManager.getApplication().invokeLater(
                    {
                        if (project.isDisposed) return@invokeLater
                        Messages.showErrorDialog(
                            project,
                            "Không xác định được Maven settings.xml đang dùng.",
                            "Open settings.xml"
                        )
                    },
                    ModalityState.defaultModalityState()
                )
                return@executeOnPooledThread
            }
            try {
                val parent = settingsPath.parent
                if (parent != null) {
                    Files.createDirectories(parent)
                }
                if (!Files.exists(settingsPath)) {
                    Files.writeString(settingsPath, "<settings>\n</settings>\n")
                }
            } catch (e: Exception) {
                ApplicationManager.getApplication().invokeLater(
                    {
                        if (project.isDisposed) return@invokeLater
                        Messages.showErrorDialog(
                            project,
                            "Không thể chuẩn bị file settings.xml: ${e.message}",
                            "Open settings.xml"
                        )
                    },
                    ModalityState.defaultModalityState()
                )
                return@executeOnPooledThread
            }

            ApplicationManager.getApplication().invokeLater(
                {
                    if (project.isDisposed) return@invokeLater
                    val vFile = LocalFileSystem.getInstance().refreshAndFindFileByNioFile(settingsPath)
                    if (vFile == null) {
                        Messages.showErrorDialog(
                            project,
                            "Không mở được file: $settingsPath",
                            "Open settings.xml"
                        )
                        return@invokeLater
                    }
                    OpenFileDescriptor(project, vFile).navigate(true)
                },
                ModalityState.defaultModalityState()
            )
        }
    }

    private fun resolveMavenSettingsPath(manager: MavenProjectsManager): Path? {
        val configured = manager.generalSettings.userSettingsFile?.trim().orEmpty()
        val pathText = if (configured.isNotEmpty()) {
            if (configured.startsWith("~")) {
                System.getProperty("user.home") + configured.removePrefix("~")
            } else {
                configured
            }
        } else {
            Paths.get(System.getProperty("user.home"), ".m2", "settings.xml").toString()
        }
        return try {
            Paths.get(pathText).toAbsolutePath().normalize()
        } catch (_: Exception) {
            null
        }
    }

    private fun runMavenSync(exerciseId: String, stepId: String, syncId: String, button: JButton) {
        val manager = MavenProjectsManager.getInstance(project)
        val originalText = button.text
        button.isEnabled = false
        button.text = "Syncing..."
        manager.forceUpdateAllProjectsOrFindAllAvailablePomFiles()
        Timer(2500) { _ ->
            if (!button.isDisplayable) return@Timer
            checkMavenSyncHealthyAsync { healthy ->
                button.isEnabled = true
                button.text = originalText
                if (healthy) {
                    updateMavenSyncState(syncId = syncId, synced = true)
                    updateStepStatus(exerciseId, stepId)
                    Messages.showInfoMessage(project, "Maven sync đã thành công.", "Sync thành công")
                } else {
                    Messages.showWarningDialog(
                        project,
                        "Maven sync đã được trigger. Nếu chưa đạt, vui lòng chờ kết thúc hoặc xem tab Build/Sync.",
                        "Đang đồng bộ Maven"
                    )
                }
            }
        }.apply {
            isRepeats = false
            start()
        }
    }

    private fun bindMavenSyncDetection() {
        val listener = object : MavenProjectsManager.Listener {
            override fun projectImportCompleted() {
                checkMavenSyncHealthyAsync { healthy ->
                    updateMavenSyncStateForAll(healthy)
                    refreshProgressFromEngine()
                    list.repaint()
                    refreshStepStatusesOnly()
                }
            }
        }
        MavenProjectsManager.getInstance(project).addManagerListener(listener, project)
    }

    private fun checkMavenSyncHealthyAsync(onResult: (Boolean) -> Unit) {
        ApplicationManager.getApplication().executeOnPooledThread {
            val healthy = try {
                val projects = MavenProjectsManager.getInstance(project).projects
                isMavenSyncHealthy(projects)
            } catch (_: Exception) {
                false
            }
            ApplicationManager.getApplication().invokeLater(
                {
                    if (project.isDisposed) return@invokeLater
                    onResult(healthy)
                },
                ModalityState.defaultModalityState()
            )
        }
    }

    private fun updateMavenSyncState(syncId: String, synced: Boolean) {
        val updated = if (synced) {
            snapshot.copy(successfulMavenSyncIds = snapshot.successfulMavenSyncIds + syncId)
        } else {
            snapshot.copy(successfulMavenSyncIds = snapshot.successfulMavenSyncIds - syncId)
        }
        if (updated != snapshot) {
            snapshot = updated
            persistSnapshot()
            refreshProgressFromEngine()
            list.repaint()
            refreshStepStatusesOnly()
        }
    }

    private fun updateMavenSyncStateForAll(synced: Boolean) {
        val updatedIds = if (synced) snapshot.successfulMavenSyncIds + mavenSyncIds else snapshot.successfulMavenSyncIds - mavenSyncIds
        if (updatedIds == snapshot.successfulMavenSyncIds) return
        snapshot = snapshot.copy(successfulMavenSyncIds = updatedIds)
        persistSnapshot()
    }

    private fun collectMavenSyncIds(): Set<String> {
        return TrainingCurriculumRepository.program.exercises
            .flatMap { it.steps }
            .flatMap { step -> step.activities.filterIsInstance<TrainingActivity.RefreshMavenProjects>() }
            .map { it.syncId }
            .toSet()
    }

    private fun isMavenSyncHealthy(projects: List<MavenProject>): Boolean {
        if (projects.isEmpty()) return false
        return projects.none { it.hasReadingErrors() || it.hasUnresolvedArtifacts() || it.hasUnresolvedPlugins() }
    }

    private fun summarizeOutput(raw: String): String {
        val lines = raw.lines().filter { it.isNotBlank() }
        if (lines.isEmpty()) return "Không có output."
        return lines.takeLast(12).joinToString("\n")
    }

    private fun showRunSuccessDialog(command: String) {
        val body = """
            <html>
            <b>Đã hoàn thành bước chạy test</b><br/><br/>
            Lệnh <code>$command</code> đã chạy thành công.<br/>
            Điều kiện bài tập đã được cập nhật.<br/><br/>
            <font color='#7a7a7a'>Chi tiết log xem trong tab Run.</font>
            </html>
        """.trimIndent()
        Messages.showInfoMessage(project, body, "Run thành công")
    }

    private fun updateStepStatus(exerciseId: String, stepId: String) {
        val label = stepStatusLabels[stepStatusKey(exerciseId, stepId)] ?: return
        val done = isStepCompleted(exerciseId, stepId)
        label.text = if (done) "Đã hoàn thành" else "Chưa hoàn thành"
        label.foreground = if (done) JBColor(0x1A7F37, 0x3FB950) else JBColor(0x9A6700, 0xD29922)
        detailsScroll.viewport.repaint()
    }

    private data class CommandRunResult(
        val exitCode: Int,
        val summary: String,
        val passed: Boolean
    )

    private data class CommandEvaluation(
        val passed: Boolean,
        val message: String?
    )

    private sealed interface RunnableStepAction {
        data class Command(
            val commandId: String,
            val commandHint: String
        ) : RunnableStepAction

        data class MavenSync(
            val syncId: String,
            val actionLabel: String
        ) : RunnableStepAction
    }

    private fun toVietnameseLevel(level: String): String {
        return when (level) {
            "BASIC" -> "Cơ bản"
            "INTERMEDIATE" -> "Trung cấp"
            "ADVANCED" -> "Nâng cao"
            else -> level
        }
    }

    private fun toVietnameseType(type: String): String {
        return when (type) {
            "EXERCISE" -> "Bài tập"
            "MISSION" -> "Nhiệm vụ"
            "TASK" -> "Tác vụ"
            "HOMEWORK" -> "Bài về nhà"
            else -> type
        }
    }
}
