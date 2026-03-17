package com.mb.training.karate.ui

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingType
import com.mb.training.karate.training.TrainingCurriculumRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ExerciseUiFlowDeciderIntegrationTest {

    @Test
    fun `ui flow shows dependency warning and redirects to first missing dependency`() {
        val program = TrainingCurriculumRepository.program
        val exercise2 = program.exercises.first { it.id == "basic-exercise-2" }

        val gate = ExerciseUiFlowDecider.resolveDependencyGate(
            program = program,
            exercise = exercise2,
            completedExerciseIds = emptySet()
        )

        assertNotNull(gate)
        assertEquals("basic-exercise-1", gate.firstUnmetDependencyId)
        assertTrue(gate.warningMessage.contains("bài phụ thuộc", ignoreCase = true))
        val dependencyTitle = program.exercises.first { it.id == "basic-exercise-1" }.title
        assertTrue(gate.warningMessage.contains(dependencyTitle))
    }

    @Test
    fun `ui flow does not show dependency warning when prerequisites are completed`() {
        val program = TrainingCurriculumRepository.program
        val exercise2 = program.exercises.first { it.id == "basic-exercise-2" }

        val gate = ExerciseUiFlowDecider.resolveDependencyGate(
            program = program,
            exercise = exercise2,
            completedExerciseIds = setOf("basic-exercise-1")
        )

        assertNull(gate)
    }

    @Test
    fun `ui flow keeps quiz disabled until all required steps are complete for ALL policy`() {
        val exercise = TrainingCurriculumRepository.program.exercises.first { it.id == "basic-exercise-1" }
        val firstStepOnly = setOf("${exercise.id}::${exercise.steps.first().id}")
        val allSteps = exercise.steps.map { "${exercise.id}::${it.id}" }.toSet()

        val disabled = ExerciseUiFlowDecider.isQuizEnabledByStepCompletion(
            exercise = exercise,
            completedStepIds = firstStepOnly,
            stepKey = { exId, stepId -> "$exId::$stepId" }
        )
        val enabled = ExerciseUiFlowDecider.isQuizEnabledByStepCompletion(
            exercise = exercise,
            completedStepIds = allSteps,
            stepKey = { exId, stepId -> "$exId::$stepId" }
        )

        assertTrue(!disabled)
        assertTrue(enabled)
    }

    @Test
    fun `ui flow enables quiz when any step is complete for ANY policy`() {
        val exercise = TrainingExercise(
            id = "any-policy-ex",
            title = "Any Policy Exercise",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            objective = "Test ANY policy",
            startWhen = listOf(TrainingCondition.Always),
            steps = listOf(
                TrainingStep(
                    id = "s1",
                    title = "Step 1",
                    guidance = "Do s1",
                    activities = listOf(TrainingActivity.CodeTask("dummy")),
                    doneWhen = listOf(TrainingCondition.Always)
                ),
                TrainingStep(
                    id = "s2",
                    title = "Step 2",
                    guidance = "Do s2",
                    activities = listOf(TrainingActivity.CodeTask("dummy")),
                    doneWhen = listOf(TrainingCondition.Always)
                )
            ),
            expectedOutcome = "Done",
            completionPolicy = CompletionPolicy.ANY_STEP_DONE
        )
        val completed = setOf("${exercise.id}::s2")

        val enabled = ExerciseUiFlowDecider.isQuizEnabledByStepCompletion(
            exercise = exercise,
            completedStepIds = completed,
            stepKey = { exId, stepId -> "$exId::$stepId" }
        )

        assertTrue(enabled)
    }
}
