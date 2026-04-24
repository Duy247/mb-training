# basic-exercise-11 - Basic Auth Patterns

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-11 |
| Title | Basic Auth Patterns |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-10 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Apply basic authentication patterns (token/api-key) to access protected endpoints.

### Learner Outcomes
1. Obtain or configure auth credentials
2. Attach Authorization/API-key headers
3. Validate authorized vs unauthorized behavior
4. Prepare reusable auth setup for future flows

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-11.feature`
- `src/test/resources/data/auth.json`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-11.feature`

### Dependency On Previous Exercises
- Uses query/header foundations from basic-exercise-10.

### Contribution To Final Functional Project
Unlocks protected endpoint testing needed for business workflows and security checks.

## 4) Detailed Step Design

### Step step-basic-exercise-11-01-setup - Prepare exercise assets
- Guidance: Prepare auth data/configuration for protected API usage.
- Learner tasks:
  1. Create or load auth payload/config.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-11.feature', template = '...')
- TrainingActivity.CodeTask('Create or load auth payload/config.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-11.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-11.feature', snippet = 'Authorization')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-11.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-11.feature', 'Authorization')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-11-02-implement - Implement core behavior
- Guidance: Implement authorized and unauthorized scenarios.
- Learner tasks:
  1. Add Authorization or API-key handling in feature.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Add Authorization or API-key handling in feature.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-11.feature', snippet = 'Authorization')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-11.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-11.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-11.feature', 'Authorization')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-11-03-verify - Run and verify
- Guidance: Run and verify access-control behavior.
- Learner tasks:
  1. Run verification and assert 200 vs 401/403 paths.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-11-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-11.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-11-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-11.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-11-04-harden - Harden for project continuity
- Guidance: Refactor auth setup to be reusable in advanced flows.
- Learner tasks:
  1. Extract shared auth pattern for reuse.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Extract shared auth pattern for reuse.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-11.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-11', 'step-basic-exercise-11-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-11.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A reliable auth test pattern covering positive and negative access cases.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-10")$(if (@{Order=11; Id=basic-exercise-11; Title=Basic Auth Patterns; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-10; Objective=Apply basic authentication patterns (token/api-key) to access protected endpoints.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses query/header foundations from basic-exercise-10.; Contribution=Unlocks protected endpoint testing needed for business workflows and security checks.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=Authorization; HintPath2=src/test/resources/features/basic-exercise-11.feature; Chips=System.Object[]; Subtitle=Authenticate requests to protected APIs; ExpectedOutcome=A reliable auth test pattern covering positive and negative access cases.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-11: Basic Auth Patterns
- heading: Basic Auth Patterns
- subtitle: Project capability: Authenticate requests to protected APIs
- chips: [`auth`, `token`, `API key`, `access control`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-11.feature
      ├─ src/test/resources/data/auth.json
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Basic Auth Patterns
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Auth pattern essentials
2. Implementation pattern - Token/header attachment strategy
3. Common pitfall - Unauthorized test expectations
4. Project continuity - How auth setup feeds capstone workflows

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-11?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-11.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-10'?
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
  - [ ] id = "basic-exercise-11"
  - [ ] 	itle = "Basic Auth Patterns"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=11; Id=basic-exercise-11; Title=Basic Auth Patterns; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-10; Objective=Apply basic authentication patterns (token/api-key) to access protected endpoints.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses query/header foundations from basic-exercise-10.; Contribution=Unlocks protected endpoint testing needed for business workflows and security checks.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=Authorization; HintPath2=src/test/resources/features/basic-exercise-11.feature; Chips=System.Object[]; Subtitle=Authenticate requests to protected APIs; ExpectedOutcome=A reliable auth test pattern covering positive and negative access cases.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-11-verify") matches TrainingCondition.CommandPassed("basic-exercise-11-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

