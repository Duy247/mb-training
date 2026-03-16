package com.mb.training.karate.training

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingProgram
import com.mb.training.karate.services.TrainingProgressSnapshot
import java.nio.file.Files
import java.nio.file.Path

class TrainingProgressEngine(
    private val projectRoot: Path,
    private val program: TrainingProgram
) {
    private data class FileContainsCacheKey(val file: Path, val text: String)
    private data class FileContainsCacheEntry(val lastModifiedMillis: Long, val contains: Boolean)
    private val fileContainsCache = mutableMapOf<FileContainsCacheKey, FileContainsCacheEntry>()

    fun sync(snapshot: TrainingProgressSnapshot): TrainingProgressSnapshot {
        val completedExercises = mutableSetOf<String>()
        val completedSteps = mutableSetOf<String>()

        for (exercise in program.exercises) {
            val canStart = areAllConditionsMet(
                conditions = exercise.startWhen,
                completedExercises = completedExercises,
                completedSteps = completedSteps,
                passedCommands = snapshot.passedCommandIds,
                successfulMavenSyncIds = snapshot.successfulMavenSyncIds
            )
            if (!canStart) continue

            for (step in exercise.steps) {
                if (
                    areAllConditionsMet(
                        step.doneWhen,
                        completedExercises,
                        completedSteps,
                        snapshot.passedCommandIds,
                        snapshot.successfulMavenSyncIds
                    )
                ) {
                    completedSteps.add(stepKey(exercise.id, step.id))
                }
            }

            val stepKeys = exercise.steps.map { stepKey(exercise.id, it.id) }
            val doneBySteps = when (exercise.completionPolicy) {
                CompletionPolicy.ALL_STEPS_DONE -> stepKeys.all { completedSteps.contains(it) }
                CompletionPolicy.ANY_STEP_DONE -> stepKeys.any { completedSteps.contains(it) }
            }

            if (doneBySteps) {
                completedExercises.add(exercise.id)
            }
        }

        val nextExercise = findNextExercise(
            completedExercises = completedExercises,
            completedSteps = completedSteps,
            passedCommands = snapshot.passedCommandIds,
            successfulMavenSyncIds = snapshot.successfulMavenSyncIds,
            currentItemId = snapshot.currentItemId
        )
        return snapshot.copy(
            currentItemId = nextExercise?.id ?: snapshot.currentItemId,
            completedIds = completedExercises,
            completedStepIds = completedSteps
        )
    }

    fun stepKey(exerciseId: String, stepId: String): String = "$exerciseId::$stepId"

    fun describeRule(condition: TrainingCondition): String {
        return when (condition) {
            TrainingCondition.Always -> "Always available"
            is TrainingCondition.ExerciseCompleted -> "Requires completed exercise: ${condition.exerciseId}"
            is TrainingCondition.StepCompleted -> "Requires completed step: ${condition.exerciseId}/${condition.stepId}"
            is TrainingCondition.FileExists -> "File exists: ${condition.relativePath}"
            is TrainingCondition.FolderExists -> "Folder exists: ${condition.relativePath}"
            is TrainingCondition.FileContains -> "File contains '${condition.text}': ${condition.relativePath}"
            is TrainingCondition.CommandPassed -> "Command passed: ${condition.commandId}"
            is TrainingCondition.MavenSyncSucceeded -> "Maven sync succeeded: ${condition.syncId}"
        }
    }

    fun describeExerciseCompletion(exercise: TrainingExercise): String {
        val prefix = when (exercise.completionPolicy) {
            CompletionPolicy.ALL_STEPS_DONE -> "Done when all steps are complete"
            CompletionPolicy.ANY_STEP_DONE -> "Done when any step is complete"
        }
        return "$prefix (${exercise.steps.size} step(s))"
    }

    private fun findNextExercise(
        completedExercises: Set<String>,
        completedSteps: Set<String>,
        passedCommands: Set<String>,
        successfulMavenSyncIds: Set<String>,
        currentItemId: String
    ): TrainingExercise? {
        val current = program.exercises.firstOrNull { it.id == currentItemId }
        if (current != null && !completedExercises.contains(current.id)) return current

        return program.exercises.firstOrNull { exercise ->
            !completedExercises.contains(exercise.id) &&
                areAllConditionsMet(
                    exercise.startWhen,
                    completedExercises,
                    completedSteps,
                    passedCommands,
                    successfulMavenSyncIds
                )
        }
    }

    private fun areAllConditionsMet(
        conditions: List<TrainingCondition>,
        completedExercises: Set<String>,
        completedSteps: Set<String>,
        passedCommands: Set<String>,
        successfulMavenSyncIds: Set<String>
    ): Boolean {
        return conditions.all { condition ->
            when (condition) {
                TrainingCondition.Always -> true
                is TrainingCondition.ExerciseCompleted -> completedExercises.contains(condition.exerciseId)
                is TrainingCondition.StepCompleted -> {
                    completedSteps.contains(stepKey(condition.exerciseId, condition.stepId))
                }
                is TrainingCondition.FileExists -> Files.exists(projectRoot.resolve(condition.relativePath))
                is TrainingCondition.FolderExists -> Files.isDirectory(projectRoot.resolve(condition.relativePath))
                is TrainingCondition.FileContains -> {
                    val file = projectRoot.resolve(condition.relativePath)
                    evalFileContains(file, condition.text)
                }
                is TrainingCondition.CommandPassed -> passedCommands.contains(condition.commandId)
                is TrainingCondition.MavenSyncSucceeded -> successfulMavenSyncIds.contains(condition.syncId)
            }
        }
    }

    private fun evalFileContains(file: Path, text: String): Boolean {
        if (!Files.exists(file)) return false
        val key = FileContainsCacheKey(file.normalize(), text)
        val modified = runCatching { Files.getLastModifiedTime(file).toMillis() }.getOrNull() ?: return false
        val cached = fileContainsCache[key]
        if (cached != null && cached.lastModifiedMillis == modified) {
            return cached.contains
        }
        val contains = runCatching { Files.readString(file).contains(text) }.getOrElse { false }
        fileContainsCache[key] = FileContainsCacheEntry(modified, contains)
        return contains
    }
}
