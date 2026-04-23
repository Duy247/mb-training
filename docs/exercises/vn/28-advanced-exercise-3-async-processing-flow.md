# advanced-exercise-3 - Luồng xử lý bất đồng bộ

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | advanced-exercise-3 |
| Tiêu đề | Luồng xử lý bất đồng bộ |
| Cấp độ | ADVANCED |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase C |
| Bài phụ thuộc | advanced-exercise-2 |
| Thời lượng ước tính | 50-75 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Validate asynchronous API operations using polling with retry-until semantics.

### Kết quả người học đạt được
1. Trigger async job endpoint
2. Poll status endpoint with retry rules
3. Detect completion/failure states safely
4. Assert final output consistency

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/advanced-exercise-3.feature`

### File/Thư mục được cập nhật
- `src/test/resources/features/advanced-exercise-3.feature`

### Phụ thuộc vào bài trước
- Extends synchronous CRUD flow into async operation handling.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Adds realistic async validation pattern common in production API ecosystems.

## 4) Thiết kế chi tiết từng bước

### Bước step-advanced-exercise-3-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Create async submission + polling scenario.
- Nhiệm vụ người học:
  1. Submit job and capture job identifier.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/advanced-exercise-3.feature', template = '...')
- TrainingActivity.CodeTask('Submit job and capture job identifier.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/advanced-exercise-3.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-3.feature', snippet = 'retry until')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-3.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-3.feature', 'retry until')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-advanced-exercise-3-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement retry-until logic with sane limits.
- Nhiệm vụ người học:
  1. Poll status endpoint until done condition.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Poll status endpoint until done condition.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-3.feature', snippet = 'retry until')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/advanced-exercise-3.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-3.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-3.feature', 'retry until')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-advanced-exercise-3-03-verify - Chạy và xác minh
- Hướng dẫn: Run and verify eventual-completion behavior.
- Nhiệm vụ người học:
  1. Run and assert completion/output integrity.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-3-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-3.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-3-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-3.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-advanced-exercise-3-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Tune retry strategy for deterministic CI runs.
- Nhiệm vụ người học:
  1. Adjust retry count/interval to avoid flakiness.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Adjust retry count/interval to avoid flakiness.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-3.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-3', 'step-advanced-exercise-3-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-3.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A deterministic async test flow with controlled polling and completion assertions.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("advanced-exercise-2")$(if (@{Order=28; Id=advanced-exercise-3; Title=Async Processing Flow; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=advanced-exercise-2; Objective=Validate asynchronous API operations using polling with retry-until semantics.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends synchronous CRUD flow into async operation handling.; Contribution=Adds realistic async validation pattern common in production API ecosystems.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=retry until; HintPath2=src/test/resources/features/advanced-exercise-3.feature; Chips=System.Object[]; Subtitle=Validate asynchronous processing APIs; ExpectedOutcome=A deterministic async test flow with controlled polling and completion assertions.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-3: Async Processing Flow
- heading: Async Processing Flow
- subtitle: Project capability: Validate asynchronous processing APIs
- chips: [`async`, `polling`, `retry until`, `eventual consistency`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/advanced-exercise-3.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Async Processing Flow
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - Async test architecture
2. Implementation pattern - Retry strategy tuning
3. Common pitfall - Failure-state handling
4. Project continuity - Integrating async checks in capstone

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of advanced-exercise-3?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/advanced-exercise-3.feature
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
  4. Q: Why does this exercise depend on 'advanced-exercise-2'?
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
  - [ ] id = "advanced-exercise-3"
  - [ ] 	itle = "Async Processing Flow"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=28; Id=advanced-exercise-3; Title=Async Processing Flow; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=advanced-exercise-2; Objective=Validate asynchronous API operations using polling with retry-until semantics.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends synchronous CRUD flow into async operation handling.; Contribution=Adds realistic async validation pattern common in production API ecosystems.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=retry until; HintPath2=src/test/resources/features/advanced-exercise-3.feature; Chips=System.Object[]; Subtitle=Validate asynchronous processing APIs; ExpectedOutcome=A deterministic async test flow with controlled polling and completion assertions.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-3-verify") matches TrainingCondition.CommandPassed("advanced-exercise-3-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



