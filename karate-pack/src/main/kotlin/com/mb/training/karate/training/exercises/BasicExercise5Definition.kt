package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise5Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-5",
            title = "Request Body Basics",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Send POST requests with JSON bodies and validate response payload content.",
            preconditionExerciseIds = listOf("basic-exercise-4"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-4")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-5-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Prepare JSON payload assets under resources.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-5.feature", template = "..."),
                        TrainingActivity.CodeTask("Create body file basic-request.json.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-5.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-5.feature", snippet = "And request")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-5.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-5.feature", "And request")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-5-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement POST flow with body and response checks.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Send POST and assert created response fields."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-5.feature", snippet = "And request"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/data/basic-request.json")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-5.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-5.feature", "And request")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-5-03-verify",
                    title = "Run and verify",
                    guidance = "Run verification and fix schema/value mismatches.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-5-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-5.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-5-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-5.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-5-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Keep payload structure reusable for later CRUD tests.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Refactor naming/value anchors for future reuse."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-5.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-5", "step-basic-exercise-5-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-5.feature")
                    )
                )
            ),
            expectedOutcome = "A stable POST scenario with request/response content validation.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-5: Request Body Basics",
                heading = "Request Body Basics",
                subtitle = "Project capability: Send and validate request body payloads",
                chips = listOf("POST", "JSON Body", "request", "response"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-5.feature\n      ├─ src/test/resources/data/basic-request.json",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Request Body Basics",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-5", primaryAsset = "src/test/resources/features/basic-exercise-5.feature", prerequisiteId = "basic-exercise-4")
            )
        )
}
