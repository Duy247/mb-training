package com.mb.training.karate.packs

import com.mb.training.karate.model.TrainingProgram

data class TrainingPackOnboardingContent(
    val dialogTitle: String,
    val heading: String,
    val subtitle: String,
    val chips: List<String> = emptyList(),
    val message: String,
    val startButtonLabel: String = "Get Started",
    val closeButtonLabel: String = "Close",
    val doNotShowAgainLabel: String = "Do not show again",
    val firstTaskTitle: String,
    val firstTaskMessage: String,
    val pickFolderTitle: String,
    val pickFolderDescription: String
)

data class TrainingPackDefinition(
    val id: String,
    val displayName: String,
    val description: String,
    val program: TrainingProgram,
    val onboarding: TrainingPackOnboardingContent? = null
)

interface TrainingPackProvider {
    fun buildPack(): TrainingPackDefinition
}
