package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise4Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-4",
            title = "Global One-Time Setup with callSingle",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Use karate.callSingle() for global initialization shared across feature files.",
            preconditionExerciseIds = listOf("intermediate-exercise-3"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-3")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-4-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create global init feature and callSingle wiring.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/common/global-init.feature", template = "..."),
                        TrainingActivity.CodeTask("Add global-init.feature with bootstrap actions.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/common/global-init.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/global-init.feature", snippet = "karate.callSingle")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/common/global-init.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/common/global-init.feature", "karate.callSingle")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-4-02-implement",
                    title = "Implement core behavior",
                    guidance = "Integrate global data into runtime config.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Wire karate.callSingle() in karate-config.js."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/global-init.feature", snippet = "karate.callSingle"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/java/karate-config.js")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/common/global-init.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/common/global-init.feature", "karate.callSingle")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-4-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify one-time global initialization.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "intermediate-exercise-4-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/global-init.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-4-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/common/global-init.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-4-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Stabilize returned data contract for downstream usage.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Keep bootstrap payload minimal and deterministic."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/global-init.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-4", "step-intermediate-exercise-4-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/common/global-init.feature")
                    )
                )
            ),
            expectedOutcome = "A reliable global bootstrap mechanism available across feature suites.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-4: Global One-Time Setup with callSingle",
                heading = "Global One-Time Setup with callSingle",
                subtitle = "Project capability: Establish global one-time initialization",
                chips = listOf("callSingle", "global init", "config", "bootstrap"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/common/global-init.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Global One-Time Setup with callSingle",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-4", primaryAsset = "src/test/resources/features/common/global-init.feature", prerequisiteId = "intermediate-exercise-3")
            )
        )
}
