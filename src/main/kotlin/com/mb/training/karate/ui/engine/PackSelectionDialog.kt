package com.mb.training.karate.ui.engine

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import com.mb.training.karate.packs.TrainingPackDefinition
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JPanel

class PackSelectionDialog(
    project: Project,
    installedPacks: List<TrainingPackDefinition>,
    activePackId: String?
) : DialogWrapper(project) {

    private val packChoices: List<PackChoice> = installedPacks.map { PackChoice(it.id, it.displayName, it.description) }
    private val packCombo = JComboBox(packChoices.toTypedArray())

    init {
        title = "MBTraining Engine Framework"
        setOKButtonText("Activate Pack")
        setCancelButtonText("Close Without Activating")

        val selected = packChoices.firstOrNull { it.packId == activePackId } ?: packChoices.firstOrNull()
        if (selected != null) {
            packCombo.selectedItem = selected
        }
        init()
    }

    fun selectedPackId(): String? {
        return (packCombo.selectedItem as? PackChoice)?.packId
    }

    override fun createCenterPanel(): JComponent {
        val helpLabel = JBLabel(
            "<html>No training pack is currently active.<br/>" +
                "Choose one installed pack to activate, or close to keep engine idle.</html>"
        )
        return FormBuilder.createFormBuilder()
            .addComponent(helpLabel)
            .addLabeledComponent(JBLabel("Training Pack:"), packCombo, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    private data class PackChoice(
        val packId: String,
        val displayName: String,
        val description: String
    ) {
        override fun toString(): String = "$displayName ($packId)"
    }
}
