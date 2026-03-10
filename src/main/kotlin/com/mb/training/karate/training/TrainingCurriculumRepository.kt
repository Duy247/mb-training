package com.mb.training.karate.training

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingItem
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingProgram
import com.mb.training.karate.model.TrainingStep
import com.mb.training.karate.model.TrainingType

object TrainingCurriculumRepository {
    val program: TrainingProgram = TrainingProgram(
        id = "mb-karate-core-program",
        title = "MB Training for Karate Framework",
        exercises = listOf(
            TrainingExercise(
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
                                <dependency>
                                    <groupId>com.intuit.karate</groupId>
                                    <artifactId>karate-junit5</artifactId>
                                    <version>1.4.1</version>
                                    <scope>test</scope>
                                </dependency>
                                """.trimIndent(),
                                note = "Snippet bắt buộc để framework nhận diện dependency Karate."
                            ),
                            TrainingHint.ContentHint(
                                filePath = "pom.xml",
                                title = "Block Surefire Plugin",
                                snippet = """
                                <plugin>
                                    <artifactId>maven-surefire-plugin</artifactId>
                                    <version>3.2.5</version>
                                </plugin>
                                """.trimIndent(),
                                note = "Thêm block này trong phần <build><plugins>."
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
                        id = "step-verify-build-command",
                        title = "Xác nhận lệnh build/test",
                        guidance = "Chạy `mvn -q test` để kiểm tra project đã sẵn sàng cho bài tập tiếp theo.",
                        activities = listOf(
                            TrainingActivity.RunTestTask("mvn -q test")
                        ),
                        doneWhen = listOf(
                            TrainingCondition.FileExists("pom.xml")
                        )
                    )
                ),
                expectedOutcome = "Bạn có một Maven project tối thiểu dùng Karate Framework và sẵn sàng sang bài tiếp theo.",
                completionPolicy = CompletionPolicy.ALL_STEPS_DONE
            )
            
        )
    )

    val items: List<TrainingItem> = program.exercises.map { exercise ->
        TrainingItem(
            id = exercise.id,
            title = exercise.title,
            level = exercise.level,
            type = exercise.type,
            objective = exercise.objective,
            steps = exercise.steps.map { it.title },
            expectedOutcome = exercise.expectedOutcome
        )
    }
}
