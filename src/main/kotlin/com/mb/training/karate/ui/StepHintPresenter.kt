package com.mb.training.karate.ui

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorCustomElementRenderer
import com.intellij.openapi.editor.Inlay
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.ui.awt.RelativePoint
import com.intellij.util.ui.JBUI
import com.mb.training.karate.model.TrainingHint
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Point
import java.awt.Rectangle
import java.nio.file.Path
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class StepHintPresenter(
    private val project: Project,
    private val projectRoot: Path?
) {
    fun showHints(hints: List<TrainingHint>, anchor: JComponent) {
        if (hints.isEmpty()) return
        if (hints.size == 1) {
            showHint(hints.first(), anchor)
            return
        }

        val labels = hints.mapIndexed { index, hint -> "${index + 1}. ${labelFor(hint)}" }
        JBPopupFactory.getInstance().createPopupChooserBuilder(labels).apply {
            setTitle("Chọn gợi ý")
            setItemChosenCallback {
                val idx = labels.indexOf(it)
                if (idx >= 0) showHint(hints[idx], anchor)
            }
        }.createPopup().showUnderneathOf(anchor)
    }

    private fun showHint(hint: TrainingHint, anchor: JComponent) {
        when (hint) {
            is TrainingHint.LocationHint -> {
                val note = hint.note?.let { "<br/><i>$it</i>" } ?: ""
                showHudBalloon(
                    """
                    <b>${hint.title}</b><br/>
                    Loại: ${hint.targetType}<br/>
                    Tạo tại: <code>${hint.suggestedPath}</code>
                    $note
                    """.trimIndent(),
                    anchor
                )
            }
            is TrainingHint.RenameHint -> {
                val ctx = hint.contextPath?.let { "<br/>Bối cảnh: $it" } ?: ""
                showHudBalloon(
                    """
                    <b>${hint.title}</b><br/>
                    <code>${hint.fromName}</code> -> <code>${hint.toName}</code>
                    $ctx
                    """.trimIndent(),
                    anchor
                )
            }
            is TrainingHint.ContentHint -> {
                showContentHint(hint, anchor)
            }
        }
    }

    private fun showContentHint(hint: TrainingHint.ContentHint, anchor: JComponent) {
        val root = projectRoot ?: run {
            showHudBalloon("Không xác định được thư mục project hiện tại.", anchor)
            return
        }

        val nioPath = root.resolve(hint.filePath)
        if (!nioPath.toFile().exists()) {
            showHudBalloon("Chưa tìm thấy file <code>${hint.filePath}</code> để hiển thị gợi ý inline.", anchor)
            return
        }

        ApplicationManager.getApplication().executeOnPooledThread {
            val vFile = LocalFileSystem.getInstance().refreshAndFindFileByNioFile(nioPath)
            ApplicationManager.getApplication().invokeLater(
                {
                    if (project.isDisposed) return@invokeLater
                    if (vFile == null) {
                        showHudBalloon("Không mở được file <code>${hint.filePath}</code>.", anchor)
                        return@invokeLater
                    }

                    FileEditorManager.getInstance(project).openFile(vFile, true)
                    val editor = FileEditorManager.getInstance(project).selectedTextEditor
                    if (editor == null) {
                        showHudBalloon("Không tìm thấy editor đang mở để chèn gợi ý inline.", anchor)
                        return@invokeLater
                    }

                    renderInlineSuggestion(editor, hint)
                    showHudBalloon("Đã chèn gợi ý inline vào file <code>${hint.filePath}</code>.", anchor)
                },
                ModalityState.defaultModalityState()
            )
        }
    }

    private fun renderInlineSuggestion(editor: Editor, hint: TrainingHint.ContentHint) {
        val existing = editor.getUserData(INLAY_HINT_KEY)
        existing?.dispose()

        val renderer = InlineHintRenderer(
            title = hint.title,
            snippet = hint.snippet,
            note = hint.note
        )
        val inlay = editor.inlayModel.addBlockElement(
            0,
            true,
            true,
            0,
            renderer
        )
        editor.putUserData(INLAY_HINT_KEY, inlay)
    }

    private fun showHudBalloon(messageHtml: String, anchor: JComponent) {
        val panel = JPanel().apply {
            border = JBUI.Borders.empty(8)
            add(JLabel("<html>$messageHtml</html>"))
            isOpaque = false
        }

        val balloon = JBPopupFactory.getInstance()
            .createBalloonBuilder(panel)
            .setFadeoutTime(7000)
            .setHideOnClickOutside(true)
            .setHideOnAction(true)
            .setFillColor(Color(34, 40, 49))
            .createBalloon()

        val p = RelativePoint(anchor, Point(anchor.width / 2, anchor.height))
        balloon.show(p, Balloon.Position.below)
    }

    private fun labelFor(hint: TrainingHint): String {
        return when (hint) {
            is TrainingHint.LocationHint -> "${hint.title} (${hint.targetType})"
            is TrainingHint.RenameHint -> "${hint.title}: ${hint.fromName} -> ${hint.toName}"
            is TrainingHint.ContentHint -> "${hint.title} (${hint.filePath})"
        }
    }

    companion object {
        private val INLAY_HINT_KEY: Key<Inlay<*>> = Key.create("mb.training.karate.inline.hint.inlay")
    }
}

private class InlineHintRenderer(
    private val title: String,
    private val snippet: String,
    private val note: String?
) : EditorCustomElementRenderer {
    override fun calcWidthInPixels(inlay: Inlay<*>): Int {
        val fm = inlay.editor.contentComponent.getFontMetrics(Font(Font.MONOSPACED, Font.PLAIN, 12))
        val lines = buildLines()
        return (lines.maxOfOrNull { fm.stringWidth(it) } ?: 120) + 16
    }

    override fun calcHeightInPixels(inlay: Inlay<*>): Int {
        val fm = inlay.editor.contentComponent.getFontMetrics(Font(Font.MONOSPACED, Font.PLAIN, 12))
        return (fm.height * buildLines().size) + 12
    }

    override fun paint(inlay: Inlay<*>, g: Graphics, targetRegion: Rectangle, textAttributes: com.intellij.openapi.editor.markup.TextAttributes) {
        val lines = buildLines()
        g.color = Color(34, 40, 49)
        g.fillRoundRect(targetRegion.x, targetRegion.y, targetRegion.width, targetRegion.height, 8, 8)
        g.color = Color(121, 192, 255)
        g.drawRoundRect(targetRegion.x, targetRegion.y, targetRegion.width - 1, targetRegion.height - 1, 8, 8)
        g.font = Font(Font.MONOSPACED, Font.PLAIN, 12)
        val fm = g.fontMetrics
        var y = targetRegion.y + fm.ascent + 6
        lines.forEachIndexed { idx, line ->
            g.color = if (idx == 0) Color(121, 192, 255) else Color(220, 220, 220)
            g.drawString(line, targetRegion.x + 8, y)
            y += fm.height
        }
    }

    private fun buildLines(): List<String> {
        val lines = mutableListOf("💡 $title")
        lines.addAll(snippet.lines().ifEmpty { listOf(snippet) })
        if (!note.isNullOrBlank()) {
            lines.addAll(note.lines())
        }
        return lines
    }
}
