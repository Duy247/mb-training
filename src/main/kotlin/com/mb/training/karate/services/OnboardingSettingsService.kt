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
        var doNotShowAgain: Boolean = false
    )

    private var state = State()
    @Volatile
    private var shownInCurrentSession: Boolean = false

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state
    }

    fun isDoNotShowAgainEnabled(): Boolean = state.doNotShowAgain

    fun setDoNotShowAgain(enabled: Boolean) {
        state.doNotShowAgain = enabled
    }

    fun canShowThisSession(): Boolean = !shownInCurrentSession

    fun markShownThisSession() {
        shownInCurrentSession = true
    }

    companion object {
        fun getInstance(): OnboardingSettingsService {
            return ApplicationManager.getApplication().getService(OnboardingSettingsService::class.java)
        }
    }
}
