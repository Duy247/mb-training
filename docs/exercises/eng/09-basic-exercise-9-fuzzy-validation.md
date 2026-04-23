# basic-exercise-9 - Fuzzy Validation

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-9 |
| Title | Fuzzy Validation |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-8 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Use fuzzy markers and type-oriented checks for dynamic values in response payloads.

### Learner Outcomes
1. Apply #string, #number, and array markers
2. Handle dynamic IDs/timestamps safely
3. Use predicate-style checks where useful
4. Reduce brittle exact-value assertions

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-9.feature`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-9.feature`

### Dependency On Previous Exercises
- Builds on basic match operators from basic-exercise-8.

### Contribution To Final Functional Project
Prevents fragile tests and improves resilience as data variability increases in later flows.

## 4) Detailed Step Design

### Step step-basic-exercise-9-01-setup - Prepare exercise assets
- Guidance: Create schema-like validation scenarios with fuzzy markers.
- Learner tasks:
  1. Define expected fuzzy schema in feature.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-9.feature', template = '...')
- TrainingActivity.CodeTask('Define expected fuzzy schema in feature.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-9.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-9.feature', snippet = '#string')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-9.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-9.feature', '#string')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-9-02-implement - Implement core behavior
- Guidance: Implement type and shape assertions for dynamic payloads.
- Learner tasks:
  1. Apply fuzzy checks to real response payloads.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Apply fuzzy checks to real response payloads.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-9.feature', snippet = '#string')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-9.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-9.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-9.feature', '#string')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-9-03-verify - Run and verify
- Guidance: Run and validate robustness against changing values.
- Learner tasks:
  1. Run verification repeatedly for stability.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-9-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-9.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-9-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-9.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-9-04-harden - Harden for project continuity
- Guidance: Harden fuzzy rules to avoid over-permissive checks.
- Learner tasks:
  1. Tighten checks where validation is too loose.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Tighten checks where validation is too loose.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-9.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-9', 'step-basic-exercise-9-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-9.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A resilient validation style that handles dynamic payload values safely.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-8")$(if (@{Order=9; Id=basic-exercise-9; Title=Fuzzy Validation; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-8; Objective=Use fuzzy markers and type-oriented checks for dynamic values in response payloads.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on basic match operators from basic-exercise-8.; Contribution=Prevents fragile tests and improves resilience as data variability increases in later flows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=#string; HintPath2=src/test/resources/features/basic-exercise-9.feature; Chips=System.Object[]; Subtitle=Stabilize validations for dynamic responses; ExpectedOutcome=A resilient validation style that handles dynamic payload values safely.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-9: Fuzzy Validation
- heading: Fuzzy Validation
- subtitle: Project capability: Stabilize validations for dynamic responses
- chips: [`fuzzy match`, `types`, `dynamic data`, `schema-like checks`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-9.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Fuzzy Validation
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Fuzzy marker semantics
2. Implementation pattern - Schema-like assertion patterns
3. Common pitfall - Overly-loose validation risk
4. Project continuity - Applying this in contract-focused phases

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-9?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-9.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-8'?
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
  - [ ] id = "basic-exercise-9"
  - [ ] 	itle = "Fuzzy Validation"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=9; Id=basic-exercise-9; Title=Fuzzy Validation; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-8; Objective=Use fuzzy markers and type-oriented checks for dynamic values in response payloads.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on basic match operators from basic-exercise-8.; Contribution=Prevents fragile tests and improves resilience as data variability increases in later flows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=#string; HintPath2=src/test/resources/features/basic-exercise-9.feature; Chips=System.Object[]; Subtitle=Stabilize validations for dynamic responses; ExpectedOutcome=A resilient validation style that handles dynamic payload values safely.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-9-verify") matches TrainingCondition.CommandPassed("basic-exercise-9-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

