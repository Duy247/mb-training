package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object AdvancedExercise2Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "advanced-exercise-2",
            title = "Stateful CRUD Business Journey",
            level = TrainingLevel.ADVANCED,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Implement realistic business entity lifecycle testing across create, read, update, and delete operations.",
            preconditionExerciseIds = listOf("advanced-exercise-1"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("advanced-exercise-1")),
            steps = listOf(
                TrainingStep(
                    id = "step-advanced-exercise-2-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Design full CRUD journey scenario.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/advanced-exercise-2.feature", template = "..."),
                        TrainingActivity.CodeTask("Capture entity ID from create response.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/advanced-exercise-2.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-2.feature", snippet = "method post")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-2.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-2.feature", "method post")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-2-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement chained create/read/update/delete steps.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Use ID in read/update/delete operations."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-2.feature", snippet = "method post"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/advanced-exercise-2.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-2.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-2.feature", "method post")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-2-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify state transition correctness.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.env=mock", commandId = "advanced-exercise-2-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.env=mock"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-2.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("advanced-exercise-2-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-2.feature")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-2-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Stabilize journey assertions for reuse in capstone.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Add idempotent cleanup assertion."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-2.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("advanced-exercise-2", "step-advanced-exercise-2-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-2.feature")
                    )
                )
            ),
            expectedOutcome = "A reliable entity lifecycle flow with strong state-transition assertions.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "advanced-exercise-2: Stateful CRUD Business Journey",
                heading = "Stateful CRUD Business Journey",
                subtitle = "Project capability: Validate end-to-end CRUD journey",
                chips = listOf("CRUD", "state transitions", "entity lifecycle", "business flow"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/advanced-exercise-2.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Stateful CRUD Business Journey",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "advanced-exercise-2", primaryAsset = "src/test/resources/features/advanced-exercise-2.feature", prerequisiteId = "advanced-exercise-1")
            )
        )
}
