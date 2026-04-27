package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise12Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-12",
            title = "Mini Checkpoint Mission",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Combine all basic skills into a mini end-to-end API mission inside the same project.",
            preconditionExerciseIds = listOf("basic-exercise-11"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-11")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-12-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Design a mini flow scenario sequence for checkpoint.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-12.feature", template = "..."),
                        TrainingActivity.CodeTask("Create checkpoint feature with multi-step mission.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-12.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-12.feature", snippet = "Scenario:")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-12.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-12.feature", "Scenario:")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-12-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement chained steps with shared context data.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Chain create/read/update-like actions in one storyline."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-12.feature", snippet = "Scenario:"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-12.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-12.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-12.feature", "Scenario:")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-12-03-verify",
                    title = "Run and verify",
                    guidance = "Run full checkpoint and verify all assertions.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-12-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-12.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-12-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-12.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-12-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Refine flow structure for transition to intermediate level.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Clean scenario naming and split helper pieces if needed."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-12.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-12", "step-basic-exercise-12-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-12.feature")
                    )
                )
            ),
            expectedOutcome = "A functional mini-flow feature proving readiness for intermediate phase.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-12: Mini Checkpoint Mission",
                heading = "Mini Checkpoint Mission",
                subtitle = "Project capability: Integrate basic skills into one mission",
                chips = listOf("checkpoint", "mini flow", "integration", "reuse"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-12.feature\n      ├─ src/test/resources/features/common/",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Mini Checkpoint Mission",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-12", primaryAsset = "src/test/resources/features/basic-exercise-12.feature", prerequisiteId = "basic-exercise-11")
            )
        )
}
