package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise6Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-6",
            title = "External Datasets",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Drive scenario execution from external JSON/CSV datasets for broader, maintainable coverage.",
            preconditionExerciseIds = listOf("intermediate-exercise-5"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-5")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-6-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create external dataset and feature consumer.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/data/intermediate-exercise-6.json", template = "..."),
                        TrainingActivity.CodeTask("Add JSON/CSV dataset under resources.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/data/intermediate-exercise-6.json"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/data/intermediate-exercise-6.json", snippet = "read(")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/data/intermediate-exercise-6.json"),
                        TrainingCondition.FileContains("src/test/resources/data/intermediate-exercise-6.json", "read(")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-6-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement data loading and iteration mapping.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Read dataset and map rows in scenario."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/data/intermediate-exercise-6.json", snippet = "read("),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/data/intermediate-exercise-6.json")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/data/intermediate-exercise-6.json"),
                        TrainingCondition.FileContains("src/test/resources/data/intermediate-exercise-6.json", "read(")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-6-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify dataset-driven outcomes.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "intermediate-exercise-6-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/data/intermediate-exercise-6.json", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-6-verify"),
                        TrainingCondition.FileExists("src/test/resources/data/intermediate-exercise-6.json")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-6-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Organize dataset structure for long-term maintainability.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Separate stable fixtures from temporary data."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/data/intermediate-exercise-6.json", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-6", "step-intermediate-exercise-6-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/data/intermediate-exercise-6.json")
                    )
                )
            ),
            expectedOutcome = "A feature that executes against external datasets with clear data/logic separation.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-6: External Datasets",
                heading = "External Datasets",
                subtitle = "Project capability: Externalize test datasets",
                chips = listOf("external data", "JSON/CSV", "read", "data separation"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/data/intermediate-exercise-6.json\n      ├─ src/test/resources/features/intermediate-exercise-6.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: External Datasets",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-6", primaryAsset = "src/test/resources/data/intermediate-exercise-6.json", prerequisiteId = "intermediate-exercise-5")
            )
        )
}
