package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingExerciseIntro
import com.mb.training.karate.model.TrainingKnowledgeCard
import com.mb.training.karate.model.TrainingKnowledgeSummary
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingQuizOption
import com.mb.training.karate.model.TrainingQuizQuestion
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingTheoryQuiz
import com.mb.training.karate.model.TrainingType

object BasicExercise1Definition {
    val exercise: TrainingExercise = TrainingExercise(
        id = "basic-exercise-1",
        title = "Initialize a Basic Maven Karate Project",
        level = TrainingLevel.BASIC,
        type = TrainingType.EXERCISE,
        objective = "Create a standard Maven project structure to start practicing Karate Framework.",
        startWhen = listOf(TrainingCondition.Always),
        steps = listOf(
            TrainingStep(
                id = "step-create-project-structure",
                title = "Create Maven folder structure for Karate",
                guidance = "Create required folders for Java tests and feature files.",
                activities = listOf(
                    TrainingActivity.CreateFolder("src/test/java"),
                    TrainingActivity.CreateFolder("src/test/resources/features")
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Create Java test folder",
                        targetType = "folder",
                        suggestedPath = "src/test/java",
                        note = "Create it from the project root."
                    ),
                    TrainingHint.LocationHint(
                        title = "Create feature folder",
                        targetType = "folder",
                        suggestedPath = "src/test/resources/features"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FolderExists("src/test/java"),
                    TrainingCondition.FolderExists("src/test/resources/features")
                )
            ),
            TrainingStep(
                id = "step-create-pom-with-karate",
                title = "Create pom.xml with Karate dependency",
                guidance = "Create `pom.xml` using `com.intuit.karate:karate-junit5` and configure `maven-surefire-plugin`.",
                activities = listOf(
                    TrainingActivity.CreateFile(
                        relativePath = "pom.xml",
                        template = """
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mb.training</groupId>
  <artifactId>karate-playground</artifactId>
  <version>1.0-SNAPSHOT</version>
  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>
</project>
                        """.trimIndent()
                    ),
                    TrainingActivity.CodeTask("Make sure pom.xml includes Karate dependency and the surefire plugin.")
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Create pom.xml at root",
                        targetType = "file",
                        suggestedPath = "pom.xml",
                        note = "pom.xml must be located at project root."
                    ),
                    TrainingHint.ContentHint(
                        filePath = "pom.xml",
                        title = "Karate Dependency Block",
                        snippet = """
                                <dependencies>
                                    <dependency>
                                        <groupId>com.intuit.karate</groupId>
                                        <artifactId>karate-junit5</artifactId>
                                        <version>1.4.1</version>
                                        <scope>test</scope>
                                    </dependency>
                                </dependencies>
                                """.trimIndent(),
                        note = "This snippet is required so the framework can resolve Karate dependency."
                    ),
                    TrainingHint.ContentHint(
                        filePath = "pom.xml",
                        title = "Surefire Plugin Block",
                        snippet = """
                                <build>
                                    <plugins>
                                        <plugin>
                                            <artifactId>maven-surefire-plugin</artifactId>
                                            <version>3.2.5</version>
                                        </plugin>
                                    </plugins>
                                </build>
                                """.trimIndent(),
                        note = "Add this block in pom.xml."
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileExists("pom.xml"),
                    TrainingCondition.FileContains("pom.xml", "<groupId>com.intuit.karate</groupId>"),
                    TrainingCondition.FileContains("pom.xml", "<artifactId>karate-junit5</artifactId>"),
                    TrainingCondition.FileContains("pom.xml", "<artifactId>maven-surefire-plugin</artifactId>")
                )
            ),
            TrainingStep(
                id = "step-check-maven-repository-source",
                title = "Check package repository source",
                guidance = "Verify whether Maven is using Maven Central or internal Nexus.",
                activities = listOf(
                    TrainingActivity.RunCommandTask(
                        instruction = "Export effective settings to check active mirrors/repositories.",
                        commandHint = "mvn help:effective-settings -Doutput=target/effective-settings.xml",
                        commandId = "basic-exercise-1-maven-repo-check"
                    ),
                    TrainingActivity.OpenMavenSettings("Open settings.xml"),
                    TrainingActivity.CodeTask(
                        "If Maven Central is detected, update ~/.m2/settings.xml to use internal Nexus, then rerun this step."
                    )
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Local Maven settings location",
                        targetType = "file",
                        suggestedPath = "~/.m2/settings.xml",
                        note = "Windows: C:/Users/<user>/.m2/settings.xml"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.CommandPassed("basic-exercise-1-maven-repo-check")
                )
            ),
            TrainingStep(
                id = "step-refresh-maven-project",
                title = "Refresh Maven project",
                guidance = "Click Reload All Maven Projects to fetch dependencies and refresh project model.",
                activities = listOf(
                    TrainingActivity.RefreshMavenProjects(
                        syncId = "basic-exercise-1-maven-sync",
                        actionLabel = "Maven: Reload All Maven Projects"
                    )
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Maven Reload button",
                        targetType = "tool-window button",
                        suggestedPath = "Maven Tool Window > Reload All Maven Projects",
                        note = "Watch Build/Sync tab and wait for a green success state."
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileExists("pom.xml"),
                    TrainingCondition.StepCompleted("basic-exercise-1", "step-check-maven-repository-source"),
                    TrainingCondition.MavenSyncSucceeded("basic-exercise-1-maven-sync")
                )
            ),
            TrainingStep(
                id = "step-verify-build-command",
                title = "Verify build/test command",
                guidance = "Run `mvn test` to confirm the project is ready for the next exercise.",
                activities = listOf(
                    TrainingActivity.RunTestTask(
                        commandHint = "mvn test",
                        commandId = "basic-exercise-1-mvn-test"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.StepCompleted("basic-exercise-1", "step-refresh-maven-project"),
                    TrainingCondition.CommandPassed("basic-exercise-1-mvn-test")
                )
            )
        ),
        expectedOutcome = "You have a minimal Maven project using Karate Framework and are ready for the next exercise.",
        completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
        intro = TrainingExerciseIntro(
            dialogTitle = "Basic Exercise 1: Karate Project Structure",
            heading = "Initialize Your Karate Framework Workspace",
            subtitle = "Goal: create a standard Maven project to start training",
            chips = listOf("Maven", "Karate", "pom.xml", "Folder Structure"),
            structureTitle = "Required structure:",
            structureTree = """
.
├─ pom.xml
└─ src
   └─ test
      ├─ java
      └─ resources
         └─ features
            """.trimIndent(),
            tasksTitle = "Exercise requirements:",
            tasks = """
1) Create the Maven folder structure shown above.
2) Create `pom.xml` with Karate dependency: `com.intuit.karate:karate-junit5`.
3) Configure `maven-surefire-plugin` in the build section so tests are runnable.
            """.trimIndent()
        ),
        knowledgeSummary = TrainingKnowledgeSummary(
            title = "Knowledge Summary: Maven + Karate Fundamentals",
            subtitle = "After this exercise, you should understand the core points below",
            labels = listOf("Maven", "Dependency", "Sync", "Test"),
            cards = listOf(
                card(
                    "Karate test project structure",
                    """
Standard Maven test project structure:
```text
project-root/
 ├─ pom.xml
 └─ src/test
    ├─ java
    └─ resources/features
```

Maven maps `src/test/resources` to the test classpath by default.
That is why Karate can reliably read files via `classpath:`.
                    """.trimIndent()
                ),
                card(
                    "Role of pom.xml",
                    """
`pom.xml` is the center of Maven configuration:
- Declare dependencies (for example `karate-junit5`)
- Declare build plugins (for example `maven-surefire-plugin`)
- Control lifecycle when running `mvn test`

If pom.xml is correct, Maven resolves dependencies and runs tests consistently.
                    """.trimIndent()
                ),
                card(
                    "Maven Central and local cache",
                    """
Maven Central is the primary package repository in the Java ecosystem.
When dependencies are declared, Maven downloads and caches them at:
`~/.m2/repository`

Subsequent builds reuse cache and run faster.
                    """.trimIndent()
                ),
                card(
                    "Enterprise environment and Nexus",
                    """
In corporate environments, internet access is often restricted.
In practice, internal Nexus/Artifactory is used to:
- control dependency security
- standardize versions
- improve download stability
                    """.trimIndent()
                ),
                card(
                    "settings.xml and Maven Sync",
                    """
`~/.m2/settings.xml` controls which mirrors/repositories Maven uses.
After changing `pom.xml`, click Maven Sync (Reload All) so IDEA can:
- re-read pom.xml
- download dependencies
- update classpath and project model

{{image}}

After reloading, monitor the Maven/Build tab to make sure sync succeeds.
                    """.trimIndent(),
                    imagePath = "/summary-img/reload_maven.png"
                ),
                card(
                    "Confirm readiness with mvn test",
                    """
When `mvn test` passes, it means:
- dependencies are resolved correctly
- surefire plugin is working
- test runtime is ready

This is the standard checkpoint before moving to the next exercise.
                    """.trimIndent()
                )
            )
        ),
        theoryQuiz = TrainingTheoryQuiz(
            title = "Theory Check: Exercise 1",
            questionsToAsk = 5,
            passThreshold = 3,
            questionPool = listOf(
                quizQuestion(
                    id = "ex1-q1",
                    prompt = "In a Maven-based Karate project, which folder usually contains feature files?",
                    options = listOf(
                        quizOption("A", "src/main/resources/features"),
                        quizOption("B", "src/test/resources/features"),
                        quizOption("C", "src/test/java/features"),
                        quizOption("D", "src/resources/test/features")
                    ),
                    correct = "B",
                    hint = "Karate test resources are typically under test scope."
                ),
                quizQuestion(
                    id = "ex1-q2",
                    prompt = "What is the correct Karate JUnit5 dependency in this exercise?",
                    options = listOf(
                        quizOption("A", "com.intuit.karate:karate-junit5"),
                        quizOption("B", "org.karate:karate-core"),
                        quizOption("C", "com.intuit.karate:karate-spring"),
                        quizOption("D", "io.karatelabs:karate-junit5")
                    ),
                    correct = "A",
                    hint = "This exercise uses groupId com.intuit.karate."
                ),
                quizQuestion(
                    id = "ex1-q3",
                    prompt = "Which plugin is configured for Maven test execution?",
                    options = listOf(
                        quizOption("A", "maven-compiler-plugin"),
                        quizOption("B", "maven-failsafe-plugin"),
                        quizOption("C", "maven-surefire-plugin"),
                        quizOption("D", "maven-jar-plugin")
                    ),
                    correct = "C",
                    hint = "Surefire is the default plugin for unit tests."
                ),
                quizQuestion(
                    id = "ex1-q4",
                    prompt = "Why do you need Maven Reload/Sync after editing pom.xml?",
                    options = listOf(
                        quizOption("A", "To change IntelliJ theme"),
                        quizOption("B", "To let the IDE resolve dependencies and update classpath"),
                        quizOption("C", "To clear .m2 cache"),
                        quizOption("D", "To immediately make builds faster")
                    ),
                    correct = "B",
                    hint = "Reload updates the IDE project model."
                ),
                quizQuestion(
                    id = "ex1-q5",
                    prompt = "Which sign shows the test-readiness step is complete?",
                    options = listOf(
                        quizOption("A", "README file exists"),
                        quizOption("B", "mvn test passes"),
                        quizOption("C", "src/main folder exists"),
                        quizOption("D", "Maven tool window is opened")
                    ),
                    correct = "B",
                    hint = "The final step requires successful test execution."
                ),
                quizQuestion(
                    id = "ex1-q6",
                    prompt = "In enterprise environments, why use internal Nexus?",
                    options = listOf(
                        quizOption("A", "To skip pom.xml"),
                        quizOption("B", "To control dependency source stability and security"),
                        quizOption("C", "So developer machines never need internet"),
                        quizOption("D", "To guarantee builds never fail")
                    ),
                    correct = "B",
                    hint = "Nexus provides centralized dependency governance."
                )
            )
        )
    )

    private fun card(title: String, content: String, imagePath: String? = null): TrainingKnowledgeCard {
        return TrainingKnowledgeCard(title = title, content = content, imagePath = imagePath)
    }

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
