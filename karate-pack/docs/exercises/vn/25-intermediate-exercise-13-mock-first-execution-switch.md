# intermediate-exercise-13 - Chuyển đổi chạy mock-first

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | intermediate-exercise-13 |
| Tiêu đề | Chuyển đổi chạy mock-first |
| Cấp độ | INTERMEDIATE |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase B |
| Bài phụ thuộc | intermediate-exercise-12 |
| Thời lượng ước tính | 40-60 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Enable env-driven switching between local mock target and real integration target.

### Kết quả người học đạt được
1. Expose mock target in config
2. Route existing tests by environment
3. Run suite fully against local mock
4. Keep optional real-env pathway intact

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/common/target-resolution.feature`

### File/Thư mục được cập nhật
- `src/test/java/karate-config.js`
- `src/test/resources/features/common/target-resolution.feature`

### Phụ thuộc vào bài trước
- Depends on working local mock API from intermediate-exercise-12.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Completes local-first strategy so project can run end-to-end without public APIs.

## 4) Thiết kế chi tiết từng bước

### Bước step-intermediate-exercise-13-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Define target resolution by karate.env.
- Nhiệm vụ người học:
  1. Add mock profile in config.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/common/target-resolution.feature', template = '...')
- TrainingActivity.CodeTask('Add mock profile in config.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/common/target-resolution.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/target-resolution.feature', snippet = 'mock')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/common/target-resolution.feature')
- TrainingCondition.FileContains('src/test/resources/features/common/target-resolution.feature', 'mock')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-intermediate-exercise-13-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Wire existing tests to consume resolved base target.
- Nhiệm vụ người học:
  1. Centralize target selection logic.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Centralize target selection logic.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/target-resolution.feature', snippet = 'mock')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/java/karate-config.js')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/common/target-resolution.feature')
- TrainingCondition.FileContains('src/test/resources/features/common/target-resolution.feature', 'mock')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-intermediate-exercise-13-03-verify - Chạy và xác minh
- Hướng dẫn: Run in mock mode and validate full suite path.
- Nhiệm vụ người học:
  1. Run with -Dkarate.env=mock and verify.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'intermediate-exercise-13-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/target-resolution.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-13-verify')
- TrainingCondition.FileExists('src/test/resources/features/common/target-resolution.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-intermediate-exercise-13-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Preserve and document real env option.
- Nhiệm vụ người học:
  1. Document switch behavior for contributors.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Document switch behavior for contributors.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/target-resolution.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-13', 'step-intermediate-exercise-13-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/common/target-resolution.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: Existing scenarios execute deterministically against local mock via environment switch.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-12")$(if (@{Order=25; Id=intermediate-exercise-13; Title=Mock-First Execution Switch; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-12; Objective=Enable env-driven switching between local mock target and real integration target.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Depends on working local mock API from intermediate-exercise-12.; Contribution=Completes local-first strategy so project can run end-to-end without public APIs.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=mock; HintPath2=src/test/java/karate-config.js; Chips=System.Object[]; Subtitle=Switch suite between mock and real targets; ExpectedOutcome=Existing scenarios execute deterministically against local mock via environment switch.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-13: Mock-First Execution Switch
- heading: Mock-First Execution Switch
- subtitle: Project capability: Switch suite between mock and real targets
- chips: [`mock-first`, `env switch`, `target routing`, `local execution`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/common/target-resolution.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Mock-First Execution Switch
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Target routing architecture
2. Implementation pattern - Mock-first development workflow
3. Common pitfall - Parity considerations with real env
4. Project continuity - Readiness gate for advanced phase

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of intermediate-exercise-13?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/common/target-resolution.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-12'?
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
  - [ ] id = "intermediate-exercise-13"
  - [ ] 	itle = "Mock-First Execution Switch"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=25; Id=intermediate-exercise-13; Title=Mock-First Execution Switch; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-12; Objective=Enable env-driven switching between local mock target and real integration target.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Depends on working local mock API from intermediate-exercise-12.; Contribution=Completes local-first strategy so project can run end-to-end without public APIs.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=mock; HintPath2=src/test/java/karate-config.js; Chips=System.Object[]; Subtitle=Switch suite between mock and real targets; ExpectedOutcome=Existing scenarios execute deterministically against local mock via environment switch.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-13-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-13-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



