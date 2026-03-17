package com.mb.training.karate.services

import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.writeText
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TrainingProjectProgressStoreTest {

    @Test
    fun `classify empty folder as empty`() {
        withTempDir { root ->
            val folderType = TrainingProjectProgressStore.classifyFolder(root)
            assertEquals(TrainingFolderType.EMPTY_FOLDER, folderType)
        }
    }

    @Test
    fun `initialize training project creates progress file and marks folder as training project`() {
        withTempDir { root ->
            TrainingProjectProgressStore.initializeNewTrainingProject(root)

            val progressFile = root.resolve(".idea").resolve("mb-training-progress.properties")
            val pomFile = root.resolve("pom.xml")
            val appJava = root.resolve("src/main/java/com/mb/training/App.java")
            assertTrue(progressFile.exists())
            assertTrue(pomFile.exists())
            assertTrue(appJava.exists())
            assertTrue(TrainingProjectProgressStore.hasProgressFile(root))
            assertEquals(
                TrainingFolderType.EXISTING_TRAINING_PROJECT,
                TrainingProjectProgressStore.classifyFolder(root)
            )
        }
    }

    @Test
    fun `save and load progress snapshot roundtrip`() {
        withTempDir { root ->
            val snapshot = TrainingProgressSnapshot(
                currentItemId = "advanced-homework-1",
                completedIds = setOf("basic-exercise-1", "intermediate-mission-1"),
                completedStepIds = setOf("basic-exercise-1::step-create-feature-file"),
                passedCommandIds = setOf("basic-exercise-1-mvn-test"),
                successfulMavenSyncIds = setOf("basic-exercise-1-maven-sync"),
                passedTheoryQuizExerciseIds = setOf("basic-exercise-1")
            )

            TrainingProjectProgressStore.save(root, snapshot)
            val loaded = TrainingProjectProgressStore.load(root)

            assertNotNull(loaded)
            assertEquals(snapshot.currentItemId, loaded.currentItemId)
            assertEquals(snapshot.completedIds, loaded.completedIds)
            assertEquals(snapshot.completedStepIds, loaded.completedStepIds)
            assertEquals(snapshot.passedCommandIds, loaded.passedCommandIds)
            assertEquals(snapshot.successfulMavenSyncIds, loaded.successfulMavenSyncIds)
            assertEquals(snapshot.passedTheoryQuizExerciseIds, loaded.passedTheoryQuizExerciseIds)
        }
    }

    @Test
    fun `classify non-empty folder without progress file as invalid`() {
        withTempDir { root ->
            root.resolve("notes.txt").writeText("not a training project")

            assertFalse(TrainingProjectProgressStore.hasProgressFile(root))
            assertEquals(
                TrainingFolderType.INVALID_FOLDER,
                TrainingProjectProgressStore.classifyFolder(root)
            )
        }
    }

    @Test
    fun `has progress file returns false for missing directory`() {
        val missingRoot = Path.of("build", "tmp", "missing-training-root")
        assertFalse(TrainingProjectProgressStore.hasProgressFile(missingRoot))
    }

    private fun withTempDir(block: (Path) -> Unit) {
        val dir = Files.createTempDirectory("mb-training-test-")
        try {
            dir.createDirectories()
            block(dir)
        } finally {
            deleteDirectory(dir)
        }
    }

    private fun deleteDirectory(dir: Path) {
        Files.walkFileTree(dir, object : SimpleFileVisitor<Path>() {
            override fun visitFile(file: Path, attrs: BasicFileAttributes): java.nio.file.FileVisitResult {
                Files.deleteIfExists(file)
                return java.nio.file.FileVisitResult.CONTINUE
            }

            override fun postVisitDirectory(directory: Path, exc: java.io.IOException?): java.nio.file.FileVisitResult {
                Files.deleteIfExists(directory)
                return java.nio.file.FileVisitResult.CONTINUE
            }
        })
    }
}
