package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingExerciseIntro
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingQuizOption
import com.mb.training.karate.model.TrainingQuizQuestion
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingTheoryQuiz
import com.mb.training.karate.model.TrainingType

object BasicExercise2Definition {
    val exercise: TrainingExercise = TrainingExercise(
        id = "basic-exercise-2",
        title = "Tạo Feature File Cơ Bản",
        level = TrainingLevel.BASIC,
        type = TrainingType.EXERCISE,
        objective = "Tạo một file feature có Feature, Background, Scenario và lệnh print đơn giản.",
        preconditionExerciseIds = listOf("basic-exercise-1"),
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
        ),
        theoryQuiz = TrainingTheoryQuiz(
            title = "Kiểm tra lý thuyết Exercise 2",
            questionsToAsk = 3,
            passThreshold = 2,
            questionPool = listOf(
                quizQuestion(
                    id = "ex2-q1",
                    prompt = "Trong feature cơ bản, block nào mô tả kịch bản test chính?",
                    options = listOf(
                        quizOption("A", "Feature"),
                        quizOption("B", "Background"),
                        quizOption("C", "Scenario"),
                        quizOption("D", "Examples")
                    ),
                    correct = "C",
                    hint = "Scenario là đơn vị mô tả luồng test cụ thể."
                ),
                quizQuestion(
                    id = "ex2-q2",
                    prompt = "Background trong Karate dùng để làm gì?",
                    options = listOf(
                        quizOption("A", "Khai báo setup chạy trước mỗi scenario"),
                        quizOption("B", "Định nghĩa endpoint production"),
                        quizOption("C", "Tạo report HTML"),
                        quizOption("D", "Build project Maven")
                    ),
                    correct = "A",
                    hint = "Background thường chứa setup dùng chung."
                ),
                quizQuestion(
                    id = "ex2-q3",
                    prompt = "Lệnh nào phù hợp để in log trong feature file?",
                    options = listOf(
                        quizOption("A", "echo"),
                        quizOption("B", "print"),
                        quizOption("C", "console.log"),
                        quizOption("D", "System.out.println")
                    ),
                    correct = "B",
                    hint = "Karate hỗ trợ keyword print."
                ),
                quizQuestion(
                    id = "ex2-q4",
                    prompt = "Vị trí đúng của file basic-exercise-2.feature là?",
                    options = listOf(
                        quizOption("A", "src/test/resources/features"),
                        quizOption("B", "src/main/resources/features"),
                        quizOption("C", "src/test/java/features"),
                        quizOption("D", "features/")
                    ),
                    correct = "A",
                    hint = "Bài yêu cầu rõ vị trí file trong test resources."
                )
            )
        )
    )

    private fun quizQuestion(
        id: String,
        prompt: String,
        options: List<TrainingQuizOption>,
        correct: String,
        hint: String
    ): TrainingQuizQuestion {
        return TrainingQuizQuestion(
            id = id,
            prompt = prompt,
            options = options,
            correctOptionId = correct,
            hint = hint
        )
    }

    private fun quizOption(id: String, text: String): TrainingQuizOption {
        return TrainingQuizOption(id = id, text = text)
    }
}
