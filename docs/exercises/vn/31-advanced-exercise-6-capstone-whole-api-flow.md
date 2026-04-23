# advanced-exercise-6 - Capstone luồng API hoàn chỉnh

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | advanced-exercise-6 |
| Tiêu đề | Capstone luồng API hoàn chỉnh |
| Cấp độ | ADVANCED |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase C |
| Bài phụ thuộc | advanced-exercise-5 |
| Thời lượng ước tính | 50-75 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Assemble a full CI-ready end-to-end API flow suite runnable on local mock and optional real environment.

### Kết quả người học đạt được
1. Execute complete authenticated business flow
2. Run flow against mock-first target strategy
3. Generate test reports for CI consumption
4. Deliver a functional maintainable project outcome

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/capstone/`
- `src/test/java/com/mb/training/runner/CapstoneRunner.java`

### File/Thư mục được cập nhật
- `src/test/resources/features/capstone/`
- `src/test/java/com/mb/training/runner/CapstoneRunner.java`
- `src/test/java/karate-config.js`

### Phụ thuộc vào bài trước
- Composes all previous capabilities: config, reuse, mock, auth, CRUD, async, negative, security.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Delivers the final functional project objective: a complete, runnable, maintainable Karate API automation suite.

## 4) Thiết kế chi tiết từng bước

### Bước step-advanced-exercise-6-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Design and assemble capstone flow modules.
- Nhiệm vụ người học:
  1. Create capstone feature package and runner.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/capstone/', template = '...')
- TrainingActivity.CodeTask('Create capstone feature package and runner.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/capstone/')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/capstone/', snippet = 'capstone')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/capstone/')
- TrainingCondition.FileContains('src/test/resources/features/capstone/', 'capstone')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-advanced-exercise-6-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Integrate auth + CRUD + async + negative/security checks.
- Nhiệm vụ người học:
  1. Wire all reusable modules into unified flow.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Wire all reusable modules into unified flow.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/capstone/', snippet = 'capstone')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/capstone/')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/capstone/')
- TrainingCondition.FileContains('src/test/resources/features/capstone/', 'capstone')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-advanced-exercise-6-03-verify - Chạy và xác minh
- Hướng dẫn: Run full capstone suite and validate reporting outputs.
- Nhiệm vụ người học:
  1. Run and verify deterministic end-to-end pass.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-6-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/capstone/', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-6-verify')
- TrainingCondition.FileExists('src/test/resources/features/capstone/')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-advanced-exercise-6-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Finalize project structure/documentation for handoff.
- Nhiệm vụ người học:
  1. Ensure artifacts are CI-ready and readable.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Ensure artifacts are CI-ready and readable.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/capstone/', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-6', 'step-advanced-exercise-6-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/capstone/')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A complete API testing project runnable locally (mock) and adaptable for integration environments.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("advanced-exercise-5")$(if (@{Order=31; Id=advanced-exercise-6; Title=Capstone Whole API Flow; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=advanced-exercise-5; Objective=Assemble a full CI-ready end-to-end API flow suite runnable on local mock and optional real environment.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Composes all previous capabilities: config, reuse, mock, auth, CRUD, async, negative, security.; Contribution=Delivers the final functional project objective: a complete, runnable, maintainable Karate API automation suite.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=capstone; HintPath2=src/test/resources/features/capstone/; Chips=System.Object[]; Subtitle=Deliver complete functional API project; ExpectedOutcome=A complete API testing project runnable locally (mock) and adaptable for integration environments.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-6: Capstone Whole API Flow
- heading: Capstone Whole API Flow
- subtitle: Project capability: Deliver complete functional API project
- chips: [`capstone`, `end-to-end`, `CI-ready`, `functional project`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/capstone/
      ├─ src/test/java/com/mb/training/runner/CapstoneRunner.java
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Capstone Whole API Flow
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - Capstone architecture
2. Implementation pattern - Module composition strategy
3. Common pitfall - CI/reporting readiness
4. Project continuity - Final project maintainability checklist

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of advanced-exercise-6?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/capstone/
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
  4. Q: Why does this exercise depend on 'advanced-exercise-5'?
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
  - [ ] id = "advanced-exercise-6"
  - [ ] 	itle = "Capstone Whole API Flow"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=31; Id=advanced-exercise-6; Title=Capstone Whole API Flow; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=advanced-exercise-5; Objective=Assemble a full CI-ready end-to-end API flow suite runnable on local mock and optional real environment.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Composes all previous capabilities: config, reuse, mock, auth, CRUD, async, negative, security.; Contribution=Delivers the final functional project objective: a complete, runnable, maintainable Karate API automation suite.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=capstone; HintPath2=src/test/resources/features/capstone/; Chips=System.Object[]; Subtitle=Deliver complete functional API project; ExpectedOutcome=A complete API testing project runnable locally (mock) and adaptable for integration environments.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-6-verify") matches TrainingCondition.CommandPassed("advanced-exercise-6-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



