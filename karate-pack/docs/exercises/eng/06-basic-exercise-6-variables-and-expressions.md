# basic-exercise-6 - Variables and Expressions

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-6 |
| Title | Variables and Expressions |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-5 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Use variables and expressions to make API scenarios dynamic and less repetitive.

### Learner Outcomes
1. Define and reuse variables with def
2. Interpolate values into path/body/query
3. Compute small derived values
4. Reduce hard-coded duplication

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-6.feature`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-6.feature`

### Dependency On Previous Exercises
- Builds directly on request and payload patterns from basic-exercise-5.

### Contribution To Final Functional Project
Enables maintainable test scripts where data and logic can evolve without rewriting whole scenarios.

## 4) Detailed Step Design

### Step step-basic-exercise-6-01-setup - Prepare exercise assets
- Guidance: Create dynamic test script with variable definitions.
- Learner tasks:
  1. Define reusable variables near scenario start.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-6.feature', template = '...')
- TrainingActivity.CodeTask('Define reusable variables near scenario start.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-6.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-6.feature', snippet = '* def')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-6.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-6.feature', '* def')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-6-02-implement - Implement core behavior
- Guidance: Apply interpolation and expression logic in request/assertion.
- Learner tasks:
  1. Use variables in path/query/body and matches.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Use variables in path/query/body and matches.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-6.feature', snippet = '* def')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-6.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-6.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-6.feature', '* def')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-6-03-verify - Run and verify
- Guidance: Run and verify dynamic behavior remains stable.
- Learner tasks:
  1. Run and ensure dynamic values behave as expected.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-6-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-6.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-6-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-6.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-6-04-harden - Harden for project continuity
- Guidance: Harden variable naming and scoping discipline.
- Learner tasks:
  1. Rename unclear vars and remove duplicated literals.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Rename unclear vars and remove duplicated literals.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-6.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-6', 'step-basic-exercise-6-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-6.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A cleaner scenario that uses variables and expressions to avoid repetition.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-5")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-6: Variables and Expressions
- heading: Variables and Expressions
- subtitle: Project capability: Make scripts dynamic with variables
- chips: [`def`, `variables`, `interpolation`, `expressions`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-6.feature
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Variables and Expressions
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Variable scope basics
2. Implementation pattern - Interpolation patterns
3. Common pitfall - Expression readability
4. Project continuity - Why dynamic scripts scale better

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-6?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-6.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-5'?
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
  - [ ] id = "basic-exercise-6"
  - [ ] 	itle = "Variables and Expressions"
  - [ ] level = TrainingLevel.BASIC
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "basic-exercise-5"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-6-verify") matches TrainingCondition.CommandPassed("basic-exercise-6-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


