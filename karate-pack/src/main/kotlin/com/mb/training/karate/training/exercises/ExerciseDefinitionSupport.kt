package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingQuizOption
import com.mb.training.karate.model.TrainingQuizQuestion

internal fun defaultTheoryQuestions(
    exerciseId: String,
    primaryAsset: String,
    prerequisiteId: String
): List<TrainingQuizQuestion> {
    return listOf(
        question(
            id = "$exerciseId-q1",
            prompt = "What is the primary goal of this exercise?",
            options = listOf(
                option("A", "Implement the intended behavior for exercise assets"),
                option("B", "Skip implementation and only edit documentation"),
                option("C", "Delete outputs from previous exercises"),
                option("D", "Only run tooling without validations")
            ),
            correct = "A",
            hint = "Follow objective and expected outcome."
        ),
        question(
            id = "$exerciseId-q2",
            prompt = "Which file/path is central in this exercise?",
            options = listOf(
                option("A", primaryAsset),
                option("B", ".idea/workspace.xml"),
                option("C", "README.md only"),
                option("D", "No files are required")
            ),
            correct = "A",
            hint = "Check exercise asset focus."
        ),
        question(
            id = "$exerciseId-q3",
            prompt = "Which exercise should be completed before this one?",
            options = listOf(
                option("A", prerequisiteId),
                option("B", "none"),
                option("C", "the capstone only"),
                option("D", "there are no dependencies")
            ),
            correct = "A",
            hint = "This roadmap is project-based and sequential."
        )
    )
}

private fun question(
    id: String,
    prompt: String,
    options: List<TrainingQuizOption>,
    correct: String,
    hint: String
): TrainingQuizQuestion {
    return TrainingQuizQuestion(
        id = id,
        prompt = prompt,
        promptRich = prompt,
        options = options,
        correctOptionId = correct,
        hint = hint
    )
}

private fun option(id: String, text: String): TrainingQuizOption {
    return TrainingQuizOption(id = id, text = text)
}
