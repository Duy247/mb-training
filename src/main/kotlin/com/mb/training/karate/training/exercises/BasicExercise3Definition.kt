package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingExerciseIntro
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingMode
import com.mb.training.karate.model.TrainingQuizOption
import com.mb.training.karate.model.TrainingQuizQuestion
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingTheoryQuiz
import com.mb.training.karate.model.TrainingType

object BasicExercise3Definition {
    val exercise: TrainingExercise = TrainingExercise(
        id = "basic-exercise-3",
        title = "Scenario: Fix Runner Path",
        level = TrainingLevel.BASIC,
        type = TrainingType.EXERCISE,
        mode = TrainingMode.SCENARIO,
        objective = "Work in a sandbox project, fix the path error in TestRunner, then run tests successfully.",
        startWhen = listOf(TrainingCondition.Always),
        completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
        steps = listOf(
            TrainingStep(
                id = "setup-scenario-sandbox",
                title = "Initialize scenario sandbox",
                guidance = "Click Run to create a temporary workspace with a preset scenario project.",
                activities = listOf(
                    TrainingActivity.SetupScenarioWorkspace(
                        scenarioId = "scenario-runner-path-fix-1",
                        actionLabel = "Initialize scenario sandbox"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileExists("pom.xml"),
                    TrainingCondition.FileExists("src/test/java/com/mb/training/scenario/TestRunner.java"),
                    TrainingCondition.FileExists("src/test/resources/features/scenario-runner-path-fix.feature")
                )
            ),
            TrainingStep(
                id = "fix-runner-path",
                title = "Fix incorrect feature path in TestRunner",
                guidance = "Open TestRunner and correct the feature classpath.",
                activities = listOf(
                    TrainingActivity.CodeTask(
                        "Update Karate.run(...) to point to classpath:features/scenario-runner-path-fix.feature"
                    )
                ),
                hints = listOf(
                    TrainingHint.ContentHint(
                        filePath = "src/test/java/com/mb/training/scenario/TestRunner.java",
                        title = "Correct path hint",
                        snippet = "return Karate.run(\"classpath:features/scenario-runner-path-fix.feature\");",
                        note = "Current file uses 'feature' (missing trailing 's')."
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileContains(
                        relativePath = "src/test/java/com/mb/training/scenario/TestRunner.java",
                        text = "classpath:features/scenario-runner-path-fix.feature"
                    )
                )
            ),
            TrainingStep(
                id = "run-scenario-test",
                title = "Validate with test command",
                guidance = "Run the Maven command targeting the correct runner.",
                activities = listOf(
                    TrainingActivity.RunTestTask(
                        commandHint = "mvn test -Dtest=TestRunner",
                        commandId = "scenario3-mvn-test"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.CommandPassed("scenario3-mvn-test")
                )
            )
        ),
        expectedOutcome = "Scenario project passes through TestRunner after fixing the correct path.",
        intro = TrainingExerciseIntro(
            dialogTitle = "Scenario Exercise 3: Fix Runner Path",
            heading = "Freestyle Scenario: Runner Path",
            subtitle = "You will work on a sandbox project preconfigured with a realistic defect.",
            chips = listOf("Scenario", "Debug", "Runner", "Maven"),
            structureTitle = "Sandbox is pre-configured",
            structureTree = """
.
├─ pom.xml
└─ src
   └─ test
      ├─ java/com/mb/training/scenario/TestRunner.java
      └─ resources/features/scenario-runner-path-fix.feature
            """.trimIndent(),
            tasksTitle = "Exercise goals",
            tasks = """
1) Open `TestRunner.java` and find the classpath issue.
2) Fix the path so the runner calls the correct feature file.
3) Run `mvn test -Dtest=TestRunner` and reach BUILD SUCCESS.
            """.trimIndent()
        ),
        theoryQuiz = TrainingTheoryQuiz(
            title = "Theory Check: Exercise 3",
            questionsToAsk = 2,
            passThreshold = 1,
            questionPool = listOf(
                TrainingQuizQuestion(
                    id = "ex3-q1",
                    prompt = "In a Karate runner, what error is commonly caused by incorrect classpath?",
                    promptRich = "In a Karate runner, what error is commonly caused by incorrect classpath?",
                    options = listOf(
                        TrainingQuizOption("A", "Feature file not found"),
                        TrainingQuizOption("B", "Wrong JDK version"),
                        TrainingQuizOption("C", "Maven network error"),
                        TrainingQuizOption("D", "IDE cannot open project")
                    ),
                    correctOptionId = "A",
                    hint = "Runner resolves feature files directly from classpath."
                ),
                TrainingQuizQuestion(
                    id = "ex3-q2",
                    prompt = "Which command is required to validate this scenario exercise?",
                    promptRich = "Which command is required to validate this scenario exercise?",
                    options = listOf(
                        TrainingQuizOption("A", "mvn -q test"),
                        TrainingQuizOption("B", "mvn test -Dtest=TestRunner"),
                        TrainingQuizOption("C", "gradle test"),
                        TrainingQuizOption("D", "mvn clean install")
                    ),
                    correctOptionId = "B",
                    hint = "This exercise requires targeting the specific runner class."
                )
            )
        )
    )
}
