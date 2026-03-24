package com.mb.training.karate.training

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingItem
import com.mb.training.karate.model.TrainingProgram
import com.mb.training.karate.training.exercises.BasicExercise1Definition
import com.mb.training.karate.training.exercises.BasicExercise2Definition
import com.mb.training.karate.training.exercises.BasicExercise3Definition

object TrainingCurriculumRepository {
    private val exerciseDefinitions: List<TrainingExercise> = listOf(
        BasicExercise1Definition.exercise,
        BasicExercise2Definition.exercise,
        BasicExercise3Definition.exercise
    )

    val program: TrainingProgram = TrainingProgram(
        id = "mb-karate-core-program",
        title = "MB Training for Karate Framework",
        exercises = exerciseDefinitions
    )

    val exerciseById: Map<String, TrainingExercise> = program.exercises.associateBy { it.id }

    val items: List<TrainingItem> = program.exercises.map { exercise ->
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
}
