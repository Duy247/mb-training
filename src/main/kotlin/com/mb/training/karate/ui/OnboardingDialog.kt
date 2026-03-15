package com.mb.training.karate.ui

import com.intellij.ide.impl.OpenProjectTask
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.IconUtil
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.mb.training.karate.MbTrainingConstants
import com.mb.training.karate.services.OnboardingSettingsService
import com.mb.training.karate.services.TrainingFolderType
import com.mb.training.karate.services.TrainingProjectProgressStore
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dialog
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.Window
import java.awt.event.KeyEvent
import java.nio.file.Path
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JDialog
import javax.swing.JEditorPane
import javax.swing.JPanel
import javax.swing.KeyStroke
import javax.swing.SwingConstants
import kotlin.io.path.pathString

class OnboardingDialog(
    private val currentProject: Project,
    private val settingsService: OnboardingSettingsService
) {
    companion object {
        private const val DIALOG_WIDTH = 980
        private const val DIALOG_HEIGHT = 720
        private const val HEADER_LOGO_SCALE = 0.12f
        private const val CARD_LOGO_SCALE = 1f
    }

    private val doNotShowAgainCheckBox = JBCheckBox("Không hiển thị lại").apply {
        isSelected = settingsService.isDoNotShowAgainEnabled()
        isOpaque = false
        background = Color(0, 0, 0, 0)
    }

    fun show() {
        val owner = resolveOwnerWindow()
        val dialog = JDialog(owner, Dialog.ModalityType.APPLICATION_MODAL).apply {
            isUndecorated = true
            title = MbTrainingConstants.ONBOARDING_TITLE
            defaultCloseOperation = JDialog.DISPOSE_ON_CLOSE
            contentPane = createDialogRoot(this)
            pack()
            setSize(DIALOG_WIDTH, DIALOG_HEIGHT)
            setLocationRelativeTo(owner)
        }

        registerEscapeToClose(dialog)
        dialog.isVisible = true
    }

    private fun resolveOwnerWindow(): Window? {
        return WindowManager.getInstance().getFrame(currentProject)
    }

    private fun createDialogRoot(dialog: JDialog): JComponent {
        val root = object : JPanel(BorderLayout()) {
            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                val g2 = g as Graphics2D
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

                val base = GradientPaint(
                    0f, 0f, JBColor(Color(34, 41, 61), Color(34, 41, 61)),
                    width.toFloat(), height.toFloat(), JBColor(Color(55, 45, 60), Color(55, 45, 60))
                )
                g2.paint = base
                g2.fillRect(0, 0, width, height)

                g2.color = JBColor(Color(70, 104, 178, 30), Color(70, 104, 178, 30))
                g2.fillOval(-width / 5, -height / 3, width / 2, height / 2)
                g2.color = JBColor(Color(140, 102, 67, 26), Color(140, 102, 67, 26))
                g2.fillOval(width / 2, height / 5, width / 2, height / 2)
            }
        }.apply {
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(JBColor(0x4A5160, 0x4A5160), 1, true),
                JBUI.Borders.empty(0)
            )
            isOpaque = false
        }

        root.add(createHeader(dialog), BorderLayout.NORTH)
        root.add(createCenterPanel(dialog), BorderLayout.CENTER)
        return root
    }

    private fun createHeader(dialog: JDialog): JComponent {
        val appIcon = IconUtil.scale(
            IconLoader.getIcon("/icons/company-logo.svg", javaClass),
            null,
            HEADER_LOGO_SCALE
        )
        return JPanel(BorderLayout()).apply {
            isOpaque = false
            border = JBUI.Borders.empty(12, 14, 4, 14)
            add(
                JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(8), 0)).apply {
                    isOpaque = false
                    add(JBLabel(appIcon))
                    add(
                        JBLabel(MbTrainingConstants.ONBOARDING_TITLE).apply {
                            font = JBFont.label().deriveFont(JBFont.label().size + 1f)
                            foreground = JBColor(0xE6EDF7, 0xE6EDF7)
                        }
                    )
                },
                BorderLayout.WEST
            )
        }
    }

    private fun createCenterPanel(dialog: JDialog): JComponent {
        val panel = JPanel(BorderLayout(JBUI.scale(16), JBUI.scale(16))).apply {
            border = JBUI.Borders.empty(8, 12, 8, 12)
            isOpaque = false
        }

        panel.add(createLogoCard(), BorderLayout.WEST)
        panel.add(createContentPanel(dialog), BorderLayout.CENTER)
        return panel
    }

    private fun createLogoCard(): JComponent {
        val rawIcon = IconUtil.scale(
            IconLoader.getIcon(MbTrainingConstants.COMPANY_LOGO_PATH, javaClass),
            null,
            CARD_LOGO_SCALE
        )
        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.customLine(JBColor(0xD9DDE6, 0x3D4350), 1, 1, 1, 1)
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
            add(
                JBLabel("<html>MB Training for<br/>Karate Framework</html>").apply {
                    horizontalAlignment = SwingConstants.LEFT
                    border = JBUI.Borders.empty(0, 14, 14, 14)
                    foreground = JBColor(0xF2F6FF, 0xDDE7FF)
                    font = Font("Segoe UI", Font.BOLD, 20)
                },
                BorderLayout.SOUTH
            )
        }
    }

    private fun createContentPanel(dialog: JDialog): JComponent {
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

        content.add(titleLabel)
        content.add(Box.createVerticalStrut(JBUI.scale(8)))
        content.add(subtitleLabel)
        content.add(Box.createVerticalStrut(JBUI.scale(16)))
        content.add(createChipsPanel())
        content.add(Box.createVerticalStrut(JBUI.scale(16)))
        content.add(createMessagePane(MbTrainingConstants.ONBOARDING_MESSAGE))
        content.add(Box.createVerticalGlue())
        content.add(createFooter(dialog))
        return content
    }

    private fun createMessagePane(text: String): JComponent {
        val bodyFontSize = (JBFont.label().size - 3).coerceAtLeast(8)
        val html = MarkdownTableSupport.toHtml(
            input = text,
            bodyStyle = "font-family:'Montserrat',sans-serif; font-size:${bodyFontSize}px; font-weight:300; line-height:1.18;"
        )

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

        listOf("Hướng dẫn", "Lý giải", "Bài tập", "Project").forEachIndexed { index, label ->
            val (bgColor, borderColor, fgColor) = when (index) {
                0 -> Triple(JBColor(0x1D4F44, 0x1D4F44), JBColor(0x27B082, 0x27B082), JBColor(0x8AF7C9, 0x8AF7C9))
                1 -> Triple(JBColor(0x43355A, 0x43355A), JBColor(0x7E5CE6, 0x7E5CE6), JBColor(0xE3D8FF, 0xE3D8FF))
                2 -> Triple(JBColor(0x4A3A1F, 0x4A3A1F), JBColor(0xD79B2F, 0xD79B2F), JBColor(0xFFE7B8, 0xFFE7B8))
                else -> Triple(JBColor(0x2A3F5B, 0x2A3F5B), JBColor(0x4FA0FF, 0x4FA0FF), JBColor(0xCFE7FF, 0xCFE7FF))
            }
            panel.add(
                JBLabel(label).apply {
                    border = BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(borderColor, 1, true),
                        JBUI.Borders.empty(5, 12)
                    )
                    foreground = fgColor
                    background = bgColor
                    isOpaque = true
                    font = JBFont.label().deriveFont(Font.BOLD)
                }
            )
        }
        return panel
    }

    private fun createFooter(dialog: JDialog): JComponent {
        val startButton = JButton(MbTrainingConstants.ONBOARDING_START_BUTTON).apply {
            foreground = JBColor(0xEAF7EE, 0xEAF7EE)
            font = JBFont.label().deriveFont(Font.BOLD)
            background = JBColor(0x3F9E69, 0x3F9E69)
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(JBColor(0x74C48F, 0x74C48F), 1, true),
                JBUI.Borders.empty(4, 18)
            )
            isOpaque = true
            isContentAreaFilled = true
            isBorderPainted = true
            isFocusPainted = false
            addActionListener {
                persistPreference()
                dialog.dispose()
                runFirstOnboardingTask()
            }
        }

        val closeButton = JButton("Đóng").apply {
            foreground = JBColor(0xE4E7EF, 0xE4E7EF)
            font = JBFont.label().deriveFont(Font.PLAIN)
            background = JBColor(0x45414D, 0x45414D)
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(JBColor(0x696577, 0x696577), 1, true),
                JBUI.Borders.empty(4, 18)
            )
            isOpaque = true
            isContentAreaFilled = true
            isBorderPainted = true
            isFocusPainted = false
            addActionListener {
                persistPreference()
                dialog.dispose()
            }
        }

        val buttonRow = JPanel(FlowLayout(FlowLayout.RIGHT, JBUI.scale(10), 0)).apply {
            isOpaque = false
            add(startButton)
            add(closeButton)
        }

        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            border = JBUI.Borders.empty(0, 0, 0, 10)
            add(
                JPanel(FlowLayout(FlowLayout.RIGHT, JBUI.scale(10), 0)).apply {
                    isOpaque = false
                    border = JBUI.Borders.emptyBottom(8)
                    add(doNotShowAgainCheckBox)
                }
            )
            add(buttonRow)
        }
    }

    private fun registerEscapeToClose(dialog: JDialog) {
        val rootPane = dialog.rootPane
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            "mb-training-close"
        )
        rootPane.actionMap.put("mb-training-close", object : javax.swing.AbstractAction() {
            override fun actionPerformed(e: java.awt.event.ActionEvent?) {
                persistPreference()
                dialog.dispose()
            }
        })
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
        val currentBasePath = currentProject.basePath?.let { Path.of(it).normalize().pathString }
        val selectedPath = projectPath.normalize().pathString
        if (currentBasePath != null && currentBasePath == selectedPath) {
            Messages.showInfoMessage(
                currentProject,
                "Project này đang được mở sẵn. Tiếp tục training tại cửa sổ hiện tại.",
                MbTrainingConstants.ONBOARDING_TITLE
            )
            return
        }
        ProjectUtil.openOrImport(projectPath, OpenProjectTask(forceOpenInNewFrame = true))
    }
}
