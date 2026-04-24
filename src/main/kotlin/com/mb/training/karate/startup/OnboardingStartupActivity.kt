package com.mb.training.karate.startup

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.mb.training.karate.packs.TrainingPackRegistry
import com.mb.training.karate.services.OnboardingSettingsService
import com.mb.training.karate.services.TrainingProjectProgressStore
import com.mb.training.karate.ui.OnboardingDialog
import com.mb.training.karate.ui.engine.PackSelectionDialog
import java.nio.file.Path

class OnboardingStartupActivity : StartupActivity, DumbAware {
    override fun runActivity(project: Project) {
        val application = ApplicationManager.getApplication()
        if (application.isHeadlessEnvironment) return

        val settingsService = OnboardingSettingsService.getInstance()
        val packRegistry = TrainingPackRegistry.getInstance()
        packRegistry.normalizeActivePackSelection()

        val installedPacks = packRegistry.installedPacks()
        if (installedPacks.isEmpty()) {
            packRegistry.refreshToolWindowAvailabilityAcrossProjects()
            return
        }

        val basePath = project.basePath
        application.invokeLater {
            if (project.isDisposed) return@invokeLater

            var activePack = packRegistry.getActivePack()
            if (activePack == null && settingsService.canShowPackSelectorThisSession()) {
                settingsService.markPackSelectorShownThisSession()
                val selectionDialog = PackSelectionDialog(
                    project = project,
                    installedPacks = installedPacks,
                    activePackId = settingsService.getActivePackId()
                )
                val activated = selectionDialog.showAndGet()
                if (activated) {
                    packRegistry.setActivePack(selectionDialog.selectedPackId())
                }
                activePack = packRegistry.getActivePack()
            }

            packRegistry.refreshToolWindowAvailabilityAcrossProjects()
            val selectedPack = activePack ?: return@invokeLater
            if (basePath == null) return@invokeLater
            if (TrainingProjectProgressStore.hasProgressFile(Path.of(basePath))) return@invokeLater
            if (selectedPack.onboarding == null) return@invokeLater
            if (settingsService.isPackOnboardingSuppressed(selectedPack.id)) return@invokeLater
            if (!settingsService.canShowPackOnboardingThisSession(selectedPack.id)) return@invokeLater

            settingsService.markPackOnboardingShownThisSession(selectedPack.id)
            OnboardingDialog(project, selectedPack, settingsService).show()
        }
    }
}
