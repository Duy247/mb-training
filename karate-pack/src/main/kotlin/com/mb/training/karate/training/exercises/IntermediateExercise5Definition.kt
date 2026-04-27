package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise5Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-5",
            title = "Data-Driven Outline",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Create data-driven scenarios using Scenario Outline and Examples tables.",
            preconditionExerciseIds = listOf("intermediate-exercise-4"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-4")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-5-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create data-driven feature skeleton.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/intermediate-exercise-5.feature", template = "..."),
                        TrainingActivity.CodeTask("Add outline scenario with variables.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/intermediate-exercise-5.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-5.feature", snippet = "Scenario Outline")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-5.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-5.feature", "Scenario Outline")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-5-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement placeholders and examples table.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Populate Examples with meaningful variations."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-5.feature", snippet = "Scenario Outline"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/intermediate-exercise-5.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-5.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-5.feature", "Scenario Outline")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-5-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify each row executes correctly.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "intermediate-exercise-5-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-5.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-5-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-5.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-5-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Refine data table structure for maintainability.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Clean column naming and value clarity."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-5.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-5", "step-intermediate-exercise-5-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-5.feature")
                    )
                )
            ),
            expectedOutcome = "A maintainable data-driven scenario with multiple executed permutations.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-5: Data-Driven Outline",
                heading = "Data-Driven Outline",
                subtitle = "Project capability: Scale scenarios with outline data",
                chips = listOf("Scenario Outline", "Examples", "data-driven", "coverage"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/intermediate-exercise-5.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Data-Driven Outline",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-5", primaryAsset = "src/test/resources/features/intermediate-exercise-5.feature", prerequisiteId = "intermediate-exercise-4")
            )
        )
}
