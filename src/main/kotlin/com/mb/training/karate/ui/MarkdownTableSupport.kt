package com.mb.training.karate.ui

import com.intellij.ui.JBColor
import com.intellij.ui.components.JBTextArea
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.table.DefaultTableModel

internal object MarkdownTableSupport {
    internal sealed interface Block {
        data class Paragraph(val text: String) : Block
        data class Table(val headers: List<String>, val rows: List<List<String>>) : Block
    }

    private val tableSeparatorRegex = Regex("^\\s*\\|?(\\s*:?-{3,}:?\\s*\\|)+\\s*:?-{3,}:?\\s*\\|?\\s*$")

    internal fun parseBlocks(input: String): List<Block> {
        val lines = input.trim().lines()
        if (lines.isEmpty()) return emptyList()

        val blocks = mutableListOf<Block>()
        val paragraphBuffer = StringBuilder()
        var i = 0

        fun flushParagraph() {
            val text = paragraphBuffer.toString().trim()
            if (text.isNotBlank()) blocks.add(Block.Paragraph(text))
            paragraphBuffer.clear()
        }

        while (i < lines.size) {
            val line = lines[i]
            val next = lines.getOrNull(i + 1)
            val isTableHeader = line.contains('|') && next != null && tableSeparatorRegex.matches(next)

            if (isTableHeader) {
                flushParagraph()

                val headers = parseTableRow(line)
                i += 2 // skip header + separator

                val rows = mutableListOf<List<String>>()
                while (i < lines.size) {
                    val rowLine = lines[i]
                    if (rowLine.isBlank() || !rowLine.contains('|')) break
                    rows.add(parseTableRow(rowLine))
                    i++
                }

                if (headers.isNotEmpty()) {
                    blocks.add(Block.Table(headers, rows))
                }
            } else {
                paragraphBuffer.append(line)
                if (i < lines.lastIndex) paragraphBuffer.append('\n')
                i++
            }
        }

        flushParagraph()
        return blocks
    }

    internal fun toHtml(input: String, bodyStyle: String): String {
        val blocks = parseBlocks(input)
        val htmlBlocks = blocks.joinToString("\n") { block ->
            when (block) {
                is Block.Paragraph -> "<div>${escapeHtml(block.text).replace("\n", "<br/>")}</div>"
                is Block.Table -> {
                    val headerHtml = block.headers.joinToString("") { "<th>${escapeHtml(it)}</th>" }
                    val rowHtml = block.rows.joinToString("") { row ->
                        "<tr>${row.normalizeTo(block.headers.size).joinToString("") { "<td>${escapeHtml(it)}</td>" }}</tr>"
                    }
                    """
                    <table class="mb-table">
                      <thead><tr>$headerHtml</tr></thead>
                      <tbody>$rowHtml</tbody>
                    </table>
                    """.trimIndent()
                }
            }
        }

        return """
            <html>
            <head>
              <style>
                body { $bodyStyle margin: 0; }
                .mb-table {
                  border-collapse: collapse;
                  margin: 8px 0;
                  width: 100%;
                }
                .mb-table th, .mb-table td {
                  border: 1px solid #4A5160;
                  padding: 6px 8px;
                  text-align: left;
                }
                .mb-table th {
                  background: #2A3548;
                  color: #EAF2FF;
                  font-weight: 600;
                }
                .mb-table td {
                  background: #252B36;
                  color: #DCE6F7;
                }
              </style>
            </head>
            <body>
              $htmlBlocks
            </body>
            </html>
        """.trimIndent()
    }

    internal fun createBlocksPanel(
        text: String,
        paragraphFont: Font,
        paragraphColumns: Int = 56
    ): JComponent {
        val blocks = parseBlocks(text)
        val panel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }

        blocks.forEachIndexed { index, block ->
            when (block) {
                is Block.Paragraph -> {
                    panel.add(
                        JBTextArea(block.text).apply {
                            isEditable = false
                            lineWrap = true
                            wrapStyleWord = true
                            border = null
                            isOpaque = false
                            font = paragraphFont
                            alignmentX = Component.LEFT_ALIGNMENT
                            columns = paragraphColumns
                        }
                    )
                }
                is Block.Table -> panel.add(createSwingTable(block, paragraphFont))
            }
            if (index < blocks.lastIndex) {
                panel.add(Box.createVerticalStrut(JBUI.scale(6)))
            }
        }

        return panel
    }

    private fun createSwingTable(table: Block.Table, paragraphFont: Font): JComponent {
        val columnCount = table.headers.size.coerceAtLeast(1)
        val model = DefaultTableModel(table.headers.toTypedArray(), 0)
        table.rows.forEach { row ->
            model.addRow(row.normalizeTo(columnCount).toTypedArray())
        }

        val jTable = JTable(model).apply {
            isEnabled = false
            tableHeader.reorderingAllowed = false
            autoResizeMode = JTable.AUTO_RESIZE_ALL_COLUMNS
            font = paragraphFont
            rowHeight = JBUI.scale(22)
            foreground = JBColor(0x1F2937, 0xD8DEE9)
            background = JBColor(0xFFFFFF, 0x252B36)
            gridColor = JBColor(0xD0D7DE, 0x3D4350)
            tableHeader.font = paragraphFont.deriveFont(Font.BOLD)
            tableHeader.foreground = JBColor(0x1F3B73, 0xDCE6FF)
            tableHeader.background = JBColor(0xEEF2F7, 0x2A3548)
            border = BorderFactory.createLineBorder(JBColor(0xD0D7DE, 0x3D4350), 1, true)
        }

        val tableHeight = jTable.rowHeight * (model.rowCount + 1) + JBUI.scale(4)
        return JScrollPane(jTable).apply {
            border = BorderFactory.createLineBorder(JBColor(0xD0D7DE, 0x3D4350), 1, true)
            viewport.border = null
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
            verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_NEVER
            alignmentX = Component.LEFT_ALIGNMENT
            preferredSize = Dimension(preferredSize.width, tableHeight)
            maximumSize = Dimension(Int.MAX_VALUE, tableHeight)
        }
    }

    private fun parseTableRow(line: String): List<String> {
        val normalized = line.trim().removePrefix("|").removeSuffix("|")
        return normalized.split('|').map { it.trim() }
    }

    private fun List<String>.normalizeTo(size: Int): List<String> {
        return when {
            this.size == size -> this
            this.size > size -> this.take(size)
            else -> this + List(size - this.size) { "" }
        }
    }

    private fun escapeHtml(input: String): String {
        return buildString(input.length) {
            input.forEach { ch ->
                when (ch) {
                    '&' -> append("&amp;")
                    '<' -> append("&lt;")
                    '>' -> append("&gt;")
                    '"' -> append("&quot;")
                    '\'' -> append("&#39;")
                    else -> append(ch)
                }
            }
        }
    }
}

