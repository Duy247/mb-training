package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise9Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-9",
            title = "Fuzzy Validation",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Use fuzzy markers and type-oriented checks for dynamic values in response payloads.",
            preconditionExerciseIds = listOf("basic-exercise-8"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-8")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-9-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create schema-like validation scenarios with fuzzy markers.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-9.feature", template = "..."),
                        TrainingActivity.CodeTask("Define expected fuzzy schema in feature.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-9.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-9.feature", snippet = "#string")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-9.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-9.feature", "#string")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-9-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement type and shape assertions for dynamic payloads.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Apply fuzzy checks to real response payloads."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-9.feature", snippet = "#string"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-9.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-9.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-9.feature", "#string")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-9-03-verify",
                    title = "Run and verify",
                    guidance = "Run and validate robustness against changing values.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-9-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-9.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-9-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-9.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-9-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Harden fuzzy rules to avoid over-permissive checks.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Tighten checks where validation is too loose."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-9.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-9", "step-basic-exercise-9-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-9.feature")
                    )
                )
            ),
            expectedOutcome = "A resilient validation style that handles dynamic payload values safely.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-9: Fuzzy Validation",
                heading = "Fuzzy Validation",
                subtitle = "Project capability: Stabilize validations for dynamic responses",
                chips = listOf("fuzzy match", "types", "dynamic data", "schema-like checks"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-9.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Fuzzy Validation",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-9", primaryAsset = "src/test/resources/features/basic-exercise-9.feature", prerequisiteId = "basic-exercise-8")
            )
        )
}
