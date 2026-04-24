# intermediate-exercise-11 - Khởi tạo Mock API

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | intermediate-exercise-11 |
| Tiêu đề | Khởi tạo Mock API |
| Cấp độ | INTERMEDIATE |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase B |
| Bài phụ thuộc | intermediate-exercise-10 |
| Thời lượng ước tính | 40-60 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Create and run a local Karate mock API so exercises no longer depend on external/public APIs.

### Kết quả người học đạt được
1. Implement first mock routes in Karate
2. Run mock server from project runner
3. Return deterministic mock responses
4. Point tests to local mock target

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/mock/mock-api.feature`
- `src/test/java/com/mb/training/mock/MockServerRunner.java`

### File/Thư mục được cập nhật
- `src/test/resources/mock/mock-api.feature`
- `src/test/java/com/mb/training/mock/MockServerRunner.java`

### Phụ thuộc vào bài trước
- Uses contract/feature foundations from previous exercises to define mock response behavior.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Introduces local test backend, making project self-contained and suitable for offline/local development.

## 4) Thiết kế chi tiết từng bước

### Bước step-intermediate-exercise-11-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Create initial mock API feature routes.
- Nhiệm vụ người học:
  1. Define basic GET/POST mock routes.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/mock/mock-api.feature', template = '...')
- TrainingActivity.CodeTask('Define basic GET/POST mock routes.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/mock/mock-api.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'Scenario:')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- TrainingCondition.FileContains('src/test/resources/mock/mock-api.feature', 'Scenario:')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-intermediate-exercise-11-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Add runner to start mock API locally.
- Nhiệm vụ người học:
  1. Implement MockServer runner entry point.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Implement MockServer runner entry point.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'Scenario:')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/mock/mock-api.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- TrainingCondition.FileContains('src/test/resources/mock/mock-api.feature', 'Scenario:')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-intermediate-exercise-11-03-verify - Chạy và xác minh
- Hướng dẫn: Run mock server command and validate route responses.
- Nhiệm vụ người học:
  1. Run and hit mock endpoints in tests.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dtest=MockServerRunner', commandId = 'intermediate-exercise-11-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dtest=MockServerRunner')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-11-verify')
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-intermediate-exercise-11-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Stabilize route contracts for downstream tests.
- Nhiệm vụ người học:
  1. Ensure response payloads are deterministic.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Ensure response payloads are deterministic.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-11', 'step-intermediate-exercise-11-03-verify')
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A runnable local mock API server with stable base routes.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-10")$(if (@{Order=23; Id=intermediate-exercise-11; Title=Mock API Bootstrap; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-10; Objective=Create and run a local Karate mock API so exercises no longer depend on external/public APIs.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses contract/feature foundations from previous exercises to define mock response behavior.; Contribution=Introduces local test backend, making project self-contained and suitable for offline/local development.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dtest=MockServerRunner; KeywordHint=Scenario:; HintPath2=src/test/resources/mock/mock-api.feature; Chips=System.Object[]; Subtitle=Stand up local Karate mock API; ExpectedOutcome=A runnable local mock API server with stable base routes.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-11: Mock API Bootstrap
- heading: Mock API Bootstrap
- subtitle: Project capability: Stand up local Karate mock API
- chips: [`mock API`, `local server`, `routes`, `deterministic responses`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/mock/mock-api.feature
      ├─ src/test/java/com/mb/training/mock/MockServerRunner.java
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Mock API Bootstrap
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Why local mock first
2. Implementation pattern - Mock route structure
3. Common pitfall - Runner startup pattern
4. Project continuity - How mock removes external dependency risk

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of intermediate-exercise-11?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/mock/mock-api.feature
     - B: .idea/workspace.xml
     - C: README.md only
     - D: No files are required
     - Correct: A
     - Hint: Check Files/Folders Added.
  3. Q: How is step verification tracked by the plugin?
     - A: TrainingCondition.CommandPassed with a commandId
     - B: Manual verbal confirmation
     - C: Random timer expiration
     - D: UI screenshot attachment
     - Correct: A
     - Hint: Look at step 3 doneWhen design.
  4. Q: Why does this exercise depend on 'intermediate-exercise-10'?
     - A: It reuses prior project assets or skills
     - B: No reason, dependency is arbitrary
     - C: Only to increase file count
     - D: Because tests cannot run without internet
     - Correct: A
     - Hint: See dependency section.
  5. Q: Which completion policy is recommended here?
     - A: ALL_STEPS_DONE
     - B: ANY_STEP_DONE
     - C: No policy
     - D: External policy file
     - Correct: A
     - Hint: Project-based continuity needs full completion.
  6. Q: What keeps this exercise reusable for the capstone?
     - A: Deterministic assertions and clean structure
     - B: Hard-coded temporary values everywhere
     - C: Manual hidden setup steps
     - D: Skipping validation commands
     - Correct: A
     - Hint: Think CI and maintainability.

## 9) Checklist ánh xạ Kotlin

- TrainingExercise fields:
  - [ ] id = "intermediate-exercise-11"
  - [ ] 	itle = "Mock API Bootstrap"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=23; Id=intermediate-exercise-11; Title=Mock API Bootstrap; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-10; Objective=Create and run a local Karate mock API so exercises no longer depend on external/public APIs.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses contract/feature foundations from previous exercises to define mock response behavior.; Contribution=Introduces local test backend, making project self-contained and suitable for offline/local development.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dtest=MockServerRunner; KeywordHint=Scenario:; HintPath2=src/test/resources/mock/mock-api.feature; Chips=System.Object[]; Subtitle=Stand up local Karate mock API; ExpectedOutcome=A runnable local mock API server with stable base routes.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-11-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-11-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



