package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object AdvancedExercise3Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "advanced-exercise-3",
            title = "Async Processing Flow",
            level = TrainingLevel.ADVANCED,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Validate asynchronous API operations using polling with retry-until semantics.",
            preconditionExerciseIds = listOf("advanced-exercise-2"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("advanced-exercise-2")),
            steps = listOf(
                TrainingStep(
                    id = "step-advanced-exercise-3-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create async submission + polling scenario.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/advanced-exercise-3.feature", template = "..."),
                        TrainingActivity.CodeTask("Submit job and capture job identifier.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/advanced-exercise-3.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-3.feature", snippet = "retry until")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-3.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-3.feature", "retry until")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-3-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement retry-until logic with sane limits.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Poll status endpoint until done condition."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-3.feature", snippet = "retry until"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/advanced-exercise-3.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-3.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-3.feature", "retry until")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-3-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify eventual-completion behavior.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.env=mock", commandId = "advanced-exercise-3-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.env=mock"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-3.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("advanced-exercise-3-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-3.feature")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-3-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Tune retry strategy for deterministic CI runs.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Adjust retry count/interval to avoid flakiness."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-3.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("advanced-exercise-3", "step-advanced-exercise-3-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-3.feature")
                    )
                )
            ),
            expectedOutcome = "A deterministic async test flow with controlled polling and completion assertions.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "advanced-exercise-3: Async Processing Flow",
                heading = "Async Processing Flow",
                subtitle = "Project capability: Validate asynchronous processing APIs",
                chips = listOf("async", "polling", "retry until", "eventual consistency"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/advanced-exercise-3.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Async Processing Flow",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "advanced-exercise-3", primaryAsset = "src/test/resources/features/advanced-exercise-3.feature", prerequisiteId = "advanced-exercise-2")
            )
        )
}
