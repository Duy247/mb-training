package com.mb.training.karate.ui

import com.intellij.ide.impl.OpenProjectTask
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.ex.ProjectManagerEx
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import com.intellij.terminal.ui.TerminalWidget
import com.intellij.ui.JBColor
import com.intellij.ui.JBSplitter
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingItem
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.services.ScenarioWorkspaceService
import com.mb.training.karate.services.TrainingProgressSnapshot
import com.mb.training.karate.services.TrainingProjectProgressStore
import com.mb.training.karate.training.TrainingCurriculumRepository
import com.mb.training.karate.training.TrainingProgressEngine
import org.jetbrains.plugins.terminal.TerminalToolWindowManager
import org.jetbrains.idea.maven.project.MavenProject
import org.jetbrains.idea.maven.project.MavenProjectsManager
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.ListSelectionModel
import javax.swing.Timer

class TrainingToolWindowPanel(
    private val project: Project
) : JPanel(BorderLayout()) {
    companion object {
        private const val AUTO_REFRESH_MS = 3_000
        private val BG_START = JBColor(Color(34, 41, 61), Color(34, 41, 61))
        private val BG_END = JBColor(Color(55, 45, 60), Color(55, 45, 60))
        private val PANEL_BG = JBColor(0x1F242D, 0x1F242D)
        private val BORDER_COLOR = JBColor(0x3D4350, 0x3D4350)
        private val TITLE_COLOR = JBColor(0xDDE7FF, 0xDDE7FF)
        private val SUBTITLE_COLOR = JBColor(0xA9B4CA, 0xA9B4CA)
        private val LIST_SELECTED_BG = JBColor(0x2A3F5B, 0x2A3F5B)
        private val LIST_HOVER_BG = JBColor(0x242B36, 0x242B36)
        private val LIST_CARD_BG = JBColor(0x202733, 0x202733)
        private val LIST_DONE_BG = JBColor(0x1F5A43, 0x1F5A43)
        private val LIST_DONE_FG = JBColor(0x8AF7C9, 0x8AF7C9)
        private val LIST_TODO_BG = JBColor(0x3A4250, 0x3A4250)
        private val LIST_TODO_FG = JBColor(0xD5DCEB, 0xD5DCEB)
    }

    private val projectRoot = project.basePath?.let { Path.of(it) }
    private var scenarioContext = projectRoot?.let { ScenarioWorkspaceService.loadScenarioContext(it) }
    private val hintPresenter = StepHintPresenter(project, projectRoot)
    private val engine = projectRoot?.let { TrainingProgressEngine(it, TrainingCurriculumRepository.program) }
    private val listModel = DefaultListModel<TrainingItem>()
    private val list = JBList(listModel)
    private val detailsScroll = JBScrollPane()
    private val progressLabel = JBLabel()
    private val validateButton = JButton("Refresh Progress")
    private val resetButton = JButton("Reset")
    private val pendingCommandExpectationsById = linkedMapOf<String, PendingCommandExpectation>()
    private val commandHintById = collectCommandHintsById()
    private val terminalEventLogFile = projectRoot?.resolve(".idea")?.resolve("mbtraining-terminal-events.log")
    private val terminalHookDir = projectRoot?.resolve(".idea")?.resolve("mbtraining-terminal")
    private var terminalProcessedEventLines = 0
    private var terminalBootstrapFailedNotified = false
    private val bootstrappedTerminalSessionKeys = mutableSetOf<Int>()
    private var terminalSetupDisposable: Disposable? = null
    private val fallbackCurrentId = TrainingCurriculumRepository.items.firstOrNull()?.id.orEmpty()
    private var snapshot = runEngine(loadInitialSnapshot())
    private var introPopupEnabled = false
    private val projectRootPathString = projectRoot?.normalize()?.toString()
    private var currentDetailsExerciseId: String? = null
    private val stepStatusLabels = linkedMapOf<String, JBLabel>()
    private val quizActionButtons = linkedMapOf<String, JButton>()
    private val quizStatusLabels = linkedMapOf<String, JBLabel>()
    private val mavenSyncIds = collectMavenSyncIds()
    private var scenarioAutoReturnTriggered = false
    private var suppressProgressDialogs = false
    private var pendingRefresh = false
    private var pendingSnapshotSave = false
    private val refreshDebounceTimer = Timer(350) {
        if (!pendingRefresh) return@Timer
        pendingRefresh = false
        if (!isShowing) return@Timer
        refreshProgressFromEngine()
        list.repaint()
        refreshStepStatusesOnly()
    }.apply {
        isRepeats = false
    }
    private val snapshotSaveDebounceTimer = Timer(350) {
        flushSnapshotSave()
    }.apply {
        isRepeats = false
    }
    private val autoRefreshTimer = Timer(AUTO_REFRESH_MS) {
        if (!isShowing) return@Timer
        requestProgressRefresh()
    }

    init {
        isOpaque = false
        border = JBUI.Borders.empty(8)
        populateList()
        configureList()
        configureDetails()
        configureLayout()
        bindActions()
        bindAutoDetection()
        bindAutoRefreshPolling()
        bindMavenSyncDetection()
        bindTerminalObserverAutoBootstrap()
        applyCurrentSelectionFromSnapshot()
        refreshProgress()
        refreshDetailsFromSelection()
        introPopupEnabled = true
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val g2 = g as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val base = GradientPaint(
            0f, 0f, BG_START,
            width.toFloat(), height.toFloat(), BG_END
        )
        g2.paint = base
        g2.fillRect(0, 0, width, height)
        g2.color = JBColor(Color(70, 104, 178, 22), Color(70, 104, 178, 22))
        g2.fillOval(-width / 4, -height / 4, width / 2, height / 2)
        g2.color = JBColor(Color(140, 102, 67, 20), Color(140, 102, 67, 20))
        g2.fillOval(width / 2, height / 4, width / 2, height / 2)
    }

    private fun loadInitialSnapshot(): TrainingProgressSnapshot {
        val root = projectRoot ?: return TrainingProgressSnapshot(fallbackCurrentId, emptySet())
        return TrainingProjectProgressStore.load(root) ?: TrainingProgressSnapshot(fallbackCurrentId, emptySet())
    }

    private fun populateList() {
        TrainingCurriculumRepository.items.forEach(listModel::addElement)
    }

    private fun configureList() {
        list.background = PANEL_BG
        list.foreground = TITLE_COLOR
        list.font = JBFont.label().deriveFont(Font.PLAIN, JBFont.label().size + 1f)
        list.selectionBackground = LIST_SELECTED_BG
        list.selectionForeground = TITLE_COLOR
        list.fixedCellHeight = JBUI.scale(44)
        list.border = JBUI.Borders.empty(6, 6, 6, 6)
        list.selectionMode = ListSelectionModel.SINGLE_SELECTION
        list.cellRenderer = TrainingItemCellRenderer()
    }

    private inner class TrainingItemCellRenderer : JPanel(BorderLayout()), javax.swing.ListCellRenderer<TrainingItem> {
        private val badgeLabel = JBLabel()
        private val titleLabel = JBLabel().apply {
            font = JBFont.label().deriveFont(Font.PLAIN, JBFont.label().size + 1f)
            foreground = TITLE_COLOR
        }
        private val row = JPanel(BorderLayout(JBUI.scale(8), 0)).apply {
            isOpaque = true
            add(badgeLabel, BorderLayout.WEST)
            add(titleLabel, BorderLayout.CENTER)
        }

        init {
            isOpaque = true
            background = PANEL_BG
            border = BorderFactory.createEmptyBorder(2, 0, 2, 0)
            add(row, BorderLayout.CENTER)
        }

        override fun getListCellRendererComponent(
            list: javax.swing.JList<out TrainingItem>?,
            value: TrainingItem?,
            index: Int,
            isSelected: Boolean,
            cellHasFocus: Boolean
        ): Component {
            val item = value
            if (item == null) {
                titleLabel.text = ""
                badgeLabel.text = ""
                return this
            }

            val done = isCompleted(item.id)
            badgeLabel.text = if (done) "DONE" else "TODO"
            badgeLabel.font = JBFont.small().deriveFont(Font.BOLD)
            badgeLabel.foreground = if (done) LIST_DONE_FG else LIST_TODO_FG
            badgeLabel.background = if (done) LIST_DONE_BG else LIST_TODO_BG
            badgeLabel.isOpaque = true
            badgeLabel.border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    if (done) JBColor(0x27B082, 0x27B082) else JBColor(0x677184, 0x677184),
                    1,
                    true
                ),
                BorderFactory.createEmptyBorder(2, 8, 2, 8)
            )

            titleLabel.text = item.title
            row.background = if (isSelected) LIST_SELECTED_BG else LIST_CARD_BG
            row.border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    if (isSelected) JBColor(0x4FA0FF, 0x4FA0FF) else BORDER_COLOR,
                    1,
                    true
                ),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
            )
            return this
        }
    }

    private fun configureDetails() {
        detailsScroll.border = BorderFactory.createLineBorder(BORDER_COLOR, 1, true)
        detailsScroll.horizontalScrollBarPolicy = JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        detailsScroll.verticalScrollBarPolicy = JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        detailsScroll.viewport.background = PANEL_BG
        detailsScroll.background = PANEL_BG
        setDetailsView(createEmptyDetailsPanel())
    }

    private fun configureLayout() {
        val left = JPanel(BorderLayout()).apply {
            isOpaque = false
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                JBUI.Borders.empty(8)
            )
            add(sectionHeaderLabel("Roadmap"), BorderLayout.NORTH)
            add(JBScrollPane(list), BorderLayout.CENTER)
        }

        val right = JPanel(BorderLayout()).apply {
            isOpaque = false
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                JBUI.Borders.empty(8)
            )
            add(sectionHeaderLabel("Details"), BorderLayout.NORTH)
            add(detailsScroll, BorderLayout.CENTER)
        }

        val splitter = JBSplitter(false, 0.35f).apply {
            firstComponent = left
            secondComponent = right
        }

        val actions = JPanel().apply {
            isOpaque = false
            border = JBUI.Borders.emptyTop(8)
            progressLabel.font = JBFont.label().deriveFont(Font.PLAIN, JBFont.label().size + 1f)
            progressLabel.foreground = SUBTITLE_COLOR
            add(progressLabel)
            add(validateButton)
            add(resetButton)
        }

        add(splitter, BorderLayout.CENTER)
        add(actions, BorderLayout.SOUTH)
    }

    private fun sectionHeaderLabel(text: String): JComponent {
        return JBLabel(text).apply {
            font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 2f)
            foreground = TITLE_COLOR
            border = JBUI.Borders.emptyBottom(6)
        }
    }

    private fun bindActions() {
        list.addListSelectionListener {
            if (!it.valueIsAdjusting) {
                val item = list.selectedValue ?: return@addListSelectionListener
                snapshot = snapshot.copy(currentItemId = item.id)
                persistSnapshot()
                if (introPopupEnabled) {
                    maybeShowCurrentExerciseIntro()
                }
                refreshDetailsFromSelection()
            }
        }

        validateButton.addActionListener {
            requestProgressRefresh()
        }

        resetButton.addActionListener {
            val currentExerciseId = resolveCurrentExerciseIdForReset()
            val currentExercise = currentExerciseId?.let { TrainingCurriculumRepository.exerciseById[it] }
            if (currentExercise == null) {
                Messages.showWarningDialog(
                    project,
                    "No exercise selected to reset.",
                    "Reset"
                )
                return@addActionListener
            }

            val confirmed = Messages.showYesNoDialog(
                project,
                "Reset will clear saved progress for the current exercise (including run/sync state). Continue?",
                "Confirm Reset",
                "Reset",
                "Cancel",
                null
            )
            if (confirmed != Messages.YES) return@addActionListener

            resetExerciseProgress(currentExercise)
            scenarioAutoReturnTriggered = false
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
                    requestProgressRefresh()
                }
            }
        )
    }

    private fun requestProgressRefresh() {
        ApplicationManager.getApplication().invokeLater(
            {
                if (project.isDisposed) return@invokeLater
                pendingRefresh = true
                refreshDebounceTimer.restart()
            },
            ModalityState.defaultModalityState()
        )
    }

    private fun refreshProgressFromEngine() {
        val beforeSync = snapshot
        autoDetectPendingCommandCompletions()
        val updated = runEngine(snapshot)
        if (updated != snapshot) {
            snapshot = updated
            persistSnapshot()
        }
        maybeAutoReturnFromScenario(beforeSync = beforeSync, afterSync = snapshot)
        refreshProgress()
        if (list.selectedIndex < 0) {
            applyCurrentSelectionFromSnapshot()
        }
    }

    private fun maybeAutoReturnFromScenario(
        beforeSync: TrainingProgressSnapshot,
        afterSync: TrainingProgressSnapshot
    ) {
        if (scenarioAutoReturnTriggered) return
        val context = resolveScenarioContext() ?: return
        val scenarioExerciseId = context.scenarioExerciseId
        val becameCompleted = !beforeSync.completedIds.contains(scenarioExerciseId) &&
            afterSync.completedIds.contains(scenarioExerciseId)
        val alreadyCompleted = afterSync.completedIds.contains(scenarioExerciseId)
        if (!becameCompleted && !alreadyCompleted) return
        scenarioAutoReturnTriggered = true
        ApplicationManager.getApplication().invokeLater(
            { returnToOriginalProject() },
            ModalityState.defaultModalityState()
        )
    }

    private fun refreshProgress() {
        val total = TrainingCurriculumRepository.items.size
        val completed = TrainingCurriculumRepository.items.count { isCompleted(it.id) }
        progressLabel.text = "Progress: $completed/$total"
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
            quizActionButtons.clear()
            quizStatusLabels.clear()
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
            add(sectionTitle("Exercise Details"))
            add(Box.createVerticalStrut(6))
            add(wrappedText("Select an item in the roadmap to view objective, steps, and expected outcome."))
            add(Box.createVerticalGlue())
        }
        return panel
    }

    private fun createExerciseDetailsPanel(itemId: String): JComponent {
        val exercise = TrainingCurriculumRepository.exerciseById[itemId]
            ?: return createEmptyDetailsPanel()
        stepStatusLabels.clear()
        quizActionButtons.clear()
        quizStatusLabels.clear()

        val panel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            border = JBUI.Borders.empty(10)
            alignmentX = Component.LEFT_ALIGNMENT
        }

        panel.add(titleLabel(exercise.title))
        panel.add(Box.createVerticalStrut(4))
        panel.add(metaLabel("Level: ${toVietnameseLevel(exercise.level.name)}  |  Type: ${toVietnameseType(exercise.type.name)}"))
        panel.add(Box.createVerticalStrut(10))
        panel.add(sectionTitle("Objective"))
        panel.add(Box.createVerticalStrut(4))
        panel.add(wrappedText(exercise.objective))
        panel.add(Box.createVerticalStrut(10))
        panel.add(sectionTitle("Steps"))
        panel.add(Box.createVerticalStrut(6))

        exercise.steps.forEach { step ->
            panel.add(createStepCard(exercise.id, step))
            panel.add(Box.createVerticalStrut(8))
        }

        panel.add(sectionTitle("Completion Criteria"))
        panel.add(Box.createVerticalStrut(4))
        panel.add(
            wrappedText(
                when (exercise.completionPolicy.name) {
                    "ALL_STEPS_DONE" -> "Complete when all steps are done."
                    "ANY_STEP_DONE" -> "Complete when at least one step is done."
                    else -> "Complete based on exercise rules."
                }
            )
        )
        panel.add(Box.createVerticalStrut(10))
        panel.add(sectionTitle("Expected Outcome"))
        panel.add(Box.createVerticalStrut(4))
        panel.add(wrappedText(exercise.expectedOutcome))
        val summary = exercise.knowledgeSummary
        if (summary != null) {
            panel.add(Box.createVerticalStrut(10))
            panel.add(sectionTitle("Summary"))
            panel.add(Box.createVerticalStrut(4))
            panel.add(
                JButton("Open Knowledge Summary").apply {
                    alignmentX = Component.LEFT_ALIGNMENT
                    addActionListener { showKnowledgeSummary(summary) }
                }
            )
        }
        val quiz = exercise.theoryQuiz
        if (quiz != null) {
            val stepsReadyForQuiz = isExerciseStepsSatisfied(exercise)
            panel.add(Box.createVerticalStrut(10))
            panel.add(sectionTitle("Theory Quiz"))
            panel.add(Box.createVerticalStrut(4))
            val quizStatusLabel = JBLabel(
                if (isTheoryQuizPassed(exercise.id)) "Status: Passed" else "Status: Not Passed"
            ).apply {
                font = JBFont.small()
                alignmentX = Component.LEFT_ALIGNMENT
                foreground = if (isTheoryQuizPassed(exercise.id)) {
                    JBColor(0x1A7F37, 0x3FB950)
                } else {
                    JBColor(0x9A6700, 0xD29922)
                }
            }
            quizStatusLabels[exercise.id] = quizStatusLabel
            panel.add(quizStatusLabel)
            panel.add(Box.createVerticalStrut(4))
            val quizButton = JButton("Take Theory Quiz").apply {
                alignmentX = Component.LEFT_ALIGNMENT
                isEnabled = stepsReadyForQuiz
                toolTipText = if (stepsReadyForQuiz) {
                    "Open theory quiz"
                } else {
                    "Complete all steps before taking the quiz"
                }
                addActionListener {
                    TheoryQuizDialog(project, quiz) {
                        markTheoryQuizPassed(exercise.id)
                    }.show()
                }
            }
            quizActionButtons[exercise.id] = quizButton
            panel.add(quizButton)
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

        val statusText = if (done) "Completed" else "Not Completed"
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
        card.add(wrappedText("Guidance: ${step.guidance}", italic = true))
        card.add(Box.createVerticalStrut(6))
        val actionRow = JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(6), 0)).apply {
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
            maximumSize = Dimension(Int.MAX_VALUE, Int.MAX_VALUE)
        }
        val hintButton = JButton("Hint").apply {
            isEnabled = step.hints.isNotEmpty()
            toolTipText = if (step.hints.isNotEmpty()) "Show HUD hints for this step" else "No hints available for this step"
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
                            command = runnableAction.commandHint
                        )
                        is RunnableStepAction.MavenSync -> runMavenSync(
                            exerciseId = exerciseId,
                            stepId = step.id,
                            syncId = runnableAction.syncId,
                            button = this
                        )
                        is RunnableStepAction.ScenarioSetup -> runScenarioSetup(
                            exerciseId = exerciseId,
                            scenarioId = runnableAction.scenarioId,
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
                toolTipText = "Open effective Maven settings.xml in IntelliJ"
                addActionListener { openEffectiveMavenSettings() }
            }
            actionRow.add(openSettingsButton)
        }
        card.add(actionRow)
        card.add(Box.createVerticalStrut(6))
        card.add(JBLabel("Tasks").apply { font = JBFont.label().asBold() })
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
        val program = TrainingCurriculumRepository.program
        val exercise = TrainingCurriculumRepository.exerciseById[currentExerciseId] ?: return
        val intro = exercise.intro ?: return
        val dependencyGate = ExerciseUiFlowDecider.resolveDependencyGate(
            program = program,
            exercise = exercise,
            completedExerciseIds = snapshot.completedIds
        )
        val dialog = if (dependencyGate != null) {
            BasicExerciseIntroDialog(
                project = project,
                contentModel = intro,
                warningMessage = dependencyGate.warningMessage,
                onAcknowledge = {
                    snapshot = snapshot.copy(currentItemId = dependencyGate.firstUnmetDependencyId)
                    persistSnapshot()
                    applyCurrentSelectionFromSnapshot()
                    refreshDetailsFromSelection()
                }
            )
        } else {
            BasicExerciseIntroDialog(project, intro)
        }
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

    private fun isTheoryQuizPassed(exerciseId: String): Boolean {
        return snapshot.passedTheoryQuizExerciseIds.contains(exerciseId)
    }

    private fun isExerciseStepsSatisfied(exercise: com.mb.training.karate.model.TrainingExercise): Boolean {
        return ExerciseUiFlowDecider.isQuizEnabledByStepCompletion(
            exercise = exercise,
            completedStepIds = snapshot.completedStepIds,
            stepKey = ::stepStatusKey
        )
    }

    private fun refreshStepStatusesOnly() {
        val exerciseId = currentDetailsExerciseId ?: return
        val exercise = TrainingCurriculumRepository.exerciseById[exerciseId] ?: return
        exercise.steps.forEach { step ->
            val key = stepStatusKey(exercise.id, step.id)
            val label = stepStatusLabels[key] ?: return@forEach
            val done = isStepCompleted(exercise.id, step.id)
            label.text = if (done) "Completed" else "Not Completed"
            label.foreground = if (done) JBColor(0x1A7F37, 0x3FB950) else JBColor(0x9A6700, 0xD29922)
        }
        refreshQuizSectionState(exercise)
        detailsScroll.viewport.repaint()
    }

    private fun refreshQuizSectionState(exercise: com.mb.training.karate.model.TrainingExercise) {
        if (exercise.theoryQuiz == null) return
        val ready = isExerciseStepsSatisfied(exercise)
        quizActionButtons[exercise.id]?.apply {
            isEnabled = ready
            toolTipText = if (ready) {
                "Open theory quiz"
            } else {
                "Complete all steps before taking the quiz"
            }
        }
        quizStatusLabels[exercise.id]?.apply {
            val passed = isTheoryQuizPassed(exercise.id)
            text = if (passed) "Status: Passed" else "Status: Not Passed"
            foreground = if (passed) JBColor(0x1A7F37, 0x3FB950) else JBColor(0x9A6700, 0xD29922)
        }
    }

    private fun stepStatusKey(exerciseId: String, stepId: String): String {
        return "$exerciseId::$stepId"
    }

    private fun resolveCurrentExerciseIdForReset(): String? {
        val selectedId = list.selectedValue?.id
        if (!selectedId.isNullOrBlank()) return selectedId
        if (!currentDetailsExerciseId.isNullOrBlank()) return currentDetailsExerciseId
        return snapshot.currentItemId.takeIf { it.isNotBlank() }
    }

    private fun resetExerciseProgress(exercise: com.mb.training.karate.model.TrainingExercise) {
        val stepKeys = exercise.steps.map { stepStatusKey(exercise.id, it.id) }.toSet()
        val commandIds = collectExerciseCommandIds(exercise)
        val mavenSyncIds = collectExerciseMavenSyncIds(exercise)

        snapshot = snapshot.copy(
            completedIds = snapshot.completedIds - exercise.id,
            completedStepIds = snapshot.completedStepIds - stepKeys,
            passedCommandIds = snapshot.passedCommandIds - commandIds,
            successfulMavenSyncIds = snapshot.successfulMavenSyncIds - mavenSyncIds,
            passedTheoryQuizExerciseIds = snapshot.passedTheoryQuizExerciseIds - exercise.id
        )
        projectRoot?.let { TrainingProjectProgressStore.clearScenarioCompleted(it, exercise.id) }
    }

    private fun collectExerciseCommandIds(exercise: com.mb.training.karate.model.TrainingExercise): Set<String> {
        return exercise.steps.flatMap { step ->
            val fromActivities = step.activities.mapNotNull { activity ->
                when (activity) {
                    is TrainingActivity.RunCommandTask -> activity.commandId
                    is TrainingActivity.RunTestTask -> activity.commandId
                    is TrainingActivity.CompileTask -> activity.commandId
                    else -> null
                }
            }
            val fromConditions = step.doneWhen.mapNotNull { condition ->
                when (condition) {
                    is TrainingCondition.CommandPassed -> condition.commandId
                    else -> null
                }
            }
            fromActivities + fromConditions
        }.toSet()
    }

    private fun collectExerciseMavenSyncIds(exercise: com.mb.training.karate.model.TrainingExercise): Set<String> {
        return exercise.steps.flatMap { step ->
            val fromActivities = step.activities.mapNotNull { activity ->
                when (activity) {
                    is TrainingActivity.RefreshMavenProjects -> activity.syncId
                    else -> null
                }
            }
            val fromConditions = step.doneWhen.mapNotNull { condition ->
                when (condition) {
                    is TrainingCondition.MavenSyncSucceeded -> condition.syncId
                    else -> null
                }
            }
            fromActivities + fromConditions
        }.toSet()
    }

    private fun persistSnapshot() {
        pendingSnapshotSave = true
        snapshotSaveDebounceTimer.restart()
    }

    private fun flushSnapshotSave() {
        if (!pendingSnapshotSave) return
        pendingSnapshotSave = false
        val root = projectRoot ?: return
        TrainingProjectProgressStore.save(root, snapshot)
    }

    private fun runEngine(current: TrainingProgressSnapshot): TrainingProgressSnapshot {
        val synced = engine?.sync(current) ?: current
        if (resolveScenarioContext() == null) return synced
        // In scenario temp workspace, preserve imported progress from original project
        // so users see consistent state while working in sandbox.
        return synced.copy(
            completedIds = synced.completedIds + current.completedIds,
            completedStepIds = synced.completedStepIds + current.completedStepIds
        )
    }

    private fun resolveScenarioContext(): ScenarioWorkspaceService.ScenarioWorkspaceContext? {
        val cached = scenarioContext
        if (cached != null) return cached
        val root = projectRoot ?: return null
        val loaded = ScenarioWorkspaceService.loadScenarioContext(root)
        scenarioContext = loaded
        return loaded
    }

    private fun bindAutoRefreshPolling() {
        autoRefreshTimer.initialDelay = AUTO_REFRESH_MS
        autoRefreshTimer.isRepeats = true
        autoRefreshTimer.start()
    }

    override fun removeNotify() {
        autoRefreshTimer.stop()
        refreshDebounceTimer.stop()
        snapshotSaveDebounceTimer.stop()
        terminalSetupDisposable?.let(Disposer::dispose)
        terminalSetupDisposable = null
        flushSnapshotSave()
        super.removeNotify()
    }

    private fun renderActivity(activity: TrainingActivity): String {
        return when (activity) {
            is TrainingActivity.CreateFolder -> "Create folder ${activity.relativePath}"
            is TrainingActivity.CreateFile -> "Create file ${activity.relativePath} from template"
            is TrainingActivity.CodeTask -> "Coding task: ${activity.instruction} Ctrl + S (Save) to record coding progress"
            is TrainingActivity.SetupScenarioWorkspace -> activity.actionLabel
            is TrainingActivity.OpenMavenSettings -> "Open Maven settings currently used by IntelliJ"
            is TrainingActivity.RunCommandTask -> "${activity.instruction} (${activity.commandHint})"
            is TrainingActivity.RefreshMavenProjects -> activity.actionLabel
            is TrainingActivity.CompileTask -> "Suggested compile command: ${activity.commandHint}"
            is TrainingActivity.RunTestTask -> "Suggested test command: ${activity.commandHint}"
        }
    }

    private fun extractRunnableActions(activities: List<TrainingActivity>): List<RunnableStepAction> {
        return activities.mapNotNull { activity ->
            when (activity) {
                is TrainingActivity.RunCommandTask -> RunnableStepAction.Command(activity.commandId, activity.commandHint)
                is TrainingActivity.RunTestTask -> RunnableStepAction.Command(activity.commandId, activity.commandHint)
                is TrainingActivity.CompileTask -> RunnableStepAction.Command(activity.commandId, activity.commandHint)
                is TrainingActivity.RefreshMavenProjects -> RunnableStepAction.MavenSync(activity.syncId, activity.actionLabel)
                is TrainingActivity.SetupScenarioWorkspace -> RunnableStepAction.ScenarioSetup(
                    scenarioId = activity.scenarioId,
                    actionLabel = activity.actionLabel
                )
                else -> null
            }
        }
    }

    private fun runnableActionLabel(action: RunnableStepAction): String {
        return when (action) {
            is RunnableStepAction.Command -> "Run"
            is RunnableStepAction.MavenSync -> "Run"
            is RunnableStepAction.ScenarioSetup -> "Run"
        }
    }

    private fun runnableActionTooltip(action: RunnableStepAction): String {
        return when (action) {
            is RunnableStepAction.Command -> "Run: ${action.commandHint}"
            is RunnableStepAction.MavenSync -> action.actionLabel
            is RunnableStepAction.ScenarioSetup -> action.actionLabel
        }
    }

    private fun runScenarioSetup(exerciseId: String, scenarioId: String, button: JButton) {
        val originalText = button.text
        button.isEnabled = false
        button.text = "Setting up..."
        try {
            val sourceSnapshot = runEngine(snapshot)
            if (sourceSnapshot != snapshot) {
                snapshot = sourceSnapshot
                persistSnapshot()
            }
            val workspace = ScenarioWorkspaceService.setupAndOpenScenario(
                project = project,
                scenarioId = scenarioId,
                scenarioExerciseId = exerciseId,
                sourceSnapshot = snapshot
            )
            Messages.showInfoMessage(
                project,
                "Scenario sandbox created at:\n$workspace\n\nThe plugin has opened the sandbox project in a new window.",
                "Scenario sandbox is ready"
            )
        } catch (e: Exception) {
            Messages.showErrorDialog(
                project,
                "Unable to initialize scenario sandbox.\n${e.message}",
                "MB Training"
            )
        } finally {
            button.isEnabled = true
            button.text = originalText
        }
    }

    private fun returnToOriginalProject() {
        val root = projectRoot ?: return
        val context = resolveScenarioContext() ?: return
        val originalRoot = context.originalRootPath

        val originalCurrent = TrainingProjectProgressStore.load(originalRoot)
            ?: TrainingProgressSnapshot(
                currentItemId = context.scenarioExerciseId,
                completedIds = emptySet()
            )
        val merged = originalCurrent.copy(
            currentItemId = context.scenarioExerciseId,
            completedIds = originalCurrent.completedIds + snapshot.completedIds + context.scenarioExerciseId,
            completedStepIds = originalCurrent.completedStepIds + snapshot.completedStepIds,
            passedCommandIds = originalCurrent.passedCommandIds + snapshot.passedCommandIds,
            successfulMavenSyncIds = originalCurrent.successfulMavenSyncIds + snapshot.successfulMavenSyncIds,
            passedTheoryQuizExerciseIds = originalCurrent.passedTheoryQuizExerciseIds +
                snapshot.passedTheoryQuizExerciseIds +
                context.scenarioExerciseId
        )
        TrainingProjectProgressStore.save(originalRoot, merged)
        TrainingProjectProgressStore.markScenarioCompleted(originalRoot, context.scenarioExerciseId)

        val originalRootText = originalRoot.toAbsolutePath().normalize().toString()
        val alreadyOpen = ProjectManager.getInstance().openProjects.any { openProject ->
            val openPath = openProject.basePath ?: return@any false
            runCatching {
                Path.of(openPath).toAbsolutePath().normalize().toString()
            }.getOrNull() == originalRootText
        }
        if (!alreadyOpen) {
            ProjectUtil.openOrImport(originalRoot, OpenProjectTask(forceOpenInNewFrame = true))
        }

        ProjectManagerEx.getInstanceEx().forceCloseProject(project, true)
        ApplicationManager.getApplication().executeOnPooledThread {
            ScenarioWorkspaceService.deleteWorkspaceQuietly(root)
        }
    }

    private fun runStepCommand(
        exerciseId: String,
        stepId: String,
        commandId: String,
        command: String
    ) {
        val effectiveCommand = normalizeSuggestedCommand(command)
        pendingCommandExpectationsById[commandId] = PendingCommandExpectation(
            exerciseId = exerciseId,
            stepId = stepId,
            commandId = commandId,
            expectedCommand = effectiveCommand,
            registeredAtEpochSeconds = currentEpochSeconds()
        )
        openTerminalSidecarInfo(command = effectiveCommand)
    }

    private fun openTerminalSidecarInfo(command: String) {
        val manager = TerminalToolWindowManager.getInstance(project)
        val root = project.basePath?.takeIf { it.isNotBlank() }
        var observerAttached = false
        runCatching {
            fun currentTerminalWidgets(): List<TerminalWidget> {
                return manager.terminalWidgets.toList()
            }

            var terminalWidgets = currentTerminalWidgets()
            if (terminalWidgets.isEmpty()) {
                manager.createShellWidget(root, "MBTraining", true, true)
                terminalWidgets = currentTerminalWidgets()
            }
            manager.toolWindow?.show()
            terminalWidgets.forEach { widget ->
                if (ensureTerminalObserverBootstrapped(widget)) {
                    observerAttached = true
                }
            }
        }.onFailure {
            Messages.showErrorDialog(
                project,
                "Unable to open Terminal tool window.\n${it.message}",
                "MB Training"
            )
            return
        }
        Messages.showInfoMessage(
            project,
            buildString {
                append("Run this command in Terminal:\n\n")
                append(command)
                if (observerAttached) {
                    append("\n\nCommand observer has been attached for this terminal session.")
                } else {
                    append("\n\nUnable to attach command observer automatically.")
                    val manualBootstrap = buildManualObserverBootstrapHint()
                    if (manualBootstrap != null) {
                        append("\nRun this once in terminal first:\n")
                        append(manualBootstrap)
                    }
                }
                append("\n\nThen click Refresh Progress.")
            },
            "MBTraining Command"
        )
    }

    private fun normalizeSuggestedCommand(command: String): String {
        val normalized = command.trim()
        return if (Regex("""\bmvn(\.cmd)?\s+-q\s+test\b""", RegexOption.IGNORE_CASE).containsMatchIn(normalized)) {
            normalized.replace(Regex("""\s+-q\b""", RegexOption.IGNORE_CASE), "")
        } else {
            normalized
        }
    }

    private fun bindTerminalObserverAutoBootstrap() {
        val manager = TerminalToolWindowManager.getInstance(project)
        val disposable = Disposer.newDisposable("mbtraining-terminal-bootstrap")
        terminalSetupDisposable = disposable
        manager.addNewTerminalSetupHandler(
            { widget -> bootstrapTerminalWidget(widget) },
            disposable
        )
        manager.terminalWidgets.forEach { widget ->
            ensureTerminalObserverBootstrapped(widget)
        }
    }

    private fun autoDetectPendingCommandCompletions() {
        val root = projectRoot ?: return
        if (pendingCommandExpectationsById.isEmpty()) return
        val completionEvents = loadNewTerminalCompletionEvents()
        if (completionEvents.isEmpty()) return

        val passedCommandIds = linkedSetOf<String>()
        val successMessages = mutableListOf<String>()

        completionEvents.forEach { event ->
            pendingCommandExpectationsById.values.forEach { expectation ->
                if (expectation.registeredAtEpochSeconds > event.epochSeconds) return@forEach
                if (!commandsEquivalent(event.command, expectation.expectedCommand)) return@forEach
                if (event.exitCode != 0) return@forEach

                val evaluation = evaluateObservedCommandResult(
                    commandId = expectation.commandId,
                    command = expectation.expectedCommand,
                    exitCode = event.exitCode,
                    root = root
                )
                if (evaluation.passed) {
                    if (passedCommandIds.add(expectation.commandId)) {
                        successMessages += expectation.expectedCommand
                    }
                }
            }
        }

        if (passedCommandIds.isEmpty()) return

        snapshot = snapshot.copy(
            passedCommandIds = snapshot.passedCommandIds + passedCommandIds
        )
        passedCommandIds.forEach { pendingCommandExpectationsById.remove(it) }
        persistSnapshot()
        refreshProgressFromEngine()
        list.repaint()
        refreshStepStatusesOnly()
        if (successMessages.isNotEmpty()) {
            Messages.showInfoMessage(
                project,
                "Detected successful command run:\n\n${successMessages.joinToString("\n")}",
                "MBTraining"
            )
        }
    }

    private fun loadNewTerminalCompletionEvents(): List<TerminalCommandEvent> {
        val file = terminalEventLogFile ?: return emptyList()
        if (!Files.exists(file)) return emptyList()
        return runCatching {
            val lines = Files.readAllLines(file)
            if (lines.size < terminalProcessedEventLines) {
                terminalProcessedEventLines = 0
            }
            val events = mutableListOf<TerminalCommandEvent>()
            for (index in terminalProcessedEventLines until lines.size) {
                parseTerminalCommandEvent(lines[index])?.let(events::add)
            }
            terminalProcessedEventLines = lines.size
            events
        }.getOrElse { emptyList() }
    }

    private fun parseTerminalCommandEvent(line: String): TerminalCommandEvent? {
        if (!line.startsWith("MBTRAINING_EVT|v1|")) return null
        val parts = line.split('|')
        if (parts.size < 8) return null
        if (!parts[2].equals("complete", ignoreCase = true)) return null
        val epoch = parts[4].toLongOrNull() ?: return null
        val exitCode = parts[5].toIntOrNull() ?: return null
        val cwd = decodeHookText(parts[6])
        val command = decodeHookText(parts[7])
        return TerminalCommandEvent(
            shell = parts[3],
            epochSeconds = epoch,
            exitCode = exitCode,
            workingDirectory = cwd,
            command = command
        )
    }

    private fun decodeHookText(text: String): String {
        return text
            .replace("%0D", "\r")
            .replace("%0A", "\n")
            .replace("%7C", "|")
            .replace("%25", "%")
    }

    private fun commandsEquivalent(actual: String, expected: String): Boolean {
        return normalizeCommandForComparison(actual) == normalizeCommandForComparison(expected)
    }

    private fun normalizeCommandForComparison(command: String): String {
        return command
            .trim()
            .replace(Regex("""\s+"""), " ")
            .replace(Regex("""\bmvn(\.cmd)?\s+-q\s+test\b""", RegexOption.IGNORE_CASE), "mvn test")
            .replace(Regex("""\bmvn(\.cmd)?\s+test\s+-q\b""", RegexOption.IGNORE_CASE), "mvn test")
            .lowercase()
    }

    private fun buildObserverBootstrapCommand(widget: TerminalWidget): String? {
        val hookDir = terminalHookDir ?: return null
        val eventFile = terminalEventLogFile ?: return null
        ensureObserverHookFiles(hookDir)

        val shellType = detectShellType(widget)
        val eventPath = eventFile.toAbsolutePath().normalize().toString()
        val powershellHook = hookDir.resolve("hook-powershell.ps1").toAbsolutePath().normalize().toString()
        val bashHook = hookDir.resolve("hook-bash.sh").toAbsolutePath().normalize().toString()
        val zshHook = hookDir.resolve("hook-zsh.zsh").toAbsolutePath().normalize().toString()
        if (!Files.exists(Path.of(powershellHook)) || !Files.exists(Path.of(bashHook)) || !Files.exists(Path.of(zshHook))) {
            return null
        }

        return when (shellType) {
            ShellType.POWERSHELL -> {
                "\$env:MBTRAINING_TERMINAL_EVENT_FILE='${escapeSingleQuotedForPowerShell(eventPath)}'; . '${escapeSingleQuotedForPowerShell(powershellHook)}'"
            }
            ShellType.ZSH -> {
                "export MBTRAINING_TERMINAL_EVENT_FILE='${escapeSingleQuotedForPosix(eventPath)}'; source '${escapeSingleQuotedForPosix(zshHook)}'"
            }
            ShellType.BASH -> {
                "export MBTRAINING_TERMINAL_EVENT_FILE='${escapeSingleQuotedForPosix(eventPath)}'; source '${escapeSingleQuotedForPosix(bashHook)}'"
            }
        }
    }

    private fun buildManualObserverBootstrapHint(): String? {
        val hookDir = terminalHookDir ?: return null
        ensureObserverHookFiles(hookDir)
        val eventPath = terminalEventLogFile?.toAbsolutePath()?.normalize()?.toString() ?: return null
        val os = System.getProperty("os.name").lowercase()
        return if (os.contains("win")) {
            val powershellHook = hookDir.resolve("hook-powershell.ps1").toAbsolutePath().normalize().toString()
            "\$env:MBTRAINING_TERMINAL_EVENT_FILE='${escapeSingleQuotedForPowerShell(eventPath)}'; . '${escapeSingleQuotedForPowerShell(powershellHook)}'"
        } else {
            val bashHook = hookDir.resolve("hook-bash.sh").toAbsolutePath().normalize().toString()
            "export MBTRAINING_TERMINAL_EVENT_FILE='${escapeSingleQuotedForPosix(eventPath)}'; source '${escapeSingleQuotedForPosix(bashHook)}'"
        }
    }

    private fun detectShellType(widget: TerminalWidget): ShellType {
        val shellTokens = widget.shellCommand.orEmpty().map { it.lowercase() }
        if (shellTokens.any { it.contains("powershell") || it.contains("pwsh") }) return ShellType.POWERSHELL
        if (shellTokens.any { it.contains("zsh") }) return ShellType.ZSH
        if (shellTokens.any { it.contains("bash") || it.contains("sh") }) return ShellType.BASH
        val os = System.getProperty("os.name").lowercase()
        return if (os.contains("win")) ShellType.POWERSHELL else ShellType.BASH
    }

    private fun bootstrapTerminalWidget(widget: TerminalWidget) {
        ensureTerminalObserverBootstrapped(widget)
    }

    private fun ensureTerminalObserverBootstrapped(widget: TerminalWidget): Boolean {
        val sessionKey = terminalSessionKey(widget)
        if (bootstrappedTerminalSessionKeys.contains(sessionKey)) return true
        val bootstrapped = bootstrapShellWidget(widget)
        if (bootstrapped) {
            bootstrappedTerminalSessionKeys.add(sessionKey)
        }
        return bootstrapped
    }

    private fun terminalSessionKey(widget: TerminalWidget): Int {
        val connector = runCatching { widget.ttyConnector }.getOrNull()
        return connector?.let { System.identityHashCode(it) } ?: System.identityHashCode(widget)
    }

    private fun bootstrapShellWidget(widget: TerminalWidget): Boolean {
        val command = buildObserverBootstrapCommand(widget) ?: return false
        runCatching {
            widget.sendCommandToExecute(command)
            return true
        }.onFailure {
            // Keep silent here; user can still bootstrap manually from Run action message.
        }
        return false
    }

    private fun ensureObserverHookFiles(hookDir: Path) {
        runCatching {
            Files.createDirectories(hookDir)
            val powershellHook = hookDir.resolve("hook-powershell.ps1")
            val bashHook = hookDir.resolve("hook-bash.sh")
            val zshHook = hookDir.resolve("hook-zsh.zsh")
            writeIfChanged(powershellHook, powershellHookTemplate())
            writeIfChanged(bashHook, bashHookTemplate())
            writeIfChanged(zshHook, zshHookTemplate())
            terminalBootstrapFailedNotified = false
        }.onFailure {
            if (!terminalBootstrapFailedNotified) {
                terminalBootstrapFailedNotified = true
                Messages.showWarningDialog(
                    project,
                    "Unable to prepare terminal observer files.\n${it.message}",
                    "MB Training"
                )
            }
        }
    }

    private fun writeIfChanged(path: Path, content: String) {
        val existing = if (Files.exists(path)) runCatching { Files.readString(path) }.getOrNull() else null
        if (existing == content) return
        Files.writeString(path, content)
    }

    private fun escapeSingleQuotedForPowerShell(text: String): String = text.replace("'", "''")

    private fun escapeSingleQuotedForPosix(text: String): String = text.replace("'", "'\"'\"'")

    private fun currentEpochSeconds(): Long = System.currentTimeMillis() / 1000L

    private fun powershellHookTemplate(): String {
        return """
            if (§env:MBTRAINING_HOOK_ACTIVE -eq '1') { return }
            §env:MBTRAINING_HOOK_ACTIVE = '1'
            §script:mbtEventFile = §env:MBTRAINING_TERMINAL_EVENT_FILE
            if ([string]::IsNullOrWhiteSpace(§script:mbtEventFile)) { return }
            
            function global:MBT_Escape([string]§text) {
              if (§null -eq §text) { return "" }
              return §text.Replace('%','%25').Replace('|','%7C').Replace("`r","%0D").Replace("`n","%0A")
            }
            
            §script:mbtLastCommand = §null
            
            try {
              if (Get-Command Set-PSReadLineOption -ErrorAction SilentlyContinue) {
                Set-PSReadLineOption -AddToHistoryHandler {
                  param([string]§line)
                  §script:mbtLastCommand = §line
                  return §true
                } | Out-Null
              }
            } catch {}
            
            if (-not §script:mbtOriginalPrompt) {
              §script:mbtOriginalPrompt = (Get-Item Function:\prompt).ScriptBlock
            }
            
            function global:prompt {
              §rc = 0
              if (-not §?) {
                if (§null -ne §LASTEXITCODE) { §rc = [int]§LASTEXITCODE } else { §rc = 1 }
              }
              if (-not [string]::IsNullOrWhiteSpace(§script:mbtLastCommand)) {
                try {
                  §epoch = [DateTimeOffset]::UtcNow.ToUnixTimeSeconds()
                  §cmdEsc = MBT_Escape §script:mbtLastCommand
                  §cwdEsc = MBT_Escape ((Get-Location).Path)
                  §line = "MBTRAINING_EVT|v1|complete|powershell|§epoch|§rc|§cwdEsc|§cmdEsc"
                  Add-Content -LiteralPath §script:mbtEventFile -Value §line -Encoding UTF8
                } catch {}
                §script:mbtLastCommand = §null
              }
              if (§script:mbtOriginalPrompt) {
                return & §script:mbtOriginalPrompt
              }
              return "PS §(§executionContext.SessionState.Path.CurrentLocation)§('>' * (§nestedPromptLevel + 1)) "
            }
        """.trimIndent().replace('§', '$')
    }

    private fun bashHookTemplate(): String {
        return """
            if [[ "${'$'}{MBTRAINING_HOOK_ACTIVE:-}" == "1" ]]; then
              return 0
            fi
            export MBTRAINING_HOOK_ACTIVE=1
            __mbt_event_file="${'$'}{MBTRAINING_TERMINAL_EVENT_FILE:-}"
            if [[ -z "${'$'}__mbt_event_file" ]]; then
              return 0
            fi
            
            __mbt_escape() {
              local s="${'$'}1"
              s="${'$'}{s//%/%25}"
              s="${'$'}{s//|/%7C}"
              s="${'$'}{s//$'\r'/%0D}"
              s="${'$'}{s//$'\n'/%0A}"
              printf '%s' "${'$'}s"
            }
            
            __mbt_last_cmd=""
            __mbt_precmd() {
              local rc="${'$'}?"
              local hist_line
              hist_line="$(history 1 2>/dev/null || true)"
              hist_line="${'$'}(printf '%s' "${'$'}hist_line" | sed -E 's/^[[:space:]]*[0-9]+[[:space:]]+//')"
              local cmd="${'$'}hist_line"
              if [[ -z "${'$'}cmd" ]]; then
                cmd="${'$'}__mbt_last_cmd"
              fi
              if [[ -z "${'$'}cmd" ]]; then
                return
              fi
              local epoch
              epoch="$(date +%s 2>/dev/null || printf '0')"
              printf 'MBTRAINING_EVT|v1|complete|bash|%s|%s|%s|%s\n' \
                "${'$'}epoch" "${'$'}rc" "$(__mbt_escape "${'$'}PWD")" "$(__mbt_escape "${'$'}cmd")" >> "${'$'}__mbt_event_file"
              __mbt_last_cmd=""
            }
            
            trap '__mbt_last_cmd="${'$'}BASH_COMMAND"' DEBUG
            if [[ "${'$'}PROMPT_COMMAND" == *"__mbt_precmd"* ]]; then
              :
            elif [[ -z "${'$'}PROMPT_COMMAND" ]]; then
              PROMPT_COMMAND="__mbt_precmd"
            else
              PROMPT_COMMAND="__mbt_precmd;${'$'}PROMPT_COMMAND"
            fi
        """.trimIndent()
    }

    private fun zshHookTemplate(): String {
        return """
            if [[ "${'$'}{MBTRAINING_HOOK_ACTIVE:-}" == "1" ]]; then
              return 0
            fi
            export MBTRAINING_HOOK_ACTIVE=1
            __mbt_event_file="${'$'}{MBTRAINING_TERMINAL_EVENT_FILE:-}"
            if [[ -z "${'$'}__mbt_event_file" ]]; then
              return 0
            fi
            
            __mbt_escape() {
              local s="${'$'}1"
              s="${'$'}{s//\%/%25}"
              s="${'$'}{s//|/%7C}"
              s="${'$'}{s//$'\r'/%0D}"
              s="${'$'}{s//$'\n'/%0A}"
              print -rn -- "${'$'}s"
            }
            
            __mbt_last_cmd=""
            __mbt_preexec() {
              __mbt_last_cmd="${'$'}1"
            }
            
            __mbt_precmd() {
              local rc="${'$'}?"
              local cmd="${'$'}__mbt_last_cmd"
              if [[ -z "${'$'}cmd" ]]; then
                return
              fi
              local epoch
              epoch="$(date +%s 2>/dev/null || print -rn -- 0)"
              print -r -- "MBTRAINING_EVT|v1|complete|zsh|${'$'}epoch|${'$'}rc|$(__mbt_escape "${'$'}PWD")|$(__mbt_escape "${'$'}cmd")" >> "${'$'}__mbt_event_file"
              __mbt_last_cmd=""
            }
            
            autoload -Uz add-zsh-hook
            add-zsh-hook preexec __mbt_preexec
            add-zsh-hook precmd __mbt_precmd
        """.trimIndent()
    }

    private fun evaluateObservedCommandResult(
        commandId: String,
        command: String,
        exitCode: Int,
        root: Path
    ): CommandEvaluation {
        if (commandId == "basic-exercise-1-verify") {
            if (exitCode != 0) return CommandEvaluation(false, null)
            val pom = root.resolve("pom.xml")
            if (!Files.exists(pom)) return CommandEvaluation(false, null)
            val pomContent = runCatching { Files.readString(pom) }.getOrDefault("")
            val hasKarateJunit5 = pomContent.contains("<artifactId>karate-junit5</artifactId>")
            val hasSurefire = pomContent.contains("<artifactId>maven-surefire-plugin</artifactId>")
            return CommandEvaluation(hasKarateJunit5 && hasSurefire, null)
        }

        if (commandId == "basic-exercise-1-maven-repo-check") {
            val effectiveSettings = root.resolve("target").resolve("effective-settings.xml")
            if (!Files.exists(effectiveSettings)) {
                return CommandEvaluation(
                    passed = false,
                    message = "File target/effective-settings.xml was not found."
                )
            }
            val content = Files.readString(effectiveSettings)
            val usesMavenCentral = content.contains("repo.maven.apache.org", ignoreCase = true) ||
                content.contains("repo1.maven.org", ignoreCase = true) ||
                content.contains("repo.maven.apache.org/maven2", ignoreCase = true)
            if (usesMavenCentral) {
                return CommandEvaluation(
                    passed = false,
                    message = "Detected Maven is pulling from Maven Central."
                )
            }
            return CommandEvaluation(passed = true, message = null)
        }

        val normalized = command.lowercase()
        if (normalized.contains("mvn") && normalized.contains("compile")) {
            return CommandEvaluation(
                passed = Files.isDirectory(root.resolve("target").resolve("classes")),
                message = null
            )
        }
        if (normalized.contains("mvn") && (normalized.contains("test") || normalized.contains("verify"))) {
            val reports = root.resolve("target").resolve("surefire-reports")
            if (!Files.isDirectory(reports)) return CommandEvaluation(false, null)
            val hasReports = Files.newDirectoryStream(reports).use { stream -> stream.iterator().hasNext() }
            return CommandEvaluation(hasReports, null)
        }

        val fallbackHint = commandHintById[commandId]?.lowercase().orEmpty()
        if (fallbackHint.contains("test") || fallbackHint.contains("verify")) {
            val reports = root.resolve("target").resolve("surefire-reports")
            if (!Files.isDirectory(reports)) return CommandEvaluation(false, null)
            val hasReports = Files.newDirectoryStream(reports).use { stream -> stream.iterator().hasNext() }
            return CommandEvaluation(hasReports, null)
        }
        return CommandEvaluation(exitCode == 0, null)
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
                            "Unable to determine active Maven settings.xml.",
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
                            "Unable to prepare settings.xml file: ${e.message}",
                            "Open settings.xml"
                        )
                    },
                    ModalityState.defaultModalityState()
                )
                return@executeOnPooledThread
            }

            val vFile = LocalFileSystem.getInstance().refreshAndFindFileByNioFile(settingsPath)
            ApplicationManager.getApplication().invokeLater(
                {
                    if (project.isDisposed) return@invokeLater
                    if (vFile == null) {
                        Messages.showErrorDialog(
                            project,
                            "Unable to open file: $settingsPath",
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
                    Messages.showInfoMessage(project, "Maven sync completed successfully.", "Sync Successful")
                } else {
                    Messages.showWarningDialog(
                        project,
                        "Maven sync was triggered. If not complete yet, wait for completion or check Build/Sync tab.",
                        "Maven Sync in Progress"
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

    private fun collectCommandHintsById(): Map<String, String> {
        return TrainingCurriculumRepository.program.exercises
            .flatMap { it.steps }
            .flatMap { step ->
                step.activities.mapNotNull { activity ->
                    when (activity) {
                        is TrainingActivity.RunCommandTask -> activity.commandId to activity.commandHint
                        is TrainingActivity.RunTestTask -> activity.commandId to activity.commandHint
                        is TrainingActivity.CompileTask -> activity.commandId to activity.commandHint
                        else -> null
                    }
                }
            }
            .toMap()
    }

    private fun isMavenSyncHealthy(projects: List<MavenProject>): Boolean {
        if (projects.isEmpty()) return false
        return projects.none { it.hasReadingErrors() || it.hasUnresolvedArtifacts() || it.hasUnresolvedPlugins() }
    }

    private fun updateStepStatus(exerciseId: String, stepId: String) {
        val label = stepStatusLabels[stepStatusKey(exerciseId, stepId)] ?: return
        val done = isStepCompleted(exerciseId, stepId)
        label.text = if (done) "Completed" else "Not Completed"
        label.foreground = if (done) JBColor(0x1A7F37, 0x3FB950) else JBColor(0x9A6700, 0xD29922)
        detailsScroll.viewport.repaint()
    }

    private fun markTheoryQuizPassed(exerciseId: String) {
        if (snapshot.passedTheoryQuizExerciseIds.contains(exerciseId)) return
        snapshot = snapshot.copy(
            passedTheoryQuizExerciseIds = snapshot.passedTheoryQuizExerciseIds + exerciseId
        )
        persistSnapshot()
        refreshProgressFromEngine()
        list.repaint()
        refreshDetailsFromSelection()
        Messages.showInfoMessage(project, "You passed the theory quiz.", "MB Training")
    }

    private data class CommandEvaluation(
        val passed: Boolean,
        val message: String?
    )

    private data class PendingCommandExpectation(
        val exerciseId: String,
        val stepId: String,
        val commandId: String,
        val expectedCommand: String,
        val registeredAtEpochSeconds: Long
    )

    private data class TerminalCommandEvent(
        val shell: String,
        val epochSeconds: Long,
        val exitCode: Int,
        val workingDirectory: String,
        val command: String
    )

    private enum class ShellType {
        POWERSHELL,
        BASH,
        ZSH
    }

    private sealed interface RunnableStepAction {
        data class Command(
            val commandId: String,
            val commandHint: String
        ) : RunnableStepAction

        data class MavenSync(
            val syncId: String,
            val actionLabel: String
        ) : RunnableStepAction

        data class ScenarioSetup(
            val scenarioId: String,
            val actionLabel: String
        ) : RunnableStepAction
    }

    private fun toVietnameseLevel(level: String): String {
        return when (level) {
            "BASIC" -> "Basic"
            "INTERMEDIATE" -> "Intermediate"
            "ADVANCED" -> "Advanced"
            else -> level
        }
    }

    private fun toVietnameseType(type: String): String {
        return when (type) {
            "EXERCISE" -> "Exercise"
            "MISSION" -> "Mission"
            "TASK" -> "Task"
            "HOMEWORK" -> "Homework"
            else -> type
        }
    }
}

