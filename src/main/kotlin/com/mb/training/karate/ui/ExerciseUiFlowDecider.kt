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

        val dependencyTitles = unmet.joinToString(", ") { depId ->
            program.exercises.firstOrNull { it.id == depId }?.title ?: depId
        }
        return DependencyGate(
            unmetDependencyIds = unmet,
            firstUnmetDependencyId = unmet.first(),
            warningMessage = "Bạn chưa hoàn thành bài phụ thuộc: $dependencyTitles.\n" +
                "Bấm Đã hiểu để quay về bài phụ thuộc đầu tiên."
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
