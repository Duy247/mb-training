package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise9Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-9",
            title = "Hooks and Lifecycle",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Use lifecycle hooks for controlled diagnostics and cleanup behavior.",
            preconditionExerciseIds = listOf("intermediate-exercise-8"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-8")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-9-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Add lifecycle behavior in config and exercise feature.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/intermediate-exercise-9.feature", template = "..."),
                        TrainingActivity.CodeTask("Define afterScenario style callback logic.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/intermediate-exercise-9.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-9.feature", snippet = "afterScenario")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-9.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-9.feature", "afterScenario")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-9-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement diagnostics/cleanup callbacks.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Log key debug context and cleanup safe artifacts."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-9.feature", snippet = "afterScenario"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/java/karate-config.js")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-9.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-9.feature", "afterScenario")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-9-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify hook triggering behavior.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "intermediate-exercise-9-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-9.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-9-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-9.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-9-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Keep hook logic lightweight and deterministic.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Remove noisy or side-effect-prone hook code."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-9.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-9", "step-intermediate-exercise-9-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-9.feature")
                    )
                )
            ),
            expectedOutcome = "Hook-enabled suite with predictable diagnostics and cleanup behavior.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-9: Hooks and Lifecycle",
                heading = "Hooks and Lifecycle",
                subtitle = "Project capability: Control diagnostics and cleanup with hooks",
                chips = listOf("hooks", "lifecycle", "diagnostics", "cleanup"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/intermediate-exercise-9.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Hooks and Lifecycle",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-9", primaryAsset = "src/test/resources/features/intermediate-exercise-9.feature", prerequisiteId = "intermediate-exercise-8")
            )
        )
}
