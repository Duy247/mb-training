# basic-exercise-8 - Nền tảng match

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | basic-exercise-8 |
| Tiêu đề | Nền tảng match |
| Cấp độ | BASIC |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase A |
| Bài phụ thuộc | basic-exercise-7 |
| Thời lượng ước tính | 30-45 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Apply strict and partial matching assertions for reliable response validation.

### Kết quả người học đạt được
1. Use match == for exact checks
2. Use contains for partial object checks
3. Use contains only for set-like lists
4. Improve assertion intent clarity

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/basic-exercise-8.feature`

### File/Thư mục được cập nhật
- `src/test/resources/features/basic-exercise-8.feature`

### Phụ thuộc vào bài trước
- Leverages response handling foundations from basic-exercise-7.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Establishes robust assertion style used repeatedly in contract and business-flow validation.

## 4) Thiết kế chi tiết từng bước

### Bước step-basic-exercise-8-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Create assertion-focused feature examples.
- Nhiệm vụ người học:
  1. Add exact match cases for strict fields.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-8.feature', template = '...')
- TrainingActivity.CodeTask('Add exact match cases for strict fields.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-8.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-8.feature', snippet = 'match')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-8.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-8.feature', 'match')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-basic-exercise-8-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement exact and partial match strategies.
- Nhiệm vụ người học:
  1. Add contains/contains only cases for flexible checks.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Add contains/contains only cases for flexible checks.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-8.feature', snippet = 'match')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-8.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-8.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-8.feature', 'match')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-basic-exercise-8-03-verify - Chạy và xác minh
- Hướng dẫn: Run and confirm assertions fail/pass correctly.
- Nhiệm vụ người học:
  1. Run command and validate assertion behavior.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-8-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-8.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-8-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-8.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-basic-exercise-8-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Refine assertion style for maintainability.
- Nhiệm vụ người học:
  1. Group and comment assertions for readability.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Group and comment assertions for readability.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-8.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-8', 'step-basic-exercise-8-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-8.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A test script using the right match operator for each assertion intent.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("basic-exercise-7")$(if (@{Order=8; Id=basic-exercise-8; Title=Match Fundamentals; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-7; Objective=Apply strict and partial matching assertions for reliable response validation.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Leverages response handling foundations from basic-exercise-7.; Contribution=Establishes robust assertion style used repeatedly in contract and business-flow validation.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=match; HintPath2=src/test/resources/features/basic-exercise-8.feature; Chips=System.Object[]; Subtitle=Master core Karate match assertions; ExpectedOutcome=A test script using the right match operator for each assertion intent.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: basic-exercise-8: Match Fundamentals
- heading: Match Fundamentals
- subtitle: Project capability: Master core Karate match assertions
- chips: [`match ==`, `contains`, `contains only`, `assertion style`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-8.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Match Fundamentals
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Choosing the right match operator
2. Implementation pattern - Balancing strictness and flexibility
3. Common pitfall - List/object assertion mistakes
4. Project continuity - How these checks support contracts later

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of basic-exercise-8?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-8.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-7'?
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
  - [ ] id = "basic-exercise-8"
  - [ ] 	itle = "Match Fundamentals"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=8; Id=basic-exercise-8; Title=Match Fundamentals; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-7; Objective=Apply strict and partial matching assertions for reliable response validation.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Leverages response handling foundations from basic-exercise-7.; Contribution=Establishes robust assertion style used repeatedly in contract and business-flow validation.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=match; HintPath2=src/test/resources/features/basic-exercise-8.feature; Chips=System.Object[]; Subtitle=Master core Karate match assertions; ExpectedOutcome=A test script using the right match operator for each assertion intent.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-8-verify") matches TrainingCondition.CommandPassed("basic-exercise-8-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



