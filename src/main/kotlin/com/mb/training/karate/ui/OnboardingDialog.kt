package com.mb.training.karate.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.util.IconLoader
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.mb.training.karate.MbTrainingConstants
import com.mb.training.karate.services.OnboardingSettingsService
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.Action
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JEditorPane
import javax.swing.JPanel
import javax.swing.SwingConstants

class OnboardingDialog(
    project: Project,
    private val settingsService: OnboardingSettingsService
) : DialogWrapper(project) {

    private val doNotShowAgainCheckBox = JBCheckBox("Do not show this again")

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
        return arrayOf(cancelAction)
    }

    override fun doCancelAction() {
        settingsService.setDoNotShowAgain(doNotShowAgainCheckBox.isSelected)
        super.doCancelAction()
    }
}
