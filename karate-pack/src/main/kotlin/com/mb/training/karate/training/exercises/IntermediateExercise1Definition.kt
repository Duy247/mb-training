package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise1Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-1",
            title = "karate-config.js Mastery",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Centralize environment configuration with karate-config.js and runtime karate.env switching.",
            preconditionExerciseIds = listOf("basic-exercise-12"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-12")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-1-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create central configuration file for environments.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/java/karate-config.js", template = "..."),
                        TrainingActivity.CodeTask("Add karate-config.js with default/env blocks.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/java/karate-config.js"),
                        TrainingHint.ContentHint(filePath = "src/test/java/karate-config.js", snippet = "karate.env")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/java/karate-config.js"),
                        TrainingCondition.FileContains("src/test/java/karate-config.js", "karate.env")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-1-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement env-based routing and shared values.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Expose baseUrl and reusable settings."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/java/karate-config.js", snippet = "karate.env"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/java/karate-config.js")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/java/karate-config.js"),
                        TrainingCondition.FileContains("src/test/java/karate-config.js", "karate.env")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-1-03-verify",
                    title = "Run and verify",
                    guidance = "Run with specific env to verify switching.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.env=dev", commandId = "intermediate-exercise-1-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.env=dev"),
                        TrainingHint.ContentHint(filePath = "src/test/java/karate-config.js", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-1-verify"),
                        TrainingCondition.FileExists("src/test/java/karate-config.js")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-1-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Document config conventions for future exercises.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Keep config keys stable and explicit."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/java/karate-config.js", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-1", "step-intermediate-exercise-1-03-verify"),
                        TrainingCondition.FileExists("src/test/java/karate-config.js")
                    )
                )
            ),
            expectedOutcome = "A stable config layer allowing tests to run across environments.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-1: karate-config.js Mastery",
                heading = "karate-config.js Mastery",
                subtitle = "Project capability: Enable environment-driven configuration",
                chips = listOf("config", "karate.env", "baseUrl", "environment"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/java/karate-config.js",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: karate-config.js Mastery",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-1", primaryAsset = "src/test/java/karate-config.js", prerequisiteId = "basic-exercise-12")
            )
        )
}
