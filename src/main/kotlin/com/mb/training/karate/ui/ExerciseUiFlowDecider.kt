package com.mb.training.karate.ui

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingProgram

internal object ExerciseUiFlowDecider {
    internal data class DependencyGate(
        val unmetDependencyIds: List<String>,
        val firstUnmetDependencyId: String,
        val warningMessage: String
    )

    internal fun resolveDependencyGate(
        program: TrainingProgram,
        exercise: TrainingExercise,
        completedExerciseIds: Set<String>
    ): DependencyGate? {
        val unmet = exercise.preconditionExerciseIds.filterNot { completedExerciseIds.contains(it) }
        if (unmet.isEmpty()) return null

        val titleByExerciseId = program.exercises.associate { it.id to it.title }
        val dependencyTitles = unmet.joinToString(", ") { depId ->
            titleByExerciseId[depId] ?: depId
        }
        return DependencyGate(
            unmetDependencyIds = unmet,
            firstUnmetDependencyId = unmet.first(),
            warningMessage = "You have not completed required prerequisite exercises: $dependencyTitles.\n" +
                "Click Acknowledge to return to the first prerequisite exercise."
        )
    }

    internal fun isQuizEnabledByStepCompletion(
        exercise: TrainingExercise,
        completedStepIds: Set<String>,
        stepKey: (String, String) -> String
    ): Boolean {
        val stepKeys = exercise.steps.map { stepKey(exercise.id, it.id) }
        return when (exercise.completionPolicy) {
            CompletionPolicy.ANY_STEP_DONE -> stepKeys.any { completedStepIds.contains(it) }
            CompletionPolicy.ALL_STEPS_DONE -> stepKeys.all { completedStepIds.contains(it) }
        }
    }
}

