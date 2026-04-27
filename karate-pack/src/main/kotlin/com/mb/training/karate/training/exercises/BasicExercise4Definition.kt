package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise4Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-4",
            title = "Core Request Flow",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Implement a basic API GET flow using url, path, method, and status assertions.",
            preconditionExerciseIds = listOf("basic-exercise-3"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-3")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-4-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create dedicated feature for first GET endpoint flow.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-4.feature", template = "..."),
                        TrainingActivity.CodeTask("Create basic-exercise-4.feature.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-4.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-4.feature", snippet = "Given url")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-4.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-4.feature", "Given url")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-4-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement request steps with url/path/method/status.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Write GET scenario with status assertions."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-4.feature", snippet = "Given url"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-4.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-4.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-4.feature", "Given url")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-4-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify endpoint interaction.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-4-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-4.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-4-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-4.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-4-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Refine assertions for stability and readability.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Keep request blocks reusable for later endpoints."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-4.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-4", "step-basic-exercise-4-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-4.feature")
                    )
                )
            ),
            expectedOutcome = "A passing GET API scenario with clear request and status validation.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-4: Core Request Flow",
                heading = "Core Request Flow",
                subtitle = "Project capability: Implement first API request flow",
                chips = listOf("HTTP GET", "url/path", "method", "status"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-4.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Core Request Flow",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-4", primaryAsset = "src/test/resources/features/basic-exercise-4.feature", prerequisiteId = "basic-exercise-3")
            )
        )
}
