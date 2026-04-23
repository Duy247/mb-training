# basic-exercise-5 - Cơ bản về request body

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | basic-exercise-5 |
| Tiêu đề | Cơ bản về request body |
| Cấp độ | BASIC |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase A |
| Bài phụ thuộc | basic-exercise-4 |
| Thời lượng ước tính | 30-45 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Send POST requests with JSON bodies and validate response payload content.

### Kết quả người học đạt được
1. Build JSON request payload
2. Use request + method post sequence
3. Validate response body fields
4. Add one negative payload check

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/basic-exercise-5.feature`
- `src/test/resources/data/basic-request.json`

### File/Thư mục được cập nhật
- `src/test/resources/features/basic-exercise-5.feature`

### Phụ thuộc vào bài trước
- Extends basic request flow from basic-exercise-4 by introducing body payloads.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Introduces payload-driven interaction required for create/update operations in later CRUD exercises.

## 4) Thiết kế chi tiết từng bước

### Bước step-basic-exercise-5-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Prepare JSON payload assets under resources.
- Nhiệm vụ người học:
  1. Create body file basic-request.json.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-5.feature', template = '...')
- TrainingActivity.CodeTask('Create body file basic-request.json.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-5.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-5.feature', snippet = 'And request')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-5.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-5.feature', 'And request')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-basic-exercise-5-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement POST flow with body and response checks.
- Nhiệm vụ người học:
  1. Send POST and assert created response fields.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Send POST and assert created response fields.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-5.feature', snippet = 'And request')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/data/basic-request.json')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-5.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-5.feature', 'And request')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-basic-exercise-5-03-verify - Chạy và xác minh
- Hướng dẫn: Run verification and fix schema/value mismatches.
- Nhiệm vụ người học:
  1. Run command and confirm deterministic pass.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-5-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-5.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-5-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-5.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-basic-exercise-5-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Keep payload structure reusable for later CRUD tests.
- Nhiệm vụ người học:
  1. Refactor naming/value anchors for future reuse.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Refactor naming/value anchors for future reuse.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-5.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-5', 'step-basic-exercise-5-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-5.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A stable POST scenario with request/response content validation.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("basic-exercise-4")$(if (@{Order=5; Id=basic-exercise-5; Title=Request Body Basics; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-4; Objective=Send POST requests with JSON bodies and validate response payload content.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends basic request flow from basic-exercise-4 by introducing body payloads.; Contribution=Introduces payload-driven interaction required for create/update operations in later CRUD exercises.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=And request; HintPath2=src/test/resources/data/basic-request.json; Chips=System.Object[]; Subtitle=Send and validate request body payloads; ExpectedOutcome=A stable POST scenario with request/response content validation.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: basic-exercise-5: Request Body Basics
- heading: Request Body Basics
- subtitle: Project capability: Send and validate request body payloads
- chips: [`POST`, `JSON Body`, `request`, `response`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-5.feature
      ├─ src/test/resources/data/basic-request.json
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Request Body Basics
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Request body construction
2. Implementation pattern - Inline vs external payload files
3. Common pitfall - Payload mismatch debugging
4. Project continuity - Reusing payload assets in business flows

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of basic-exercise-5?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-5.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-4'?
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
  - [ ] id = "basic-exercise-5"
  - [ ] 	itle = "Request Body Basics"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=5; Id=basic-exercise-5; Title=Request Body Basics; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-4; Objective=Send POST requests with JSON bodies and validate response payload content.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends basic request flow from basic-exercise-4 by introducing body payloads.; Contribution=Introduces payload-driven interaction required for create/update operations in later CRUD exercises.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=And request; HintPath2=src/test/resources/data/basic-request.json; Chips=System.Object[]; Subtitle=Send and validate request body payloads; ExpectedOutcome=A stable POST scenario with request/response content validation.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-5-verify") matches TrainingCondition.CommandPassed("basic-exercise-5-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



