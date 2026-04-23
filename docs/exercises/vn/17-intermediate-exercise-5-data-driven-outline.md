# intermediate-exercise-5 - Scenario Outline hướng dữ liệu

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | intermediate-exercise-5 |
| Tiêu đề | Scenario Outline hướng dữ liệu |
| Cấp độ | INTERMEDIATE |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase B |
| Bài phụ thuộc | intermediate-exercise-4 |
| Thời lượng ước tính | 40-60 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Create data-driven scenarios using Scenario Outline and Examples tables.

### Kết quả người học đạt được
1. Template scenario with placeholders
2. Design useful examples table
3. Validate row-specific behavior
4. Keep data-driven cases readable

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/intermediate-exercise-5.feature`

### File/Thư mục được cập nhật
- `src/test/resources/features/intermediate-exercise-5.feature`

### Phụ thuộc vào bài trước
- Uses global config/bootstrap and reusable setup from prior exercises.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Expands coverage without duplicating scenario logic, essential for scalable project test sets.

## 4) Thiết kế chi tiết từng bước

### Bước step-intermediate-exercise-5-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Create data-driven feature skeleton.
- Nhiệm vụ người học:
  1. Add outline scenario with variables.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/intermediate-exercise-5.feature', template = '...')
- TrainingActivity.CodeTask('Add outline scenario with variables.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/intermediate-exercise-5.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-5.feature', snippet = 'Scenario Outline')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-5.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-5.feature', 'Scenario Outline')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-intermediate-exercise-5-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement placeholders and examples table.
- Nhiệm vụ người học:
  1. Populate Examples with meaningful variations.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Populate Examples with meaningful variations.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-5.feature', snippet = 'Scenario Outline')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/intermediate-exercise-5.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-5.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-5.feature', 'Scenario Outline')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-intermediate-exercise-5-03-verify - Chạy và xác minh
- Hướng dẫn: Run and verify each row executes correctly.
- Nhiệm vụ người học:
  1. Run and inspect per-row results.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'intermediate-exercise-5-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-5.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-5-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-5.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-intermediate-exercise-5-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Refine data table structure for maintainability.
- Nhiệm vụ người học:
  1. Clean column naming and value clarity.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Clean column naming and value clarity.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-5.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-5', 'step-intermediate-exercise-5-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-5.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A maintainable data-driven scenario with multiple executed permutations.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-4")$(if (@{Order=17; Id=intermediate-exercise-5; Title=Data-Driven Outline; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-4; Objective=Create data-driven scenarios using Scenario Outline and Examples tables.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses global config/bootstrap and reusable setup from prior exercises.; Contribution=Expands coverage without duplicating scenario logic, essential for scalable project test sets.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=Scenario Outline; HintPath2=src/test/resources/features/intermediate-exercise-5.feature; Chips=System.Object[]; Subtitle=Scale scenarios with outline data; ExpectedOutcome=A maintainable data-driven scenario with multiple executed permutations.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-5: Data-Driven Outline
- heading: Data-Driven Outline
- subtitle: Project capability: Scale scenarios with outline data
- chips: [`Scenario Outline`, `Examples`, `data-driven`, `coverage`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/intermediate-exercise-5.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Data-Driven Outline
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Outline design patterns
2. Implementation pattern - Choosing example dimensions
3. Common pitfall - Debugging per-row failures
4. Project continuity - Using outlines in business flow suites

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of intermediate-exercise-5?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/intermediate-exercise-5.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-4'?
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
  - [ ] id = "intermediate-exercise-5"
  - [ ] 	itle = "Data-Driven Outline"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=17; Id=intermediate-exercise-5; Title=Data-Driven Outline; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-4; Objective=Create data-driven scenarios using Scenario Outline and Examples tables.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses global config/bootstrap and reusable setup from prior exercises.; Contribution=Expands coverage without duplicating scenario logic, essential for scalable project test sets.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=Scenario Outline; HintPath2=src/test/resources/features/intermediate-exercise-5.feature; Chips=System.Object[]; Subtitle=Scale scenarios with outline data; ExpectedOutcome=A maintainable data-driven scenario with multiple executed permutations.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-5-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-5-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



