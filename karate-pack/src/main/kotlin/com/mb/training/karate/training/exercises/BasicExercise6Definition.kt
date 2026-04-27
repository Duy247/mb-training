package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise6Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-6",
            title = "Variables and Expressions",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Use variables and expressions to make API scenarios dynamic and less repetitive.",
            preconditionExerciseIds = listOf("basic-exercise-5"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-5")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-6-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create dynamic test script with variable definitions.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/basic-exercise-6.feature", template = "..."),
                        TrainingActivity.CodeTask("Define reusable variables near scenario start.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/basic-exercise-6.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-6.feature", snippet = "* def")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-6.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-6.feature", "* def")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-6-02-implement",
                    title = "Implement core behavior",
                    guidance = "Apply interpolation and expression logic in request/assertion.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Use variables in path/query/body and matches."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-6.feature", snippet = "* def"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/basic-exercise-6.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-6.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/basic-exercise-6.feature", "* def")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-6-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify dynamic behavior remains stable.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-6-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-6.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-6-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-6.feature")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-6-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Harden variable naming and scoping discipline.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Rename unclear vars and remove duplicated literals."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/basic-exercise-6.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-6", "step-basic-exercise-6-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/basic-exercise-6.feature")
                    )
                )
            ),
            expectedOutcome = "A cleaner scenario that uses variables and expressions to avoid repetition.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-6: Variables and Expressions",
                heading = "Variables and Expressions",
                subtitle = "Project capability: Make scripts dynamic with variables",
                chips = listOf("def", "variables", "interpolation", "expressions"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/basic-exercise-6.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Variables and Expressions",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-6", primaryAsset = "src/test/resources/features/basic-exercise-6.feature", prerequisiteId = "basic-exercise-5")
            )
        )
}
