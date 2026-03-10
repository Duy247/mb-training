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
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.Action
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.SwingConstants

class BasicExerciseIntroDialog(project: Project) : DialogWrapper(project) {

    init {
        title = MbTrainingConstants.BASIC_EXERCISE_1_TITLE
        setResizable(false)
        init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout(JBUI.scale(16), JBUI.scale(16))).apply {
            border = JBUI.Borders.empty(18)
            preferredSize = JBUI.size(390, 200)
        }

        panel.add(createLogoCard(), BorderLayout.WEST)
        panel.add(createContentPanel(), BorderLayout.CENTER)
        return panel
    }

    override fun createActions(): Array<Action> {
        okAction.putValue(Action.NAME, "Đã hiểu")
        return arrayOf(okAction)
    }

    private fun createLogoCard(): JComponent {
        val rawIcon = IconLoader.getIcon(MbTrainingConstants.COMPANY_LOGO_PATH, javaClass)
        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.customLine(JBColor(0xD9DDE6, 0x3D4350), 1, 1, 1, 1)
            background = JBColor(0xF7F9FC, 0x2E3440)
            preferredSize = JBUI.size(200, 250)
            minimumSize = JBUI.size(200, 250)
            maximumSize = JBUI.size(200, 250)
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

        val titleLabel = JBLabel("Khởi tạo sân chơi Karate Framework").apply {
            font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 8f)
            maximumSize = JBUI.size(Int.MAX_VALUE, preferredSize.height)
        }
        val subtitleLabel = JBLabel("Mục tiêu: tạo project Maven chuẩn để bắt đầu luyện tập").apply {
            font = JBFont.label().deriveFont(JBFont.label().size + 1f)
            foreground = JBColor.GRAY
            maximumSize = JBUI.size(Int.MAX_VALUE, preferredSize.height)
        }

        val chipRow = JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(8), 0)).apply {
            isOpaque = false
            listOf("Maven", "Karate", "pom.xml", "Folder Structure").forEach { label ->
                add(
                    JBLabel(label).apply {
                        border = BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(JBColor(0xD9DDE6, 0x4A5160), 1, true),
                            JBUI.Borders.empty(4, 10)
                        )
                        font = JBFont.label().deriveFont(Font.BOLD)
                        foreground = JBColor(0x1F3B73, 0xC8D4FF)
                    }
                )
            }
        }

        val structureBox = JBTextArea(MbTrainingConstants.BASIC_EXERCISE_1_STRUCTURE_TREE).apply {
            isEditable = false
            lineWrap = false
            wrapStyleWord = false
            font = Font(Font.MONOSPACED, Font.PLAIN, JBFont.label().size + 1)
            border = JBUI.Borders.empty(10)
            background = JBColor(0xF8FAFF, 0x252B36)
            preferredSize = JBUI.size(300, 160)
            minimumSize = JBUI.size(300, 160)
            maximumSize = JBUI.size(300, 160)
        }

        val tasks = JBTextArea(MbTrainingConstants.BASIC_EXERCISE_1_TASKS).apply {
            isEditable = false
            lineWrap = true
            wrapStyleWord = true
            border = null
            isOpaque = false
            font = JBFont.label().deriveFont(JBFont.label().size + 1f)
            maximumSize = JBUI.size(Int.MAX_VALUE, preferredSize.height)
        }

        content.add(titleLabel)
        content.add(Box.createVerticalStrut(JBUI.scale(8)))
        content.add(subtitleLabel)
        content.add(Box.createVerticalStrut(JBUI.scale(14)))
        content.add(chipRow)
        content.add(Box.createVerticalStrut(JBUI.scale(14)))
        content.add(JBLabel("Cấu trúc cần tạo:").apply { font = JBFont.label().deriveFont(Font.BOLD) })
        content.add(Box.createVerticalStrut(JBUI.scale(6)))
        content.add(structureBox)
        content.add(Box.createVerticalStrut(JBUI.scale(12)))
        content.add(JBLabel("Yêu cầu bài tập:").apply { font = JBFont.label().deriveFont(Font.BOLD) })
        content.add(Box.createVerticalStrut(JBUI.scale(6)))
        content.add(tasks)

        return content
    }
}
