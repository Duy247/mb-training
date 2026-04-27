package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise3Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-3",
            title = "Efficient Setup with callonce",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Optimize repeated setup by applying callonce where one-time execution is safe.",
            preconditionExerciseIds = listOf("intermediate-exercise-2"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-2")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-3-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create or refactor one-time bootstrap feature.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/common/bootstrap.feature", template = "..."),
                        TrainingActivity.CodeTask("Isolate expensive setup into bootstrap feature.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/common/bootstrap.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/bootstrap.feature", snippet = "callonce")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/common/bootstrap.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/common/bootstrap.feature", "callonce")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-3-02-implement",
                    title = "Implement core behavior",
                    guidance = "Apply callonce in safe setup points.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Use callonce in caller flow."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/bootstrap.feature", snippet = "callonce"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/common/bootstrap.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/common/bootstrap.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/common/bootstrap.feature", "callonce")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-3-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify setup executes only as intended.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "intermediate-exercise-3-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/bootstrap.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-3-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/common/bootstrap.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-3-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Document where callonce should and should not be used.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Avoid applying callonce to mutable scenario-specific data."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/bootstrap.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-3", "step-intermediate-exercise-3-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/common/bootstrap.feature")
                    )
                )
            ),
            expectedOutcome = "A predictable one-time setup pattern that improves test efficiency.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-3: Efficient Setup with callonce",
                heading = "Efficient Setup with callonce",
                subtitle = "Project capability: Optimize setup execution safely",
                chips = listOf("callonce", "performance", "setup", "isolation"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/common/bootstrap.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Efficient Setup with callonce",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-3", primaryAsset = "src/test/resources/features/common/bootstrap.feature", prerequisiteId = "intermediate-exercise-2")
            )
        )
}
