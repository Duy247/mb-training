package com.mb.training.karate.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.util.IconLoader
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextArea
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.mb.training.karate.MbTrainingConstants
import com.mb.training.karate.model.TrainingKnowledgeSummary
import java.awt.BorderLayout
import java.awt.Component
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.Action
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane

class KnowledgeSummaryDialog(
    project: Project,
    private val summary: TrainingKnowledgeSummary
) : DialogWrapper(project) {

    init {
        title = "Tóm tắt kiến thức"
        setResizable(true)
        init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout(JBUI.scale(15), 0)).apply {
            border = JBUI.Borders.empty(14)
            preferredSize = JBUI.size(760, 500)
        }
        panel.add(createLogoCard(), BorderLayout.WEST)

        val content = JPanel(BorderLayout(0, JBUI.scale(10))).apply {
            isOpaque = false
        }
        val header = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }

        header.add(
                JBLabel(summary.title).apply {
                    font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 6f)
                    foreground = JBColor(0x1F3B73, 0xC8D4FF)
                    alignmentX = Component.LEFT_ALIGNMENT
                }
            )
        summary.subtitle?.takeIf { it.isNotBlank() }?.let { subtitle ->
            header.add(Box.createVerticalStrut(6))
            header.add(
                JBLabel(subtitle).apply {
                    font = JBFont.label().deriveFont(JBFont.label().size + 1f)
                    foreground = JBColor.GRAY
                    alignmentX = Component.LEFT_ALIGNMENT
                }
            )
        }
        if (summary.labels.isNotEmpty()) {
            header.add(Box.createVerticalStrut(10))
            header.add(createLabelsRow(summary.labels))
        }
        content.add(header, BorderLayout.NORTH)
        val summaryArea = JBTextArea(summary.content).apply {
            isEditable = false
            lineWrap = true
            wrapStyleWord = true
            border = JBUI.Borders.empty(10)
            background = JBColor(0xF6F8FA, 0x1F242D)
            font = JBFont.label()
            alignmentX = Component.LEFT_ALIGNMENT
        }
        val summaryScroll = JScrollPane(summaryArea).apply {
            border = BorderFactory.createLineBorder(JBColor(0xD0D7DE, 0x30363D), 1, true)
            viewport.border = null
            viewport.isOpaque = true
            viewport.background = summaryArea.background
            verticalScrollBar.unitIncrement = JBUI.scale(16)
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        }
        content.add(summaryScroll, BorderLayout.CENTER)

        panel.add(content, BorderLayout.CENTER)
        return panel
    }

    private fun createLogoCard(): JComponent {
        val rawIcon = IconLoader.getIcon(MbTrainingConstants.COMPANY_LOGO_PATH, javaClass)
        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.customLine(JBColor(0xD9DDE6, 0x3D4350), 1, 1, 1, 1)
            background = JBColor(0xF7F9FC, 0x2E3440)
            preferredSize = JBUI.size(260, 420)
            minimumSize = JBUI.size(260, 420)
            maximumSize = JBUI.size(260, 420)
            add(
                JBLabel().apply {
                    icon = rawIcon
                    horizontalAlignment = JBLabel.CENTER
                    verticalAlignment = JBLabel.CENTER
                    border = JBUI.Borders.empty(16)
                },
                BorderLayout.CENTER
            )
        }
    }

    private fun createLabelsRow(labels: List<String>): JComponent {
        return JPanel(FlowLayout(FlowLayout.RIGHT, JBUI.scale(8), 0)).apply {
            isOpaque = false
            labels.forEach { label ->
                add(
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
        }
    }

    override fun createActions(): Array<Action> {
        return arrayOf(cancelAction)
    }

    override fun createJButtonForAction(action: Action): JButton {
        val button = super.createJButtonForAction(action)
        if (action === cancelAction) {
            button.text = "Close"
        }
        return button
    }
}
