package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise7Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-7",
            title = "Tags and Execution Slices",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Organize scenarios by tags for smoke/regression/environment-specific execution slices.",
            preconditionExerciseIds = listOf("intermediate-exercise-6"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-6")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-7-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Define and apply tag conventions in feature files.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/intermediate-exercise-7.feature", template = "..."),
                        TrainingActivity.CodeTask("Tag scenarios with @smoke, @regression, etc.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/intermediate-exercise-7.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-7.feature", snippet = "@smoke")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-7.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-7.feature", "@smoke")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-7-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement runner strategy for tag filters.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Create or update runner with tag options."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-7.feature", snippet = "@smoke"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/intermediate-exercise-7.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-7.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-7.feature", "@smoke")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-7-03-verify",
                    title = "Run and verify",
                    guidance = "Run smoke/regression slices to verify grouping.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.options=\"--tags @smoke\"", commandId = "intermediate-exercise-7-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.options=\"--tags @smoke\""),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-7.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-7-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-7.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-7-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Document tag usage standards for project team.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Normalize tag naming and avoid overlaps."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-7.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-7", "step-intermediate-exercise-7-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-7.feature")
                    )
                )
            ),
            expectedOutcome = "A tagged suite that can run targeted subsets deterministically.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-7: Tags and Execution Slices",
                heading = "Tags and Execution Slices",
                subtitle = "Project capability: Slice suite execution with tags",
                chips = listOf("tags", "smoke", "regression", "selective execution"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/intermediate-exercise-7.feature\n      ├─ src/test/java/com/mb/training/runner/TagRunner.java",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Tags and Execution Slices",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-7", primaryAsset = "src/test/resources/features/intermediate-exercise-7.feature", prerequisiteId = "intermediate-exercise-6")
            )
        )
}
