package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise7Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-7",
            title = "Response Handling",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Validate response metadata such as status, headers, and response time for richer API checks.",
            preconditionExerciseIds = listOf("basic-exercise-6"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-6")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-7-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create response-handling focused feature scenario.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-7.feature", template = "..."),
                        TrainingActivity.CodeTask("Capture responseStatus/responseHeaders/responseTime.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-7.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-7.feature", snippet = "responseHeaders")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-7.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-7.feature", "responseHeaders")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-7-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement header/time/status assertions.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Add critical header and SLA-lite checks."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-7.feature", snippet = "responseHeaders"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-7.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-7.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-7.feature", "responseHeaders")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-7-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify metadata checks.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-7-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-7.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-7-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-7.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-7-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Tune thresholds and assertions for deterministic stability.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Adjust brittle thresholds where necessary."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-7.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-7", "step-basic-exercise-7-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-7.feature")
                    )
                )
            ),
            expectedOutcome = "A scenario that validates API metadata and basic timing expectations.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-7: Response Handling",
                heading = "Response Handling",
                subtitle = "Project capability: Validate response metadata",
                chips = listOf("responseStatus", "responseHeaders", "responseTime", "assertions"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-7.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Response Handling",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-7", primaryAsset = "src/test/resources/features/basic-exercise-7.feature", prerequisiteId = "basic-exercise-6")
            )
        )
}
