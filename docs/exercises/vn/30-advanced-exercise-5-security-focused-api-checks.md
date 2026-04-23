# advanced-exercise-5 - Kiểm thử API tập trung bảo mật

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | advanced-exercise-5 |
| Tiêu đề | Kiểm thử API tập trung bảo mật |
| Cấp độ | ADVANCED |
| Loại | EXERCISE |
| Mode | GUIDED |
| Giai đoạn | Phase C |
| Bài phụ thuộc | advanced-exercise-4 |
| Thời lượng ước tính | 50-75 minutes |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
Implement practical API security checks focused on authorization and abuse-path behavior.

### Kết quả người học đạt được
1. Validate object-level authorization boundaries
2. Verify role-based access controls
3. Probe abuse-prone endpoints safely
4. Assert secure error response semantics

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `src/test/resources/features/advanced-exercise-5.feature`

### File/Thư mục được cập nhật
- `src/test/resources/features/advanced-exercise-5.feature`

### Phụ thuộc vào bài trước
- Builds on negative/error matrix to add security-focused risk scenarios.

### Đóng góp cho dự án hoàn chỉnh cuối cùng
Raises project confidence against common API security misconfigurations.

## 4) Thiết kế chi tiết từng bước

### Bước step-advanced-exercise-5-01-setup - Chuẩn bị tài nguyên cho bài tập
- Hướng dẫn: Design security-focused scenario set.
- Nhiệm vụ người học:
  1. Add BOLA/BFLA-like authorization checks.
  2. Create or update required files under the same project.
- Đề xuất TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/advanced-exercise-5.feature', template = '...')
- TrainingActivity.CodeTask('Add BOLA/BFLA-like authorization checks.')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/advanced-exercise-5.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-5.feature', snippet = '403')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-5.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-5.feature', '403')
- Dấu hiệu hoàn thành mong đợi:
  - Core file exists and includes the expected DSL/pattern anchor.
### Bước step-advanced-exercise-5-02-implement - Triển khai hành vi cốt lõi
- Hướng dẫn: Implement authorization and abuse-path checks.
- Nhiệm vụ người học:
  1. Test invalid/over-privileged access attempts.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Test invalid/over-privileged access attempts.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Đề xuất TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-5.feature', snippet = '403')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/advanced-exercise-5.feature')
- Đề xuất doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-5.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-5.feature', '403')
- Dấu hiệu hoàn thành mong đợi:
  - Main exercise behavior is implemented and readable.
### Bước step-advanced-exercise-5-03-verify - Chạy và xác minh
- Hướng dẫn: Run and verify secure behavior expectations.
- Nhiệm vụ người học:
  1. Run and verify expected deny behavior.
  2. Investigate failures and fix deterministic issues before re-running.
- Đề xuất TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-5-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-5.feature', snippet = 'status / match assertions')
- Đề xuất doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-5-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-5.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Verification command passes and plugin can track command completion.
### Bước step-advanced-exercise-5-04-harden - Hoàn thiện để duy trì dự án
- Hướng dẫn: Stabilize security assertions for CI execution.
- Nhiệm vụ người học:
  1. Ensure no sensitive data leaks in errors.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Đề xuất TrainingActivity:
- TrainingActivity.CodeTask('Ensure no sensitive data leaks in errors.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Đề xuất TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-5.feature', snippet = 'clear naming + stable assertions')
- Đề xuất doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-5', 'step-advanced-exercise-5-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-5.feature')
- Dấu hiệu hoàn thành mong đợi:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Thiết kế điều kiện hoàn thành bài

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A focused security check suite covering key auth/authorization risk areas.
- Đề xuất startWhen:
  - TrainingCondition.ExerciseCompleted("advanced-exercise-4")$(if (@{Order=30; Id=advanced-exercise-5; Title=Security-Focused API Checks; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=advanced-exercise-4; Objective=Implement practical API security checks focused on authorization and abuse-path behavior.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on negative/error matrix to add security-focused risk scenarios.; Contribution=Raises project confidence against common API security misconfigurations.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=403; HintPath2=src/test/resources/features/advanced-exercise-5.feature; Chips=System.Object[]; Subtitle=Validate core API security behaviors; ExpectedOutcome=A focused security check suite covering key auth/authorization risk areas.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Bản nháp Intro Dialog (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-5: Security-Focused API Checks
- heading: Security-Focused API Checks
- subtitle: Project capability: Validate core API security behaviors
- chips: [`security`, `authorization`, `OWASP API`, `abuse paths`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/advanced-exercise-5.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Triển khai đúng mục tiêu bài tập vào các file trong dự án.
2) Xác minh hành vi bằng điều kiện deterministic và lệnh chạy.
3) Giữ kết quả có thể tái sử dụng cho các bài tiếp theo.
`

## 7) Bản nháp Knowledge Summary (TrainingKnowledgeSummary)

- 	itle: Tổng kết kiến thức - Security-Focused API Checks
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - Security test scope
2. Implementation pattern - Authorization boundary checks
3. Common pitfall - Abuse-path simulation
4. Project continuity - Security posture before capstone

## 8) Bản nháp Theory Quiz (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Ngân hàng câu hỏi (6 câu):
  1. Q: What is the primary goal of advanced-exercise-5?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/advanced-exercise-5.feature
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
  4. Q: Why does this exercise depend on 'advanced-exercise-4'?
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
  - [ ] id = "advanced-exercise-5"
  - [ ] 	itle = "Security-Focused API Checks"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=30; Id=advanced-exercise-5; Title=Security-Focused API Checks; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=advanced-exercise-4; Objective=Implement practical API security checks focused on authorization and abuse-path behavior.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on negative/error matrix to add security-focused risk scenarios.; Contribution=Raises project confidence against common API security misconfigurations.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=403; HintPath2=src/test/resources/features/advanced-exercise-5.feature; Chips=System.Object[]; Subtitle=Validate core API security behaviors; ExpectedOutcome=A focused security check suite covering key auth/authorization risk areas.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-5-verify") matches TrainingCondition.CommandPassed("advanced-exercise-5-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Checklist nghiệm thu

1. Bài tập có thể hoàn thành trong cùng một dự án đang xây dựng.
2. Tất cả điều kiện doneWhen đều deterministic và plugin theo dõi được.
3. Asset tạo ra được tái sử dụng ở các bài tiếp theo.
4. Lệnh xác minh có thể chạy lặp lại với kết quả ổn định.



