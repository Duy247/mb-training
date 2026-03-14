package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingExerciseIntro
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingType

object BasicExercise2Definition {
    val exercise: TrainingExercise = TrainingExercise(
        id = "basic-exercise-2",
        title = "Tạo Feature File Cơ Bản",
        level = TrainingLevel.BASIC,
        type = TrainingType.EXERCISE,
        objective = "Tạo một file feature có Feature, Background, Scenario và lệnh print đơn giản.",
        startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-1")),
        steps = listOf(
            TrainingStep(
                id = "step-create-basic-feature-file",
                title = "Tạo feature file in thông điệp đơn giản",
                guidance = "Tạo file `src/test/resources/features/basic-exercise-2.feature` gồm Feature, Background, Scenario và `print`.",
                activities = listOf(
                    TrainingActivity.CreateFile(
                        relativePath = "src/test/resources/features/basic-exercise-2.feature",
                        template = """
Feature: Basic Exercise 2

  Background:
    * print 'Background ready'

  Scenario: Print hello
    * print 'Hello from Karate'
                        """.trimIndent()
                    ),
                    TrainingActivity.CodeTask("Có thể đổi text print theo ý bạn, miễn là vẫn giữ đủ cấu trúc.")
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Tạo file feature",
                        targetType = "file",
                        suggestedPath = "src/test/resources/features/basic-exercise-2.feature"
                    ),
                    TrainingHint.ContentHint(
                        filePath = "src/test/resources/features/basic-exercise-2.feature",
                        title = "Mẫu nội dung feature tối thiểu",
                        snippet = """
Feature: Basic Exercise 2

  Background:
    * print 'Background ready'

  Scenario: Print hello
    * print 'Hello from Karate'
                        """.trimIndent()
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileExists("src/test/resources/features/basic-exercise-2.feature"),
                    TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Feature:"),
                    TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Background:"),
                    TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Scenario:"),
                    TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "print")
                )
            )
        ),
        expectedOutcome = "Bạn tạo được feature file Karate cơ bản và sẵn sàng viết test flow.",
        completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
        intro = TrainingExerciseIntro(
            dialogTitle = "Basic Exercise 2: Feature file cơ bản",
            heading = "Tạo feature file Karate đầu tiên",
            subtitle = "Mục tiêu: làm quen cấu trúc Feature/Background/Scenario",
            chips = listOf("Feature", "Background", "Scenario", "Print"),
            structureTitle = "Vị trí file cần tạo:",
            structureTree = """
src
└─ test
   └─ resources
      └─ features
         └─ basic-exercise-2.feature
            """.trimIndent(),
            tasksTitle = "Yêu cầu bài tập:",
            tasks = """
1) Tạo file `basic-exercise-2.feature` trong `src/test/resources/features`.
2) Trong file có đủ: `Feature`, `Background`, `Scenario`.
3) Dùng ít nhất một lệnh `print` để kiểm tra chạy flow cơ bản.
            """.trimIndent()
        )
    )
}
