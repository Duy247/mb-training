package com.mb.training.karate.packs

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.wm.ToolWindowManager
import com.mb.training.karate.MbTrainingConstants
import com.mb.training.karate.services.OnboardingSettingsService

@Service(Service.Level.APP)
class TrainingPackRegistry {
    @Volatile
    private var installedPacksCache: List<TrainingPackDefinition>? = null
    @Volatile
    private var installedPacksByIdCache: Map<String, TrainingPackDefinition>? = null

    private val settingsService: OnboardingSettingsService
        get() = OnboardingSettingsService.getInstance()

    fun installedPacks(): List<TrainingPackDefinition> {
        installedPacksCache?.let { return it }
        synchronized(this) {
            installedPacksCache?.let { return it }
            val resolved = PACK_EP_NAME.extensionList
                .mapNotNull { provider ->
                    runCatching { provider.buildPack() }.getOrNull()
                }
                .distinctBy { it.id }
                .sortedBy { it.displayName.lowercase() }
            installedPacksCache = resolved
            installedPacksByIdCache = resolved.associateBy { it.id }
            return resolved
        }
    }

    fun findPack(packId: String?): TrainingPackDefinition? {
        val normalized = packId?.trim()?.ifEmpty { null } ?: return null
        installedPacksByIdCache?.get(normalized)?.let { return it }
        installedPacks()
        return installedPacksByIdCache?.get(normalized)
    }

    fun getActivePack(): TrainingPackDefinition? {
        return findPack(settingsService.getActivePackId())
    }

    fun setActivePack(packId: String?) {
        val validId = findPack(packId)?.id
        settingsService.setActivePackId(validId)
    }

    fun clearActivePack() {
        settingsService.setActivePackId(null)
    }

    fun normalizeActivePackSelection() {
        val activeId = settingsService.getActivePackId() ?: return
        if (findPack(activeId) == null) {
            settingsService.setActivePackId(null)
        }
    }

    fun refreshToolWindowAvailabilityAcrossProjects() {
        val activePack = getActivePack()
        val available = activePack != null
        ProjectManager.getInstance().openProjects.forEach { project ->
            val toolWindow = ToolWindowManager.getInstance(project).getToolWindow(MbTrainingConstants.TOOL_WINDOW_ID)
            toolWindow?.isAvailable = available
        }
    }

    companion object {
        private val PACK_EP_NAME: ExtensionPointName<TrainingPackProvider> =
            ExtensionPointName.create("com.mb.training.engine.trainingPackProvider")

        fun getInstance(): TrainingPackRegistry {
            return ApplicationManager.getApplication().getService(TrainingPackRegistry::class.java)
        }
    }
}
