package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise13Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-13",
            title = "Mock-First Execution Switch",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Enable env-driven switching between local mock target and real integration target.",
            preconditionExerciseIds = listOf("intermediate-exercise-12"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-12")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-13-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Define target resolution by karate.env.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/common/target-resolution.feature", template = "..."),
                        TrainingActivity.CodeTask("Add mock profile in config.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/common/target-resolution.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/target-resolution.feature", snippet = "mock")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/common/target-resolution.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/common/target-resolution.feature", "mock")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-13-02-implement",
                    title = "Implement core behavior",
                    guidance = "Wire existing tests to consume resolved base target.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Centralize target selection logic."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/target-resolution.feature", snippet = "mock"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/java/karate-config.js")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/common/target-resolution.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/common/target-resolution.feature", "mock")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-13-03-verify",
                    title = "Run and verify",
                    guidance = "Run in mock mode and validate full suite path.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.env=mock", commandId = "intermediate-exercise-13-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.env=mock"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/target-resolution.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-13-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/common/target-resolution.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-13-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Preserve and document real env option.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Document switch behavior for contributors."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/target-resolution.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-13", "step-intermediate-exercise-13-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/common/target-resolution.feature")
                    )
                )
            ),
            expectedOutcome = "Existing scenarios execute deterministically against local mock via environment switch.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-13: Mock-First Execution Switch",
                heading = "Mock-First Execution Switch",
                subtitle = "Project capability: Switch suite between mock and real targets",
                chips = listOf("mock-first", "env switch", "target routing", "local execution"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/common/target-resolution.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Mock-First Execution Switch",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-13", primaryAsset = "src/test/resources/features/common/target-resolution.feature", prerequisiteId = "intermediate-exercise-12")
            )
        )
}
