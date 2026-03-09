package com.mb.training.karate.startup

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.mb.training.karate.services.OnboardingSettingsService
import com.mb.training.karate.services.TrainingProjectProgressStore
import com.mb.training.karate.ui.OnboardingDialog
import java.nio.file.Path

class OnboardingStartupActivity : StartupActivity, DumbAware {
    override fun runActivity(project: Project) {
        val application = ApplicationManager.getApplication()
        if (application.isHeadlessEnvironment) return

        val basePath = project.basePath ?: return
        if (TrainingProjectProgressStore.hasProgressFile(Path.of(basePath))) return

        val settingsService = OnboardingSettingsService.getInstance()
        if (!settingsService.canShowThisSession()) return

        settingsService.markShownThisSession()
        application.invokeLater {
            if (!project.isDisposed) {
                OnboardingDialog(project, settingsService).show()
            }
        }
    }
}
