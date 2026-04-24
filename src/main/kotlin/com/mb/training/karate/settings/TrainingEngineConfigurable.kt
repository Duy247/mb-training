package com.mb.training.karate.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import com.mb.training.karate.packs.TrainingPackRegistry
import com.mb.training.karate.services.OnboardingSettingsService
import javax.swing.JComboBox
import javax.swing.JComponent

class TrainingEngineConfigurable : SearchableConfigurable, Configurable.NoScroll {
    private val settingsService = OnboardingSettingsService.getInstance()
    private val packRegistry = TrainingPackRegistry.getInstance()

    private val packCombo = JComboBox<PackChoice>()
    private var component: JComponent? = null

    override fun getId(): String = "mb.training.engine.settings"

    override fun getDisplayName(): String = "MBTraining Engine"

    override fun createComponent(): JComponent {
        if (component == null) {
            reloadPackChoices()
            component = FormBuilder.createFormBuilder()
                .addLabeledComponent(JBLabel("Active Training Pack:"), packCombo, 1, false)
                .addComponentFillVertically(JBLabel(""), 0)
                .panel
        }
        return component!!
    }

    override fun reset() {
        reloadPackChoices()
        val activeId = settingsService.getActivePackId()
        val selected = (0 until packCombo.itemCount)
            .asSequence()
            .map { packCombo.getItemAt(it) }
            .firstOrNull { it.packId == activeId }
            ?: packCombo.getItemAt(0)
        packCombo.selectedItem = selected
    }

    override fun apply() {
        val selected = packCombo.selectedItem as? PackChoice
        packRegistry.setActivePack(selected?.packId)
        packRegistry.refreshToolWindowAvailabilityAcrossProjects()
    }

    override fun isModified(): Boolean {
        val selected = packCombo.selectedItem as? PackChoice
        val current = settingsService.getActivePackId()
        return selected?.packId != current
    }

    private fun reloadPackChoices() {
        val selectedId = (packCombo.selectedItem as? PackChoice)?.packId ?: settingsService.getActivePackId()
        packCombo.removeAllItems()
        packCombo.addItem(PackChoice(null, "None (Engine idle)"))
        packRegistry.installedPacks().forEach { pack ->
            packCombo.addItem(PackChoice(pack.id, "${pack.displayName} (${pack.id})"))
        }
        val target = (0 until packCombo.itemCount)
            .asSequence()
            .map { packCombo.getItemAt(it) }
            .firstOrNull { it.packId == selectedId }
            ?: packCombo.getItemAt(0)
        packCombo.selectedItem = target
    }

    private data class PackChoice(val packId: String?, val label: String) {
        override fun toString(): String = label
    }
}
