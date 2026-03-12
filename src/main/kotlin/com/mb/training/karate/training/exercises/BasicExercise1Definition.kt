package com.mb.training.karate.training.exercises

import com.mb.training.karate.model.CompletionPolicy
import com.mb.training.karate.model.TrainingActivity
import com.mb.training.karate.model.TrainingCondition
import com.mb.training.karate.model.TrainingExercise
import com.mb.training.karate.model.TrainingHint
import com.mb.training.karate.model.TrainingKnowledgeSummary
import com.mb.training.karate.model.TrainingLevel
import com.mb.training.karate.model.TrainingStep
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
        knowledgeSummary = TrainingKnowledgeSummary(
            title = "Tổng kết kiến thức: Maven + Karate nền tảng",
            subtitle = "Sau bài này, bạn cần nắm chắc các điểm cốt lõi dưới đây",
            labels = listOf("Maven", "Dependency", "Sync", "Test"),
            content = """
1) Cấu trúc project test với Karate

Cấu trúc chuẩn của một project Maven dùng cho automation test thường như sau:

project-root/
 ├─ pom.xml
 └─ src
     └─ test
         ├─ java
         │    └─ runners / helper classes / test utilities
         └─ resources
              └─ features / test data / payload / config

Ví dụ cụ thể:

src
 └─ test
     ├─ java
     │    └─ runners
     │         └─ ApiTestRunner.java
     │
     └─ resources
          ├─ features
          │     ├─ login.feature
          │     ├─ payment.feature
          │     └─ card.feature
          │
          ├─ payload
          │     └─ create-user.json
          │
          └─ config
                └─ karate-config.js


Vì sao phải theo chuẩn này?

Maven có một build lifecycle mặc định và nó "hiểu" cấu trúc project theo convention. Khi Maven build project:

- src/main/java      -> compile thành code chính của application
- src/main/resources -> resource của application
- src/test/java      -> compile thành test classes
- src/test/resources -> resource phục vụ test

Điều này có nghĩa:

1. Maven tự động add src/test/resources vào test classpath.
2. Test có thể load file rất đơn giản.

Ví dụ trong Karate:

read('classpath:features/login.feature')
read('classpath:payload/create-user.json')

Classpath này chính là src/test/resources.

Nếu không theo chuẩn này thì:
- Maven sẽ không tìm thấy file
- phải cấu hình thủ công
- CI/CD pipeline dễ lỗi

Vì vậy gần như mọi project automation Maven đều dùng cấu trúc này.


------------------------------------------------------------


2) Vai trò của pom.xml

pom.xml là file trung tâm của toàn bộ project Maven.

POM = Project Object Model

Nó định nghĩa:

- Project là gì
- Dependency nào cần dùng
- Plugin nào chạy khi build
- Lifecycle của project


Ví dụ pom.xml cơ bản cho Karate:

<project>
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.company</groupId>
  <artifactId>karate-tests</artifactId>
  <version>1.0.0</version>

  <dependencies>

    <dependency>
      <groupId>com.intuit.karate</groupId>
      <artifactId>karate-junit5</artifactId>
      <version>1.4.1</version>
      <scope>test</scope>
    </dependency>

  </dependencies>

</project>


Vai trò chính của pom.xml:

1. Quản lý dependency

Ví dụ Karate cần các thư viện:

- Karate core
- HTTP client
- JSON parsing
- JUnit integration

Ta chỉ cần khai báo:

karate-junit5

Maven sẽ tự động tải tất cả dependency con.


2. Định nghĩa plugin build

Ví dụ plugin quan trọng nhất cho test:

maven-surefire-plugin

Plugin này có nhiệm vụ:

- tìm test class
- chạy test bằng JUnit
- generate report

Ví dụ:

<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>3.1.2</version>
</plugin>


3. Điều khiển lifecycle

Khi chạy:

mvn test

Maven sẽ chạy chuỗi lifecycle:

validate
compile
test-compile
test

Trong pha test, plugin surefire sẽ được gọi để chạy test.


------------------------------------------------------------


3) Maven Central là gì?

Maven Central là repository package lớn nhất của hệ sinh thái Java.

Có thể hiểu đơn giản:

Nó giống như "npm registry" của NodeJS hoặc "pip repository" của Python.

Địa chỉ:

https://repo.maven.apache.org/maven2


Trong Maven Central có hàng triệu package như:

- Selenium
- Karate
- Spring Boot
- Jackson
- Apache Commons
- JUnit
- Log4j


Ví dụ khi pom.xml khai báo:

<dependency>
  <groupId>com.intuit.karate</groupId>
  <artifactId>karate-junit5</artifactId>
  <version>1.4.1</version>
</dependency>

Maven sẽ:

1. kết nối internet
2. download package từ Maven Central
3. lưu vào local cache

Local cache nằm ở:

~/.m2/repository

Từ lần sau build sẽ dùng lại local cache nên rất nhanh.


------------------------------------------------------------


4) Vì sao môi trường doanh nghiệp thường chặn Maven Central

Trong môi trường corporate (bank, fintech, enterprise), việc truy cập trực tiếp internet thường bị hạn chế.

Có 3 lý do chính.


Lý do 1: Security

Nếu developer có thể tải dependency trực tiếp từ internet:

- có thể tải package chứa malware
- có thể dùng version không được kiểm soát
- khó audit security

Trong ngân hàng, điều này là rủi ro lớn.


Lý do 2: Dependency control

Doanh nghiệp thường muốn kiểm soát:

- version nào được phép dùng
- thư viện nào bị cấm
- library nào có vulnerability

Vì vậy họ dùng repository nội bộ.


Lý do 3: Network stability

Nếu 1000 developer cùng download dependency từ internet:

- tốn bandwidth
- build chậm
- phụ thuộc external service


------------------------------------------------------------


5) Nexus / Artifactory nội bộ

Để giải quyết vấn đề này, công ty thường dùng:

- Nexus Repository
- JFrog Artifactory

Đây là repository nội bộ.


Luồng hoạt động sẽ là:

Developer Maven
        |
        v
Corporate Nexus
        |
        v
Maven Central


Nexus sẽ:

1. download dependency từ Maven Central lần đầu
2. cache lại trong server nội bộ
3. các developer sau tải từ Nexus

Ưu điểm:

- build nhanh hơn
- kiểm soát security
- có thể chặn dependency nguy hiểm


------------------------------------------------------------


6) Maven settings.xml dùng để cấu hình repository

File:

~/.m2/settings.xml

Ví dụ:

<settings>

  <mirrors>

    <mirror>
      <id>company-nexus</id>
      <mirrorOf>*</mirrorOf>
      <url>https://nexus.company.com/repository/maven-public/</url>
    </mirror>

  </mirrors>

</settings>


mirrorOf="*"

có nghĩa:

Mọi request dependency của Maven sẽ được redirect qua Nexus nội bộ thay vì Maven Central.


------------------------------------------------------------


7) Vì sao cần Maven Sync trong IntelliJ

Khi mở project Maven trong IntelliJ, IDE phải:

1. đọc pom.xml
2. resolve dependency
3. download library
4. build dependency graph


Nếu pom.xml thay đổi (ví dụ thêm dependency mới):

IDE chưa tự hiểu ngay.


Do đó cần:

Reload Maven Project
hoặc
Maven Sync


Sync sẽ:

- re-read pom.xml
- download dependency mới
- update classpath
- rebuild project structure


Nếu không sync có thể xảy ra lỗi:

- class not found
- dependency missing
- compile error dù pom.xml đúng


------------------------------------------------------------


8) Xác nhận project ready bằng mvn test

Sau khi:

- cấu trúc project đúng
- pom.xml đúng
- dependency resolve thành công

ta chạy:

mvn test


Maven sẽ thực hiện:

validate
compile
test-compile
test


Trong pha test:

maven-surefire-plugin sẽ:

1. scan test class
2. chạy JUnit runner
3. execute Karate feature
4. generate report


Nếu mọi thứ pass, điều đó chứng minh:

- dependency đã tải đúng
- plugin hoạt động
- test runtime hoạt động
- project đã sẵn sàng cho bước tiếp theo

Đây thường là bước xác nhận "environment ready" trong automation project setup.
            """.trimIndent()
        )
    )
}
