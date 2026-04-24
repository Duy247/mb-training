# basic-exercise-2 - Feature script đầu tiên

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | basic-exercise-2 |
| Tiêu đề | Feature script đầu tiên |
| Cấp độ | BASIC |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase A |
| Bài phụ thuộc | basic-exercise-1 |
| Thời lượng ước tính | 30-45 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Create the first valid Karate feature file using Feature, Background, and Scenario blocks with simple executable DSL.

### Kết quả người học đạt được
1. Write valid Gherkin structure for Karate
2. Use Background for shared setup lines
3. Execute simple print steps
4. Store first feature under correct classpath

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/basic-exercise-2.feature`

### File/Thư mục được cập nhật
- `src/test/resources/features/basic-exercise-2.feature`

### Phụ thuộc vào bài trước
- Reuses project structure and Maven setup from basic-exercise-1.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Introduces the first executable test artifact; later exercises extend this syntax into real HTTP flows.

## 4) Thiết kế chi tiết từng bước

### Bước step-basic-exercise-2-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Create the first feature file in the expected path.
- Nhiệm vụ người học:
  1. Add basic-exercise-2.feature under src/test/resources/features.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-2.feature', template = '...')
- TrainingActivity.CodeTask('Add basic-exercise-2.feature under src/test/resources/features.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-2.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-2.feature', snippet = 'Scenario:')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-2.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-2.feature', 'Scenario:')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-basic-exercise-2-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement valid Feature/Background/Scenario sections.
- Nhiệm vụ người học:
  1. Write simple Background and Scenario with print.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Write simple Background and Scenario with print.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-2.feature', snippet = 'Scenario:')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-2.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-2.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-2.feature', 'Scenario:')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-basic-exercise-2-03-verify - Chạy và xác minh
- Hướng dẫn: Run tests to confirm feature parsing/execution.
- Nhiệm vụ người học:
  1. Run the verification command and ensure pass.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-2-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-2.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-2-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-2.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-basic-exercise-2-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Polish script readability for reuse as syntax reference.
- Nhiệm vụ người học:
  1. Keep indentation and naming clear for later copy/reuse.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Keep indentation and naming clear for later copy/reuse.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-2.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-2', 'step-basic-exercise-2-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-2.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A valid and runnable first Karate feature file in project classpath.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("basic-exercise-1")$(if (@{Order=2; Id=basic-exercise-2; Title=First Feature Script; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-1; Objective=Create the first valid Karate feature file using Feature, Background, and Scenario blocks with simple executable DSL.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Reuses project structure and Maven setup from basic-exercise-1.; Contribution=Introduces the first executable test artifact; later exercises extend this syntax into real HTTP flows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=Scenario:; HintPath2=src/test/resources/features/basic-exercise-2.feature; Chips=System.Object[]; Subtitle=Create your first executable Karate feature; ExpectedOutcome=A valid and runnable first Karate feature file in project classpath.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: basic-exercise-2: First Feature Script
- heading: First Feature Script
- subtitle: Project capability: Create your first executable Karate feature
- chips: [`Feature`, `Background`, `Scenario`, `Print`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-2.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - First Feature Script
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Feature file anatomy
2. Implementation pattern - When to use Background
3. Common pitfall - Indentation and syntax pitfalls
4. Project continuity - Using this file as base for API request scripts

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of basic-exercise-2?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-2.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-1'?
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
  - [ ] id = "basic-exercise-2"
  - [ ] 	itle = "First Feature Script"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=2; Id=basic-exercise-2; Title=First Feature Script; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-1; Objective=Create the first valid Karate feature file using Feature, Background, and Scenario blocks with simple executable DSL.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Reuses project structure and Maven setup from basic-exercise-1.; Contribution=Introduces the first executable test artifact; later exercises extend this syntax into real HTTP flows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=Scenario:; HintPath2=src/test/resources/features/basic-exercise-2.feature; Chips=System.Object[]; Subtitle=Create your first executable Karate feature; ExpectedOutcome=A valid and runnable first Karate feature file in project classpath.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-2-verify") matches TrainingCondition.CommandPassed("basic-exercise-2-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



