package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise3Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-3",
            title = "Runner Path Fix (Scenario)",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.SCENARIO,
            objective = "Diagnose and fix a broken runner classpath in a scenario sandbox so tests execute successfully.",
            preconditionExerciseIds = listOf("basic-exercise-2"),
            startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-2")),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-3-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Open scenario workspace and inspect failing runner setup.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "src/test/java/com/mb/training/scenario/TestRunner.java", template = "..."),
                        TrainingActivity.CodeTask("Set up sandbox and locate TestRunner.java.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "src/test/java/com/mb/training/scenario/TestRunner.java"),
                        TrainingHint.ContentHint(filePath = "src/test/java/com/mb/training/scenario/TestRunner.java", snippet = "classpath:features/")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/java/com/mb/training/scenario/TestRunner.java"),
                        TrainingCondition.FileContains("src/test/java/com/mb/training/scenario/TestRunner.java", "classpath:features/")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-3-02-implement",
                    title = "Implement core behavior",
                    guidance = "Fix incorrect classpath in runner method.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Replace wrong feature path with correct classpath reference."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "src/test/java/com/mb/training/scenario/TestRunner.java", snippet = "classpath:features/"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "src/test/java/com/mb/training/scenario/TestRunner.java")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("src/test/java/com/mb/training/scenario/TestRunner.java"),
                        TrainingCondition.FileContains("src/test/java/com/mb/training/scenario/TestRunner.java", "classpath:features/")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-3-03-verify",
                    title = "Run and verify",
                    guidance = "Run targeted test to verify correction.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test -Dtest=TestRunner", commandId = "basic-exercise-3-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test -Dtest=TestRunner"),
                        TrainingHint.ContentHint(filePath = "src/test/java/com/mb/training/scenario/TestRunner.java", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-3-verify"),
                        TrainingCondition.FileExists("src/test/java/com/mb/training/scenario/TestRunner.java")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-3-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Capture fix pattern for future runner troubleshooting.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Ensure runner code is clean and explicit."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "src/test/java/com/mb/training/scenario/TestRunner.java", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-3", "step-basic-exercise-3-03-verify"),
                        TrainingCondition.FileExists("src/test/java/com/mb/training/scenario/TestRunner.java")
                    )
                )
            ),
            expectedOutcome = "Scenario runner executes successfully after classpath correction.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-3: Runner Path Fix (Scenario)",
                heading = "Runner Path Fix (Scenario)",
                subtitle = "Project capability: Fix runner classpath in sandbox",
                chips = listOf("Runner", "Classpath", "Debug", "Scenario"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ src/test/java/com/mb/training/scenario/TestRunner.java\n      ├─ src/test/resources/features/scenario-runner-path-fix.feature",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Runner Path Fix (Scenario)",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-3", primaryAsset = "src/test/java/com/mb/training/scenario/TestRunner.java", prerequisiteId = "basic-exercise-2")
            )
        )
}
