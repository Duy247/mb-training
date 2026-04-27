package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object AdvancedExercise1Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "advanced-exercise-1",
            title = "Authentication Workflow",
            level = TrainingLevel.ADVANCED,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Validate complete authentication lifecycle including login, token use, and refresh behavior.",
            preconditionExerciseIds = listOf("intermediate-exercise-13"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("intermediate-exercise-13")),
            steps = listOf(
                TrainingStep(
                    id = "step-advanced-exercise-1-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Design end-to-end auth lifecycle scenarios.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/resources/features/advanced-exercise-1.feature", template = "..."),
                        TrainingActivity.CodeTask("Add auth helper feature and main workflow feature.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/resources/features/advanced-exercise-1.feature"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-1.feature", snippet = "Authorization")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-1.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-1.feature", "Authorization")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-1-02-implement",
                    title = "Implement core behavior",
                    guidance = "Implement token acquisition/use/refresh behavior.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Wire token sharing across steps safely."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-1.feature", snippet = "Authorization"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/resources/features/common/auth.feature")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-1.feature"),
                        TrainingCondition.FileContains("src/test/resources/features/advanced-exercise-1.feature", "Authorization")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-1-03-verify",
                    title = "Run and verify",
                    guidance = "Run and verify auth behavior in mock mode.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dkarate.env=mock", commandId = "advanced-exercise-1-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dkarate.env=mock"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-1.feature", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("advanced-exercise-1-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-1.feature")
                    )
                ),
                TrainingStep(
                    id = "step-advanced-exercise-1-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Harden auth helper reusability.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Keep auth helper APIs consistent."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/resources/features/advanced-exercise-1.feature", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("advanced-exercise-1", "step-advanced-exercise-1-03-verify"),
                        TrainingCondition.FileExists("src/test/resources/features/advanced-exercise-1.feature")
                    )
                )
            ),
            expectedOutcome = "A reusable auth workflow validated across protected endpoint scenarios.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "advanced-exercise-1: Authentication Workflow",
                heading = "Authentication Workflow",
                subtitle = "Project capability: Test full authentication lifecycle",
                chips = listOf("authentication", "token lifecycle", "refresh", "protected APIs"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/resources/features/advanced-exercise-1.feature\n      ├─ src/test/resources/features/common/auth.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Authentication Workflow",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "advanced-exercise-1", primaryAsset = "src/test/resources/features/advanced-exercise-1.feature", prerequisiteId = "intermediate-exercise-13")
            )
        )
}
