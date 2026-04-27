package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise8Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-8",
            title = "Parallel-Safe Design",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Refactor tests to run safely in parallel without shared-state collisions.",
            preconditionExerciseIds = listOf("intermediate-exercise-7"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-7")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-8-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Audit scenarios for shared-state problems.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/intermediate-exercise-8.feature", template = "..."),
                        TrainingActivity.CodeTask("Detect and remove shared mutable dependencies.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/intermediate-exercise-8.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-8.feature", snippet = "parallel")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-8.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-8.feature", "parallel")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-8-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement data isolation and parallel-safe patterns.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Use unique IDs/data per scenario."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-8.feature", snippet = "parallel"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/java/com/mb/training/runner/ParallelRunner.java")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-8.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-8.feature", "parallel")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-8-03-verify",
                    title = "Run and verify",
                    guidance = "Run in parallel and verify stability.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "intermediate-exercise-8-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-8.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-8-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-8.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-8-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Harden flaky spots until deterministic.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Refactor fragile sections to deterministic patterns."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-8.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-8", "step-intermediate-exercise-8-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-8.feature")
                    )
                )
            ),
            expectedOutcome = "A suite segment that runs in parallel with stable, repeatable outcomes.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-8: Parallel-Safe Design",
                heading = "Parallel-Safe Design",
                subtitle = "Project capability: Make suite parallel-safe",
                chips = listOf("parallel", "isolation", "stability", "CI speed"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/intermediate-exercise-8.feature\n      ├─ src/test/java/com/mb/training/runner/ParallelRunner.java",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Parallel-Safe Design",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-8", primaryAsset = "src/test/resources/features/intermediate-exercise-8.feature", prerequisiteId = "intermediate-exercise-7")
            )
        )
}
