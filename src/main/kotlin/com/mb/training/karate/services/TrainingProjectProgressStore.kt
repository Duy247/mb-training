package com.mb.training.karate.services

import com.mb.training.karate.training.TrainingCurriculumRepository
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.Properties
import kotlin.io.path.exists
import kotlin.io.path.isDirectory

data class TrainingProgressSnapshot(
    val currentItemId: String,
    val completedIds: Set<String>,
    val completedStepIds: Set<String> = emptySet(),
    val passedCommandIds: Set<String> = emptySet(),
    val successfulMavenSyncIds: Set<String> = emptySet()
)

enum class TrainingFolderType {
    EXISTING_TRAINING_PROJECT,
    EMPTY_FOLDER,
    INVALID_FOLDER
}

object TrainingProjectProgressStore {
    private const val PROGRESS_FILE_NAME = "mb-training-progress.properties"
    private const val CURRENT_ITEM_KEY = "current.item.id"
    private const val COMPLETED_IDS_KEY = "completed.ids"
    private const val COMPLETED_STEP_IDS_KEY = "completed.step.ids"
    private const val PASSED_COMMAND_IDS_KEY = "passed.command.ids"
    private const val SUCCESSFUL_MAVEN_SYNC_IDS_KEY = "successful.maven.sync.ids"
    private const val ONBOARDING_INIT_KEY = "onboarding.initialized"

    fun hasProgressFile(projectRoot: Path): Boolean {
        return progressFile(projectRoot).exists()
    }

    fun isEmptyFolder(projectRoot: Path): Boolean {
        if (!projectRoot.exists() || !projectRoot.isDirectory()) return false
        Files.newDirectoryStream(projectRoot).use { stream ->
            return !stream.iterator().hasNext()
        }
    }

    fun classifyFolder(projectRoot: Path): TrainingFolderType {
        if (hasProgressFile(projectRoot)) return TrainingFolderType.EXISTING_TRAINING_PROJECT
        if (isEmptyFolder(projectRoot)) return TrainingFolderType.EMPTY_FOLDER
        return TrainingFolderType.INVALID_FOLDER
    }

    fun initializeNewTrainingProject(projectRoot: Path) {
        initializeJavaProjectSkeleton(projectRoot)
        val defaultItemId = TrainingCurriculumRepository.items.firstOrNull()?.id.orEmpty()
        save(projectRoot, TrainingProgressSnapshot(currentItemId = defaultItemId, completedIds = emptySet()))
    }

    fun load(projectRoot: Path): TrainingProgressSnapshot? {
        val file = progressFile(projectRoot)
        if (!file.exists()) return null

        val props = Properties()
        Files.newInputStream(file).use { input: InputStream ->
            props.load(input)
        }

        val fallbackId = TrainingCurriculumRepository.items.firstOrNull()?.id.orEmpty()
        val currentId = props.getProperty(CURRENT_ITEM_KEY)?.trim().orEmpty().ifEmpty { fallbackId }
        val completed = props.getProperty(COMPLETED_IDS_KEY)
            ?.split(",")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() }
            ?.toSet()
            ?: emptySet()
        val completedSteps = props.getProperty(COMPLETED_STEP_IDS_KEY)
            ?.split(",")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() }
            ?.toSet()
            ?: emptySet()
        val passedCommands = props.getProperty(PASSED_COMMAND_IDS_KEY)
            ?.split(",")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() }
            ?.toSet()
            ?: emptySet()
        val successfulMavenSyncIds = props.getProperty(SUCCESSFUL_MAVEN_SYNC_IDS_KEY)
            ?.split(",")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() }
            ?.toSet()
            ?: emptySet()

        return TrainingProgressSnapshot(
            currentItemId = currentId,
            completedIds = completed,
            completedStepIds = completedSteps,
            passedCommandIds = passedCommands,
            successfulMavenSyncIds = successfulMavenSyncIds
        )
    }

    fun save(projectRoot: Path, snapshot: TrainingProgressSnapshot) {
        val ideaDir = projectRoot.resolve(".idea")
        Files.createDirectories(ideaDir)

        val props = Properties().apply {
            setProperty(ONBOARDING_INIT_KEY, "true")
            setProperty(CURRENT_ITEM_KEY, snapshot.currentItemId)
            setProperty(COMPLETED_IDS_KEY, snapshot.completedIds.sorted().joinToString(","))
            setProperty(COMPLETED_STEP_IDS_KEY, snapshot.completedStepIds.sorted().joinToString(","))
            setProperty(PASSED_COMMAND_IDS_KEY, snapshot.passedCommandIds.sorted().joinToString(","))
            setProperty(SUCCESSFUL_MAVEN_SYNC_IDS_KEY, snapshot.successfulMavenSyncIds.sorted().joinToString(","))
        }

        Files.newOutputStream(progressFile(projectRoot)).use { output: OutputStream ->
            props.store(output, "MB Training for Karate progress")
        }
    }

    private fun progressFile(projectRoot: Path): Path {
        return projectRoot.resolve(".idea").resolve(PROGRESS_FILE_NAME)
    }

    private fun initializeJavaProjectSkeleton(projectRoot: Path) {
        Files.createDirectories(projectRoot)

        val pom = projectRoot.resolve("pom.xml")
        if (!Files.exists(pom)) {
            Files.writeString(
                pom,
                """
                <project xmlns="http://maven.apache.org/POM/4.0.0"
                         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
                  <modelVersion>4.0.0</modelVersion>
                  <groupId>com.mb.training</groupId>
                  <artifactId>karate-training-playground</artifactId>
                  <version>1.0-SNAPSHOT</version>
                  <properties>
                    <maven.compiler.release>17</maven.compiler.release>
                    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                  </properties>
                </project>
                """.trimIndent()
            )
        }

        val starterJava = projectRoot.resolve("src/main/java/com/mb/training/App.java")
        if (!Files.exists(starterJava)) {
            Files.createDirectories(starterJava.parent)
            Files.writeString(
                starterJava,
                """
                package com.mb.training;

                public class App {
                    public static void main(String[] args) {
                        System.out.println("MB Training Karate playground");
                    }
                }
                """.trimIndent()
            )
        }
    }
}
