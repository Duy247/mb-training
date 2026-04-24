package com.mb.training.karate.services

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.APP)
@State(name = "MbKarateOnboardingSettings", storages = [Storage("mbKarateOnboardingSettings.xml")])
class OnboardingSettingsService : PersistentStateComponent<OnboardingSettingsService.State> {

    data class State(
        var activePackId: String? = null,
        var onboardingSuppressedPackIds: MutableSet<String> = mutableSetOf()
    )

    private var state = State()
    @Volatile
    private var packSelectorShownInCurrentSession: Boolean = false
    private val packOnboardingShownInCurrentSession: MutableSet<String> = mutableSetOf()

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state
    }

    fun getActivePackId(): String? = state.activePackId?.trim()?.ifEmpty { null }

    fun setActivePackId(packId: String?) {
        state.activePackId = packId?.trim()?.ifEmpty { null }
    }

    fun isPackOnboardingSuppressed(packId: String): Boolean {
        return state.onboardingSuppressedPackIds.contains(packId)
    }

    fun setPackOnboardingSuppressed(packId: String, suppressed: Boolean) {
        val normalized = packId.trim()
        if (normalized.isEmpty()) return
        if (suppressed) {
            state.onboardingSuppressedPackIds.add(normalized)
        } else {
            state.onboardingSuppressedPackIds.remove(normalized)
        }
    }

    fun canShowPackSelectorThisSession(): Boolean = !packSelectorShownInCurrentSession

    fun markPackSelectorShownThisSession() {
        packSelectorShownInCurrentSession = true
    }

    fun canShowPackOnboardingThisSession(packId: String): Boolean {
        return !packOnboardingShownInCurrentSession.contains(packId)
    }

    fun markPackOnboardingShownThisSession(packId: String) {
        packOnboardingShownInCurrentSession.add(packId)
    }

    companion object {
        fun getInstance(): OnboardingSettingsService {
            return ApplicationManager.getApplication().getService(OnboardingSettingsService::class.java)
        }
    }
}
