package com.mb.training.karate.ui

import com.intellij.openapi.util.IconLoader
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextArea
import com.intellij.util.ui.JBUI
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import java.awt.Image
import javax.swing.BorderFactory
import javax.swing.JComponent
import javax.swing.JEditorPane
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.SwingConstants

internal object RichContentRenderer {
    private const val IMAGE_MARKER_PREFIX = "{{image:"
    private const val IMAGE_PLACEHOLDER = "{{image}}"
    private val CODE_BLOCK_REGEX = Regex("(?s)```(?:[a-zA-Z0-9_-]+)?\\n(.*?)```")

    internal data class Style(
        val paragraphFont: Font,
        val paragraphColumns: Int,
        val codeFont: Font,
        val codeBaseWidth: Int,
        val codeMinWidth: Int = 300,
        val imageScale: Double = 0.6,
        val imageMaxWidth: Int,
        val imageAlignmentX: Float = Component.LEFT_ALIGNMENT,
        val segmentGapPx: Int = 6
    )

    internal fun createHtmlPane(text: String, bodyStyle: String): JComponent {
        val html = MarkdownTableSupport.toHtml(
            input = text,
            bodyStyle = bodyStyle
        )
        return JEditorPane("text/html", html).apply {
            isEditable = false
            isOpaque = false
            border = null
        }
    }

    internal fun addRichContent(
        container: JPanel,
        raw: String,
        hostClass: Class<*>,
        style: Style,
        inlineImagePath: String? = null
    ) {
        val normalized = normalizeInlineImageMarker(raw, inlineImagePath)
        val segments = parseSegments(normalized)
        segments.forEachIndexed { index, segment ->
            when (segment) {
                is Segment.Text -> {
                    val block = MarkdownTableSupport.createBlocksPanel(
                        text = segment.value,
                        paragraphFont = style.paragraphFont,
                        paragraphColumns = style.paragraphColumns
                    )
                    block.alignmentX = Component.LEFT_ALIGNMENT
                    val preferred = block.preferredSize
                    block.maximumSize = Dimension(Int.MAX_VALUE, preferred.height)
                    container.add(block)
                }
                is Segment.Code -> container.add(createCodeBlock(segment.value, style))
                is Segment.Image -> container.add(createImageBlock(segment.path, hostClass, style))
            }
            if (index < segments.lastIndex) {
                container.add(javax.swing.Box.createVerticalStrut(JBUI.scale(style.segmentGapPx)))
            }
        }
    }

    private fun normalizeInlineImageMarker(raw: String, inlineImagePath: String?): String {
        val imagePath = inlineImagePath?.takeIf { it.isNotBlank() } ?: return raw
        return if (raw.contains(IMAGE_PLACEHOLDER)) {
            raw.replace(IMAGE_PLACEHOLDER, "$IMAGE_MARKER_PREFIX$imagePath}}")
        } else {
            "$raw\n\n$IMAGE_MARKER_PREFIX$imagePath}}"
        }
    }

    private fun parseSegments(raw: String): List<Segment> {
        val text = raw.trim()
        if (text.isBlank()) return listOf(Segment.Text(""))

        val segments = mutableListOf<Segment>()
        val codeMatches = CODE_BLOCK_REGEX.findAll(text).toList()
        var codeMatchIndex = 0
        var index = 0

        fun nextCodeMatch(fromIndex: Int): MatchResult? {
            while (codeMatchIndex < codeMatches.size && codeMatches[codeMatchIndex].range.last < fromIndex) {
                codeMatchIndex++
            }
            return codeMatches.getOrNull(codeMatchIndex)
        }

        fun appendTextSegment(startInclusive: Int, endExclusive: Int) {
            if (endExclusive <= startInclusive) return
            val value = text.substring(startInclusive, endExclusive).trim()
            if (value.isNotBlank()) segments.add(Segment.Text(value))
        }

        while (index < text.length) {
            val codeMatch = nextCodeMatch(index)
            val imageStart = text.indexOf(IMAGE_MARKER_PREFIX, index).takeIf { it >= 0 }
            val imageEnd = imageStart?.let { start ->
                text.indexOf("}}", start + IMAGE_MARKER_PREFIX.length).takeIf { end -> end >= 0 }
            }
            val nextCodeStart = codeMatch?.range?.first
            val nextImageStart = imageStart?.takeIf { imageEnd != null }
            val takeImage = when {
                nextImageStart == null -> false
                nextCodeStart == null -> true
                else -> nextImageStart < nextCodeStart
            }

            if (takeImage && nextImageStart != null && imageEnd != null) {
                appendTextSegment(index, nextImageStart)
                val path = text.substring(nextImageStart + IMAGE_MARKER_PREFIX.length, imageEnd).trim()
                if (path.isNotBlank()) segments.add(Segment.Image(path))
                index = imageEnd + 2
                continue
            }

            if (codeMatch != null) {
                appendTextSegment(index, codeMatch.range.first)
                val code = codeMatch.groupValues.getOrElse(1) { "" }.trimEnd()
                if (code.isNotBlank()) segments.add(Segment.Code(code))
                index = codeMatch.range.last + 1
                codeMatchIndex++
            } else {
                val tail = text.substring(index).trim()
                if (tail.isNotBlank()) segments.add(Segment.Text(tail))
                break
            }
        }

        return if (segments.isEmpty()) listOf(Segment.Text(text)) else segments
    }

    private fun createCodeBlock(code: String, style: Style): JComponent {
        val codeArea = JBTextArea(code).apply {
            isEditable = false
            lineWrap = false
            wrapStyleWord = false
            border = JBUI.Borders.empty(8)
            background = JBColor(0xEEF2F7, 0x1B2230)
            foreground = JBColor(0x1F2937, 0xD8DEE9)
            font = style.codeFont
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
            preferredSize = JBUI.size(style.codeBaseWidth, contentHeight)
            minimumSize = JBUI.size(style.codeMinWidth, contentHeight)
            maximumSize = Dimension(Int.MAX_VALUE, contentHeight)
        }
    }

    private fun createImageBlock(path: String, hostClass: Class<*>, style: Style): JComponent {
        val icon = runCatching { IconLoader.getIcon(path, hostClass) }.getOrNull()
        val label = JBLabel().apply {
            alignmentX = style.imageAlignmentX
            horizontalAlignment = SwingConstants.CENTER
        }
        if (icon != null) {
            val maxWidth = JBUI.scale(style.imageMaxWidth)
            val width = icon.iconWidth.coerceAtLeast(1)
            val height = icon.iconHeight.coerceAtLeast(1)
            val targetWidth = (width * style.imageScale).toInt().coerceAtLeast(1)
            val targetHeight = (height * style.imageScale).toInt().coerceAtLeast(1)
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
                javax.swing.ImageIcon(
                    icon.paintedImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH)
                )
            }
            label.icon = rendered
        } else {
            label.text = "Unable to load image: $path"
            label.foreground = JBColor.GRAY
        }
        return label
    }

    private fun javax.swing.Icon.paintedImage(): java.awt.image.BufferedImage {
        val image = java.awt.image.BufferedImage(iconWidth, iconHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB)
        val g = image.createGraphics()
        paintIcon(null, g, 0, 0)
        g.dispose()
        return image
    }

    private sealed interface Segment {
        data class Text(val value: String) : Segment
        data class Code(val value: String) : Segment
        data class Image(val path: String) : Segment
    }
}

