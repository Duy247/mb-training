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
import com.mb.training.karate.model.TrainingKnowledgeCard
import com.mb.training.karate.model.TrainingKnowledgeSummary
import java.awt.Image
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
    companion object {
        // Marker to place card image in between text blocks.
        private const val IMAGE_INLINE_MARKER = "{{image}}"
        // Main knowledge summary dialog dimensions.
        private const val DIALOG_WIDTH = 760
        private const val DIALOG_HEIGHT = 500
        // Left logo card dimensions.
        private const val LOGO_CARD_WIDTH = 260
        private const val LOGO_CARD_HEIGHT = 420
        // Max rendered width for inline images in summary cards.
        private const val CARD_IMAGE_MAX_WIDTH = 520
    }

    private val cards: List<TrainingKnowledgeCard> = summary.cards.ifEmpty {
        listOf(TrainingKnowledgeCard(title = "Tổng kết", content = "Chưa có nội dung."))
    }
    private var cardIndex: Int = 0
    private lateinit var cardContentPanel: JPanel
    private lateinit var cardIndicatorLabel: JBLabel
    private lateinit var previousButton: JButton
    private lateinit var nextButton: JButton

    init {
        title = "Tóm tắt kiến thức"
        setResizable(true)
        init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout(JBUI.scale(15), 0)).apply {
            border = JBUI.Borders.empty(14)
            preferredSize = JBUI.size(DIALOG_WIDTH, DIALOG_HEIGHT)
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
        cardContentPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            border = JBUI.Borders.empty(10)
            background = JBColor(0xF6F8FA, 0x1F242D)
            isOpaque = true
        }
        val summaryScroll = JScrollPane(cardContentPanel).apply {
            border = BorderFactory.createLineBorder(JBColor(0xD0D7DE, 0x30363D), 1, true)
            viewport.border = null
            viewport.isOpaque = true
            viewport.background = cardContentPanel.background
            verticalScrollBar.unitIncrement = JBUI.scale(16)
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        }
        content.add(summaryScroll, BorderLayout.CENTER)
        content.add(createCardNavigation(), BorderLayout.SOUTH)
        refreshCardView()

        panel.add(content, BorderLayout.CENTER)
        return panel
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

    private fun createCardNavigation(): JComponent {
        previousButton = JButton("Trở lại").apply {
            addActionListener {
                if (cardIndex > 0) {
                    cardIndex--
                    refreshCardView()
                }
            }
        }
        nextButton = JButton("Tiếp theo").apply {
            addActionListener {
                if (cardIndex < cards.lastIndex) {
                    cardIndex++
                    refreshCardView()
                }
            }
        }
        cardIndicatorLabel = JBLabel().apply {
            foreground = JBColor.GRAY
        }

        return JPanel(BorderLayout()).apply {
            isOpaque = false
            border = JBUI.Borders.emptyTop(8)
            add(cardIndicatorLabel, BorderLayout.WEST)
            add(
                JPanel(FlowLayout(FlowLayout.RIGHT, JBUI.scale(8), 0)).apply {
                    isOpaque = false
                    add(previousButton)
                    add(nextButton)
                },
                BorderLayout.EAST
            )
        }
    }

    private fun refreshCardView() {
        val total = cards.size.coerceAtLeast(1)
        val safeIndex = cardIndex.coerceIn(0, total - 1)
        cardIndex = safeIndex
        val current = cards.getOrElse(safeIndex) { TrainingKnowledgeCard("", "") }
        renderCard(current)
        cardIndicatorLabel.text = "Thẻ ${safeIndex + 1}/$total"
        previousButton.isEnabled = safeIndex > 0
        nextButton.isEnabled = safeIndex < total - 1
    }

    private fun renderCard(card: TrainingKnowledgeCard) {
        cardContentPanel.removeAll()
        val cardTitle = card.title.trim()
        val cardBody = card.content.trim()
        val cardIcon = card.imagePath?.takeIf { it.isNotBlank() }?.let { path ->
            runCatching { IconLoader.getIcon(path, javaClass) }.getOrNull()
        }

        if (cardTitle.isNotBlank()) {
            cardContentPanel.add(
                JBLabel(cardTitle).apply {
                    font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 2f)
                    foreground = JBColor(0x1F3B73, 0xC8D4FF)
                    alignmentX = Component.LEFT_ALIGNMENT
                }
            )
            cardContentPanel.add(Box.createVerticalStrut(8))
        }

        if (cardIcon != null && cardBody.contains(IMAGE_INLINE_MARKER)) {
            val parts = cardBody.split(IMAGE_INLINE_MARKER, limit = 2)
            val before = parts.getOrElse(0) { "" }.trim()
            val after = parts.getOrElse(1) { "" }.trim()
            if (before.isNotBlank()) {
                addRichTextWithCodeBlocks(cardContentPanel, before)
                cardContentPanel.add(Box.createVerticalStrut(8))
            }
            cardContentPanel.add(createImageLabel(cardIcon))
            if (after.isNotBlank()) {
                cardContentPanel.add(Box.createVerticalStrut(8))
                addRichTextWithCodeBlocks(cardContentPanel, after)
            }
        } else {
            if (cardBody.isNotBlank()) {
                addRichTextWithCodeBlocks(cardContentPanel, cardBody)
            }
            if (cardIcon != null) {
                if (cardBody.isNotBlank()) {
                    cardContentPanel.add(Box.createVerticalStrut(8))
                }
                cardContentPanel.add(createImageLabel(cardIcon))
            }
        }

        cardContentPanel.revalidate()
        cardContentPanel.repaint()
    }

    private fun addRichTextWithCodeBlocks(container: JPanel, content: String) {
        val codeBlockRegex = Regex("(?s)```(?:[a-zA-Z0-9_-]+)?\\n(.*?)```")
        var cursor = 0
        val matches = codeBlockRegex.findAll(content).toList()
        if (matches.isEmpty()) {
            addCardTextWithTables(container, content)
            return
        }

        matches.forEach { match ->
            val start = match.range.first
            val endExclusive = match.range.last + 1
            if (start > cursor) {
                val textPart = content.substring(cursor, start).trim()
                if (textPart.isNotBlank()) {
                    addCardTextWithTables(container, textPart)
                    container.add(Box.createVerticalStrut(4))
                }
            }

            val codePart = match.groupValues.getOrElse(1) { "" }.trimEnd()
            if (codePart.isNotBlank()) {
                container.add(createCodeBlock(codePart))
                container.add(Box.createVerticalStrut(4))
            }
            cursor = endExclusive
        }

        if (cursor < content.length) {
            val tail = content.substring(cursor).trim()
            if (tail.isNotBlank()) {
                addCardTextWithTables(container, tail)
            }
        }
    }

    private fun addCardTextWithTables(container: JPanel, text: String) {
        val panel = MarkdownTableSupport.createBlocksPanel(
            text = text,
            paragraphFont = JBFont.label(),
            paragraphColumns = 56
        )
        panel.alignmentX = Component.LEFT_ALIGNMENT
        container.add(panel)
    }

    private fun createCardText(text: String): JComponent {
        return JBTextArea(text).apply {
            isEditable = false
            lineWrap = true
            wrapStyleWord = true
            border = null
            isOpaque = false
            font = JBFont.label()
            alignmentX = Component.LEFT_ALIGNMENT
            columns = 56
            val pref = preferredSize
            maximumSize = java.awt.Dimension(Int.MAX_VALUE, pref.height)
        }
    }

    private fun createCodeBlock(code: String): JComponent {
        val codeArea = JBTextArea(code).apply {
            isEditable = false
            lineWrap = false
            wrapStyleWord = false
            border = JBUI.Borders.empty(8)
            background = JBColor(0xEEF2F7, 0x1B2230)
            foreground = JBColor(0x1F2937, 0xD8DEE9)
            font = Font(Font.MONOSPACED, Font.PLAIN, JBFont.label().size)
            alignmentX = Component.LEFT_ALIGNMENT
        }
        return JScrollPane(codeArea).apply {
            border = BorderFactory.createLineBorder(JBColor(0xD0D7DE, 0x3D4350), 1, true)
            viewport.border = null
            viewport.background = codeArea.background
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
            verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_NEVER
            alignmentX = Component.LEFT_ALIGNMENT
            val pref = preferredSize
            maximumSize = java.awt.Dimension(Int.MAX_VALUE, pref.height)
        }
    }

    private fun createImageLabel(rawIcon: javax.swing.Icon): JComponent {
        val maxWidth = JBUI.scale(CARD_IMAGE_MAX_WIDTH)
        val width = rawIcon.iconWidth.coerceAtLeast(1)
        val height = rawIcon.iconHeight.coerceAtLeast(1)
        val halfWidth = (width * 0.5).toInt().coerceAtLeast(1)
        val halfHeight = (height * 0.5).toInt().coerceAtLeast(1)
        val scaledIcon = if (halfWidth > maxWidth) {
            val ratio = maxWidth.toDouble() / halfWidth.toDouble()
            val scaledHeight = (halfHeight * ratio).toInt().coerceAtLeast(1)
            javax.swing.ImageIcon(
                rawIcon
                    .paintedImage()
                    .getScaledInstance(maxWidth, scaledHeight, Image.SCALE_SMOOTH)
            )
        } else {
            javax.swing.ImageIcon(
                rawIcon
                    .paintedImage()
                    .getScaledInstance(halfWidth, halfHeight, Image.SCALE_SMOOTH)
            )
        }
        return JBLabel(scaledIcon).apply {
            alignmentX = Component.LEFT_ALIGNMENT
            horizontalAlignment = JBLabel.CENTER
        }
    }

    private fun javax.swing.Icon.paintedImage(): java.awt.image.BufferedImage {
        val image = java.awt.image.BufferedImage(iconWidth, iconHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB)
        val g = image.createGraphics()
        paintIcon(null, g, 0, 0)
        g.dispose()
        return image
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
