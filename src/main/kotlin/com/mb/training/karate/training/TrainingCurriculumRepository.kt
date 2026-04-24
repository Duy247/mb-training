package com.mb.training.karate.training

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingItem
import com.mb.training.karate.model.TrainingProgram
import com.mb.training.karate.packs.TrainingPackDefinition
import com.mb.training.karate.packs.TrainingPackRegistry

object TrainingCurriculumRepository {
    private val emptyProgram = TrainingProgram(
        id = "mb-training-empty-program",
        title = "MBTraining Engine Framework (No active training pack)",
        exercises = emptyList()
    )
    @Volatile
    private var cache: CurriculumCache? = null

    private data class CurriculumCache(
        val activePackRef: TrainingPackDefinition?,
        val program: TrainingProgram,
        val exerciseById: Map<String, TrainingExercise>,
        val items: List<TrainingItem>
    )

    val program: TrainingProgram
        get() = resolveCache().program

    val exerciseById: Map<String, TrainingExercise>
        get() = resolveCache().exerciseById

    val items: List<TrainingItem>
        get() = resolveCache().items

    private fun resolveCache(): CurriculumCache {
        val activePack = resolveActivePackOrNull()
        val current = cache
        if (current != null && current.activePackRef === activePack) {
            return current
        }

        synchronized(this) {
            val again = cache
            if (again != null && again.activePackRef === activePack) {
                return again
            }

            val activeProgram = activePack?.program ?: emptyProgram
            val built = CurriculumCache(
                activePackRef = activePack,
                program = activeProgram,
                exerciseById = activeProgram.exercises.associateBy { it.id },
                items = activeProgram.exercises.map { exercise ->
                    TrainingItem(
                        id = exercise.id,
                        title = exercise.title,
                        level = exercise.level,
                        type = exercise.type,
                        objective = exercise.objective,
                        steps = exercise.steps.map { it.title },
                        expectedOutcome = exercise.expectedOutcome
                    )
                }
            )
            cache = built
            return built
        }
    }

    private fun resolveActivePackOrNull(): TrainingPackDefinition? {
        return runCatching {
            TrainingPackRegistry.getInstance().getActivePack()
        }.getOrNull()
    }
}
