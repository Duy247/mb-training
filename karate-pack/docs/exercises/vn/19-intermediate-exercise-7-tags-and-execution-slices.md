# intermediate-exercise-7 - Tags và lát cắt thực thi

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | intermediate-exercise-7 |
| Tiêu đề | Tags và lát cắt thực thi |
| Cấp độ | INTERMEDIATE |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase B |
| Bài phụ thuộc | intermediate-exercise-6 |
| Thời lượng ước tính | 40-60 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Organize scenarios by tags for smoke/regression/environment-specific execution slices.

### Kết quả người học đạt được
1. Define tag taxonomy
2. Annotate scenarios consistently
3. Run targeted suites by tag filters
4. Prepare suite segmentation for CI jobs

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/intermediate-exercise-7.feature`
- `src/test/java/com/mb/training/runner/TagRunner.java`

### File/Thư mục được cập nhật
- `src/test/resources/features/intermediate-exercise-7.feature`
- `src/test/java/com/mb/training/runner/TagRunner.java`

### Phụ thuộc vào bài trước
- Uses growing scenario set from previous exercises to create execution slices.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Enables selective pipeline execution and faster feedback loops in project CI workflows.

## 4) Thiết kế chi tiết từng bước

### Bước step-intermediate-exercise-7-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Define and apply tag conventions in feature files.
- Nhiệm vụ người học:
  1. Tag scenarios with @smoke, @regression, etc.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/intermediate-exercise-7.feature', template = '...')
- TrainingActivity.CodeTask('Tag scenarios with @smoke, @regression, etc.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/intermediate-exercise-7.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-7.feature', snippet = '@smoke')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-7.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-7.feature', '@smoke')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-intermediate-exercise-7-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement runner strategy for tag filters.
- Nhiệm vụ người học:
  1. Create or update runner with tag options.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Create or update runner with tag options.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-7.feature', snippet = '@smoke')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/intermediate-exercise-7.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-7.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-7.feature', '@smoke')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-intermediate-exercise-7-03-verify - Chạy và xác minh
- Hướng dẫn: Run smoke/regression slices to verify grouping.
- Nhiệm vụ người học:
  1. Execute filtered command and verify selected tests.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.options="--tags @smoke"', commandId = 'intermediate-exercise-7-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.options="--tags @smoke"')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-7.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-7-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-7.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-intermediate-exercise-7-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Document tag usage standards for project team.
- Nhiệm vụ người học:
  1. Normalize tag naming and avoid overlaps.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Normalize tag naming and avoid overlaps.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-7.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-7', 'step-intermediate-exercise-7-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-7.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A tagged suite that can run targeted subsets deterministically.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-6")$(if (@{Order=19; Id=intermediate-exercise-7; Title=Tags and Execution Slices; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-6; Objective=Organize scenarios by tags for smoke/regression/environment-specific execution slices.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses growing scenario set from previous exercises to create execution slices.; Contribution=Enables selective pipeline execution and faster feedback loops in project CI workflows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.options="--tags @smoke"; KeywordHint=@smoke; HintPath2=src/test/resources/features/intermediate-exercise-7.feature; Chips=System.Object[]; Subtitle=Slice suite execution with tags; ExpectedOutcome=A tagged suite that can run targeted subsets deterministically.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-7: Tags and Execution Slices
- heading: Tags and Execution Slices
- subtitle: Project capability: Slice suite execution with tags
- chips: [`tags`, `smoke`, `regression`, `selective execution`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/intermediate-exercise-7.feature
      ├─ src/test/java/com/mb/training/runner/TagRunner.java
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Tags and Execution Slices
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Tag taxonomy design
2. Implementation pattern - Selective run strategies
3. Common pitfall - Tag misuse pitfalls
4. Project continuity - CI pipeline alignment

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of intermediate-exercise-7?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/intermediate-exercise-7.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-6'?
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
  - [ ] id = "intermediate-exercise-7"
  - [ ] 	itle = "Tags and Execution Slices"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=19; Id=intermediate-exercise-7; Title=Tags and Execution Slices; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-6; Objective=Organize scenarios by tags for smoke/regression/environment-specific execution slices.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses growing scenario set from previous exercises to create execution slices.; Contribution=Enables selective pipeline execution and faster feedback loops in project CI workflows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.options="--tags @smoke"; KeywordHint=@smoke; HintPath2=src/test/resources/features/intermediate-exercise-7.feature; Chips=System.Object[]; Subtitle=Slice suite execution with tags; ExpectedOutcome=A tagged suite that can run targeted subsets deterministically.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-7-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-7-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



