package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object AdvancedExercise4Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "advanced-exercise-4",
            title = "Negative and Error Matrix",
            level = TrainingLevel.ADVANCED,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Create structured negative/error coverage across validation, auth, and server-side failure classes.",
            preconditionExerciseIds = listOf("advanced-exercise-3"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("advanced-exercise-3")),
            steps = listOf(
                TrainingStep(
                    id = "step-advanced-exercise-4-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Design error matrix data structure.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/advanced-exercise-4.feature", template = "..."),
                        TrainingActivity.CodeTask("Add negative matrix JSON definitions.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/advanced-exercise-4.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-4.feature", snippet = "400")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-4.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-4.feature", "400")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-4-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement scenario execution across matrix cases.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Map matrix entries to requests/assertions."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-4.feature", snippet = "400"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/data/negative-matrix.json")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-4.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-4.feature", "400")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-4-03-verify",
                    title = "Run and verify",
                    guidance = "Run and validate expected error contracts.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.env=mock", commandId = "advanced-exercise-4-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.env=mock"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-4.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("advanced-exercise-4-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-4.feature")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-4-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Refine strictness to prevent accidental passes.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Harden assertions for code/message consistency."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-4.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("advanced-exercise-4", "step-advanced-exercise-4-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-4.feature")
                    )
                )
            ),
            expectedOutcome = "A maintainable negative-test matrix with strict error contract checks.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "advanced-exercise-4: Negative and Error Matrix",
                heading = "Negative and Error Matrix",
                subtitle = "Project capability: Systematically validate error behavior",
                chips = listOf("negative testing", "error matrix", "validation", "robustness"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/advanced-exercise-4.feature\n      ├─ src/test/resources/data/negative-matrix.json",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Negative and Error Matrix",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "advanced-exercise-4", primaryAsset = "src/test/resources/features/advanced-exercise-4.feature", prerequisiteId = "advanced-exercise-3")
            )
        )
}
