# MB Training for Karate Framework

Plugin IntelliJ IDEA hỗ trợ đào tạo Karate Framework theo lộ trình bài tập tương tác (exercise/mission/task/homework), theo dõi tiến độ và hiển thị hướng dẫn trực tiếp trong IDE.

## Chính sách ngôn ngữ theo nhánh
- `master`: phiên bản tiếng Anh (UI và nội dung hướng dẫn cho người học).
- `vn-lang`: phiên bản tiếng Việt (UI và nội dung hướng dẫn cho người học).

## 1) Mục tiêu dự án
- Cung cấp trải nghiệm học Karate ngay trong IntelliJ.
- Chuẩn hóa nội dung training theo framework dữ liệu (modular, dễ mở rộng).
- Cho phép contributor thêm bài mới mà không phải sửa logic lõi.

## 2) Yêu cầu môi trường
- JDK 17 (khuyến nghị)
- IntelliJ IDEA (để phát triển plugin)
- Git
- Windows PowerShell (các lệnh dưới đây dùng PowerShell)

## 3) Setup môi trường phát triển
1. Clone repository.
2. Mở project bằng IntelliJ IDEA.
3. Chờ Gradle sync hoàn tất.
4. Kiểm tra JDK project là 17.

## 4) Các lệnh `./gradlew` thường dùng
> Trên Windows dùng `.\gradlew.bat` hoặc `.\gradlew` (PowerShell).

- Chạy IDE sandbox để test plugin:
```powershell
.\gradlew.bat runIde
```

- Chạy IDE sandbox qua task alias `openTestIde` (để collaborator dùng 1 lệnh thống nhất):
```powershell
.\gradlew.bat openTestIde
```
`openTestIde` đang `dependsOn("runIde")`, nên hành vi runtime tương đương `runIde`.

- Build plugin:
```powershell
.\gradlew.bat build
```

- Chạy toàn bộ test:
```powershell
.\gradlew.bat test
```

- Chạy test sạch (tránh cache cũ):
```powershell
.\gradlew.bat test --rerun-tasks
```

- Dừng daemon Gradle (khi gặp lỗi cache/NoSuchMethodError):
```powershell
.\gradlew.bat --stop
```

- Clean + build lại:
```powershell
.\gradlew.bat clean build
```

## 5) Kiến trúc nhanh
- `src/main/kotlin/com/mb/training/karate/model/TrainingModels.kt`
  - Định nghĩa model của framework training (`TrainingExercise`, `TrainingStep`, `TrainingCondition`, `TrainingActivity`, ...)
- `src/main/kotlin/com/mb/training/karate/training/TrainingCurriculumRepository.kt`
  - Đăng ký danh sách exercise hiện có.
- `src/main/kotlin/com/mb/training/karate/training/exercises/*`
  - Mỗi exercise đặt trong file riêng (quản lý theo bài).
- `src/main/kotlin/com/mb/training/karate/training/TrainingProgressEngine.kt`
  - Engine đánh giá điều kiện hoàn thành step/exercise.
- `src/main/kotlin/com/mb/training/karate/ui/*`
  - Tool window, onboarding popup, intro popup, knowledge summary popup, hint presenter.

## 6) Task Framework hoạt động như thế nào?
Mỗi exercise gồm:
- `startWhen`: điều kiện để bài được mở.
- `steps`: danh sách bước cần làm.
- Mỗi `step` có:
  - `activities`: việc người học cần thực hiện.
  - `hints`: gợi ý UI/HUD.
  - `doneWhen`: điều kiện pass của step.
- `completionPolicy`: quy tắc hoàn thành bài (`ALL_STEPS_DONE` hoặc `ANY_STEP_DONE`).
- `intro`: nội dung popup mở đầu bài (đã chuyển về ngay trong file exercise).
- `knowledgeSummary`: popup tổng kết theo dạng card.

### Ví dụ điều kiện (`TrainingCondition`)
- `FileExists("pom.xml")`
- `FolderExists("src/test/resources/features")`
- `FileContains("pom.xml", "<artifactId>karate-junit5</artifactId>")`
- `CommandPassed("basic-exercise-1-mvn-test")`
- `MavenSyncSucceeded("basic-exercise-1-maven-sync")`

### Ví dụ activity (`TrainingActivity`)
- `CreateFolder`, `CreateFile`, `CodeTask`
- `RunCommandTask`, `RunTestTask`, `RefreshMavenProjects`
- `OpenMavenSettings`

## 7) Cách thêm một exercise mới
### Bước 1: Tạo file exercise mới
Tạo file tại:
- `src/main/kotlin/com/mb/training/karate/training/exercises/BasicExercise3Definition.kt`

Khai báo object có `val exercise: TrainingExercise`.

### Bước 2: Khai báo intro + step + điều kiện
Trong `TrainingExercise`:
- Điền `intro` để popup mở bài.
- Khai báo `steps`, `activities`, `doneWhen`.
- Nếu cần nối tuyến tính, dùng `startWhen = listOf(TrainingCondition.ExerciseCompleted("basic-exercise-2"))`.

### Bước 3: Đăng ký vào repository
Sửa `TrainingCurriculumRepository` để thêm exercise mới vào `exerciseDefinitions` theo thứ tự mong muốn.

### Bước 4: Thêm test
Khuyến nghị thêm test tại `src/test/kotlin/com/mb/training/karate/training/`:
- Test start condition
- Test done condition
- Test dữ liệu intro/summary hợp lệ

### Bước 5: Chạy test
```powershell
.\gradlew.bat test --rerun-tasks
```

## 8) Quy ước nội dung Intro/Summary
### Intro popup (`TrainingExerciseIntro`)
- Hỗ trợ text thường
- Hỗ trợ code block bằng fenced block:
````text
```xml
<dependency>...</dependency>
```
````
- Hỗ trợ ảnh inline bằng marker:
- `{{image:/summary-img/reload_maven.png}}`

### Knowledge summary (`TrainingKnowledgeSummary`)
- Nội dung chia thành `cards`.
- Mỗi card có `title`, `content`, `imagePath` (optional).
- Có điều hướng `Trở lại / Tiếp theo`.
- Chèn ảnh giữa text bằng marker `{{image}}` trong `card.content`.

## 9) Quy trình đóng góp
- Tạo branch theo tính năng.
- Code + test.
- Chạy `test --rerun-tasks` trước khi commit.
- Tạo commit theo checkpoint rõ ràng.

## 10) Ghi chú khi debug lỗi thường gặp
- `NoSuchMethodError` sau khi đổi model constructor:
  - chạy lại `clean test` hoặc `test --rerun-tasks`
- Lỗi Gradle daemon/cached classes:
  - `.\gradlew.bat --stop` rồi chạy lại

---
Nếu cần, có thể mở IDE sandbox bằng `runIde` để kiểm thử toàn bộ luồng UI thực tế trước khi merge.
