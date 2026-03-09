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

data class TrainingItem(
    val id: String,
    val title: String,
    val level: TrainingLevel,
    val type: TrainingType,
    val objective: String,
    val steps: List<String>,
    val expectedOutcome: String
)
