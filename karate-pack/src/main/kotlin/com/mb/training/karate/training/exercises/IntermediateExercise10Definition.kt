package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object IntermediateExercise10Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "intermediate-exercise-10",
            title = "Contract Assertions",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Strengthen response contract validation using nested and collection-wide assertions.",
            preconditionExerciseIds = listOf("intermediate-exercise-9"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-9")),
            steps = listOf(
                TrainingStep(
                    id = "step-intermediate-exercise-10-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Create contract-focused feature and data assets.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/intermediate-exercise-10.feature", template = "..."),
                        TrainingActivity.CodeTask("Add contract fixtures/snippets under data/contracts.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/intermediate-exercise-10.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-10.feature", snippet = "match each")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-10.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-10.feature", "match each")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-10-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement nested and list-wide contract assertions.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Apply match each and nested assertions."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-10.feature", snippet = "match each"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/intermediate-exercise-10.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-10.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/intermediate-exercise-10.feature", "match each")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-10-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify strictness without flakiness.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "intermediate-exercise-10-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-10.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("intermediate-exercise-10-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-10.feature")
                    )
                ),
                TrainingStep(
                    id = "step-intermediate-exercise-10-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Document reusable contract snippets.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Refactor contract blocks for reuse."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/intermediate-exercise-10.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("intermediate-exercise-10", "step-intermediate-exercise-10-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/intermediate-exercise-10.feature")
                    )
                )
            ),
            expectedOutcome = "A reusable contract-validation layer for endpoint responses.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "intermediate-exercise-10: Contract Assertions",
                heading = "Contract Assertions",
                subtitle = "Project capability: Establish stronger response contracts",
                chips = listOf("contract", "match each", "nested checks", "schema"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/intermediate-exercise-10.feature\n      ├─ src/test/resources/data/contracts/",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Contract Assertions",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "intermediate-exercise-10", primaryAsset = "src/test/resources/features/intermediate-exercise-10.feature", prerequisiteId = "intermediate-exercise-9")
            )
        )
}
