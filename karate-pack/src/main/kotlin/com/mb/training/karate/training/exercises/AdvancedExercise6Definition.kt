package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object AdvancedExercise6Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "advanced-exercise-6",
            title = "Capstone Whole API Flow",
            level = TrainingLevel.ADVANCED,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Assemble a full CI-ready end-to-end API flow suite runnable on local mock and optional real environment.",
            preconditionExerciseIds = listOf("advanced-exercise-5"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("advanced-exercise-5")),
            steps = listOf(
                TrainingStep(
                    id = "step-advanced-exercise-6-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Design and assemble capstone flow modules.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/capstone/", template = "..."),
                        TrainingActivity.CodeTask("Create capstone feature package and runner.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/capstone/"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/capstone/", snippet = "capstone")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/capstone/"),
                        TrainingCondition.FileContains("src/test/resources/features/capstone/", "capstone")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-6-02-implement",
                    title = "Implement core behavior",
                    guidance = "Integrate auth + CRUD + async + negative/security checks.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Wire all reusable modules into unified flow."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/capstone/", snippet = "capstone"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/capstone/")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/capstone/"),
                        TrainingCondition.FileContains("src/test/resources/features/capstone/", "capstone")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-6-03-verify",
                    title = "Run and verify",
                    guidance = "Run full capstone suite and validate reporting outputs.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.env=mock", commandId = "advanced-exercise-6-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.env=mock"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/capstone/", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("advanced-exercise-6-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/capstone/")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-6-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Finalize project structure/documentation for handoff.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Ensure artifacts are CI-ready and readable."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/capstone/", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("advanced-exercise-6", "step-advanced-exercise-6-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/capstone/")
                    )
                )
            ),
            expectedOutcome = "A complete API testing project runnable locally (mock) and adaptable for integration environments.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "advanced-exercise-6: Capstone Whole API Flow",
                heading = "Capstone Whole API Flow",
                subtitle = "Project capability: Deliver complete functional API project",
                chips = listOf("capstone", "end-to-end", "CI-ready", "functional project"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/capstone/\n      ├─ src/test/java/com/mb/training/runner/CapstoneRunner.java",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Capstone Whole API Flow",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "advanced-exercise-6", primaryAsset = "src/test/resources/features/capstone/", prerequisiteId = "advanced-exercise-5")
            )
        )
}
