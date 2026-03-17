# MB Training for Karate Framework - Detailed Design

## 1. Mục tiêu tài liệu
- Chuẩn hóa thiết kế hệ thống plugin `MB Training for Karate Framework`.
- Giúp collaborator nắm nhanh:
  - Yêu cầu nghiệp vụ.
  - Tính năng đã có.
  - Luồng vận hành chính.
  - Cấu trúc code.
  - Cấu trúc task framework để thêm exercise mới.

## 2. Mô tả yêu cầu

### 2.1 Yêu cầu nghiệp vụ
- Cung cấp môi trường huấn luyện Karate Framework theo lộ trình từ cơ bản đến nâng cao.
- Người dùng học trực tiếp trong IntelliJ IDEA qua:
  - Bài tập (exercise), nhiệm vụ (mission), tác vụ (task), bài về nhà (homework).
- Theo dõi tiến độ tự động dựa trên thay đổi project.
- Duy trì tiến độ giữa các lần mở lại IDE.

### 2.2 Yêu cầu chức năng chính
- Hiển thị onboarding popup khi phù hợp.
- Cho phép khởi tạo/chọn project luyện tập và mở project trong cửa sổ mới.
- Hiển thị task window với danh sách exercise và chi tiết step.
- Tự động/thu công nhận step hoàn thành theo điều kiện `doneWhen`.
- Hỗ trợ hint theo step (vị trí thao tác, đổi tên, gợi ý nội dung).
- Hỗ trợ chạy lệnh từ UI step (`Run`) và đánh giá kết quả pass/fail.
- Hỗ trợ quiz lý thuyết theo từng exercise, có ngưỡng pass.
- Chỉ hoàn thành exercise khi đủ điều kiện step + quiz (nếu có).
- Hỗ trợ phụ thuộc giữa các exercise (`preconditionExerciseIds`).

### 2.3 Yêu cầu phi chức năng
- UI nhất quán phong cách custom (onboarding, intro, summary, task window).
- Tránh freeze UI: tách tác vụ nặng khỏi EDT, debounce refresh/snapshot save.
- Dễ mở rộng: thêm exercise mới bằng cấu hình model, không sửa lõi nhiều.

## 3. Tính năng hiện tại

### 3.1 Onboarding
- Popup onboarding tùy biến giao diện.
- Nút `Bắt đầu`, `Đóng`, tùy chọn `Không hiển thị lại`.
- Luồng chọn/chỉ định project luyện tập và khôi phục tiến độ.

### 3.2 Task Window
- Panel trái: danh sách lộ trình.
- Panel phải: chi tiết exercise:
  - Mục tiêu.
  - Các bước thực hiện.
  - Điều kiện hoàn thành.
  - Kết quả kỳ vọng.
  - Tóm tắt kiến thức.
  - Kiểm tra lý thuyết.
- Cập nhật trạng thái step theo thời gian thực/chu kỳ polling.

### 3.3 Step runtime actions
- `Run` command (ví dụ Maven test).
- `Run` Maven sync.
- Mở Maven settings.xml đang dùng.
- Đánh giá kết quả command/sync theo rule cụ thể của step.

### 3.4 Hint system
- Hint theo step, mở từ nút `Hint`.
- Các loại hint:
  - `LocationHint`
  - `RenameHint`
  - `ContentHint` (hỗ trợ snippet nhiều dòng)

### 3.5 Exercise Intro Popup
- Popup giới thiệu bài theo từng exercise.
- Hỗ trợ rich content:
  - Text
  - Code block
  - Image marker
  - Bảng markdown cơ bản
- Có cảnh báo phụ thuộc nếu chưa hoàn tất bài prerequisite.

### 3.6 Knowledge Summary
- Popup tóm tắt kiến thức dạng card.
- Có `Trở lại` / `Tiếp theo`, cuộn nội dung, hỗ trợ ảnh + code block.
- Có thể mở lại từ task window.

