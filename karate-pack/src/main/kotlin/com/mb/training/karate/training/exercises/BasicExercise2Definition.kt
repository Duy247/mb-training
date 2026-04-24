package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingExerciseIntro
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingQuizOption
import com.mb.training.karate.model.TrainingQuizQuestion
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingTheoryQuiz
import com.mb.training.karate.model.TrainingType

object BasicExercise2Definition {
    val exercise: TrainingExercise = TrainingExercise(
        id = "basic-exercise-2",
        title = "Create a Basic Feature File",
        level = TrainingLevel.BASIC,
        type = TrainingType.EXERCISE,
        objective = "Create a feature file with Feature, Background, Scenario, and a simple print command.",
        preconditionExerciseIds = listOf("basic-exercise-1"),
        startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-1")),
        steps = listOf(
            TrainingStep(
                id = "step-create-basic-feature-file",
                title = "Create a feature file that prints a simple message",
                guidance = "Create `src/test/resources/features/basic-exercise-2.feature` with Feature, Background, Scenario, and `print`.",
                activities = listOf(
                    TrainingActivity.CreateFile(
                        relativePath = "src/test/resources/features/basic-exercise-2.feature",
                        template = """
Feature: Basic Exercise 2

  Background:
    * print 'Background ready'

  Scenario: Print hello
    * print 'Hello from Karate'
                        """.trimIndent()
                    ),
                    TrainingActivity.CodeTask("You may change the print text, as long as the structure remains complete.")
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Create feature file",
                        targetType = "file",
                        suggestedPath = "src/test/resources/features/basic-exercise-2.feature"
                    ),
                    TrainingHint.ContentHint(
                        filePath = "src/test/resources/features/basic-exercise-2.feature",
                        title = "Minimal feature content sample",
                        snippet = """
Feature: Basic Exercise 2

  Background:
    * print 'Background ready'

  Scenario: Print hello
    * print 'Hello from Karate'
                        """.trimIndent()
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileExists("src/test/resources/features/basic-exercise-2.feature"),
                    TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Feature:"),
                    TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Background:"),
                    TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Scenario:"),
                    TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "print")
                )
            )
        ),
        expectedOutcome = "You created a basic Karate feature file and are ready to write test flows.",
        completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
        intro = TrainingExerciseIntro(
            dialogTitle = "Basic Exercise 2: Basic Feature File",
            heading = "Create Your First Karate Feature File",
            subtitle = "Goal: get familiar with Feature/Background/Scenario structure",
            chips = listOf("Feature", "Background", "Scenario", "Print"),
            structureTitle = "File location to create:",
            structureTree = """
src
└─ test
   └─ resources
      └─ features
         └─ basic-exercise-2.feature
            """.trimIndent(),
            tasksTitle = "Exercise requirements:",
            tasks = """
1) Create `basic-exercise-2.feature` in `src/test/resources/features`.
2) Ensure the file contains: `Feature`, `Background`, `Scenario`.
3) Use at least one `print` command to validate the basic flow.
            """.trimIndent()
        ),
        theoryQuiz = TrainingTheoryQuiz(
            title = "Theory Check: Exercise 2",
            questionsToAsk = 3,
            passThreshold = 2,
            questionPool = listOf(
                quizQuestion(
                    id = "ex2-q1",
                    prompt = "In a basic feature file, which block describes the main test flow?",
                    options = listOf(
                        quizOption("A", "Feature"),
                        quizOption("B", "Background"),
                        quizOption("C", "Scenario"),
                        quizOption("D", "Examples")
                    ),
                    correct = "C",
                    hint = "Scenario is the unit that describes a concrete test flow."
                ),
                quizQuestion(
                    id = "ex2-q2",
                    prompt = "What is Background used for in Karate?",
                    options = listOf(
                        quizOption("A", "Define setup that runs before each scenario"),
                        quizOption("B", "Define production endpoint"),
                        quizOption("C", "Generate HTML report"),
                        quizOption("D", "Build Maven project")
                    ),
                    correct = "A",
                    hint = "Background usually contains shared setup."
                ),
                quizQuestion(
                    id = "ex2-q3",
                    prompt = "Which command is suitable for logging in a feature file?",
                    promptRich = """
In Karate, given this scenario:
```gherkin
Scenario: demo
  * def value = 1
  * ??? 'hello'
```
Which keyword should replace `???` to print logs to console?
                    """.trimIndent(),
                    options = listOf(
                        quizOption("A", "echo"),
                        quizOption("B", "print"),
                        quizOption("C", "console.log"),
                        quizOption("D", "System.out.println")
                    ),
                    correct = "B",
                    hint = "Karate supports the `print` keyword."
                ),
                quizQuestion(
                    id = "ex2-q4",
                    prompt = "What is the correct location for basic-exercise-2.feature?",
                    options = listOf(
                        quizOption("A", "src/test/resources/features"),
                        quizOption("B", "src/main/resources/features"),
                        quizOption("C", "src/test/java/features"),
                        quizOption("D", "features/")
                    ),
                    correct = "A",
                    hint = "The exercise explicitly requires test resources location."
                ),
                quizQuestion(
                    id = "ex2-q5",
                    prompt = "In Maven tool window, which action refreshes dependencies/model after editing pom.xml?",
                    promptRich = """
Look at the image and choose the correct action to refresh Maven:

{{image:/summary-img/reload_maven.png}}

Hint: This is the action commonly used after updating `pom.xml`.
                    """.trimIndent(),
                    options = listOf(
                        quizOption("A", "Reload All Maven Projects"),
                        quizOption("B", "Run current file"),
                        quizOption("C", "Open terminal"),
                        quizOption("D", "Invalidate caches")
                    ),
                    correct = "A",
                    hint = "In Maven tool window, reload icon is used to re-import/reload project."
                )
            )
        )
    )

    private fun quizQuestion(
        id: String,
        prompt: String,
        promptRich: String = prompt,
        options: List<TrainingQuizOption>,
        correct: String,
        hint: String
    ): TrainingQuizQuestion {
        return TrainingQuizQuestion(
            id = id,
            prompt = prompt,
            promptRich = promptRich,
            options = options,
            correctOptionId = correct,
            hint = hint
        )
    }

    private fun quizOption(id: String, text: String): TrainingQuizOption {
        return TrainingQuizOption(id = id, text = text)
    }
}
