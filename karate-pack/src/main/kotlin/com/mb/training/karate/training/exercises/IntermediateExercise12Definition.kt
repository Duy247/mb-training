package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise12Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-12",
            title = "Stateful Mock Behavior",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Add stateful behavior to mock API for realistic CRUD/auth workflows.",
            preconditionExerciseIds = listOf("intermediate-exercise-11"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-11")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-12-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Extend mock routes to maintain state transitions.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/mock/mock-api.feature", template = "..."),
                        TrainingActivity.CodeTask("Create in-memory store helpers.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/mock/mock-api.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/mock/mock-api.feature", snippet = "def store")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/mock/mock-api.feature"),
                        TrainingCondition.FileContains("src/test/resources/mock/mock-api.feature", "def store")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-12-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement auth and CRUD behavior in mock.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Wire store operations into routes."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/mock/mock-api.feature", snippet = "def store"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/mock/state-utils.js")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/mock/mock-api.feature"),
                        TrainingCondition.FileContains("src/test/resources/mock/mock-api.feature", "def store")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-12-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify stateful operations deterministically.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dtest=MockServerRunner", commandId = "intermediate-exercise-12-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dtest=MockServerRunner"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/mock/mock-api.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-12-verify"),
                        TrainingCondition.FileExists("src/test/resources/mock/mock-api.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-12-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Add clean state-reset strategy.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Reset state per run/suite to avoid drift."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/mock/mock-api.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-12", "step-intermediate-exercise-12-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/mock/mock-api.feature")
                    )
                )
            ),
            expectedOutcome = "A stateful local mock API supporting realistic CRUD and auth test flows.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-12: Stateful Mock Behavior",
                heading = "Stateful Mock Behavior",
                subtitle = "Project capability: Make mock API behavior realistic",
                chips = listOf("stateful mock", "CRUD", "auth", "in-memory state"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/mock/mock-api.feature\n      ├─ src/test/resources/mock/state-utils.js",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Stateful Mock Behavior",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-12", primaryAsset = "src/test/resources/mock/mock-api.feature", prerequisiteId = "intermediate-exercise-11")
            )
        )
}