### 3.7 Theory Quiz
- Quiz theo exercise:
  - Random subset câu hỏi từ pool.
  - Tráo đáp án.
  - Hint theo câu.
  - `Gửi` để chấm điểm theo threshold.
- Pass quiz mới đạt điều kiện hoàn thành exercise (khi exercise có quiz).
- Fail quiz thì yêu cầu làm lại với bộ câu hỏi/đáp án xáo lại.

### 3.8 Lưu tiến độ
- Lưu tiến độ theo project tại `.idea` qua `TrainingProjectProgressStore`:
  - Current exercise.
  - Completed exercises.
  - Completed steps.
  - Passed commands.
  - Maven sync success.
  - Passed theory quiz exercises.

## 4. Luồng hệ thống

### 4.1 Luồng khởi động
1. Plugin load.
2. Đọc snapshot tiến độ từ store.
3. Đồng bộ trạng thái qua `TrainingProgressEngine`.
4. Render task window và chọn exercise hiện tại.
5. Khi user chọn exercise, mở intro popup của bài đó.

### 4.2 Luồng cập nhật tiến độ
1. Có thay đổi file hoặc timer polling.
2. `TrainingToolWindowPanel` gọi `engine.sync(snapshot)`.
3. Engine đánh giá:
  - `startWhen`
  - `preconditionExerciseIds`
  - `doneWhen` từng step
  - `completionPolicy`
  - quiz pass
4. Trả snapshot mới.
5. UI refresh badge/list/step status/quiz status.
6. Snapshot được save (debounce).

### 4.3 Luồng phụ thuộc exercise
1. User chọn exercise B.
2. Kiểm tra `preconditionExerciseIds`.
3. Nếu thiếu prerequisite:
  - Intro popup hiển thị cảnh báo.
  - Nhấn `Đã hiểu` -> điều hướng về prerequisite đầu tiên.
4. Nếu đủ prerequisite -> tiếp tục làm bài bình thường.

### 4.4 Luồng quiz
1. Khi step của exercise đạt điều kiện, nút làm quiz được bật.
2. User làm quiz theo card.
3. Nhấn `Gửi`:
  - Nếu đạt threshold -> đánh dấu pass quiz.
  - Nếu không đạt -> thông báo fail, tạo bộ đề mới.
4. Engine sync lại để xác nhận exercise complete.

## 5. Cấu trúc code

## 5.1 Package chính
- `com.mb.training.karate.model`
  - Khai báo model domain: exercise, step, condition, activity, intro, summary, quiz...
- `com.mb.training.karate.training`
  - `TrainingCurriculumRepository`: tập hợp chương trình đào tạo.
  - `TrainingProgressEngine`: máy đánh giá tiến độ.
- `com.mb.training.karate.training.exercises`
  - Definition theo từng bài (ví dụ `BasicExercise1Definition`, `BasicExercise2Definition`).
- `com.mb.training.karate.ui`
  - `TrainingToolWindowPanel`, onboarding dialog, intro dialog, summary dialog, quiz dialog, hint presenter...
- `com.mb.training.karate.services`
  - `TrainingProjectProgressStore` + snapshot model.

### 5.2 Thành phần lõi
- `TrainingProgressEngine`
  - Nhiệm vụ: biến trạng thái filesystem + runtime signals thành trạng thái nghiệp vụ.
  - Có cache cho rule `FileContains` để giảm đọc file lặp lại.
- `TrainingToolWindowPanel`
  - Nhiệm vụ: điều phối UI + runtime actions + đồng bộ engine + lưu tiến độ.
  - Có debounce refresh/snapshot save.
- `TrainingProjectProgressStore`
  - Nhiệm vụ: lưu/tải snapshot.

## 6. Cấu trúc Task Framework

### 6.1 Model cốt lõi
- `TrainingProgram`
  - Danh sách `TrainingExercise`.
