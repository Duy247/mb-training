package com.mb.training.karate.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextArea
import com.intellij.util.IconUtil
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.mb.training.karate.MbTrainingConstants
import com.mb.training.karate.model.TrainingKnowledgeCard
import com.mb.training.karate.model.TrainingKnowledgeSummary
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.Dialog
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Image
import java.awt.RenderingHints
import java.awt.Window
import java.awt.event.KeyEvent
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JDialog
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.KeyStroke
import javax.swing.SwingConstants

class KnowledgeSummaryDialog(
    private val project: Project,
    private val summary: TrainingKnowledgeSummary
) {
    companion object {
        private const val IMAGE_INLINE_MARKER = "{{image}}"
        private const val DIALOG_WIDTH = 980
        private const val DIALOG_HEIGHT = 640
        private const val HEADER_LOGO_SCALE = 0.12f
        private const val CARD_LOGO_SCALE = 0.9f
        private const val LOGO_CARD_WIDTH = 280
        private const val LOGO_CARD_HEIGHT = 420
        private const val CARD_IMAGE_MAX_WIDTH = 520
        private const val CODE_BLOCK_BASE_WIDTH = 440
    }

    private val cards: List<TrainingKnowledgeCard> = summary.cards.ifEmpty {
        listOf(TrainingKnowledgeCard(title = "Tổng kết", content = "Chưa có nội dung."))
    }
    private var cardIndex: Int = 0
    private lateinit var cardContentPanel: JPanel
    private lateinit var cardIndicatorLabel: JBLabel
    private lateinit var previousButton: HoverPaintButton
    private lateinit var nextButton: HoverPaintButton

    fun show() {
        val owner = resolveOwnerWindow()
        val dialog = JDialog(owner, Dialog.ModalityType.APPLICATION_MODAL).apply {
            isUndecorated = true
            title = "Tóm tắt kiến thức"
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
        return WindowManager.getInstance().getFrame(project)
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
        root.add(createHeader(), BorderLayout.NORTH)
        root.add(createCenterPanel(dialog), BorderLayout.CENTER)
        return root
    }

    private fun createHeader(): JComponent {
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
                        JBLabel("Tóm tắt kiến thức").apply {
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
            border = JBUI.Borders.empty(8, 12, 10, 12)
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
        val content = JPanel(BorderLayout(0, JBUI.scale(10))).apply { isOpaque = false }
        val header = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
        }
        header.add(
            JBLabel(summary.title).apply {
                font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 6f)
                foreground = JBColor(0xDDE7FF, 0xDDE7FF)
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
        content.add(createBottomBar(dialog), BorderLayout.SOUTH)
        refreshCardView()
        return content
    }

    private fun createLabelsRow(labels: List<String>): JComponent {
        val panel = JPanel(FlowLayout(FlowLayout.RIGHT, JBUI.scale(8), 0)).apply {
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }
        labels.forEachIndexed { index, label ->
            val (bgColor, borderColor, fgColor) = when (index % 4) {
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

    private fun createBottomBar(dialog: JDialog): JComponent {
        previousButton = HoverPaintButton(
            text = "Trở lại",
            baseBg = JBColor(0x3A3D45, 0x3A3D45),
            hoverBg = JBColor(0x4E5360, 0x4E5360),
            pressedBg = JBColor(0x31343B, 0x31343B),
            baseBorder = JBColor(0x676D7A, 0x676D7A),
            hoverBorder = JBColor(0xA6AFBF, 0xA6AFBF),
            textColor = JBColor(0xFFFFFF, 0xFFFFFF)
        ).apply {
            addActionListener {
                if (cardIndex > 0) {
                    cardIndex--
                    refreshCardView()
                }
            }
        }
        nextButton = HoverPaintButton(
            text = "Tiếp theo",
            baseBg = JBColor(0x2F8D5A, 0x2F8D5A),
            hoverBg = JBColor(0x39A266, 0x39A266),
            pressedBg = JBColor(0x26764B, 0x26764B),
            baseBorder = JBColor(0x69C58E, 0x69C58E),
            hoverBorder = JBColor(0xA3F0C1, 0xA3F0C1),
            textColor = JBColor(0xFFFFFF, 0xFFFFFF)
        ).apply {
            addActionListener {
                if (cardIndex < cards.lastIndex) {
                    cardIndex++
                    refreshCardView()
                }
            }
        }
        val closeButton = HoverPaintButton(
            text = "Đóng",
            baseBg = JBColor(0x3A3D45, 0x3A3D45),
            hoverBg = JBColor(0x4E5360, 0x4E5360),
            pressedBg = JBColor(0x31343B, 0x31343B),
            baseBorder = JBColor(0x676D7A, 0x676D7A),
            hoverBorder = JBColor(0xA6AFBF, 0xA6AFBF),
            textColor = JBColor(0xFFFFFF, 0xFFFFFF)
        ).apply {
            addActionListener { dialog.dispose() }
        }
        cardIndicatorLabel = JBLabel().apply { foreground = JBColor.GRAY }

        return JPanel(BorderLayout()).apply {
            isOpaque = false
            border = JBUI.Borders.emptyTop(8)
            add(cardIndicatorLabel, BorderLayout.WEST)
            add(
                JPanel(FlowLayout(FlowLayout.RIGHT, JBUI.scale(8), 0)).apply {
                    isOpaque = false
                    add(previousButton)
                    add(nextButton)
                    add(closeButton)
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
            if (cardBody.isNotBlank()) addRichTextWithCodeBlocks(cardContentPanel, cardBody)
            if (cardIcon != null) {
                if (cardBody.isNotBlank()) cardContentPanel.add(Box.createVerticalStrut(8))
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
            if (tail.isNotBlank()) addCardTextWithTables(container, tail)
        }
    }

    private fun addCardTextWithTables(container: JPanel, text: String) {
        val panel = MarkdownTableSupport.createBlocksPanel(
            text = text,
            paragraphFont = JBFont.label(),
            paragraphColumns = 56
        )
        panel.alignmentX = Component.LEFT_ALIGNMENT
        val pref = panel.preferredSize
        panel.maximumSize = java.awt.Dimension(Int.MAX_VALUE, pref.height)
        container.add(panel)
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
            val lineCount = code.lineSequence().count().coerceAtLeast(1)
            val lineHeight = codeArea.getFontMetrics(codeArea.font).height
            val contentHeight = (lineHeight * lineCount) + JBUI.scale(16)
            preferredSize = JBUI.size(CODE_BLOCK_BASE_WIDTH, contentHeight)
            minimumSize = JBUI.size(300, contentHeight)
            maximumSize = java.awt.Dimension(Int.MAX_VALUE, contentHeight)
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
            javax.swing.ImageIcon(rawIcon.paintedImage().getScaledInstance(maxWidth, scaledHeight, Image.SCALE_SMOOTH))
        } else {
            javax.swing.ImageIcon(rawIcon.paintedImage().getScaledInstance(halfWidth, halfHeight, Image.SCALE_SMOOTH))
        }
        return JBLabel(scaledIcon).apply {
            alignmentX = Component.LEFT_ALIGNMENT
            horizontalAlignment = SwingConstants.CENTER
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
                dialog.dispose()
            }
        })
    }

    private fun javax.swing.Icon.paintedImage(): java.awt.image.BufferedImage {
        val image = java.awt.image.BufferedImage(iconWidth, iconHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB)
        val g = image.createGraphics()
        paintIcon(null, g, 0, 0)
        g.dispose()
        return image
    }

    private class HoverPaintButton(
        text: String,
        private val baseBg: Color,
        private val hoverBg: Color,
        private val pressedBg: Color,
        private val baseBorder: Color,
        private val hoverBorder: Color,
        textColor: Color
    ) : JButton(text) {
        init {
            foreground = textColor
            isOpaque = false
            isContentAreaFilled = false
            isBorderPainted = false
            isFocusPainted = false
            margin = JBUI.insets(3, 14, 3, 14)
            preferredSize = JBUI.size(128, 36)
            cursor = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR)
        }

        override fun paintComponent(g: Graphics) {
            val g2 = g.create() as Graphics2D
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            val bg = when {
                model.isPressed -> pressedBg
                model.isRollover -> hoverBg
                else -> baseBg
            }
            val border = if (model.isRollover) hoverBorder else baseBorder
            val arc = JBUI.scale(12)
            g2.color = bg
            g2.fillRoundRect(0, 0, width - 1, height - 1, arc, arc)
            g2.color = border
            g2.drawRoundRect(0, 0, width - 1, height - 1, arc, arc)
            g2.dispose()
            super.paintComponent(g)
        }
    }
}
