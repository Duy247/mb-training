package com.mb.training.karate.training

import com.mb.training.karate.model.TrainingCondition
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TrainingCurriculumRepositoryTest {

    @Test
    fun `program registers basic exercise 1 and 2 in order`() {
        val ids = TrainingCurriculumRepository.program.exercises.map { it.id }
        assertEquals(listOf("basic-exercise-1", "basic-exercise-2"), ids)
    }

    @Test
    fun `every exercise has intro metadata for popup rendering`() {
        TrainingCurriculumRepository.program.exercises.forEach { exercise ->
            val intro = assertNotNull(exercise.intro, "Missing intro for ${exercise.id}")
            assertTrue(intro.dialogTitle.isNotBlank())
            assertTrue(intro.heading.isNotBlank())
            assertTrue(intro.structureTree.isNotBlank())
            assertTrue(intro.tasks.isNotBlank())
        }
    }

    @Test
    fun `exercise 2 starts only after exercise 1 completion`() {
        val exercise2 = TrainingCurriculumRepository.program.exercises.first { it.id == "basic-exercise-2" }
        assertTrue(
            exercise2.startWhen.contains(TrainingCondition.ExerciseCompleted("basic-exercise-1")),
            "basic-exercise-2 should be gated by basic-exercise-1 completion"
        )
    }

    @Test
    fun `exercise 1 summary contains cards and maven sync image card`() {
        val exercise1 = TrainingCurriculumRepository.program.exercises.first { it.id == "basic-exercise-1" }
        val summary = assertNotNull(exercise1.knowledgeSummary, "Missing knowledge summary for exercise 1")
        assertTrue(summary.cards.isNotEmpty(), "Summary cards must not be empty")

        val mavenSyncCard = summary.cards.firstOrNull { it.title.contains("Maven Sync") }
        assertNotNull(mavenSyncCard, "Expected a Maven Sync card")
        assertEquals("/summary-img/reload_maven.png", mavenSyncCard.imagePath)
        assertTrue(mavenSyncCard.content.contains("{{image}}"))
    }

    @Test
    fun `each exercise defines theory quiz with valid threshold and question pool`() {
        TrainingCurriculumRepository.program.exercises.forEach { exercise ->
            val quiz = assertNotNull(exercise.theoryQuiz, "Missing theory quiz for ${exercise.id}")
            assertTrue(quiz.questionsToAsk > 0, "questionsToAsk must be > 0 for ${exercise.id}")
            assertTrue(quiz.passThreshold > 0, "passThreshold must be > 0 for ${exercise.id}")
            assertTrue(
                quiz.passThreshold <= quiz.questionsToAsk,
                "passThreshold must be <= questionsToAsk for ${exercise.id}"
            )
            assertTrue(
                quiz.questionPool.size >= quiz.questionsToAsk,
                "questionPool should contain at least questionsToAsk items for ${exercise.id}"
            )
        }
    }
}
