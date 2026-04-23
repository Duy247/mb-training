# intermediate-exercise-4 - Setup một lần toàn cục với callSingle

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | intermediate-exercise-4 |
| Tiêu đề | Setup một lần toàn cục với callSingle |
| Cấp độ | INTERMEDIATE |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase B |
| Bài phụ thuộc | intermediate-exercise-3 |
| Thời lượng ước tính | 40-60 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Use karate.callSingle() for global initialization shared across feature files.

### Kết quả người học đạt được
1. Implement callSingle in config layer
2. Return global bootstrap data safely
3. Consume global values in tests
4. Handle initialization failures gracefully

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/common/global-init.feature`

### File/Thư mục được cập nhật
- `src/test/java/karate-config.js`
- `src/test/resources/features/common/global-init.feature`

### Phụ thuộc vào bài trước
- Extends setup optimization from callonce to global-level initialization.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Provides scalable global initialization mechanism for mock-server, auth, and capstone startup flows.

## 4) Thiết kế chi tiết từng bước

### Bước step-intermediate-exercise-4-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Create global init feature and callSingle wiring.
- Nhiệm vụ người học:
  1. Add global-init.feature with bootstrap actions.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/common/global-init.feature', template = '...')
- TrainingActivity.CodeTask('Add global-init.feature with bootstrap actions.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/common/global-init.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/global-init.feature', snippet = 'karate.callSingle')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/common/global-init.feature')
- TrainingCondition.FileContains('src/test/resources/features/common/global-init.feature', 'karate.callSingle')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-intermediate-exercise-4-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Integrate global data into runtime config.
- Nhiệm vụ người học:
  1. Wire karate.callSingle() in karate-config.js.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Wire karate.callSingle() in karate-config.js.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/global-init.feature', snippet = 'karate.callSingle')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/java/karate-config.js')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/common/global-init.feature')
- TrainingCondition.FileContains('src/test/resources/features/common/global-init.feature', 'karate.callSingle')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-intermediate-exercise-4-03-verify - Chạy và xác minh
- Hướng dẫn: Run and verify one-time global initialization.
- Nhiệm vụ người học:
  1. Run and confirm consistent initialization output.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'intermediate-exercise-4-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/global-init.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-4-verify')
- TrainingCondition.FileExists('src/test/resources/features/common/global-init.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-intermediate-exercise-4-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Stabilize returned data contract for downstream usage.
- Nhiệm vụ người học:
  1. Keep bootstrap payload minimal and deterministic.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Keep bootstrap payload minimal and deterministic.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/global-init.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-4', 'step-intermediate-exercise-4-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/common/global-init.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A reliable global bootstrap mechanism available across feature suites.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-3")$(if (@{Order=16; Id=intermediate-exercise-4; Title=Global One-Time Setup with callSingle; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-3; Objective=Use karate.callSingle() for global initialization shared across feature files.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends setup optimization from callonce to global-level initialization.; Contribution=Provides scalable global initialization mechanism for mock-server, auth, and capstone startup flows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=karate.callSingle; HintPath2=src/test/java/karate-config.js; Chips=System.Object[]; Subtitle=Establish global one-time initialization; ExpectedOutcome=A reliable global bootstrap mechanism available across feature suites.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-4: Global One-Time Setup with callSingle
- heading: Global One-Time Setup with callSingle
- subtitle: Project capability: Establish global one-time initialization
- chips: [`callSingle`, `global init`, `config`, `bootstrap`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/common/global-init.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Global One-Time Setup with callSingle
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Global init responsibilities
2. Implementation pattern - callSingle data contract design
3. Common pitfall - Failure handling practices
4. Project continuity - Role in project-wide startup consistency

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of intermediate-exercise-4?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/common/global-init.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-3'?
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
  - [ ] id = "intermediate-exercise-4"
  - [ ] 	itle = "Global One-Time Setup with callSingle"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=16; Id=intermediate-exercise-4; Title=Global One-Time Setup with callSingle; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-3; Objective=Use karate.callSingle() for global initialization shared across feature files.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends setup optimization from callonce to global-level initialization.; Contribution=Provides scalable global initialization mechanism for mock-server, auth, and capstone startup flows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=karate.callSingle; HintPath2=src/test/java/karate-config.js; Chips=System.Object[]; Subtitle=Establish global one-time initialization; ExpectedOutcome=A reliable global bootstrap mechanism available across feature suites.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-4-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-4-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



