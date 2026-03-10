package com.mb.training.karate.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
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
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.nio.file.Path
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
    private val fallbackCurrentId = TrainingCurriculumRepository.items.firstOrNull()?.id.orEmpty()
    private var snapshot = runEngine(loadInitialSnapshot())
    private var introShownInSession = false
    private val projectRootPathString = projectRoot?.normalize()?.toString()
    private var currentDetailsExerciseId: String? = null
    private val stepStatusLabels = linkedMapOf<String, JBLabel>()
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
        applyCurrentSelectionFromSnapshot()
        maybeShowBasicExerciseIntro()
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
        applyCurrentSelectionFromSnapshot()
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
        val hintButton = JButton("Hint").apply {
            alignmentX = Component.LEFT_ALIGNMENT
            isEnabled = step.hints.isNotEmpty()
            toolTipText = if (step.hints.isNotEmpty()) "Hiển thị gợi ý HUD cho bước này" else "Bước này chưa có gợi ý"
            addActionListener { hintPresenter.showHints(step.hints, this) }
        }
        card.add(hintButton)
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

    private fun maybeShowBasicExerciseIntro() {
        if (introShownInSession) return
        if (snapshot.currentItemId != "basic-exercise-1") return
        if (snapshot.completedIds.contains("basic-exercise-1")) return
        introShownInSession = true
        BasicExerciseIntroDialog(project).show()
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
            is TrainingActivity.CodeTask -> "Thực hiện coding: ${activity.instruction}"
            is TrainingActivity.CompileTask -> "Lệnh biên dịch gợi ý: ${activity.commandHint}"
            is TrainingActivity.RunTestTask -> "Lệnh chạy test gợi ý: ${activity.commandHint}"
        }
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
