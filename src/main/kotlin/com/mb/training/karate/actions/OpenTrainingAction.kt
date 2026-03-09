package com.mb.training.karate.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.wm.ToolWindowManager
import com.mb.training.karate.MbTrainingConstants

class OpenTrainingAction : AnAction(), DumbAware {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(MbTrainingConstants.TOOL_WINDOW_ID) ?: return
        toolWindow.show()
    }
}
