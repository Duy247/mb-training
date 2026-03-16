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
import com.mb.training.karate.model.TrainingExerciseIntro
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

class BasicExerciseIntroDialog(
    private val project: Project,
    private val contentModel: TrainingExerciseIntro
) {
    companion object {
        private const val IMAGE_MARKER_PREFIX = "{{image:"
        private const val DIALOG_WIDTH = 980
        private const val DIALOG_HEIGHT = 640
        private const val HEADER_LOGO_SCALE = 0.12f
        private const val CARD_LOGO_SCALE = 0.9f
        private const val LOGO_CARD_WIDTH = 280
        private const val LOGO_CARD_HEIGHT = 420
        private const val STRUCTURE_MIN_WIDTH = 320
        private const val CODE_BLOCK_BASE_WIDTH = 440
    }

    fun show() {
        val owner = resolveOwnerWindow()
        val dialog = JDialog(owner, Dialog.ModalityType.APPLICATION_MODAL).apply {
            isUndecorated = true
            title = contentModel.dialogTitle
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
                        JBLabel(contentModel.dialogTitle).apply {
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

        val titleLabel = JBLabel(contentModel.heading).apply {
            font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 8f)
            alignmentX = Component.LEFT_ALIGNMENT
            maximumSize = JBUI.size(Int.MAX_VALUE, preferredSize.height)
        }
        val subtitleLabel = JBLabel(contentModel.subtitle).apply {
            font = JBFont.label().deriveFont(JBFont.label().size + 1f)
            foreground = JBColor.GRAY
            alignmentX = Component.LEFT_ALIGNMENT
            maximumSize = JBUI.size(Int.MAX_VALUE, preferredSize.height)
        }
        val chipRow = createChipsPanel(contentModel.chips)

        header.add(titleLabel)
        header.add(Box.createVerticalStrut(JBUI.scale(8)))
        header.add(subtitleLabel)
        header.add(Box.createVerticalStrut(JBUI.scale(14)))
        header.add(chipRow)

        val body = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }
        body.add(sectionTitle(contentModel.structureTitle))
        body.add(Box.createVerticalStrut(JBUI.scale(6)))
        body.add(createRichSection(contentModel.structureTree, boxed = true))
        body.add(Box.createVerticalStrut(JBUI.scale(12)))
        body.add(sectionTitle(contentModel.tasksTitle))
        body.add(Box.createVerticalStrut(JBUI.scale(6)))
        body.add(createRichSection(contentModel.tasks, boxed = false))
        body.add(Box.createVerticalGlue())

        val bodyScroll = JScrollPane(body).apply {
            border = JBUI.Borders.empty()
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
            verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
            viewport.isOpaque = false
            isOpaque = false
            verticalScrollBar.unitIncrement = JBUI.scale(16)
        }

        content.add(header, BorderLayout.NORTH)
        content.add(bodyScroll, BorderLayout.CENTER)
        content.add(
            JPanel(FlowLayout(FlowLayout.RIGHT, JBUI.scale(10), 0)).apply {
                isOpaque = false
                add(
                    HoverPaintButton(
                        text = "Đã hiểu",
                        baseBg = JBColor(0x2F8D5A, 0x2F8D5A),
                        hoverBg = JBColor(0x39A266, 0x39A266),
                        pressedBg = JBColor(0x26764B, 0x26764B),
                        baseBorder = JBColor(0x69C58E, 0x69C58E),
                        hoverBorder = JBColor(0xA3F0C1, 0xA3F0C1),
                        textColor = JBColor(0xFFFFFF, 0xFFFFFF)
                    ).apply {
                        font = JBFont.label().deriveFont(Font.BOLD)
                        addActionListener { dialog.dispose() }
                    }
                )
            },
            BorderLayout.SOUTH
        )
        return content
    }

    private fun sectionTitle(text: String): JComponent {
        return JBLabel(text).apply {
            font = JBFont.label().deriveFont(Font.BOLD)
            foreground = JBColor(0xEAF2FF, 0xEAF2FF)
            alignmentX = Component.LEFT_ALIGNMENT
        }
    }

    private fun createChipsPanel(chips: List<String>): JComponent {
        val panel = JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(8), 0)).apply {
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }
        chips.forEachIndexed { index, label ->
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

    private fun createRichSection(raw: String, boxed: Boolean): JComponent {
        val container = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }

        val segments = parseRichSegments(raw)
        segments.forEachIndexed { index, segment ->
            when (segment) {
                is RichSegment.Text -> container.add(
                    MarkdownTableSupport.createBlocksPanel(
                        text = segment.value,
                        paragraphFont = JBFont.label().deriveFont(JBFont.label().size + 1f),
                        paragraphColumns = 56
                    )
                )
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
                    icon.paintedImage().getScaledInstance(maxWidth, (targetHeight * ratio).toInt().coerceAtLeast(1), Image.SCALE_SMOOTH)
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

    private sealed interface RichSegment {
        data class Text(val value: String) : RichSegment
        data class Code(val value: String) : RichSegment
        data class Image(val path: String) : RichSegment
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

