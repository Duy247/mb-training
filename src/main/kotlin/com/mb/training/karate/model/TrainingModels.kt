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
    data class CommandPassed(val commandId: String) : TrainingCondition
    data class MavenSyncSucceeded(val syncId: String) : TrainingCondition
}

sealed interface TrainingActivity {
    data class CreateFolder(val relativePath: String) : TrainingActivity
    data class CreateFile(val relativePath: String, val template: String) : TrainingActivity
    data class CodeTask(val instruction: String) : TrainingActivity
    data class OpenMavenSettings(val buttonLabel: String = "Open settings.xml") : TrainingActivity
    data class RunCommandTask(
        val instruction: String,
        val commandHint: String,
        val commandId: String = commandHint
    ) : TrainingActivity
    data class RefreshMavenProjects(
        val syncId: String,
        val actionLabel: String = "Maven: Refresh All Projects"
    ) : TrainingActivity
    data class CompileTask(val commandHint: String, val commandId: String = commandHint) : TrainingActivity
    data class RunTestTask(val commandHint: String, val commandId: String = commandHint) : TrainingActivity
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
    val preconditionExerciseIds: List<String> = emptyList(),
    val completionPolicy: CompletionPolicy = CompletionPolicy.ALL_STEPS_DONE,
    val intro: TrainingExerciseIntro? = null,
    val knowledgeSummary: TrainingKnowledgeSummary? = null,
    val theoryQuiz: TrainingTheoryQuiz? = null
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

data class TrainingKnowledgeSummary(
    val title: String,
    val subtitle: String? = null,
    val labels: List<String> = emptyList(),
    val cards: List<TrainingKnowledgeCard>
)

data class TrainingKnowledgeCard(
    val title: String,
    val content: String,
    val imagePath: String? = null
)

data class TrainingExerciseIntro(
    val dialogTitle: String,
    val heading: String,
    val subtitle: String,
    val chips: List<String>,
    val structureTitle: String,
    val structureTree: String,
    val tasksTitle: String,
    val tasks: String
)

data class TrainingTheoryQuiz(
    val title: String,
    val questionPool: List<TrainingQuizQuestion>,
    val questionsToAsk: Int,
    val passThreshold: Int
)

data class TrainingQuizQuestion(
    val id: String,
    val prompt: String,
    val options: List<TrainingQuizOption>,
    val correctOptionId: String,
    val hint: String? = null
)

data class TrainingQuizOption(
    val id: String,
    val text: String
)
