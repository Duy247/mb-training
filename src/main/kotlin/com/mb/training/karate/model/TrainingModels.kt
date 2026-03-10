package com.mb.training.karate.model

enum class TrainingLevel {
    BASIC,
    INTERMEDIATE,
    ADVANCED
}

enum class TrainingType {
    EXERCISE,
    MISSION,
    TASK,
    HOMEWORK
}

enum class CompletionPolicy {
    ALL_STEPS_DONE,
    ANY_STEP_DONE
}

sealed interface TrainingCondition {
    data object Always : TrainingCondition
    data class ExerciseCompleted(val exerciseId: String) : TrainingCondition
    data class StepCompleted(val exerciseId: String, val stepId: String) : TrainingCondition
    data class FileExists(val relativePath: String) : TrainingCondition
    data class FolderExists(val relativePath: String) : TrainingCondition
    data class FileContains(val relativePath: String, val text: String) : TrainingCondition
}

sealed interface TrainingActivity {
    data class CreateFolder(val relativePath: String) : TrainingActivity
    data class CreateFile(val relativePath: String, val template: String) : TrainingActivity
    data class CodeTask(val instruction: String) : TrainingActivity
    data class CompileTask(val commandHint: String) : TrainingActivity
    data class RunTestTask(val commandHint: String) : TrainingActivity
}

sealed interface TrainingHint {
    data class LocationHint(
        val title: String = "Gợi ý vị trí",
        val targetType: String,
        val suggestedPath: String,
        val note: String? = null
    ) : TrainingHint

    data class RenameHint(
        val title: String = "Gợi ý đổi tên",
        val fromName: String,
        val toName: String,
        val contextPath: String? = null
    ) : TrainingHint

    data class ContentHint(
        val filePath: String,
        val title: String = "Gợi ý nội dung",
        val snippet: String,
        val note: String? = null
    ) : TrainingHint
}

data class TrainingStep(
    val id: String,
    val title: String,
    val guidance: String,
    val activities: List<TrainingActivity>,
    val hints: List<TrainingHint> = emptyList(),
    val doneWhen: List<TrainingCondition>
) {
    constructor(
        id: String,
        title: String,
        guidance: String,
        activities: List<TrainingActivity>,
        doneWhen: List<TrainingCondition>
    ) : this(
        id = id,
        title = title,
        guidance = guidance,
        activities = activities,
        hints = emptyList(),
        doneWhen = doneWhen
    )
}

data class TrainingExercise(
    val id: String,
    val title: String,
    val level: TrainingLevel,
    val type: TrainingType,
    val objective: String,
    val startWhen: List<TrainingCondition>,
    val steps: List<TrainingStep>,
    val expectedOutcome: String,
    val completionPolicy: CompletionPolicy = CompletionPolicy.ALL_STEPS_DONE
)

data class TrainingProgram(
    val id: String,
    val title: String,
    val exercises: List<TrainingExercise>
)

data class TrainingItem(
    val id: String,
    val title: String,
    val level: TrainingLevel,
    val type: TrainingType,
    val objective: String,
    val steps: List<String>,
    val expectedOutcome: String
)
