package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingExerciseIntro
import com.mb.training.karate.model.TrainingKnowledgeCard
import com.mb.training.karate.model.TrainingKnowledgeSummary
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingQuizOption
import com.mb.training.karate.model.TrainingQuizQuestion
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingTheoryQuiz
import com.mb.training.karate.model.TrainingType

object BasicExercise1Definition {
    val exercise: TrainingExercise = TrainingExercise(
        id = "basic-exercise-1",
        title = "Khởi tạo Maven Karate Project Cơ Bản",
        level = TrainingLevel.BASIC,
        type = TrainingType.EXERCISE,
        objective = "Tạo cấu trúc project Maven chuẩn để bắt đầu luyện tập Karate Framework.",
        startWhen = listOf(TrainingCondition.Always),
        steps = listOf(
            TrainingStep(
                id = "step-create-project-structure",
                title = "Tạo cấu trúc thư mục Maven cho Karate",
                guidance = "Tạo các thư mục bắt buộc để chứa test Java và feature files.",
                activities = listOf(
                    TrainingActivity.CreateFolder("src/test/java"),
                    TrainingActivity.CreateFolder("src/test/resources/features")
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Tạo thư mục test Java",
                        targetType = "folder",
                        suggestedPath = "src/test/java",
                        note = "Tạo từ root project."
                    ),
                    TrainingHint.LocationHint(
                        title = "Tạo thư mục chứa feature",
                        targetType = "folder",
                        suggestedPath = "src/test/resources/features"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FolderExists("src/test/java"),
                    TrainingCondition.FolderExists("src/test/resources/features")
                )
            ),
            TrainingStep(
                id = "step-create-pom-with-karate",
                title = "Tạo pom.xml có Karate dependency",
                guidance = "Tạo file `pom.xml` dùng `com.intuit.karate:karate-junit5` và cấu hình `maven-surefire-plugin`.",
                activities = listOf(
                    TrainingActivity.CreateFile(
                        relativePath = "pom.xml",
                        template = """
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mb.training</groupId>
  <artifactId>karate-playground</artifactId>
  <version>1.0-SNAPSHOT</version>
  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>
</project>
                        """.trimIndent()
                    ),
                    TrainingActivity.CodeTask("Đảm bảo pom.xml có đủ dependency Karate và surefire plugin.")
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Tạo file pom.xml tại root",
                        targetType = "file",
                        suggestedPath = "pom.xml",
                        note = "pom.xml đặt ở root project."
                    ),
                    TrainingHint.ContentHint(
                        filePath = "pom.xml",
                        title = "Block Dependency Karate",
                        snippet = """
                                <dependencies>
                                    <dependency>
                                        <groupId>com.intuit.karate</groupId>
                                        <artifactId>karate-junit5</artifactId>
                                        <version>1.4.1</version>
                                        <scope>test</scope>
                                    </dependency>
                                </dependencies>
                                """.trimIndent(),
                        note = "Snippet bắt buộc để framework nhận diện dependency Karate."
                    ),
                    TrainingHint.ContentHint(
                        filePath = "pom.xml",
                        title = "Block Surefire Plugin",
                        snippet = """
                                <build>
                                    <plugins>
                                        <plugin>
                                            <artifactId>maven-surefire-plugin</artifactId>
                                            <version>3.2.5</version>
                                        </plugin>
                                    </plugins>
                                </build>
                                """.trimIndent(),
                        note = "Thêm block này trong file pom."
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileExists("pom.xml"),
                    TrainingCondition.FileContains("pom.xml", "<groupId>com.intuit.karate</groupId>"),
                    TrainingCondition.FileContains("pom.xml", "<artifactId>karate-junit5</artifactId>"),
                    TrainingCondition.FileContains("pom.xml", "<artifactId>maven-surefire-plugin</artifactId>")
                )
            ),
            TrainingStep(
                id = "step-check-maven-repository-source",
                title = "Kiểm tra nguồn tải package",
                guidance = "Kiểm tra Maven đang dùng Maven Central hay Nexus nội bộ.",
                activities = listOf(
                    TrainingActivity.RunCommandTask(
                        instruction = "Xuất effective settings để kiểm tra mirror/repository đang được áp dụng.",
                        commandHint = "mvn help:effective-settings -Doutput=target/effective-settings.xml",
                        commandId = "basic-exercise-1-maven-repo-check"
                    ),
                    TrainingActivity.OpenMavenSettings("Open settings.xml"),
                    TrainingActivity.CodeTask(
                        "Nếu phát hiện Maven Central, cập nhật ~/.m2/settings.xml để dùng Nexus nội bộ rồi chạy lại bước này."
                    )
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Vị trí settings Maven local",
                        targetType = "file",
                        suggestedPath = "~/.m2/settings.xml",
                        note = "Windows: C:/Users/<user>/.m2/settings.xml"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.CommandPassed("basic-exercise-1-maven-repo-check")
                )
            ),
            TrainingStep(
                id = "step-refresh-maven-project",
                title = "Đồng bộ Maven project",
                guidance = "Bấm Reload All Maven Projects để tải dependency và đồng bộ project.",
                activities = listOf(
                    TrainingActivity.RefreshMavenProjects(
                        syncId = "basic-exercise-1-maven-sync",
                        actionLabel = "Maven: Reload All Maven Projects"
                    )
                ),
                hints = listOf(
                    TrainingHint.LocationHint(
                        title = "Nút Reload Maven",
                        targetType = "tool-window button",
                        suggestedPath = "Maven Tool Window > Reload All Maven Projects",
                        note = "Theo dõi tab Build/Sync, cần có dấu tích xanh mới đạt."
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.FileExists("pom.xml"),
                    TrainingCondition.StepCompleted("basic-exercise-1", "step-check-maven-repository-source"),
                    TrainingCondition.MavenSyncSucceeded("basic-exercise-1-maven-sync")
                )
            ),
            TrainingStep(
                id = "step-verify-build-command",
                title = "Xác nhận lệnh build/test",
                guidance = "Chạy `mvn test` để kiểm tra project đã sẵn sàng cho bài tập tiếp theo.",
                activities = listOf(
                    TrainingActivity.RunTestTask(
                        commandHint = "mvn test",
                        commandId = "basic-exercise-1-mvn-test"
                    )
                ),
                doneWhen = listOf(
                    TrainingCondition.StepCompleted("basic-exercise-1", "step-refresh-maven-project"),
                    TrainingCondition.CommandPassed("basic-exercise-1-mvn-test")
                )
            )
        ),
        expectedOutcome = "Bạn có một Maven project tối thiểu dùng Karate Framework và sẵn sàng sang bài tiếp theo.",
        completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
        intro = TrainingExerciseIntro(
            dialogTitle = "Basic Exercise 1: Cấu trúc project Karate",
            heading = "Khởi tạo sân chơi Karate Framework",
            subtitle = "Mục tiêu: tạo project Maven chuẩn để bắt đầu luyện tập",
            chips = listOf("Maven", "Karate", "pom.xml", "Folder Structure"),
            structureTitle = "Cấu trúc cần tạo:",
            structureTree = """
.
├─ pom.xml
└─ src
   └─ test
      ├─ java
      └─ resources
         └─ features
            """.trimIndent(),
            tasksTitle = "Yêu cầu bài tập:",
            tasks = """
1) Tạo đúng cấu trúc thư mục Maven như bên trên.
2) Tạo `pom.xml` có dependency Karate: `com.intuit.karate:karate-junit5`.
3) Cấu hình `maven-surefire-plugin` trong phần build để sẵn sàng chạy test.
            """.trimIndent()
        ),
        knowledgeSummary = TrainingKnowledgeSummary(
            title = "Tổng kết kiến thức: Maven + Karate nền tảng",
            subtitle = "Sau bài này, bạn cần nắm chắc các điểm cốt lõi dưới đây",
            labels = listOf("Maven", "Dependency", "Sync", "Test"),
            cards = listOf(
                card(
                    "Cấu trúc project test với Karate",
                    """
Cấu trúc chuẩn của project Maven test:
```text
project-root/
 ├─ pom.xml
 └─ src/test
    ├─ java
    └─ resources/features
```

Maven mặc định map `src/test/resources` vào classpath test.
Vì vậy Karate đọc file qua `classpath:` rất ổn định.
                    """.trimIndent()
                ),
                card(
                    "Vai trò của pom.xml",
                    """
`pom.xml` là trung tâm của Maven:
- Khai báo dependency (ví dụ `karate-junit5`)
- Khai báo plugin build (ví dụ `maven-surefire-plugin`)
- Điều khiển lifecycle khi chạy `mvn test`

Nếu pom đúng, Maven sẽ resolve dependency và chạy test theo chuẩn.
                    """.trimIndent()
                ),
                card(
                    "Maven Central và local cache",
                    """
Maven Central là kho package chính của hệ Java.
Khi khai báo dependency, Maven tải về và cache tại:
`~/.m2/repository`

Lần build sau dùng lại cache nên nhanh hơn.
                    """.trimIndent()
                ),
                card(
                    "Môi trường doanh nghiệp và Nexus",
                    """
Trong môi trường corporate, truy cập internet thường bị hạn chế.
Thực tế sẽ dùng Nexus/Artifactory nội bộ để:
- kiểm soát bảo mật dependency
- chuẩn hóa version
- tăng ổn định tốc độ tải
                    """.trimIndent()
                ),
                card(
                    "settings.xml và Maven Sync",
                    """
`~/.m2/settings.xml` quyết định mirror/repository Maven dùng.
Sau khi đổi `pom.xml`, cần bấm Maven Sync (Reload All) để IDEA:
- đọc lại pom
- tải dependency
- cập nhật classpath và project model

{{image}}

Sau khi bấm reload, theo dõi tab Maven/Build để chắc chắn sync chạy thành công.
                    """.trimIndent(),
                    imagePath = "/summary-img/reload_maven.png"
                ),
                card(
                    "Xác nhận readiness bằng mvn test",
                    """
Khi chạy `mvn test` pass, nghĩa là:
- dependency tải đúng
- plugin surefire hoạt động
- runtime test sẵn sàng

Đây là checkpoint chuẩn trước khi sang bài tiếp theo.
                    """.trimIndent()
                )
            )
        ),
        theoryQuiz = TrainingTheoryQuiz(
            title = "Kiểm tra lý thuyết Exercise 1",
            questionsToAsk = 5,
            passThreshold = 3,
            questionPool = listOf(
                quizQuestion(
                    id = "ex1-q1",
                    prompt = "Trong project Karate theo Maven, thư mục nào thường chứa feature file?",
                    options = listOf(
                        quizOption("A", "src/main/resources/features"),
                        quizOption("B", "src/test/resources/features"),
                        quizOption("C", "src/test/java/features"),
                        quizOption("D", "src/resources/test/features")
                    ),
                    correct = "B",
                    hint = "Karate test thường đặt dưới scope test resources."
                ),
                quizQuestion(
                    id = "ex1-q2",
                    prompt = "Dependency đúng cho Karate JUnit5 trong bài là?",
                    options = listOf(
                        quizOption("A", "com.intuit.karate:karate-junit5"),
                        quizOption("B", "org.karate:karate-core"),
                        quizOption("C", "com.intuit.karate:karate-spring"),
                        quizOption("D", "io.karatelabs:karate-junit5")
                    ),
                    correct = "A",
                    hint = "Bài hướng dẫn đang dùng groupId com.intuit.karate."
                ),
                quizQuestion(
                    id = "ex1-q3",
                    prompt = "Plugin nào được cấu hình để Maven chạy test?",
                    options = listOf(
                        quizOption("A", "maven-compiler-plugin"),
                        quizOption("B", "maven-failsafe-plugin"),
                        quizOption("C", "maven-surefire-plugin"),
                        quizOption("D", "maven-jar-plugin")
                    ),
                    correct = "C",
                    hint = "Surefire là plugin mặc định cho unit test."
                ),
                quizQuestion(
                    id = "ex1-q4",
                    prompt = "Vì sao cần Maven Reload/Sync sau khi đổi pom.xml?",
                    options = listOf(
                        quizOption("A", "Để đổi theme IntelliJ"),
                        quizOption("B", "Để IDE resolve dependency và cập nhật classpath"),
                        quizOption("C", "Để xoá cache .m2"),
                        quizOption("D", "Để build nhanh hơn ngay lập tức")
                    ),
                    correct = "B",
                    hint = "Reload giúp IDE cập nhật model project."
                ),
                quizQuestion(
                    id = "ex1-q5",
                    prompt = "Dấu hiệu nào cho thấy bước test readiness đã đạt?",
                    options = listOf(
                        quizOption("A", "Có file README"),
                        quizOption("B", "Lệnh mvn test pass"),
                        quizOption("C", "Có folder src/main"),
                        quizOption("D", "Đã mở Maven tool window")
                    ),
                    correct = "B",
                    hint = "Step cuối yêu cầu chạy test thành công."
                ),
                quizQuestion(
                    id = "ex1-q6",
                    prompt = "Trong môi trường enterprise, lý do dùng Nexus nội bộ là gì?",
                    options = listOf(
                        quizOption("A", "Để bỏ qua pom.xml"),
                        quizOption("B", "Để kiểm soát source dependency ổn định/bảo mật"),
                        quizOption("C", "Để không cần internet trên máy dev"),
                        quizOption("D", "Để luôn build không lỗi")
                    ),
                    correct = "B",
                    hint = "Nexus giúp quản trị dependency tập trung."
                )
            )
        )
    )

    private fun card(title: String, content: String, imagePath: String? = null): TrainingKnowledgeCard {
        return TrainingKnowledgeCard(title = title, content = content, imagePath = imagePath)
    }

    private fun quizQuestion(
        id: String,
        prompt: String,
        promptRich: String = prompt,
        options: List<TrainingQuizOption>,
        correct: String,
        hint: String
    ): TrainingQuizQuestion {
        return TrainingQuizQuestion(
            id = id,
            prompt = prompt,
            promptRich = promptRich,
            options = options,
            correctOptionId = correct,
            hint = hint
        )
    }

    private fun quizOption(id: String, text: String): TrainingQuizOption {
        return TrainingQuizOption(id = id, text = text)
    }
}
