# intermediate-exercise-11 - Mock API Bootstrap

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-11 |
| Title | Mock API Bootstrap |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-10 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Create and run a local Karate mock API so exercises no longer depend on external/public APIs.

### Learner Outcomes
1. Implement first mock routes in Karate
2. Run mock server from project runner
3. Return deterministic mock responses
4. Point tests to local mock target

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/mock/mock-api.feature`
- `src/test/java/com/mb/training/mock/MockServerRunner.java`

### Files/Folders Updated
- `src/test/resources/mock/mock-api.feature`
- `src/test/java/com/mb/training/mock/MockServerRunner.java`

### Dependency On Previous Exercises
- Uses contract/feature foundations from previous exercises to define mock response behavior.

### Contribution To Final Functional Project
Introduces local test backend, making project self-contained and suitable for offline/local development.

## 4) Detailed Step Design

### Step step-intermediate-exercise-11-01-setup - Prepare exercise assets
- Guidance: Create initial mock API feature routes.
- Learner tasks:
  1. Define basic GET/POST mock routes.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/mock/mock-api.feature', template = '...')
- TrainingActivity.CodeTask('Define basic GET/POST mock routes.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/mock/mock-api.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'Scenario:')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- TrainingCondition.FileContains('src/test/resources/mock/mock-api.feature', 'Scenario:')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-11-02-implement - Implement core behavior
- Guidance: Add runner to start mock API locally.
- Learner tasks:
  1. Implement MockServer runner entry point.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Implement MockServer runner entry point.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'Scenario:')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/mock/mock-api.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- TrainingCondition.FileContains('src/test/resources/mock/mock-api.feature', 'Scenario:')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-11-03-verify - Run and verify
- Guidance: Run mock server command and validate route responses.
- Learner tasks:
  1. Run and hit mock endpoints in tests.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dtest=MockServerRunner', commandId = 'intermediate-exercise-11-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dtest=MockServerRunner')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-11-verify')
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-11-04-harden - Harden for project continuity
- Guidance: Stabilize route contracts for downstream tests.
- Learner tasks:
  1. Ensure response payloads are deterministic.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Ensure response payloads are deterministic.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-11', 'step-intermediate-exercise-11-03-verify')
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A runnable local mock API server with stable base routes.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-10")$(if (@{Order=23; Id=intermediate-exercise-11; Title=Mock API Bootstrap; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-10; Objective=Create and run a local Karate mock API so exercises no longer depend on external/public APIs.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses contract/feature foundations from previous exercises to define mock response behavior.; Contribution=Introduces local test backend, making project self-contained and suitable for offline/local development.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dtest=MockServerRunner; KeywordHint=Scenario:; HintPath2=src/test/resources/mock/mock-api.feature; Chips=System.Object[]; Subtitle=Stand up local Karate mock API; ExpectedOutcome=A runnable local mock API server with stable base routes.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-11: Mock API Bootstrap
- heading: Mock API Bootstrap
- subtitle: Project capability: Stand up local Karate mock API
- chips: [`mock API`, `local server`, `routes`, `deterministic responses`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/mock/mock-api.feature
      ├─ src/test/java/com/mb/training/mock/MockServerRunner.java
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Mock API Bootstrap
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Why local mock first
2. Implementation pattern - Mock route structure
3. Common pitfall - Runner startup pattern
4. Project continuity - How mock removes external dependency risk

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-11?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/mock/mock-api.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-10'?
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

## 9) Kotlin Mapping Checklist

- TrainingExercise fields:
  - [ ] id = "intermediate-exercise-11"
  - [ ] 	itle = "Mock API Bootstrap"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=23; Id=intermediate-exercise-11; Title=Mock API Bootstrap; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-10; Objective=Create and run a local Karate mock API so exercises no longer depend on external/public APIs.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses contract/feature foundations from previous exercises to define mock response behavior.; Contribution=Introduces local test backend, making project self-contained and suitable for offline/local development.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dtest=MockServerRunner; KeywordHint=Scenario:; HintPath2=src/test/resources/mock/mock-api.feature; Chips=System.Object[]; Subtitle=Stand up local Karate mock API; ExpectedOutcome=A runnable local mock API server with stable base routes.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-11-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-11-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

