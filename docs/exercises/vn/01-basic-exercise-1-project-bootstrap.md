# basic-exercise-1 - Khởi tạo dự án

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | basic-exercise-1 |
| Tiêu đề | Khởi tạo dự án |
| Cấp độ | BASIC |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase A |
| Bài phụ thuộc | none |
| Thời lượng ước tính | 30-45 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Initialize a Maven Karate project foundation with required structure, dependencies, and baseline run capability.

### Kết quả người học đạt được
1. Create mandatory Maven test folders for Karate
2. Configure Karate + Surefire in pom.xml
3. Run a baseline Maven test command successfully
4. Understand how this baseline enables every later exercise

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `pom.xml`
- `src/test/java/`
- `src/test/resources/features/`

### File/Thư mục được cập nhật
- `pom.xml`

### Phụ thuộc vào bài trước
- This is the first exercise and establishes base project conventions.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Creates the project skeleton and build pipeline required for all subsequent API scripting and mock/server exercises.

## 4) Thiết kế chi tiết từng bước

### Bước step-basic-exercise-1-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Set up foundational project files and folder structure.
- Nhiệm vụ người học:
  1. Create src/test/java and src/test/resources/features.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'pom.xml', template = '...')
- TrainingActivity.CodeTask('Create src/test/java and src/test/resources/features.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'pom.xml')
- TrainingHint.ContentHint(filePath = 'pom.xml', snippet = '<artifactId>karate-junit5</artifactId>')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('pom.xml')
- TrainingCondition.FileContains('pom.xml', '<artifactId>karate-junit5</artifactId>')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-basic-exercise-1-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Configure Maven dependencies and plugin configuration correctly.
- Nhiệm vụ người học:
  1. Add karate-junit5 dependency and maven-surefire-plugin.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Add karate-junit5 dependency and maven-surefire-plugin.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'pom.xml', snippet = '<artifactId>karate-junit5</artifactId>')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'pom.xml > dependencies/build')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('pom.xml')
- TrainingCondition.FileContains('pom.xml', '<artifactId>karate-junit5</artifactId>')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-basic-exercise-1-03-verify - Chạy và xác minh
- Hướng dẫn: Run Maven to validate bootstrap integrity.
- Nhiệm vụ người học:
  1. Execute baseline build/test command.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-1-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'pom.xml', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-1-verify')
- TrainingCondition.FileExists('pom.xml')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-basic-exercise-1-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Clean up naming/content so future exercises build on stable baseline.
- Nhiệm vụ người học:
  1. Ensure pom.xml and structure remain minimal and reusable.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Ensure pom.xml and structure remain minimal and reusable.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'pom.xml', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-1', 'step-basic-exercise-1-03-verify')
- TrainingCondition.FileExists('pom.xml')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A runnable Maven Karate project baseline ready for feature authoring.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("none")$(if (@{Order=1; Id=basic-exercise-1; Title=Project Bootstrap; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=none; Objective=Initialize a Maven Karate project foundation with required structure, dependencies, and baseline run capability.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=This is the first exercise and establishes base project conventions.; Contribution=Creates the project skeleton and build pipeline required for all subsequent API scripting and mock/server exercises.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=<artifactId>karate-junit5</artifactId>; HintPath2=pom.xml > dependencies/build; Chips=System.Object[]; Subtitle=Bootstrap Maven + Karate baseline; ExpectedOutcome=A runnable Maven Karate project baseline ready for feature authoring.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: basic-exercise-1: Project Bootstrap
- heading: Project Bootstrap
- subtitle: Project capability: Bootstrap Maven + Karate baseline
- chips: [`Maven`, `Karate`, `Surefire`, `Project Structure`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ pom.xml
      ├─ src/test/java/
      ├─ src/test/resources/features/
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Project Bootstrap
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Why bootstrap consistency matters
2. Implementation pattern - Minimal pom pattern for Karate tests
3. Common pitfall - Common dependency/plugin misconfigurations
4. Project continuity - How this baseline supports roadmap continuity

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of basic-exercise-1?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: pom.xml
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
  4. Q: Why does this exercise depend on 'none'?
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
  - [ ] id = "basic-exercise-1"
  - [ ] 	itle = "Project Bootstrap"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=1; Id=basic-exercise-1; Title=Project Bootstrap; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=none; Objective=Initialize a Maven Karate project foundation with required structure, dependencies, and baseline run capability.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=This is the first exercise and establishes base project conventions.; Contribution=Creates the project skeleton and build pipeline required for all subsequent API scripting and mock/server exercises.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=<artifactId>karate-junit5</artifactId>; HintPath2=pom.xml > dependencies/build; Chips=System.Object[]; Subtitle=Bootstrap Maven + Karate baseline; ExpectedOutcome=A runnable Maven Karate project baseline ready for feature authoring.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-1-verify") matches TrainingCondition.CommandPassed("basic-exercise-1-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



