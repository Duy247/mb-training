package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object AdvancedExercise5Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "advanced-exercise-5",
            title = "Security-Focused API Checks",
            level = TrainingLevel.ADVANCED,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Implement practical API security checks focused on authorization and abuse-path behavior.",
            preconditionExerciseIds = listOf("advanced-exercise-4"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("advanced-exercise-4")),
            steps = listOf(
                TrainingStep(
                    id = "step-advanced-exercise-5-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Design security-focused scenario set.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/advanced-exercise-5.feature", template = "..."),
                        TrainingActivity.CodeTask("Add BOLA/BFLA-like authorization checks.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/advanced-exercise-5.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-5.feature", snippet = "403")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-5.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-5.feature", "403")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-5-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement authorization and abuse-path checks.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Test invalid/over-privileged access attempts."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-5.feature", snippet = "403"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/advanced-exercise-5.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-5.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-5.feature", "403")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-5-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify secure behavior expectations.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.env=mock", commandId = "advanced-exercise-5-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.env=mock"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-5.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("advanced-exercise-5-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-5.feature")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-5-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Stabilize security assertions for CI execution.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Ensure no sensitive data leaks in errors."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-5.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("advanced-exercise-5", "step-advanced-exercise-5-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-5.feature")
                    )
                )
            ),
            expectedOutcome = "A focused security check suite covering key auth/authorization risk areas.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "advanced-exercise-5: Security-Focused API Checks",
                heading = "Security-Focused API Checks",
                subtitle = "Project capability: Validate core API security behaviors",
                chips = listOf("security", "authorization", "OWASP API", "abuse paths"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/advanced-exercise-5.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Security-Focused API Checks",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "advanced-exercise-5", primaryAsset = "src/test/resources/features/advanced-exercise-5.feature", prerequisiteId = "advanced-exercise-4")
            )
        )
}
