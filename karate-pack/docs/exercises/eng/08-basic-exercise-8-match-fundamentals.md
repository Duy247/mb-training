# basic-exercise-8 - Match Fundamentals

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-8 |
| Title | Match Fundamentals |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-7 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Apply strict and partial matching assertions for reliable response validation.

### Learner Outcomes
1. Use match == for exact checks
2. Use contains for partial object checks
3. Use contains only for set-like lists
4. Improve assertion intent clarity

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-8.feature`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-8.feature`

### Dependency On Previous Exercises
- Leverages response handling foundations from basic-exercise-7.

### Contribution To Final Functional Project
Establishes robust assertion style used repeatedly in contract and business-flow validation.

## 4) Detailed Step Design

### Step step-basic-exercise-8-01-setup - Prepare exercise assets
- Guidance: Create assertion-focused feature examples.
- Learner tasks:
  1. Add exact match cases for strict fields.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-8.feature', template = '...')
- TrainingActivity.CodeTask('Add exact match cases for strict fields.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-8.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-8.feature', snippet = 'match')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-8.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-8.feature', 'match')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-8-02-implement - Implement core behavior
- Guidance: Implement exact and partial match strategies.
- Learner tasks:
  1. Add contains/contains only cases for flexible checks.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Add contains/contains only cases for flexible checks.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-8.feature', snippet = 'match')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-8.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-8.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-8.feature', 'match')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-8-03-verify - Run and verify
- Guidance: Run and confirm assertions fail/pass correctly.
- Learner tasks:
  1. Run command and validate assertion behavior.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-8-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-8.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-8-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-8.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-8-04-harden - Harden for project continuity
- Guidance: Refine assertion style for maintainability.
- Learner tasks:
  1. Group and comment assertions for readability.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Group and comment assertions for readability.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-8.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-8', 'step-basic-exercise-8-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-8.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A test script using the right match operator for each assertion intent.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-7")$(if (@{Order=8; Id=basic-exercise-8; Title=Match Fundamentals; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-7; Objective=Apply strict and partial matching assertions for reliable response validation.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Leverages response handling foundations from basic-exercise-7.; Contribution=Establishes robust assertion style used repeatedly in contract and business-flow validation.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=match; HintPath2=src/test/resources/features/basic-exercise-8.feature; Chips=System.Object[]; Subtitle=Master core Karate match assertions; ExpectedOutcome=A test script using the right match operator for each assertion intent.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-8: Match Fundamentals
- heading: Match Fundamentals
- subtitle: Project capability: Master core Karate match assertions
- chips: [`match ==`, `contains`, `contains only`, `assertion style`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-8.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Match Fundamentals
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Choosing the right match operator
2. Implementation pattern - Balancing strictness and flexibility
3. Common pitfall - List/object assertion mistakes
4. Project continuity - How these checks support contracts later

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-8?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-8.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-7'?
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
  - [ ] id = "basic-exercise-8"
  - [ ] 	itle = "Match Fundamentals"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=8; Id=basic-exercise-8; Title=Match Fundamentals; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-7; Objective=Apply strict and partial matching assertions for reliable response validation.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Leverages response handling foundations from basic-exercise-7.; Contribution=Establishes robust assertion style used repeatedly in contract and business-flow validation.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=match; HintPath2=src/test/resources/features/basic-exercise-8.feature; Chips=System.Object[]; Subtitle=Master core Karate match assertions; ExpectedOutcome=A test script using the right match operator for each assertion intent.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-8-verify") matches TrainingCondition.CommandPassed("basic-exercise-8-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

