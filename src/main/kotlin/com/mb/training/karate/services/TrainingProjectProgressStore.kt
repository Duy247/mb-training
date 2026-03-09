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
    val completedIds: Set<String>
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

        return TrainingProgressSnapshot(
            currentItemId = currentId,
            completedIds = completed
        )
    }

    fun save(projectRoot: Path, snapshot: TrainingProgressSnapshot) {
        val ideaDir = projectRoot.resolve(".idea")
        Files.createDirectories(ideaDir)

        val props = Properties().apply {
            setProperty(ONBOARDING_INIT_KEY, "true")
            setProperty(CURRENT_ITEM_KEY, snapshot.currentItemId)
            setProperty(COMPLETED_IDS_KEY, snapshot.completedIds.sorted().joinToString(","))
        }

        Files.newOutputStream(progressFile(projectRoot)).use { output: OutputStream ->
            props.store(output, "MB Training for Karate progress")
        }
    }

    private fun progressFile(projectRoot: Path): Path {
        return projectRoot.resolve(".idea").resolve(PROGRESS_FILE_NAME)
    }
}
