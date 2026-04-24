# basic-exercise-5 - Request Body Basics

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-5 |
| Title | Request Body Basics |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-4 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Send POST requests with JSON bodies and validate response payload content.

### Learner Outcomes
1. Build JSON request payload
2. Use request + method post sequence
3. Validate response body fields
4. Add one negative payload check

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-5.feature`
- `src/test/resources/data/basic-request.json`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-5.feature`

### Dependency On Previous Exercises
- Extends basic request flow from basic-exercise-4 by introducing body payloads.

### Contribution To Final Functional Project
Introduces payload-driven interaction required for create/update operations in later CRUD exercises.

## 4) Detailed Step Design

### Step step-basic-exercise-5-01-setup - Prepare exercise assets
- Guidance: Prepare JSON payload assets under resources.
- Learner tasks:
  1. Create body file basic-request.json.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-5.feature', template = '...')
- TrainingActivity.CodeTask('Create body file basic-request.json.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-5.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-5.feature', snippet = 'And request')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-5.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-5.feature', 'And request')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-5-02-implement - Implement core behavior
- Guidance: Implement POST flow with body and response checks.
- Learner tasks:
  1. Send POST and assert created response fields.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Send POST and assert created response fields.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-5.feature', snippet = 'And request')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/data/basic-request.json')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-5.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-5.feature', 'And request')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-5-03-verify - Run and verify
- Guidance: Run verification and fix schema/value mismatches.
- Learner tasks:
  1. Run command and confirm deterministic pass.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-5-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-5.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-5-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-5.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-5-04-harden - Harden for project continuity
- Guidance: Keep payload structure reusable for later CRUD tests.
- Learner tasks:
  1. Refactor naming/value anchors for future reuse.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Refactor naming/value anchors for future reuse.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-5.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-5', 'step-basic-exercise-5-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-5.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A stable POST scenario with request/response content validation.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-4")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-5: Request Body Basics
- heading: Request Body Basics
- subtitle: Project capability: Send and validate request body payloads
- chips: [`POST`, `JSON Body`, `request`, `response`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-5.feature
      ├─ src/test/resources/data/basic-request.json
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Request Body Basics
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Request body construction
2. Implementation pattern - Inline vs external payload files
3. Common pitfall - Payload mismatch debugging
4. Project continuity - Reusing payload assets in business flows

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-5?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-5.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-4'?
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
  - [ ] id = "basic-exercise-5"
  - [ ] 	itle = "Request Body Basics"
  - [ ] level = TrainingLevel.BASIC
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "basic-exercise-4"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-5-verify") matches TrainingCondition.CommandPassed("basic-exercise-5-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


