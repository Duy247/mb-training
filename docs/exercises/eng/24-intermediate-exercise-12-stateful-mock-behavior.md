# intermediate-exercise-12 - Stateful Mock Behavior

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-12 |
| Title | Stateful Mock Behavior |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-11 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Add stateful behavior to mock API for realistic CRUD/auth workflows.

### Learner Outcomes
1. Model in-memory entity state
2. Implement create/read/update/delete route behavior
3. Add simple auth-protected behavior
4. Reset mock state predictably between runs

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/mock/mock-api.feature`
- `src/test/resources/mock/state-utils.js`

### Files/Folders Updated
- `src/test/resources/mock/mock-api.feature`
- `src/test/resources/mock/state-utils.js`

### Dependency On Previous Exercises
- Extends mock bootstrap routes into realistic stateful interactions.

### Contribution To Final Functional Project
Provides realistic local backend behavior needed for complete business-flow testing in project.

## 4) Detailed Step Design

### Step step-intermediate-exercise-12-01-setup - Prepare exercise assets
- Guidance: Extend mock routes to maintain state transitions.
- Learner tasks:
  1. Create in-memory store helpers.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/mock/mock-api.feature', template = '...')
- TrainingActivity.CodeTask('Create in-memory store helpers.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/mock/mock-api.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'def store')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- TrainingCondition.FileContains('src/test/resources/mock/mock-api.feature', 'def store')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-12-02-implement - Implement core behavior
- Guidance: Implement auth and CRUD behavior in mock.
- Learner tasks:
  1. Wire store operations into routes.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Wire store operations into routes.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'def store')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/mock/state-utils.js')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- TrainingCondition.FileContains('src/test/resources/mock/mock-api.feature', 'def store')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-12-03-verify - Run and verify
- Guidance: Run and verify stateful operations deterministically.
- Learner tasks:
  1. Run CRUD/auth scenarios against mock.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dtest=MockServerRunner', commandId = 'intermediate-exercise-12-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dtest=MockServerRunner')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-12-verify')
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-12-04-harden - Harden for project continuity
- Guidance: Add clean state-reset strategy.
- Learner tasks:
  1. Reset state per run/suite to avoid drift.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Reset state per run/suite to avoid drift.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/mock/mock-api.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-12', 'step-intermediate-exercise-12-03-verify')
- TrainingCondition.FileExists('src/test/resources/mock/mock-api.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A stateful local mock API supporting realistic CRUD and auth test flows.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-11")$(if (@{Order=24; Id=intermediate-exercise-12; Title=Stateful Mock Behavior; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-11; Objective=Add stateful behavior to mock API for realistic CRUD/auth workflows.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends mock bootstrap routes into realistic stateful interactions.; Contribution=Provides realistic local backend behavior needed for complete business-flow testing in project.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dtest=MockServerRunner; KeywordHint=def store; HintPath2=src/test/resources/mock/state-utils.js; Chips=System.Object[]; Subtitle=Make mock API behavior realistic; ExpectedOutcome=A stateful local mock API supporting realistic CRUD and auth test flows.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-12: Stateful Mock Behavior
- heading: Stateful Mock Behavior
- subtitle: Project capability: Make mock API behavior realistic
- chips: [`stateful mock`, `CRUD`, `auth`, `in-memory state`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/mock/mock-api.feature
      ├─ src/test/resources/mock/state-utils.js
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Stateful Mock Behavior
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - State model design
2. Implementation pattern - Route-to-state mapping
3. Common pitfall - State reset reliability
4. Project continuity - Preparing for capstone flow execution

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-12?
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-11'?
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
  - [ ] id = "intermediate-exercise-12"
  - [ ] 	itle = "Stateful Mock Behavior"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=24; Id=intermediate-exercise-12; Title=Stateful Mock Behavior; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-11; Objective=Add stateful behavior to mock API for realistic CRUD/auth workflows.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends mock bootstrap routes into realistic stateful interactions.; Contribution=Provides realistic local backend behavior needed for complete business-flow testing in project.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dtest=MockServerRunner; KeywordHint=def store; HintPath2=src/test/resources/mock/state-utils.js; Chips=System.Object[]; Subtitle=Make mock API behavior realistic; ExpectedOutcome=A stateful local mock API supporting realistic CRUD and auth test flows.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-12-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-12-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

