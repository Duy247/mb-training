package com.mb.training.karate.pack

import com.mb.training.karate.model.TrainingProgram
import com.mb.training.karate.packs.TrainingPackDefinition
import com.mb.training.karate.packs.TrainingPackOnboardingContent
import com.mb.training.karate.packs.TrainingPackProvider
import com.mb.training.karate.training.exercises.BasicExercise1Definition
import com.mb.training.karate.training.exercises.BasicExercise2Definition
import com.mb.training.karate.training.exercises.BasicExercise3Definition

class KarateTrainingPackProvider : TrainingPackProvider {
    override fun buildPack(): TrainingPackDefinition {
        return TrainingPackDefinition(
            id = PACK_ID,
            displayName = "MBTraining Karate Pack",
            description = "Karate Framework roadmap from basics to scenario-based debugging.",
            program = TrainingProgram(
                id = "mb-karate-core-program",
                title = "MB Training for Karate Framework",
                exercises = listOf(
                    BasicExercise1Definition.exercise,
                    BasicExercise2Definition.exercise,
                    BasicExercise3Definition.exercise
                )
            ),
            onboarding = TrainingPackOnboardingContent(
                dialogTitle = "Welcome to MBTraining Karate Pack",
                heading = "Learn Karate Framework Fast",
                subtitle = "From basic scripting to real API flow",
                chips = listOf("Guidance", "Theory", "Exercise", "Project"),
                message = """
This onboarding belongs to the Karate training pack.

You will learn through guided exercises directly in IntelliJ IDEA:
- Guided exercise steps
- Theory explanations and checks
- Scenario-based debugging tasks
- Project-style learning progression

Built by MB Training automation team.
                """.trimIndent(),
                startButtonLabel = "Start Karate Training",
                closeButtonLabel = "Close",
                doNotShowAgainLabel = "Do not show this Karate onboarding again",
                firstTaskTitle = "Initialize Your Karate Training Workspace",
                firstTaskMessage = "This will be an empty project where your Karate training journey starts.",
                pickFolderTitle = "Choose Training Project Location",
                pickFolderDescription = "Select a folder to begin Karate Framework training."
            )
        )
    }

    companion object {
        const val PACK_ID = "mbtraining.karate.pack"
    }
}
