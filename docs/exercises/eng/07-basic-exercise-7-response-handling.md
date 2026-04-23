# basic-exercise-7 - Response Handling

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-7 |
| Title | Response Handling |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-6 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Validate response metadata such as status, headers, and response time for richer API checks.

### Learner Outcomes
1. Use response metadata variables
2. Assert required headers
3. Set basic response time guard
4. Extract fields for later steps

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-7.feature`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-7.feature`

### Dependency On Previous Exercises
- Uses dynamic scripting from basic-exercise-6 to inspect response metadata.

### Contribution To Final Functional Project
Improves quality gates beyond payload values and introduces baseline non-functional checks.

## 4) Detailed Step Design

### Step step-basic-exercise-7-01-setup - Prepare exercise assets
- Guidance: Create response-handling focused feature scenario.
- Learner tasks:
  1. Capture responseStatus/responseHeaders/responseTime.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-7.feature', template = '...')
- TrainingActivity.CodeTask('Capture responseStatus/responseHeaders/responseTime.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-7.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-7.feature', snippet = 'responseHeaders')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-7.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-7.feature', 'responseHeaders')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-7-02-implement - Implement core behavior
- Guidance: Implement header/time/status assertions.
- Learner tasks:
  1. Add critical header and SLA-lite checks.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Add critical header and SLA-lite checks.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-7.feature', snippet = 'responseHeaders')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-7.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-7.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-7.feature', 'responseHeaders')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-7-03-verify - Run and verify
- Guidance: Run and verify metadata checks.
- Learner tasks:
  1. Run command and validate pass reliability.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-7-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-7.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-7-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-7.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-7-04-harden - Harden for project continuity
- Guidance: Tune thresholds and assertions for deterministic stability.
- Learner tasks:
  1. Adjust brittle thresholds where necessary.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Adjust brittle thresholds where necessary.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-7.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-7', 'step-basic-exercise-7-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-7.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A scenario that validates API metadata and basic timing expectations.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-6")$(if (@{Order=7; Id=basic-exercise-7; Title=Response Handling; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-6; Objective=Validate response metadata such as status, headers, and response time for richer API checks.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses dynamic scripting from basic-exercise-6 to inspect response metadata.; Contribution=Improves quality gates beyond payload values and introduces baseline non-functional checks.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=responseHeaders; HintPath2=src/test/resources/features/basic-exercise-7.feature; Chips=System.Object[]; Subtitle=Validate response metadata; ExpectedOutcome=A scenario that validates API metadata and basic timing expectations.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-7: Response Handling
- heading: Response Handling
- subtitle: Project capability: Validate response metadata
- chips: [`responseStatus`, `responseHeaders`, `responseTime`, `assertions`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-7.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Response Handling
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Metadata assertions that matter
2. Implementation pattern - Header verification strategy
3. Common pitfall - Avoiding flaky response time checks
4. Project continuity - Using extracted fields downstream

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-7?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-7.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-6'?
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
  - [ ] id = "basic-exercise-7"
  - [ ] 	itle = "Response Handling"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=7; Id=basic-exercise-7; Title=Response Handling; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-6; Objective=Validate response metadata such as status, headers, and response time for richer API checks.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses dynamic scripting from basic-exercise-6 to inspect response metadata.; Contribution=Improves quality gates beyond payload values and introduces baseline non-functional checks.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=responseHeaders; HintPath2=src/test/resources/features/basic-exercise-7.feature; Chips=System.Object[]; Subtitle=Validate response metadata; ExpectedOutcome=A scenario that validates API metadata and basic timing expectations.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-7-verify") matches TrainingCondition.CommandPassed("basic-exercise-7-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

