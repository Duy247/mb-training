package com.mb.training.karate.services

import com.intellij.ide.impl.OpenProjectTask
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.project.Project
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties
import kotlin.io.path.writeText

object ScenarioWorkspaceService {
    private const val BASE_FOLDER_NAME = ".mb-training"
    private const val SANDBOX_FOLDER_NAME = "tempProject"
    private const val SCENARIO_META_FILE_NAME = "mb-training-scenario.properties"
    private const val META_ORIGINAL_ROOT_KEY = "original.root.path"
    private const val META_SCENARIO_ID_KEY = "scenario.id"
    private const val META_SCENARIO_EXERCISE_ID_KEY = "scenario.exercise.id"
    private val SESSION_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")

    data class ScenarioWorkspaceContext(
        val originalRootPath: Path,
        val scenarioId: String,
        val scenarioExerciseId: String
    )

    fun setupAndOpenScenario(
        project: Project,
        scenarioId: String,
        scenarioExerciseId: String,
        sourceSnapshot: TrainingProgressSnapshot
    ): Path {
        val sandboxRoot = resolveSandboxRoot()
        Files.createDirectories(sandboxRoot)
        val sessionFolder = sandboxRoot.resolve("$scenarioId-${LocalDateTime.now().format(SESSION_FORMAT)}")
        Files.createDirectories(sessionFolder)

        when (scenarioId) {
            "scenario-runner-path-fix-1" -> writeRunnerPathFixScenario(sessionFolder)
            else -> error("Unsupported scenario id: $scenarioId")
        }

        TrainingProjectProgressStore.save(sessionFolder, sourceSnapshot)
        val originalRoot = project.basePath?.let { Path.of(it).normalize() }
            ?: error("Không tìm thấy project root hiện tại.")
        saveScenarioContext(
            projectRoot = sessionFolder,
            context = ScenarioWorkspaceContext(
                originalRootPath = originalRoot,
                scenarioId = scenarioId,
                scenarioExerciseId = scenarioExerciseId
            )
        )

        ProjectUtil.openOrImport(sessionFolder, OpenProjectTask(forceOpenInNewFrame = true))
        return sessionFolder
    }

    fun loadScenarioContext(projectRoot: Path): ScenarioWorkspaceContext? {
        val metaFile = scenarioMetaFile(projectRoot)
        if (!Files.exists(metaFile)) return null
        val props = Properties()
        Files.newInputStream(metaFile).use { input: InputStream ->
            props.load(input)
        }
        val original = props.getProperty(META_ORIGINAL_ROOT_KEY)?.trim().orEmpty()
        val scenarioId = props.getProperty(META_SCENARIO_ID_KEY)?.trim().orEmpty()
        val scenarioExerciseId = props.getProperty(META_SCENARIO_EXERCISE_ID_KEY)?.trim().orEmpty()
        if (original.isBlank() || scenarioId.isBlank() || scenarioExerciseId.isBlank()) return null
        return runCatching {
            ScenarioWorkspaceContext(
                originalRootPath = Path.of(original).normalize(),
                scenarioId = scenarioId,
                scenarioExerciseId = scenarioExerciseId
            )
        }.getOrNull()
    }

    fun saveScenarioContext(projectRoot: Path, context: ScenarioWorkspaceContext) {
        val ideaDir = projectRoot.resolve(".idea")
        Files.createDirectories(ideaDir)
        val props = Properties().apply {
            setProperty(META_ORIGINAL_ROOT_KEY, context.originalRootPath.toString())
            setProperty(META_SCENARIO_ID_KEY, context.scenarioId)
            setProperty(META_SCENARIO_EXERCISE_ID_KEY, context.scenarioExerciseId)
        }
        Files.newOutputStream(scenarioMetaFile(projectRoot)).use { output: OutputStream ->
            props.store(output, "MB Training scenario workspace context")
        }
    }

    fun deleteWorkspaceQuietly(workspaceRoot: Path) {
        if (!Files.exists(workspaceRoot)) return
        runCatching {
            Files.walkFileTree(workspaceRoot, object : SimpleFileVisitor<Path>() {
                override fun visitFile(file: Path, attrs: BasicFileAttributes): java.nio.file.FileVisitResult {
                    Files.deleteIfExists(file)
                    return java.nio.file.FileVisitResult.CONTINUE
                }

                override fun postVisitDirectory(
                    directory: Path,
                    exc: java.io.IOException?
                ): java.nio.file.FileVisitResult {
                    Files.deleteIfExists(directory)
                    return java.nio.file.FileVisitResult.CONTINUE
                }
            })
        }
    }

    private fun resolveSandboxRoot(): Path {
        val userHome = System.getProperty("user.home")
        return Path.of(userHome, BASE_FOLDER_NAME, SANDBOX_FOLDER_NAME)
    }

    private fun scenarioMetaFile(projectRoot: Path): Path {
        return projectRoot.resolve(".idea").resolve(SCENARIO_META_FILE_NAME)
    }

    private fun writeRunnerPathFixScenario(root: Path) {
        val pom = root.resolve("pom.xml")
        val runner = root.resolve("src/test/java/com/mb/training/scenario/TestRunner.java")
        val feature = root.resolve("src/test/resources/features/scenario-runner-path-fix.feature")

        Files.createDirectories(runner.parent)
        Files.createDirectories(feature.parent)

        pom.writeText(
            """
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
              <modelVersion>4.0.0</modelVersion>
              <groupId>com.mb.training</groupId>
              <artifactId>karate-scenario-runner-path-fix</artifactId>
              <version>1.0-SNAPSHOT</version>

              <properties>
                <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                <maven.compiler.release>17</maven.compiler.release>
                <karate.version>1.4.1</karate.version>
              </properties>

              <dependencies>
                <dependency>
                  <groupId>com.intuit.karate</groupId>
                  <artifactId>karate-junit5</artifactId>
                  <version>${'$'}{karate.version}</version>
                  <scope>test</scope>
                </dependency>
              </dependencies>

              <build>
                <plugins>
                  <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>3.2.5</version>
                    <configuration>
                      <includes>
                        <include>**/*Runner.java</include>
                      </includes>
                    </configuration>
                  </plugin>
                </plugins>
              </build>
            </project>
            """.trimIndent()
        )

        runner.writeText(
            """
            package com.mb.training.scenario;

            import com.intuit.karate.junit5.Karate;

            class TestRunner {

                @Karate.Test
                Karate testAll() {
                    // TODO: Fix đường dẫn classpath bị sai để test chạy được
                    return Karate.run("classpath:feature/scenario-runner-path-fix.feature");
                }
            }
            """.trimIndent()
        )

        feature.writeText(
            """
            Feature: Scenario path fix

              Scenario: Verify scenario is runnable
                * print 'Scenario runner path fixed'
            """.trimIndent()
        )
    }
}
