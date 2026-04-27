package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise10Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-10",
            title = "Query and Headers",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Control endpoint behavior with query parameters, headers, and optional cookie context.",
            preconditionExerciseIds = listOf("basic-exercise-9"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-9")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-10-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Build request variants using params and headers.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-10.feature", template = "..."),
                        TrainingActivity.CodeTask("Add param and header blocks.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-10.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-10.feature", snippet = "And param")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-10.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-10.feature", "And param")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-10-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement scenarios that prove metadata-driven behavior.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Assert response changes with different metadata."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-10.feature", snippet = "And param"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-10.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-10.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-10.feature", "And param")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-10-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify both positive and negative variants.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-10-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-10.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-10-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-10.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-10-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Document metadata conventions for later exercises.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Normalize header/query naming patterns."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-10.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-10", "step-basic-exercise-10-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-10.feature")
                    )
                )
            ),
            expectedOutcome = "A set of scenarios that validate query/header-driven endpoint behavior.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-10: Query and Headers",
                heading = "Query and Headers",
                subtitle = "Project capability: Drive API behavior with request metadata",
                chips = listOf("query", "headers", "cookies", "metadata-driven behavior"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-10.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Query and Headers",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-10", primaryAsset = "src/test/resources/features/basic-exercise-10.feature", prerequisiteId = "basic-exercise-9")
            )
        )
}
