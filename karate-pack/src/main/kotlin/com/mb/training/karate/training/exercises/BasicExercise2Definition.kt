package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise2Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-2",
            title = "First Feature Script",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Create the first valid Karate feature file using Feature, Background, and Scenario blocks with simple executable DSL.",
            preconditionExerciseIds = listOf("basic-exercise-1"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-1")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-2-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create the first feature file in the expected path.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-2.feature", template = "..."),
                        TrainingActivity.CodeTask("Add basic-exercise-2.feature under src/test/resources/features.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-2.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-2.feature", snippet = "Scenario:")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-2.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Scenario:")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-2-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement valid Feature/Background/Scenario sections.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Write simple Background and Scenario with print."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-2.feature", snippet = "Scenario:"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-2.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-2.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Scenario:")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-2-03-verify",
                    title = "Run and verify",
                    guidance = "Run tests to confirm feature parsing/execution.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-2-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-2.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-2-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-2.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-2-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Polish script readability for reuse as syntax reference.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Keep indentation and naming clear for later copy/reuse."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-2.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-2", "step-basic-exercise-2-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-2.feature")
                    )
                )
            ),
            expectedOutcome = "A valid and runnable first Karate feature file in project classpath.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-2: First Feature Script",
                heading = "First Feature Script",
                subtitle = "Project capability: Create your first executable Karate feature",
                chips = listOf("Feature", "Background", "Scenario", "Print"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-2.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: First Feature Script",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-2", primaryAsset = "src/test/resources/features/basic-exercise-2.feature", prerequisiteId = "basic-exercise-1")
            )
        )
}
