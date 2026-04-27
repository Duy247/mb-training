package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise11Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-11",
            title = "Basic Auth Patterns",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Apply basic authentication patterns (token/api-key) to access protected endpoints.",
            preconditionExerciseIds = listOf("basic-exercise-10"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-10")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-11-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Prepare auth data/configuration for protected API usage.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-11.feature", template = "..."),
                        TrainingActivity.CodeTask("Create or load auth payload/config.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-11.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-11.feature", snippet = "Authorization")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-11.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-11.feature", "Authorization")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-11-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement authorized and unauthorized scenarios.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Add Authorization or API-key handling in feature."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-11.feature", snippet = "Authorization"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-11.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-11.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-11.feature", "Authorization")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-11-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify access-control behavior.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-11-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-11.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-11-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-11.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-11-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Refactor auth setup to be reusable in advanced flows.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Extract shared auth pattern for reuse."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-11.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-11", "step-basic-exercise-11-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-11.feature")
                    )
                )
            ),
            expectedOutcome = "A reliable auth test pattern covering positive and negative access cases.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-11: Basic Auth Patterns",
                heading = "Basic Auth Patterns",
                subtitle = "Project capability: Authenticate requests to protected APIs",
                chips = listOf("auth", "token", "API key", "access control"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-11.feature\n      ├─ src/test/resources/data/auth.json",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Basic Auth Patterns",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-11", primaryAsset = "src/test/resources/features/basic-exercise-11.feature", prerequisiteId = "basic-exercise-10")
            )
        )
}
