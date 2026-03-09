package com.mb.training.karate.training

import com.mb.training.karate.model.TrainingItem
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingType

object TrainingCurriculumRepository {
    val items: List<TrainingItem> = listOf(
        TrainingItem(
            id = "basic-exercise-1",
            title = "Write Your First Feature File",
            level = TrainingLevel.BASIC,
            type = TrainingType.EXERCISE,
            objective = "Create and run a minimal Karate feature with one scenario.",
            steps = listOf(
                "Create a feature file under test resources.",
                "Add a simple scenario with Given/When/Then style steps.",
                "Run it using your test runner."
            ),
            expectedOutcome = "You can execute a passing Karate test from your IDE."
        ),
        TrainingItem(
            id = "basic-task-1",
            title = "Use Path and Params",
            level = TrainingLevel.BASIC,
            type = TrainingType.TASK,
            objective = "Build a request with path variables and query params.",
            steps = listOf(
                "Define base URL in Background.",
                "Use path to target a concrete endpoint.",
                "Attach two query parameters and assert status 200."
            ),
            expectedOutcome = "Request-building basics are clear and reusable."
        ),
        TrainingItem(
            id = "intermediate-mission-1",
            title = "Validate Complex JSON Response",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.MISSION,
            objective = "Use match expressions to validate nested JSON payloads.",
            steps = listOf(
                "Call an endpoint returning nested objects and arrays.",
                "Assert key fields with exact match and fuzzy match.",
                "Extract one field and reuse it in a follow-up step."
            ),
            expectedOutcome = "You can confidently assert non-trivial JSON responses."
        ),
        TrainingItem(
            id = "intermediate-homework-1",
            title = "Data-Driven Scenario Outline",
            level = TrainingLevel.INTERMEDIATE,
            type = TrainingType.HOMEWORK,
            objective = "Create parameterized tests for multiple input sets.",
            steps = listOf(
                "Convert one scenario to Scenario Outline.",
                "Provide at least three example rows.",
                "Add assertions that vary per data row."
            ),
            expectedOutcome = "You can scale test coverage with data-driven patterns."
        ),
        TrainingItem(
            id = "advanced-mission-1",
            title = "End-to-End Workflow with Reusable Features",
            level = TrainingLevel.ADVANCED,
            type = TrainingType.MISSION,
            objective = "Compose reusable feature calls into a realistic workflow.",
            steps = listOf(
                "Split common login/setup logic into reusable features.",
                "Call reusable features with call/read patterns.",
                "Chain multiple API actions and verify business outcome."
            ),
            expectedOutcome = "You can architect maintainable Karate suites for large systems."
        ),
        TrainingItem(
            id = "advanced-homework-1",
            title = "CI-Friendly Karate Project",
            level = TrainingLevel.ADVANCED,
            type = TrainingType.HOMEWORK,
            objective = "Prepare your suite for stable CI execution and reporting.",
            steps = listOf(
                "Configure environment switching and tags.",
                "Generate HTML/JUnit reports.",
                "Create a CI command that runs smoke tests only."
            ),
            expectedOutcome = "Your Karate project is ready for reliable team automation."
        )
    )
}
