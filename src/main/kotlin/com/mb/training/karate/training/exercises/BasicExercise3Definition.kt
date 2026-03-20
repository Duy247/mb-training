package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingExerciseIntro
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingMode
import com.mb.training.karate.model.TrainingQuizOption
import com.mb.training.karate.model.TrainingQuizQuestion
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingTheoryQuiz
import com.mb.training.karate.model.TrainingType

object BasicExercise3Definition {
    val exercise: TrainingExercise = TrainingExercise(
        id = "basic-exercise-3",
        title = "Scenario: Fix Runner Path",
        level = TrainingLevel.BASIC,
        type = TrainingType.EXERCISE,
        mode = TrainingMode.SCENARIO,
        objective = "Làm việc trên sandbox project, sửa lỗi path trong TestRunner rồi chạy test thành công.",
        startWhen = listOf(TrainingCondition.Always),
        completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
        steps = listOf(
            TrainingStep(
                id = "setup-scenario-sandbox",
                title = "Khởi tạo sandbox scenario",
                guidance = "Bấm Run để tạo workspace tạm với project scenario đã được preset.",
                activities = listOf(
                    TrainingActivity.SetupScenarioWorkspace(
                        scenarioId = "scenario-runner-path-fix-1",
                        actionLabel = "Khởi tạo sandbox scenario"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileExists("pom.xml"),
                    TrainingCondition.FileExists("src/test/java/com/mb/training/scenario/TestRunner.java"),
                    TrainingCondition.FileExists("src/test/resources/features/scenario-runner-path-fix.feature")
                )
            ),
            TrainingStep(
                id = "fix-runner-path",
                title = "Sửa sai đường dẫn feature trong TestRunner",
                guidance = "Mở TestRunner và sửa lại classpath feature cho đúng.",
                activities = listOf(
                    TrainingActivity.CodeTask(
                        "Sửa Karate.run(...) để trỏ đến classpath:features/scenario-runner-path-fix.feature"
                    )
                ),
                hints = listOf(
                    TrainingHint.ContentHint(
                        filePath = "src/test/java/com/mb/training/scenario/TestRunner.java",
                        title = "Gợi ý path đúng",
                        snippet = "return Karate.run(\"classpath:features/scenario-runner-path-fix.feature\");",
                        note = "Hiện tại file đang dùng 'feature' (thiếu chữ s)."
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileContains(
                        relativePath = "src/test/java/com/mb/training/scenario/TestRunner.java",
                        text = "classpath:features/scenario-runner-path-fix.feature"
                    )
                )
            ),
            TrainingStep(
                id = "run-scenario-test",
                title = "Xác nhận bằng lệnh test",
                guidance = "Chạy lệnh Maven đúng target runner.",
                activities = listOf(
                    TrainingActivity.RunTestTask(
                        commandHint = "mvn test -Dtest=TestRunner",
                        commandId = "scenario3-mvn-test"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.CommandPassed("scenario3-mvn-test")
                )
            )
        ),
        expectedOutcome = "Scenario project chạy pass qua TestRunner sau khi fix đúng path.",
        intro = TrainingExerciseIntro(
            dialogTitle = "Scenario Exercise 3: Fix Runner Path",
            heading = "Freestyle Scenario: Runner Path",
            subtitle = "Bạn sẽ làm việc trên một project sandbox đã setup sẵn lỗi thực tế.",
            chips = listOf("Scenario", "Debug", "Runner", "Maven"),
            structureTitle = "Sandbox được chuẩn bị sẵn",
            structureTree = """
.
├─ pom.xml
└─ src
   └─ test
      ├─ java/com/mb/training/scenario/TestRunner.java
      └─ resources/features/scenario-runner-path-fix.feature
            """.trimIndent(),
            tasksTitle = "Mục tiêu bài",
            tasks = """
1) Mở file `TestRunner.java` và tìm lỗi đường dẫn classpath.
2) Sửa path để runner gọi đúng feature file.
3) Chạy `mvn test -Dtest=TestRunner` và đạt BUILD SUCCESS.
            """.trimIndent()
        ),
        theoryQuiz = TrainingTheoryQuiz(
            title = "Kiểm tra lý thuyết Exercise 3",
            questionsToAsk = 2,
            passThreshold = 1,
            questionPool = listOf(
                TrainingQuizQuestion(
                    id = "ex3-q1",
                    prompt = "Trong Karate runner, classpath sai thường gây lỗi gì?",
                    promptRich = "Trong Karate runner, classpath sai thường gây lỗi gì?",
                    options = listOf(
                        TrainingQuizOption("A", "Không tìm thấy feature file"),
                        TrainingQuizOption("B", "Sai JDK version"),
                        TrainingQuizOption("C", "Lỗi network Maven"),
                        TrainingQuizOption("D", "IDE không mở được project")
                    ),
                    correctOptionId = "A",
                    hint = "Runner gọi trực tiếp đến file feature qua classpath."
                ),
                TrainingQuizQuestion(
                    id = "ex3-q2",
                    prompt = "Lệnh nào được yêu cầu để validate bài scenario này?",
                    promptRich = "Lệnh nào được yêu cầu để validate bài scenario này?",
                    options = listOf(
                        TrainingQuizOption("A", "mvn -q test"),
                        TrainingQuizOption("B", "mvn test -Dtest=TestRunner"),
                        TrainingQuizOption("C", "gradle test"),
                        TrainingQuizOption("D", "mvn clean install")
                    ),
                    correctOptionId = "B",
                    hint = "Bài yêu cầu target đúng class runner."
                )
            )
        )
    )
}
