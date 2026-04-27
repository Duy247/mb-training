package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise11Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-11",
            title = "Mock API Bootstrap",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Create and run a local Karate mock API so exercises no longer depend on external/public APIs.",
            preconditionExerciseIds = listOf("intermediate-exercise-10"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-10")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-11-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create initial mock API feature routes.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/mock/mock-api.feature", template = "..."),
                        TrainingActivity.CodeTask("Define basic GET/POST mock routes.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/mock/mock-api.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/mock/mock-api.feature", snippet = "Scenario:")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/mock/mock-api.feature"),
                        TrainingCondition.FileContains("src/test/resources/mock/mock-api.feature", "Scenario:")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-11-02-implement",
                    title = "Implement core behavior",
                    guidance = "Add runner to start mock API locally.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Implement MockServer runner entry point."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/mock/mock-api.feature", snippet = "Scenario:"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/mock/mock-api.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/mock/mock-api.feature"),
                        TrainingCondition.FileContains("src/test/resources/mock/mock-api.feature", "Scenario:")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-11-03-verify",
                    title = "Run and verify",
                    guidance = "Run mock server command and validate route responses.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dtest=MockServerRunner", commandId = "intermediate-exercise-11-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dtest=MockServerRunner"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/mock/mock-api.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-11-verify"),
                        TrainingCondition.FileExists("src/test/resources/mock/mock-api.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-11-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Stabilize route contracts for downstream tests.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Ensure response payloads are deterministic."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/mock/mock-api.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-11", "step-intermediate-exercise-11-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/mock/mock-api.feature")
                    )
                )
            ),
            expectedOutcome = "A runnable local mock API server with stable base routes.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-11: Mock API Bootstrap",
                heading = "Mock API Bootstrap",
                subtitle = "Project capability: Stand up local Karate mock API",
                chips = listOf("mock API", "local server", "routes", "deterministic responses"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/mock/mock-api.feature\n      ├─ src/test/java/com/mb/training/mock/MockServerRunner.java",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Mock API Bootstrap",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-11", primaryAsset = "src/test/resources/mock/mock-api.feature", prerequisiteId = "intermediate-exercise-10")
            )
        )
}
