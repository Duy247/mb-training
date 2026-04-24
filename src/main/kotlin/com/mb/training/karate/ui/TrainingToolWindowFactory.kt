package com.mb.training.karate.ui

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import com.mb.training.karate.packs.TrainingPackRegistry

class TrainingToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun shouldBeAvailable(project: Project): Boolean {
        return TrainingPackRegistry.getInstance().getActivePack() != null
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = TrainingToolWindowPanel(project)
        val content = ContentFactory.getInstance().createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
