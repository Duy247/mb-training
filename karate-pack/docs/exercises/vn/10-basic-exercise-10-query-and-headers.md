# basic-exercise-10 - Query và Headers

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | basic-exercise-10 |
| Tiêu đề | Query và Headers |
| Cấp độ | BASIC |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase A |
| Bài phụ thuộc | basic-exercise-9 |
| Thời lượng ước tính | 30-45 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Control endpoint behavior with query parameters, headers, and optional cookie context.

### Kết quả người học đạt được
1. Set query params with Karate DSL
2. Set custom headers/cookies
3. Assert behavior differences by request metadata
4. Cover one missing-header negative case

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/basic-exercise-10.feature`

### File/Thư mục được cập nhật
- `src/test/resources/features/basic-exercise-10.feature`

### Phụ thuộc vào bài trước
- Reuses request/assertion patterns and fuzzy checks from prior basic exercises.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Enables realistic API request shaping needed for filtering, versioning, and auth-driven behavior.

## 4) Thiết kế chi tiết từng bước

### Bước step-basic-exercise-10-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Build request variants using params and headers.
- Nhiệm vụ người học:
  1. Add param and header blocks.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-10.feature', template = '...')
- TrainingActivity.CodeTask('Add param and header blocks.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-10.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-10.feature', snippet = 'And param')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-10.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-10.feature', 'And param')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-basic-exercise-10-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement scenarios that prove metadata-driven behavior.
- Nhiệm vụ người học:
  1. Assert response changes with different metadata.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Assert response changes with different metadata.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-10.feature', snippet = 'And param')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-10.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-10.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-10.feature', 'And param')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-basic-exercise-10-03-verify - Chạy và xác minh
- Hướng dẫn: Run and verify both positive and negative variants.
- Nhiệm vụ người học:
  1. Run command and verify deterministic outcomes.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-10-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-10.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-10-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-10.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-basic-exercise-10-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Document metadata conventions for later exercises.
- Nhiệm vụ người học:
  1. Normalize header/query naming patterns.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Normalize header/query naming patterns.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-10.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-10', 'step-basic-exercise-10-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-10.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A set of scenarios that validate query/header-driven endpoint behavior.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("basic-exercise-9")$(if (@{Order=10; Id=basic-exercise-10; Title=Query and Headers; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-9; Objective=Control endpoint behavior with query parameters, headers, and optional cookie context.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Reuses request/assertion patterns and fuzzy checks from prior basic exercises.; Contribution=Enables realistic API request shaping needed for filtering, versioning, and auth-driven behavior.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=And param; HintPath2=src/test/resources/features/basic-exercise-10.feature; Chips=System.Object[]; Subtitle=Drive API behavior with request metadata; ExpectedOutcome=A set of scenarios that validate query/header-driven endpoint behavior.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: basic-exercise-10: Query and Headers
- heading: Query and Headers
- subtitle: Project capability: Drive API behavior with request metadata
- chips: [`query`, `headers`, `cookies`, `metadata-driven behavior`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-10.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Query and Headers
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Param/header DSL patterns
2. Implementation pattern - Metadata-driven behavior validation
3. Common pitfall - Negative case design
4. Project continuity - Preparing for auth flows

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of basic-exercise-10?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-10.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-9'?
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
  - [ ] id = "basic-exercise-10"
  - [ ] 	itle = "Query and Headers"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=10; Id=basic-exercise-10; Title=Query and Headers; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-9; Objective=Control endpoint behavior with query parameters, headers, and optional cookie context.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Reuses request/assertion patterns and fuzzy checks from prior basic exercises.; Contribution=Enables realistic API request shaping needed for filtering, versioning, and auth-driven behavior.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=And param; HintPath2=src/test/resources/features/basic-exercise-10.feature; Chips=System.Object[]; Subtitle=Drive API behavior with request metadata; ExpectedOutcome=A set of scenarios that validate query/header-driven endpoint behavior.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-10-verify") matches TrainingCondition.CommandPassed("basic-exercise-10-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



