package com.mb.training.karate.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindowManager
import com.mb.training.karate.MbTrainingConstants
import com.mb.training.karate.packs.TrainingPackRegistry
import com.mb.training.karate.settings.TrainingEngineConfigurable

class OpenTrainingAction : AnAction(), DumbAware {
    override fun update(event: AnActionEvent) {
        event.presentation.isEnabled = event.project != null
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        if (TrainingPackRegistry.getInstance().getActivePack() == null) {
            val choose = Messages.showYesNoDialog(
                project,
                "No training pack is active.\nDo you want to open MBTraining Engine settings now?",
                "MBTraining Engine Framework",
                "Open Settings",
                "Cancel",
                null
            )
            if (choose == Messages.YES) {
                ShowSettingsUtil.getInstance().showSettingsDialog(project, TrainingEngineConfigurable::class.java)
            }
            return
        }
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(MbTrainingConstants.TOOL_WINDOW_ID) ?: return
        toolWindow.show()
    }
}
