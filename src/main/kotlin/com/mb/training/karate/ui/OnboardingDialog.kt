package com.mb.training.karate.ui

import com.intellij.ide.impl.OpenProjectTask
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.IconLoader
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.mb.training.karate.MbTrainingConstants
import com.mb.training.karate.services.OnboardingSettingsService
import com.mb.training.karate.services.TrainingFolderType
import com.mb.training.karate.services.TrainingProjectProgressStore
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.Font
import java.nio.file.Path
import javax.swing.Action
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JEditorPane
import javax.swing.JPanel
import javax.swing.SwingConstants

class OnboardingDialog(
    private val currentProject: Project,
    private val settingsService: OnboardingSettingsService
) : DialogWrapper(currentProject) {

    private val doNotShowAgainCheckBox = JBCheckBox("Do not show this again")
    private val startAction = object : DialogWrapperAction(MbTrainingConstants.ONBOARDING_START_BUTTON) {
        override fun doAction(e: java.awt.event.ActionEvent?) {
            persistPreference()
            close(OK_EXIT_CODE)
            runFirstOnboardingTask()
        }
    }

    init {
        title = MbTrainingConstants.ONBOARDING_TITLE
        setResizable(false)
        init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout(JBUI.scale(16), JBUI.scale(16))).apply {
            border = JBUI.Borders.empty(18)
            preferredSize = JBUI.size(820, 120)
        }

        panel.add(createLogoCard(), BorderLayout.WEST)
        panel.add(createContentPanel(), BorderLayout.CENTER)
        return panel
    }

    private fun createLogoCard(): JComponent {
        val rawIcon = IconLoader.getIcon(MbTrainingConstants.COMPANY_LOGO_PATH, javaClass)

        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.customLine(
                JBColor(0xD9DDE6, 0x3D4350),
                1,
                1,
                1,
                1
            )
            background = JBColor(0xF7F9FC, 0x2E3440)
            preferredSize = JBUI.size(280, 360)
            maximumSize = JBUI.size(280, 360)
            minimumSize = JBUI.size(280, 360)
            add(
                JBLabel().apply {
                    icon = rawIcon
                    horizontalAlignment = SwingConstants.CENTER
                    verticalAlignment = SwingConstants.CENTER
                    border = JBUI.Borders.empty(16)
                },
                BorderLayout.CENTER
            )
        }
    }

    private fun createContentPanel(): JComponent {
        val content = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
        }

        val titleLabel = JBLabel("Học cấp tốc Karate Framework").apply {
            font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 8f)
        }

        val subtitleLabel = JBLabel("Từ cơ bản đến nâng cao").apply {
            font = JBFont.label().deriveFont(JBFont.label().size + 1f)
            foreground = JBColor.GRAY
        }

        val messagePane = createMessagePane(MbTrainingConstants.ONBOARDING_MESSAGE)
        val chips = createChipsPanel()
        val footer = JPanel(BorderLayout()).apply {
            isOpaque = false
            border = JBUI.Borders.emptyTop(8)
            add(doNotShowAgainCheckBox, BorderLayout.WEST)
        }

        content.add(titleLabel)
        content.add(Box.createVerticalStrut(JBUI.scale(8)))
        content.add(subtitleLabel)
        content.add(Box.createVerticalStrut(JBUI.scale(16)))
        content.add(chips)
        content.add(Box.createVerticalStrut(JBUI.scale(16)))
        content.add(messagePane)
        content.add(Box.createVerticalGlue())
        content.add(footer)

        return content
    }

    private fun createMessagePane(text: String): JComponent {
        val font = JBFont.label()
        val html = """
            <html>
            <body style="font-family:'${font.family}'; font-size:${font.size}px; line-height:1.2;">
                <div>${text.replace("\n", "<br/>")}</div>
            </body>
            </html>
        """.trimIndent()

        return JEditorPane("text/html", html).apply {
            isEditable = false
            isOpaque = false
            border = null
        }
    }

    private fun createChipsPanel(): JComponent {
        val panel = JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(8), 0)).apply {
            isOpaque = false
            border = JBUI.Borders.emptyBottom(4)
        }

        listOf("Hướng dẫn", "Lý giải", "Bài tập", "Project").forEach { label ->
            panel.add(
                JBLabel(label).apply {
                    border = BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(JBColor(0xD9DDE6, 0x4A5160), 1, true),
                        JBUI.Borders.empty(4, 10)
                    )
                    foreground = JBColor(0x1F3B73, 0xC8D4FF)
                    font = JBFont.label().deriveFont(Font.BOLD)
                }
            )
        }

        return panel
    }

    override fun createActions(): Array<Action> {
        cancelAction.putValue(Action.NAME, "Close")
        return arrayOf(startAction, cancelAction)
    }

    override fun createJButtonForAction(action: Action): JButton {
        val button = super.createJButtonForAction(action)
        if (action === startAction) {
            button.text = MbTrainingConstants.ONBOARDING_START_BUTTON
            button.foreground = JBColor(0x2EA043, 0x56D364)
            button.font = JBFont.label().deriveFont(Font.BOLD)
            button.border = BorderFactory.createLineBorder(JBColor(0x2EA043, 0x56D364), 2, true)
            button.isOpaque = false
            button.isContentAreaFilled = false
            button.isBorderPainted = true
            button.isFocusPainted = false
            button.isEnabled = true
        }
        return button
    }

    override fun doCancelAction() {
        persistPreference()
        super.doCancelAction()
    }

    private fun persistPreference() {
        settingsService.setDoNotShowAgain(doNotShowAgainCheckBox.isSelected)
    }

    private fun runFirstOnboardingTask() {
        Messages.showInfoMessage(
            currentProject,
            MbTrainingConstants.FIRST_TASK_MESSAGE,
            MbTrainingConstants.FIRST_TASK_TITLE
        )

        val descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor().apply {
            title = MbTrainingConstants.PICK_FOLDER_TITLE
            description = MbTrainingConstants.PICK_FOLDER_DESCRIPTION
        }

        while (true) {
            val selectedFolder = FileChooser.chooseFile(descriptor, currentProject, null) ?: return
            val selectedPath = Path.of(selectedFolder.path)
            when (TrainingProjectProgressStore.classifyFolder(selectedPath)) {
                TrainingFolderType.EXISTING_TRAINING_PROJECT -> {
                    openProjectInNewWindow(selectedPath)
                    return
                }
                TrainingFolderType.EMPTY_FOLDER -> {
                    TrainingProjectProgressStore.initializeNewTrainingProject(selectedPath)
                    openProjectInNewWindow(selectedPath)
                    return
                }
                TrainingFolderType.INVALID_FOLDER -> {
                    Messages.showWarningDialog(
                        currentProject,
                        "Đây không phải là một folder trống hay project luyện tập",
                        MbTrainingConstants.ONBOARDING_TITLE
                    )
                }
            }
        }
    }

    private fun openProjectInNewWindow(projectPath: Path) {
        ProjectUtil.openOrImport(
            projectPath,
            OpenProjectTask(forceOpenInNewFrame = true)
        )
    }
}
