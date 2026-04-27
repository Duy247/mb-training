package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise8Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-8",
            title = "Match Fundamentals",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Apply strict and partial matching assertions for reliable response validation.",
            preconditionExerciseIds = listOf("basic-exercise-7"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-7")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-8-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create assertion-focused feature examples.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-8.feature", template = "..."),
                        TrainingActivity.CodeTask("Add exact match cases for strict fields.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-8.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-8.feature", snippet = "match")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-8.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-8.feature", "match")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-8-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement exact and partial match strategies.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Add contains/contains only cases for flexible checks."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-8.feature", snippet = "match"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-8.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-8.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-8.feature", "match")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-8-03-verify",
                    title = "Run and verify",
                    guidance = "Run and confirm assertions fail/pass correctly.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-8-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-8.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-8-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-8.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-8-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Refine assertion style for maintainability.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Group and comment assertions for readability."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-8.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-8", "step-basic-exercise-8-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-8.feature")
                    )
                )
            ),
            expectedOutcome = "A test script using the right match operator for each assertion intent.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-8: Match Fundamentals",
                heading = "Match Fundamentals",
                subtitle = "Project capability: Master core Karate match assertions",
                chips = listOf("match ==", "contains", "contains only", "assertion style"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-8.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Match Fundamentals",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-8", primaryAsset = "src/test/resources/features/basic-exercise-8.feature", prerequisiteId = "basic-exercise-7")
            )
        )
}
