# basic-exercise-7 - Xử lý response

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | basic-exercise-7 |
| Tiêu đề | Xử lý response |
| Cấp độ | BASIC |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase A |
| Bài phụ thuộc | basic-exercise-6 |
| Thời lượng ước tính | 30-45 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Validate response metadata such as status, headers, and response time for richer API checks.

### Kết quả người học đạt được
1. Use response metadata variables
2. Assert required headers
3. Set basic response time guard
4. Extract fields for later steps

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/basic-exercise-7.feature`

### File/Thư mục được cập nhật
- `src/test/resources/features/basic-exercise-7.feature`

### Phụ thuộc vào bài trước
- Uses dynamic scripting from basic-exercise-6 to inspect response metadata.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Improves quality gates beyond payload values and introduces baseline non-functional checks.

## 4) Thiết kế chi tiết từng bước

### Bước step-basic-exercise-7-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Create response-handling focused feature scenario.
- Nhiệm vụ người học:
  1. Capture responseStatus/responseHeaders/responseTime.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-7.feature', template = '...')
- TrainingActivity.CodeTask('Capture responseStatus/responseHeaders/responseTime.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-7.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-7.feature', snippet = 'responseHeaders')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-7.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-7.feature', 'responseHeaders')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-basic-exercise-7-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement header/time/status assertions.
- Nhiệm vụ người học:
  1. Add critical header and SLA-lite checks.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Add critical header and SLA-lite checks.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-7.feature', snippet = 'responseHeaders')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-7.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-7.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-7.feature', 'responseHeaders')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-basic-exercise-7-03-verify - Chạy và xác minh
- Hướng dẫn: Run and verify metadata checks.
- Nhiệm vụ người học:
  1. Run command and validate pass reliability.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-7-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-7.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-7-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-7.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-basic-exercise-7-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Tune thresholds and assertions for deterministic stability.
- Nhiệm vụ người học:
  1. Adjust brittle thresholds where necessary.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Adjust brittle thresholds where necessary.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-7.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-7', 'step-basic-exercise-7-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-7.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A scenario that validates API metadata and basic timing expectations.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("basic-exercise-6")$(if (@{Order=7; Id=basic-exercise-7; Title=Response Handling; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-6; Objective=Validate response metadata such as status, headers, and response time for richer API checks.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses dynamic scripting from basic-exercise-6 to inspect response metadata.; Contribution=Improves quality gates beyond payload values and introduces baseline non-functional checks.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=responseHeaders; HintPath2=src/test/resources/features/basic-exercise-7.feature; Chips=System.Object[]; Subtitle=Validate response metadata; ExpectedOutcome=A scenario that validates API metadata and basic timing expectations.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: basic-exercise-7: Response Handling
- heading: Response Handling
- subtitle: Project capability: Validate response metadata
- chips: [`responseStatus`, `responseHeaders`, `responseTime`, `assertions`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-7.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Response Handling
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Metadata assertions that matter
2. Implementation pattern - Header verification strategy
3. Common pitfall - Avoiding flaky response time checks
4. Project continuity - Using extracted fields downstream

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of basic-exercise-7?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-7.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-6'?
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
  - [ ] id = "basic-exercise-7"
  - [ ] 	itle = "Response Handling"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=7; Id=basic-exercise-7; Title=Response Handling; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-6; Objective=Validate response metadata such as status, headers, and response time for richer API checks.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses dynamic scripting from basic-exercise-6 to inspect response metadata.; Contribution=Improves quality gates beyond payload values and introduces baseline non-functional checks.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=responseHeaders; HintPath2=src/test/resources/features/basic-exercise-7.feature; Chips=System.Object[]; Subtitle=Validate response metadata; ExpectedOutcome=A scenario that validates API metadata and basic timing expectations.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-7-verify") matches TrainingCondition.CommandPassed("basic-exercise-7-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



