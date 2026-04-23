# intermediate-exercise-12 - Hành vi Mock có trạng thái

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | intermediate-exercise-12 |
| Tiêu đề | Hành vi Mock có trạng thái |
| Cấp độ | INTERMEDIATE |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase B |
| Bài phụ thuộc | intermediate-exercise-11 |
| Thời lượng ước tính | 40-60 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Add stateful behavior to mock API for realistic CRUD/auth workflows.

### Kết quả người học đạt được
1. Model in-memory entity state
2. Implement create/read/update/delete route behavior
3. Add simple auth-protected behavior
4. Reset mock state predictably between runs

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/mock/mock-api.feature`
- `src/test/resources/mock/state-utils.js`

### File/Thư mục được cập nhật
- `src/test/resources/mock/mock-api.feature`
- `src/test/resources/mock/state-utils.js`

### Phụ thuộc vào bài trước
- Extends mock bootstrap routes into realistic stateful interactions.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Provides realistic local backend behavior needed for complete business-flow testing in project.

## 4) Thiết kế chi tiết từng bước

### Bước step-intermediate-exercise-12-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Extend mock routes to maintain state transitions.
- Nhiệm vụ người học:
  1. Create in-memory store helpers.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/mock/mock-api.feature', template = '...')
- TrainingActivity.CodeTask('Create in-memory store helpers.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/mock/mock-api.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'def store')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- TrainingCondition.FileContains('src/test/resources/mock/mock-api.feature', 'def store')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-intermediate-exercise-12-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement auth and CRUD behavior in mock.
- Nhiệm vụ người học:
  1. Wire store operations into routes.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Wire store operations into routes.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'def store')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/mock/state-utils.js')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- TrainingCondition.FileContains('src/test/resources/mock/mock-api.feature', 'def store')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-intermediate-exercise-12-03-verify - Chạy và xác minh
- Hướng dẫn: Run and verify stateful operations deterministically.
- Nhiệm vụ người học:
  1. Run CRUD/auth scenarios against mock.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dtest=MockServerRunner', commandId = 'intermediate-exercise-12-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dtest=MockServerRunner')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-12-verify')
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-intermediate-exercise-12-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Add clean state-reset strategy.
- Nhiệm vụ người học:
  1. Reset state per run/suite to avoid drift.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Reset state per run/suite to avoid drift.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-12', 'step-intermediate-exercise-12-03-verify')
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A stateful local mock API supporting realistic CRUD and auth test flows.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-11")$(if (@{Order=24; Id=intermediate-exercise-12; Title=Stateful Mock Behavior; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-11; Objective=Add stateful behavior to mock API for realistic CRUD/auth workflows.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends mock bootstrap routes into realistic stateful interactions.; Contribution=Provides realistic local backend behavior needed for complete business-flow testing in project.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dtest=MockServerRunner; KeywordHint=def store; HintPath2=src/test/resources/mock/state-utils.js; Chips=System.Object[]; Subtitle=Make mock API behavior realistic; ExpectedOutcome=A stateful local mock API supporting realistic CRUD and auth test flows.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-12: Stateful Mock Behavior
- heading: Stateful Mock Behavior
- subtitle: Project capability: Make mock API behavior realistic
- chips: [`stateful mock`, `CRUD`, `auth`, `in-memory state`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/mock/mock-api.feature
      ├─ src/test/resources/mock/state-utils.js
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Stateful Mock Behavior
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - State model design
2. Implementation pattern - Route-to-state mapping
3. Common pitfall - State reset reliability
4. Project continuity - Preparing for capstone flow execution

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of intermediate-exercise-12?
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-11'?
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
  - [ ] id = "intermediate-exercise-12"
  - [ ] 	itle = "Stateful Mock Behavior"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=24; Id=intermediate-exercise-12; Title=Stateful Mock Behavior; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-11; Objective=Add stateful behavior to mock API for realistic CRUD/auth workflows.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends mock bootstrap routes into realistic stateful interactions.; Contribution=Provides realistic local backend behavior needed for complete business-flow testing in project.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dtest=MockServerRunner; KeywordHint=def store; HintPath2=src/test/resources/mock/state-utils.js; Chips=System.Object[]; Subtitle=Make mock API behavior realistic; ExpectedOutcome=A stateful local mock API supporting realistic CRUD and auth test flows.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-12-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-12-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



