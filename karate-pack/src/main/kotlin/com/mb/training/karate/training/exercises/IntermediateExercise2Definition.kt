package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise2Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-2",
            title = "Shared Setup Feature",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Extract repeated setup logic into reusable called features.",
            preconditionExerciseIds = listOf("intermediate-exercise-1"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-1")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-2-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create common setup feature artifact.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/common/setup.feature", template = "..."),
                        TrainingActivity.CodeTask("Add common/setup.feature.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/common/setup.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/setup.feature", snippet = "call read(")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/common/setup.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/common/setup.feature", "call read(")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-2-02-implement",
                    title = "Implement core behavior",
                    guidance = "Replace duplicated setup with feature call pattern.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Refactor scenarios to use call read()."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/setup.feature", snippet = "call read("),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/common/setup.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/common/setup.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/common/setup.feature", "call read(")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-2-03-verify",
                    title = "Run and verify",
                    guidance = "Run suites to validate scope and behavior.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "intermediate-exercise-2-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/setup.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-2-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/common/setup.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-2-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Harden shared setup contract for later exercises.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Keep setup return structure explicit."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/common/setup.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-2", "step-intermediate-exercise-2-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/common/setup.feature")
                    )
                )
            ),
            expectedOutcome = "A common setup feature consumed by multiple scenarios.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-2: Shared Setup Feature",
                heading = "Shared Setup Feature",
                subtitle = "Project capability: Extract reusable setup feature",
                chips = listOf("reuse", "call read", "common setup", "maintainability"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/common/setup.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Shared Setup Feature",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-2", primaryAsset = "src/test/resources/features/common/setup.feature", prerequisiteId = "intermediate-exercise-1")
            )
        )
}