- `TrainingExercise`
  - Metadata: `id`, `title`, `level`, `type`, `objective`, `expectedOutcome`.
  - Điều kiện:
    - `startWhen`
    - `preconditionExerciseIds`
    - `completionPolicy`
  - Nội dung:
    - `steps`
    - `intro`
    - `knowledgeSummary`
    - `theoryQuiz`
- `TrainingStep`
  - `guidance`, `activities`, `hints`, `doneWhen`.

### 6.2 Điều kiện (TrainingCondition)
- `Always`
- `ExerciseCompleted`
- `StepCompleted`
- `FileExists`
- `FolderExists`
- `FileContains`
- `CommandPassed`
- `MavenSyncSucceeded`

### 6.3 Hoạt động (TrainingActivity)
- Tạo folder/file.
- Coding task.
- Mở Maven settings.
- Run command.
- Refresh Maven projects.
- Compile/Test task.

### 6.4 Hint
- `LocationHint`: gợi ý vị trí thao tác.
- `RenameHint`: gợi ý đổi tên.
- `ContentHint`: gợi ý snippet nội dung.

## 7. Ví dụ định nghĩa Exercise

Ví dụ rút gọn:

```kotlin
TrainingExercise(
    id = "basic-exercise-2",
    title = "Tạo Feature File Cơ Bản",
    level = TrainingLevel.BASIC,
    type = TrainingType.EXERCISE,
    objective = "Tạo feature file có Feature/Background/Scenario",
    startWhen = listOf(TrainingCondition.Always),
    preconditionExerciseIds = listOf("basic-exercise-1"),
    steps = listOf(
        TrainingStep(
            id = "create-feature-file",
            title = "Tạo file basic-exercise-2.feature",
            guidance = "Tạo file trong src/test/resources/features...",
            activities = listOf(
                TrainingActivity.CreateFile("src/test/resources/features/basic-exercise-2.feature", "")
            ),
            hints = listOf(
                TrainingHint.LocationHint(
                    title = "Tạo file ở đâu?",
                    targetType = "file",
                    suggestedPath = "src/test/resources/features"
                )
            ),
            doneWhen = listOf(
                TrainingCondition.FileExists("src/test/resources/features/basic-exercise-2.feature"),
                TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Feature:"),
                TrainingCondition.FileContains("src/test/resources/features/basic-exercise-2.feature", "Scenario:")
            )
        )
    ),
    completionPolicy = CompletionPolicy.ALL_STEPS_DONE,
    intro = ...,
    knowledgeSummary = ...,
    theoryQuiz = ...
)
```

## 8. Quy tắc mở rộng (thêm exercise mới)
1. Tạo file definition mới trong `training/exercises`.
2. Khai báo đầy đủ:
   - `startWhen`
   - `preconditionExerciseIds` (nếu có)
   - `steps + doneWhen`
   - `intro` (nếu cần popup hướng dẫn)
   - `knowledgeSummary` (nếu cần tổng kết)
   - `theoryQuiz` (nếu cần gate lý thuyết)
3. Đăng ký exercise vào `TrainingCurriculumRepository`.
4. Viết test cho engine/rule mới.
5. Chạy `./gradlew.bat test`.

## 9. Trạng thái hoàn thành exercise

Một exercise được coi là hoàn thành khi:
- Thỏa `startWhen`.
- Thỏa `preconditionExerciseIds`.
- Thỏa `completionPolicy` dựa trên trạng thái step.
- Nếu có quiz: phải pass quiz (`passedTheoryQuizExerciseIds` chứa id exercise).

## 10. Gợi ý backlog kỹ thuật tiếp theo
- Tách renderer rich content dùng chung cho onboarding/intro/summary thành module riêng.
- Chuẩn hóa schema import/export exercise (JSON/YAML) thay cho hardcode Kotlin.
- Thêm telemetry nội bộ (ẩn danh) cho tỷ lệ pass/fail theo step để tối ưu curriculum.
- Thêm integration test UI flow cho popup dependency + quiz gating.
