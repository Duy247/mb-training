package com.mb.training.karate.training

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class TrainingCurriculumRepositoryTest {

    @Test
    fun `repository is safe to access without IDE application context`() {
        val program = TrainingCurriculumRepository.program
        val exerciseById = TrainingCurriculumRepository.exerciseById
        val items = TrainingCurriculumRepository.items

        assertEquals(program.exercises.size, exerciseById.size)
        assertEquals(program.exercises.size, items.size)
        assertEquals(program.exercises.map { it.id }.toSet(), exerciseById.keys)
        assertEquals(program.exercises.map { it.id }, items.map { it.id })
    }

    @Test
    fun `repository returns stable references between repeated reads`() {
        val program1 = TrainingCurriculumRepository.program
        val exerciseById1 = TrainingCurriculumRepository.exerciseById
        val items1 = TrainingCurriculumRepository.items

        val program2 = TrainingCurriculumRepository.program
        val exerciseById2 = TrainingCurriculumRepository.exerciseById
        val items2 = TrainingCurriculumRepository.items

        assertSame(program1, program2)
        assertSame(exerciseById1, exerciseById2)
        assertSame(items1, items2)
    }
}
