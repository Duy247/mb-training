package com.mb.training.karate.services

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.APP)
@State(name = "MbKarateTrainingProgress", storages = [Storage("mbKarateTrainingProgress.xml")])
class TrainingProgressService : PersistentStateComponent<TrainingProgressService.State> {

    data class State(
        var completedIds: MutableSet<String> = mutableSetOf()
    )

    private var state = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state
    }

    fun isCompleted(itemId: String): Boolean = state.completedIds.contains(itemId)

    fun setCompleted(itemId: String, completed: Boolean) {
        if (completed) {
            state.completedIds.add(itemId)
        } else {
            state.completedIds.remove(itemId)
        }
    }

    companion object {
        fun getInstance(): TrainingProgressService {
            return ApplicationManager.getApplication().getService(TrainingProgressService::class.java)
        }
    }
}
