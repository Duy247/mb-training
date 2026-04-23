# advanced-exercise-1 - Luồng xác thực

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | advanced-exercise-1 |
| Tiêu đề | Luồng xác thực |
| Cấp độ | ADVANCED |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase C |
| Bài phụ thuộc | intermediate-exercise-13 |
| Thời lượng ước tính | 50-75 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Validate complete authentication lifecycle including login, token use, and refresh behavior.

### Kết quả người học đạt được
1. Execute login and capture access token
2. Apply token on protected APIs
3. Simulate/validate refresh path
4. Assert unauthorized fallback scenarios

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/advanced-exercise-1.feature`
- `src/test/resources/features/common/auth.feature`

### File/Thư mục được cập nhật
- `src/test/resources/features/common/auth.feature`
- `src/test/resources/features/advanced-exercise-1.feature`

### Phụ thuộc vào bài trước
- Builds on mock-first routing to test auth flows locally and optionally real env.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Establishes secure session flow foundation for all downstream business operations.

## 4) Thiết kế chi tiết từng bước

### Bước step-advanced-exercise-1-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Design end-to-end auth lifecycle scenarios.
- Nhiệm vụ người học:
  1. Add auth helper feature and main workflow feature.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/advanced-exercise-1.feature', template = '...')
- TrainingActivity.CodeTask('Add auth helper feature and main workflow feature.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/advanced-exercise-1.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-1.feature', snippet = 'Authorization')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-1.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-1.feature', 'Authorization')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-advanced-exercise-1-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement token acquisition/use/refresh behavior.
- Nhiệm vụ người học:
  1. Wire token sharing across steps safely.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Wire token sharing across steps safely.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-1.feature', snippet = 'Authorization')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/common/auth.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-1.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-1.feature', 'Authorization')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-advanced-exercise-1-03-verify - Chạy và xác minh
- Hướng dẫn: Run and verify auth behavior in mock mode.
- Nhiệm vụ người học:
  1. Run and assert 200/401 transition behavior.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-1-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-1.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-1-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-1.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-advanced-exercise-1-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Harden auth helper reusability.
- Nhiệm vụ người học:
  1. Keep auth helper APIs consistent.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Keep auth helper APIs consistent.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-1.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-1', 'step-advanced-exercise-1-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-1.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A reusable auth workflow validated across protected endpoint scenarios.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-13")$(if (@{Order=26; Id=advanced-exercise-1; Title=Authentication Workflow; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=intermediate-exercise-13; Objective=Validate complete authentication lifecycle including login, token use, and refresh behavior.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on mock-first routing to test auth flows locally and optionally real env.; Contribution=Establishes secure session flow foundation for all downstream business operations.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=Authorization; HintPath2=src/test/resources/features/common/auth.feature; Chips=System.Object[]; Subtitle=Test full authentication lifecycle; ExpectedOutcome=A reusable auth workflow validated across protected endpoint scenarios.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-1: Authentication Workflow
- heading: Authentication Workflow
- subtitle: Project capability: Test full authentication lifecycle
- chips: [`authentication`, `token lifecycle`, `refresh`, `protected APIs`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/advanced-exercise-1.feature
      ├─ src/test/resources/features/common/auth.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Authentication Workflow
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - Auth lifecycle model
2. Implementation pattern - Token propagation pattern
3. Common pitfall - Refresh handling pitfalls
4. Project continuity - Auth workflow reuse in business journey

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of advanced-exercise-1?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/advanced-exercise-1.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-13'?
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
  - [ ] id = "advanced-exercise-1"
  - [ ] 	itle = "Authentication Workflow"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=26; Id=advanced-exercise-1; Title=Authentication Workflow; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=intermediate-exercise-13; Objective=Validate complete authentication lifecycle including login, token use, and refresh behavior.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on mock-first routing to test auth flows locally and optionally real env.; Contribution=Establishes secure session flow foundation for all downstream business operations.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=Authorization; HintPath2=src/test/resources/features/common/auth.feature; Chips=System.Object[]; Subtitle=Test full authentication lifecycle; ExpectedOutcome=A reusable auth workflow validated across protected endpoint scenarios.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-1-verify") matches TrainingCondition.CommandPassed("advanced-exercise-1-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



