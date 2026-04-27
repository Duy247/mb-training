package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.*

object BasicExercise1Definition {
    val exercise: TrainingExercise = TrainingExercise(
            id = "basic-exercise-1",
            title = "Project Bootstrap",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            mode = TrainingMode.GUIDED,
            objective = "Initialize a Maven Karate project foundation with required structure, dependencies, and baseline run capability.",
            preconditionExerciseIds = emptyList(),
            startWhen = listOf(TrainingCondition.Always),
            steps = listOf(
                TrainingStep(
                    id = "step-basic-exercise-1-01-setup",
                    title = "Prepare exercise assets",
                    guidance = "Set up foundational project files and folder structure.",
                    activities = listOf(
                        TrainingActivity.CreateFile(relativePath = "pom.xml", template = "..."),
                        TrainingActivity.CodeTask("Create src/test/java and src/test/resources/features.")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "file", suggestedPath = "pom.xml"),
                        TrainingHint.ContentHint(filePath = "pom.xml", snippet = "<artifactId>karate-junit5</artifactId>")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("pom.xml"),
                        TrainingCondition.FileContains("pom.xml", "<artifactId>karate-junit5</artifactId>")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-1-02-implement",
                    title = "Implement core behavior",
                    guidance = "Configure Maven dependencies and plugin configuration correctly.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Add karate-junit5 dependency and maven-surefire-plugin."),
                        TrainingActivity.CodeTask("Refactor repeated logic into common location when applicable")
                    ),
                    hints = listOf(
                        TrainingHint.ContentHint(filePath = "pom.xml", snippet = "<artifactId>karate-junit5</artifactId>"),
                        TrainingHint.LocationHint(targetType = "section", suggestedPath = "pom.xml > dependencies/build")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.FileExists("pom.xml"),
                        TrainingCondition.FileContains("pom.xml", "<artifactId>karate-junit5</artifactId>")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-1-03-verify",
                    title = "Run and verify",
                    guidance = "Run Maven to validate bootstrap integrity.",
                    activities = listOf(
                        TrainingActivity.RunTestTask(commandHint = "mvn test", commandId = "basic-exercise-1-verify"),
                        TrainingActivity.CodeTask("Align assertions with deterministic expected behavior")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "command", suggestedPath = "mvn test"),
                        TrainingHint.ContentHint(filePath = "pom.xml", snippet = "status / match assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.CommandPassed("basic-exercise-1-verify"),
                        TrainingCondition.FileExists("pom.xml")
                    )
                ),
                TrainingStep(
                    id = "step-basic-exercise-1-04-harden",
                    title = "Harden for project continuity",
                    guidance = "Clean up naming/content so future exercises build on stable baseline.",
                    activities = listOf(
                        TrainingActivity.CodeTask("Ensure pom.xml and structure remain minimal and reusable."),
                        TrainingActivity.CodeTask("Document assumptions inline where they affect later exercises")
                    ),
                    hints = listOf(
                        TrainingHint.LocationHint(targetType = "project", suggestedPath = "src/test/resources"),
                        TrainingHint.ContentHint(filePath = "pom.xml", snippet = "clear naming + stable assertions")
                    ),
                    doneWhen = listOf(
                        TrainingCondition.StepCompleted("basic-exercise-1", "step-basic-exercise-1-03-verify"),
                        TrainingCondition.FileExists("pom.xml")
                    )
                )
            ),
            expectedOutcome = "A runnable Maven Karate project baseline ready for feature authoring.",
            completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
            intro = TrainingExerciseIntro(
                dialogTitle = "basic-exercise-1: Project Bootstrap",
                heading = "Project Bootstrap",
                subtitle = "Project capability: Bootstrap Maven + Karate baseline",
                chips = listOf("Maven", "Karate", "Surefire", "Project Structure"),
                structureTitle = "Assets touched in this exercise",
                structureTree = ".\n├─ pom.xml\n└─ src\n   └─ test\n      ├─ pom.xml\n      ├─ src/test/java/\n      ├─ src/test/resources/features/",
                tasksTitle = "Exercise tasks",
                tasks = "1) Implement the exercise objective in project files.\n2) Verify behavior using deterministic checks and run command.\n3) Keep outputs reusable for subsequent exercises."
            ),
            theoryQuiz = TrainingTheoryQuiz(
                title = "Theory Check: Project Bootstrap",
                questionsToAsk = 5,
                passThreshold = 3,
                questionPool = defaultTheoryQuestions(exerciseId = "basic-exercise-1", primaryAsset = "pom.xml", prerequisiteId = "none")
            )
        )
}
