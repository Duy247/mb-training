package com.mb.training.karate.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.JBColor
import com.intellij.ui.awt.RelativePoint
import com.intellij.ui.components.JBLabel
import com.intellij.util.IconUtil
import com.intellij.util.ui.JBFont
import com.intellij.util.ui.JBUI
import com.mb.training.karate.MbTrainingConstants
import com.mb.training.karate.model.TrainingQuizOption
import com.mb.training.karate.model.TrainingQuizQuestion
import com.mb.training.karate.model.TrainingTheoryQuiz
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.Dialog
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.Window
import java.awt.event.KeyEvent
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.ButtonGroup
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JDialog
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JScrollPane
import javax.swing.KeyStroke
import javax.swing.SwingConstants
import kotlin.random.Random

class TheoryQuizDialog(
    private val project: Project,
    private val quiz: TrainingTheoryQuiz,
    private val onPassed: () -> Unit
) {
    companion object {
        private const val DIALOG_WIDTH = 980
        private const val DIALOG_HEIGHT = 640
        private const val HEADER_LOGO_SCALE = 0.12f
        private const val CARD_LOGO_SCALE = 0.9f
        private const val LOGO_CARD_WIDTH = 280
        private const val LOGO_CARD_HEIGHT = 420
    }

    private data class QuizCard(
        val question: TrainingQuizQuestion,
        val shuffledOptions: List<TrainingQuizOption>
    )

    private val random = Random(System.nanoTime())
    private var cards: List<QuizCard> = emptyList()
    private var cardIndex = 0
    private val answers = mutableMapOf<String, String>()

    private lateinit var cardTitleLabel: JBLabel
    private lateinit var indicatorLabel: JBLabel
    private lateinit var promptContentPanel: JPanel
    private lateinit var optionsPanel: JPanel
    private lateinit var prevButton: HoverPaintButton
    private lateinit var nextButton: HoverPaintButton
    private lateinit var submitButton: HoverPaintButton
    private lateinit var hintButton: HoverPaintButton

    fun show() {
        regenerateQuestionSet()
        val owner = resolveOwnerWindow()
        val dialog = JDialog(owner, Dialog.ModalityType.APPLICATION_MODAL).apply {
            isUndecorated = true
            title = "Theory Quiz"
            defaultCloseOperation = JDialog.DISPOSE_ON_CLOSE
            contentPane = createDialogRoot(this)
            pack()
            setSize(DIALOG_WIDTH, DIALOG_HEIGHT)
            setLocationRelativeTo(owner)
        }
        registerEscapeToClose(dialog)
        refreshCardUI()
        dialog.isVisible = true
    }

    private fun resolveOwnerWindow(): Window? = WindowManager.getInstance().getFrame(project)

    private fun regenerateQuestionSet() {
        val askCount = quiz.questionsToAsk.coerceAtLeast(1).coerceAtMost(quiz.questionPool.size.coerceAtLeast(1))
        cards = quiz.questionPool
            .shuffled(random)
            .take(askCount)
            .map { q -> QuizCard(q, q.options.shuffled(random)) }
        cardIndex = 0
        answers.clear()
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
        val appIcon = IconUtil.scale(IconLoader.getIcon("/icons/company-logo.svg", javaClass), null, HEADER_LOGO_SCALE)
        return JPanel(BorderLayout()).apply {
            isOpaque = false
            border = JBUI.Borders.empty(12, 14, 4, 14)
            add(
                JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(8), 0)).apply {
                    isOpaque = false
                    add(JBLabel(appIcon))
                    add(
                        JBLabel("Theory Quiz").apply {
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
        }
    }

    private fun createContentPanel(dialog: JDialog): JComponent {
        val panel = JPanel(BorderLayout(0, JBUI.scale(10))).apply { isOpaque = false }
        val head = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
        }
        cardTitleLabel = JBLabel(quiz.title).apply {
            font = JBFont.label().deriveFont(Font.BOLD, JBFont.label().size + 6f)
            foreground = JBColor(0xDDE7FF, 0xDDE7FF)
            alignmentX = Component.LEFT_ALIGNMENT
        }
        indicatorLabel = JBLabel().apply {
            font = JBFont.label()
            foreground = JBColor.GRAY
            alignmentX = Component.LEFT_ALIGNMENT
        }
        head.add(cardTitleLabel)
        head.add(Box.createVerticalStrut(6))
        head.add(indicatorLabel)
        panel.add(head, BorderLayout.NORTH)

        val cardPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = true
            background = JBColor(0x1F242D, 0x1F242D)
            border = JBUI.Borders.empty(10)
            alignmentX = Component.LEFT_ALIGNMENT
        }
        promptContentPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }
        optionsPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            isOpaque = false
            alignmentX = Component.LEFT_ALIGNMENT
        }

        cardPanel.add(promptContentPanel)
        cardPanel.add(Box.createVerticalStrut(12))
        cardPanel.add(optionsPanel)

        panel.add(
            JScrollPane(cardPanel).apply {
                border = BorderFactory.createLineBorder(JBColor(0x3D4350, 0x3D4350), 1, true)
                horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
                viewport.isOpaque = true
                viewport.background = cardPanel.background
                verticalScrollBar.unitIncrement = JBUI.scale(16)
            },
            BorderLayout.CENTER
        )

        panel.add(createBottomBar(dialog), BorderLayout.SOUTH)
        return panel
    }

    private fun createBottomBar(dialog: JDialog): JComponent {
        prevButton = grayButton("Back") {
            if (cardIndex > 0) {
                cardIndex--
                refreshCardUI()
            }
        }
        nextButton = greenButton("Next") {
            if (cardIndex < cards.lastIndex) {
                cardIndex++
                refreshCardUI()
            }
        }
        submitButton = greenButton("Submit") {
            submitQuiz(dialog)
        }
        hintButton = grayButton("Hint") {
            showHintBubble(it.source as? JComponent ?: return@grayButton)
        }
        val closeButton = grayButton("Close") { dialog.dispose() }

        return JPanel(BorderLayout()).apply {
            isOpaque = false
            border = JBUI.Borders.emptyTop(8)
            add(
                JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(8), 0)).apply {
                    isOpaque = false
                    add(hintButton)
                },
                BorderLayout.WEST
            )
            add(
                JPanel(FlowLayout(FlowLayout.RIGHT, JBUI.scale(8), 0)).apply {
                    isOpaque = false
                    add(prevButton)
                    add(nextButton)
                    add(submitButton)
                    add(closeButton)
                },
                BorderLayout.EAST
            )
        }
    }

    private fun refreshCardUI() {
        val total = cards.size.coerceAtLeast(1)
        val safeIndex = cardIndex.coerceIn(0, total - 1)
        cardIndex = safeIndex
        val card = cards[safeIndex]
        indicatorLabel.text = "Question ${safeIndex + 1}/$total • Minimum correct answers: ${quiz.passThreshold}"
        promptContentPanel.removeAll()
        RichContentRenderer.addRichContent(
            container = promptContentPanel,
            raw = card.question.promptRich.ifBlank { card.question.prompt },
            hostClass = javaClass,
            style = RichContentRenderer.Style(
                paragraphFont = JBFont.label().deriveFont(JBFont.label().size + 1f),
                paragraphColumns = 60,
                codeFont = Font(Font.MONOSPACED, Font.PLAIN, JBFont.label().size),
                codeBaseWidth = 520,
                imageScale = 0.55,
                imageMaxWidth = 520,
                imageAlignmentX = Component.LEFT_ALIGNMENT,
                segmentGapPx = 6
            )
        )
        promptContentPanel.components.forEach { child ->
            if (child is JComponent) {
                child.alignmentX = Component.LEFT_ALIGNMENT
            }
        }
        val promptPreferred = promptContentPanel.preferredSize
        promptContentPanel.maximumSize = java.awt.Dimension(Int.MAX_VALUE, promptPreferred.height)
        promptContentPanel.revalidate()
        promptContentPanel.repaint()

        optionsPanel.removeAll()
        val selected = answers[card.question.id]
        val group = ButtonGroup()
        card.shuffledOptions.forEachIndexed { index, option ->
            val displayLetter = ('A'.code + index).toChar()
            val radio = JRadioButton("$displayLetter. ${option.text}").apply {
                isOpaque = false
                foreground = JBColor(0xE6EDF7, 0xE6EDF7)
                font = JBFont.label().deriveFont(JBFont.label().size + 1f)
                isSelected = selected == option.id
                alignmentX = Component.LEFT_ALIGNMENT
                addActionListener { answers[card.question.id] = option.id }
            }
            group.add(radio)
            optionsPanel.add(radio)
            optionsPanel.add(Box.createVerticalStrut(6))
        }
        optionsPanel.revalidate()
        optionsPanel.repaint()

        prevButton.isEnabled = safeIndex > 0
        val isLast = safeIndex == cards.lastIndex
        nextButton.isVisible = !isLast
        submitButton.isVisible = isLast
    }

    private fun submitQuiz(dialog: JDialog) {
        val correct = cards.count { card ->
            answers[card.question.id] == card.question.correctOptionId
        }
        if (correct >= quiz.passThreshold) {
            onPassed()
            dialog.dispose()
            return
        }

        JBPopupFactory.getInstance()
            .createMessage("You got $correct/${cards.size} correct. Threshold ${quiz.passThreshold} not reached. A new question set will be generated, please try again.")
            .showInFocusCenter()
        regenerateQuestionSet()
        refreshCardUI()
    }

    private fun showHintBubble(anchor: JComponent) {
        val card = cards.getOrNull(cardIndex) ?: return
        val hintText = card.question.hint?.trim().orEmpty()
        if (hintText.isBlank()) {
            JBPopupFactory.getInstance().createMessage("No hint is available for this question.").showUnderneathOf(anchor)
            return
        }
        val panel = JPanel().apply {
            border = JBUI.Borders.empty(8)
            add(JBLabel("<html><b>Hint</b><br/>$hintText</html>"))
            isOpaque = false
        }
        val balloon = JBPopupFactory.getInstance()
            .createBalloonBuilder(panel)
            .setFadeoutTime(5000)
            .setHideOnClickOutside(true)
            .setHideOnAction(true)
            .setFillColor(Color(34, 40, 49))
            .createBalloon()
        balloon.show(RelativePoint(anchor, java.awt.Point(anchor.width / 2, 0)), Balloon.Position.above)
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

    private fun greenButton(text: String, action: (java.awt.event.ActionEvent) -> Unit): HoverPaintButton {
        return HoverPaintButton(
            text = text,
            baseBg = JBColor(0x2F8D5A, 0x2F8D5A),
            hoverBg = JBColor(0x39A266, 0x39A266),
            pressedBg = JBColor(0x26764B, 0x26764B),
            baseBorder = JBColor(0x69C58E, 0x69C58E),
            hoverBorder = JBColor(0xA3F0C1, 0xA3F0C1),
            textColor = JBColor(0xFFFFFF, 0xFFFFFF)
        ).apply { addActionListener(action) }
    }

    private fun grayButton(text: String, action: (java.awt.event.ActionEvent) -> Unit): HoverPaintButton {
        return HoverPaintButton(
            text = text,
            baseBg = JBColor(0x3A3D45, 0x3A3D45),
            hoverBg = JBColor(0x4E5360, 0x4E5360),
            pressedBg = JBColor(0x31343B, 0x31343B),
            baseBorder = JBColor(0x676D7A, 0x676D7A),
            hoverBorder = JBColor(0xA6AFBF, 0xA6AFBF),
            textColor = JBColor(0xFFFFFF, 0xFFFFFF)
        ).apply { addActionListener(action) }
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
            preferredSize = JBUI.size(120, 34)
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

