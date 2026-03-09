package com.mb.training.karate.ui

import com.intellij.openapi.project.Project
import com.intellij.ui.JBSplitter
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.mb.training.karate.model.TrainingItem
import com.mb.training.karate.services.TrainingProgressSnapshot
import com.mb.training.karate.services.TrainingProjectProgressStore
import com.mb.training.karate.training.TrainingCurriculumRepository
import java.awt.BorderLayout
import java.awt.Component
import java.nio.file.Path
import javax.swing.DefaultListCellRenderer
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.ListSelectionModel

class TrainingToolWindowPanel(
    project: Project
) : JPanel(BorderLayout()) {
    private val projectRoot = project.basePath?.let { Path.of(it) }
    private val listModel = DefaultListModel<TrainingItem>()
    private val list = JBList(listModel)
    private val details = JBTextArea()
    private val progressLabel = JBLabel()
    private val completeButton = JButton("Mark Completed")
    private val resetButton = JButton("Reset Progress")
    private val fallbackCurrentId = TrainingCurriculumRepository.items.firstOrNull()?.id.orEmpty()
    private var snapshot = loadInitialSnapshot()

    init {
        populateList()
        configureList()
        configureDetails()
        configureLayout()
        bindActions()
        applyCurrentSelectionFromSnapshot()
        refreshProgress()
    }

    private fun loadInitialSnapshot(): TrainingProgressSnapshot {
        val root = projectRoot ?: return TrainingProgressSnapshot(fallbackCurrentId, emptySet())
        return TrainingProjectProgressStore.load(root) ?: TrainingProgressSnapshot(fallbackCurrentId, emptySet())
    }

    private fun populateList() {
        TrainingCurriculumRepository.items.forEach(listModel::addElement)
    }

    private fun configureList() {
        list.selectionMode = ListSelectionModel.SINGLE_SELECTION
        list.cellRenderer = object : DefaultListCellRenderer() {
            override fun getListCellRendererComponent(
                list: javax.swing.JList<*>?,
                value: Any?,
                index: Int,
                isSelected: Boolean,
                cellHasFocus: Boolean
            ): Component {
                val component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
                val item = value as? TrainingItem
                if (item != null) {
                    val donePrefix = if (isCompleted(item.id)) "[Done] " else "[Todo] "
                    text = "$donePrefix${item.title} (${item.level} / ${item.type})"
                }
                return component
            }
        }
    }

    private fun configureDetails() {
        details.isEditable = false
        details.lineWrap = true
        details.wrapStyleWord = true
        details.text = "Select a training item to view objective, steps, and expected outcome."
    }

    private fun configureLayout() {
        val left = JPanel(BorderLayout()).apply {
            add(JBLabel("Training Path"), BorderLayout.NORTH)
            add(JBScrollPane(list), BorderLayout.CENTER)
        }

        val right = JPanel(BorderLayout()).apply {
            add(JBLabel("Details"), BorderLayout.NORTH)
            add(JBScrollPane(details), BorderLayout.CENTER)
        }

        val splitter = JBSplitter(false, 0.38f).apply {
            firstComponent = left
            secondComponent = right
        }

        val actions = JPanel().apply {
            add(progressLabel)
            add(completeButton)
            add(resetButton)
        }

        add(splitter, BorderLayout.CENTER)
        add(actions, BorderLayout.SOUTH)
    }

    private fun bindActions() {
        list.addListSelectionListener {
            if (!it.valueIsAdjusting) {
                val item = list.selectedValue ?: return@addListSelectionListener
                details.text = buildDetailsText(item)
                snapshot = snapshot.copy(currentItemId = item.id)
                persistSnapshot()
                completeButton.text = if (isCompleted(item.id)) {
                    "Mark Incomplete"
                } else {
                    "Mark Completed"
                }
            }
        }

        completeButton.addActionListener {
            val item = list.selectedValue ?: return@addActionListener
            val next = !isCompleted(item.id)
            val updatedCompleted = snapshot.completedIds.toMutableSet()
            if (next) {
                updatedCompleted.add(item.id)
            } else {
                updatedCompleted.remove(item.id)
            }
            snapshot = snapshot.copy(completedIds = updatedCompleted, currentItemId = item.id)
            persistSnapshot()
            completeButton.text = if (next) "Mark Incomplete" else "Mark Completed"
            refreshProgress()
            list.repaint()
        }

        resetButton.addActionListener {
            snapshot = TrainingProgressSnapshot(fallbackCurrentId, emptySet())
            persistSnapshot()
            refreshProgress()
            list.repaint()
            applyCurrentSelectionFromSnapshot()
            completeButton.text = "Mark Completed"
        }
    }

    private fun refreshProgress() {
        val total = TrainingCurriculumRepository.items.size
        val completed = TrainingCurriculumRepository.items.count { isCompleted(it.id) }
        progressLabel.text = "Progress: $completed/$total"
    }

    private fun applyCurrentSelectionFromSnapshot() {
        val targetId = snapshot.currentItemId.ifEmpty { fallbackCurrentId }
        val idx = TrainingCurriculumRepository.items.indexOfFirst { it.id == targetId }.takeIf { it >= 0 } ?: 0
        if (listModel.size() > 0) {
            list.selectedIndex = idx
            list.ensureIndexIsVisible(idx)
        }
    }

    private fun isCompleted(itemId: String): Boolean {
        return snapshot.completedIds.contains(itemId)
    }

    private fun persistSnapshot() {
        val root = projectRoot ?: return
        TrainingProjectProgressStore.save(root, snapshot)
    }

    private fun buildDetailsText(item: TrainingItem): String {
        val steps = item.steps.mapIndexed { index, step -> "${index + 1}. $step" }.joinToString("\n")
        return """
            Title: ${item.title}
            Level: ${item.level}
            Type: ${item.type}

            Objective:
            ${item.objective}

            Steps:
            $steps

            Expected outcome:
            ${item.expectedOutcome}
        """.trimIndent()
    }
}
