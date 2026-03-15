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
import com.mb.training.karate.model.TrainingExerciseIntro
import java.awt.Image
import java.awt.BorderLayout
import java.awt.Component
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.Action
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.SwingConstants

class BasicExerciseIntroDialog(
    project: Project,
    private val contentModel: TrainingExerciseIntro
) : DialogWrapper(project) {
    companion object {
        // Marker for inline image in intro content, e.g. {{image:/summary-img/reload_maven.png}}
        private const val IMAGE_MARKER_PREFIX = "{{image:"
        // Main dialog size (content area + logo area).
        private const val DIALOG_WIDTH = 760
        private const val DIALOG_HEIGHT = 500
        // Horizontal gap between logo panel and content panel.
        private const val MAIN_HORIZONTAL_GAP = 20
        // Content container padding.
        private const val MAIN_PADDING = 10
        // Left logo card dimensions.
        private const val LOGO_CARD_WIDTH = 260
        private const val LOGO_CARD_HEIGHT = 420
        // Structure/code visual block sizing.
        private const val STRUCTURE_MIN_WIDTH = 320
        private const val CODE_BLOCK_BASE_WIDTH = 420
    }

    init {
        title = contentModel.dialogTitle
        setResizable(false)
        init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout(JBUI.scale(MAIN_HORIZONTAL_GAP), 0)).apply {
            border = JBUI.Borders.empty(MAIN_PADDING)
            preferredSize = JBUI.size(DIALOG_WIDTH, DIALOG_HEIGHT)
        }

        panel.add(createLogoCard(), BorderLayout.WEST)
        panel.add(
            JScrollPane(createContentPanel()).apply {
                border = JBUI.Borders.empty()
                horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
                viewport.isOpaque = false
                isOpaque = false
                verticalScrollBar.unitIncrement = JBUI.scale(16)
            },
            BorderLayout.CENTER
        )
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
            preferredSize = JBUI.size(LOGO_CARD_WIDTH, LOGO_CARD_HEIGHT)
            minimumSize = JBUI.size(LOGO_CARD_WIDTH, LOGO_CARD_HEIGHT)
            maximumSize = JBUI.size(LOGO_CARD_WIDTH, LOGO_CARD_HEIGHT)
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
        val content = JPanel(BorderLayout(0, JBUI.scale(10))).apply {
            isOpaque = false
        }
        val header = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }
        val body = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }

        val titleLabel = JBLabel(contentModel.heading).apply {
            font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 8f)
            maximumSize = JBUI.size(Int.MAX_VALUE, preferredSize.height)
        }
        val subtitleLabel = JBLabel(contentModel.subtitle).apply {
            font = JBFont.label().deriveFont(JBFont.label().size + 1f)
            foreground = JBColor.GRAY
            maximumSize = JBUI.size(Int.MAX_VALUE, preferredSize.height)
        }

        val chipRow = JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(8), 0)).apply {
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
            contentModel.chips.forEach { label ->
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

        val structureContent = createRichSection(contentModel.structureTree, boxed = true)
        val tasksContent = createRichSection(contentModel.tasks, boxed = false)

        header.add(titleLabel)
        header.add(Box.createVerticalStrut(JBUI.scale(8)))
        header.add(subtitleLabel)
        header.add(Box.createVerticalStrut(JBUI.scale(14)))
        header.add(chipRow)

        body.add(
            JBLabel(contentModel.structureTitle).apply {
                font = JBFont.label().deriveFont(Font.BOLD)
                alignmentX = Component.LEFT_ALIGNMENT
            }
        )
        body.add(Box.createVerticalStrut(JBUI.scale(6)))
        body.add(structureContent)
        body.add(Box.createVerticalStrut(JBUI.scale(12)))
        body.add(
            JBLabel(contentModel.tasksTitle).apply {
                font = JBFont.label().deriveFont(Font.BOLD)
                alignmentX = Component.LEFT_ALIGNMENT
            }
        )
        body.add(Box.createVerticalStrut(JBUI.scale(6)))
        body.add(tasksContent)

        content.add(header, BorderLayout.NORTH)
        content.add(body, BorderLayout.CENTER)

        return content
    }

    private fun createRichSection(raw: String, boxed: Boolean): JComponent {
        // Rich section supports plain text + code blocks (```...```) + inline images.
        val container = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }

        val segments = parseRichSegments(raw)
        segments.forEachIndexed { index, segment ->
            when (segment) {
                is RichSegment.Text -> container.add(createParagraph(segment.value))
                is RichSegment.Code -> container.add(createCodeBlock(segment.value))
                is RichSegment.Image -> container.add(createImageBlock(segment.path))
            }
            if (index < segments.lastIndex) {
                container.add(Box.createVerticalStrut(JBUI.scale(6)))
            }
        }

        if (!boxed) return container

        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.empty(8)
            background = JBColor(0xF8FAFF, 0x252B36)
            isOpaque = true
            add(container, BorderLayout.CENTER)
            alignmentX = Component.LEFT_ALIGNMENT
            val preferred = preferredSize
            minimumSize = JBUI.size(STRUCTURE_MIN_WIDTH, preferred.height)
            maximumSize = JBUI.size(Int.MAX_VALUE, preferred.height)
        }
    }

    private fun createParagraph(text: String): JComponent {
        return MarkdownTableSupport.createBlocksPanel(
            text = text,
            paragraphFont = JBFont.label().deriveFont(JBFont.label().size + 1f),
            paragraphColumns = 56
        )
    }

    private fun createCodeBlock(code: String): JComponent {
        val codeArea = JBTextArea(code).apply {
            isEditable = false
            lineWrap = false
            wrapStyleWord = false
            font = Font(Font.MONOSPACED, Font.PLAIN, JBFont.label().size + 1)
            border = JBUI.Borders.empty(8)
            background = JBColor(0xEEF2F7, 0x1B2230)
            foreground = JBColor(0x1F2937, 0xD8DEE9)
            alignmentX = Component.LEFT_ALIGNMENT
        }
        return JScrollPane(codeArea).apply {
            border = BorderFactory.createLineBorder(JBColor(0xD0D7DE, 0x3D4350), 1, true)
            viewport.border = null
            viewport.background = codeArea.background
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
            verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_NEVER
            alignmentX = Component.LEFT_ALIGNMENT
            val lineCount = code.lineSequence().count().coerceAtLeast(1)
            val lineHeight = codeArea.getFontMetrics(codeArea.font).height
            // Height grows with number of lines so short snippets stay compact.
            val contentHeight = (lineHeight * lineCount) + JBUI.scale(16)
            preferredSize = JBUI.size(CODE_BLOCK_BASE_WIDTH, contentHeight)
            minimumSize = JBUI.size(300, contentHeight)
            maximumSize = JBUI.size(Int.MAX_VALUE, contentHeight)
        }
    }

    private fun createImageBlock(path: String): JComponent {
        val icon = runCatching { IconLoader.getIcon(path, javaClass) }.getOrNull()
        val label = JBLabel().apply {
            alignmentX = Component.CENTER_ALIGNMENT
            horizontalAlignment = SwingConstants.CENTER
        }
        if (icon != null) {
            val maxWidth = JBUI.scale(360)
            val width = icon.iconWidth.coerceAtLeast(1)
            val height = icon.iconHeight.coerceAtLeast(1)
            val targetWidth = (width * 0.6).toInt().coerceAtLeast(1)
            val targetHeight = (height * 0.6).toInt().coerceAtLeast(1)
            val rendered = if (targetWidth > maxWidth) {
                val ratio = maxWidth.toDouble() / targetWidth.toDouble()
                javax.swing.ImageIcon(
                    icon.paintedImage().getScaledInstance(
                        maxWidth,
                        (targetHeight * ratio).toInt().coerceAtLeast(1),
                        Image.SCALE_SMOOTH
                    )
                )
            } else {
                javax.swing.ImageIcon(icon.paintedImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH))
            }
            label.icon = rendered
        } else {
            label.text = "Không tải được ảnh: $path"
            label.foreground = JBColor.GRAY
        }
        return label
    }

    private fun parseRichSegments(raw: String): List<RichSegment> {
        val text = raw.trim()
        if (text.isBlank()) return listOf(RichSegment.Text(""))

        val segments = mutableListOf<RichSegment>()
        val codeRegex = Regex("(?s)```(?:[a-zA-Z0-9_-]+)?\\n(.*?)```")
        var index = 0

        while (index < text.length) {
            val codeMatch = codeRegex.find(text, index)
            val imageStart = text.indexOf(IMAGE_MARKER_PREFIX, index).takeIf { it >= 0 }
            val imageEnd = imageStart?.let { text.indexOf("}}", it + IMAGE_MARKER_PREFIX.length).takeIf { end -> end >= 0 } }
            val nextCodeStart = codeMatch?.range?.first
            val nextImageStart = imageStart?.takeIf { imageEnd != null }

            val takeImage = when {
                nextImageStart == null -> false
                nextCodeStart == null -> true
                else -> nextImageStart < nextCodeStart
            }

            if (takeImage && nextImageStart != null && imageEnd != null) {
                if (nextImageStart > index) {
                    val before = text.substring(index, nextImageStart).trim()
                    if (before.isNotBlank()) segments.add(RichSegment.Text(before))
                }
                val path = text.substring(nextImageStart + IMAGE_MARKER_PREFIX.length, imageEnd).trim()
                if (path.isNotBlank()) segments.add(RichSegment.Image(path))
                index = imageEnd + 2
                continue
            }

            if (codeMatch != null) {
                if (codeMatch.range.first > index) {
                    val before = text.substring(index, codeMatch.range.first).trim()
                    if (before.isNotBlank()) segments.add(RichSegment.Text(before))
                }
                val code = codeMatch.groupValues.getOrElse(1) { "" }.trimEnd()
                if (code.isNotBlank()) segments.add(RichSegment.Code(code))
                index = codeMatch.range.last + 1
            } else {
                val tail = text.substring(index).trim()
                if (tail.isNotBlank()) segments.add(RichSegment.Text(tail))
                break
            }
        }

        return if (segments.isEmpty()) listOf(RichSegment.Text(text)) else segments
    }

    private fun javax.swing.Icon.paintedImage(): java.awt.image.BufferedImage {
        val image = java.awt.image.BufferedImage(iconWidth, iconHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB)
        val g = image.createGraphics()
        paintIcon(null, g, 0, 0)
        g.dispose()
        return image
    }

    private sealed interface RichSegment {
        data class Text(val value: String) : RichSegment
        data class Code(val value: String) : RichSegment
        data class Image(val path: String) : RichSegment
    }
}
