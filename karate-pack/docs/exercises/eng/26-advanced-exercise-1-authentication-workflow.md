# advanced-exercise-1 - Authentication Workflow

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | advanced-exercise-1 |
| Title | Authentication Workflow |
| Level | ADVANCED |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase C |
| Prerequisite Exercise IDs | intermediate-exercise-13 |
| Estimated Duration | 50-75 minutes |

## 2) Objective and Outcomes

### Objective
Validate complete authentication lifecycle including login, token use, and refresh behavior.

### Learner Outcomes
1. Execute login and capture access token
2. Apply token on protected APIs
3. Simulate/validate refresh path
4. Assert unauthorized fallback scenarios

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/advanced-exercise-1.feature`
- `src/test/resources/features/common/auth.feature`

### Files/Folders Updated
- `src/test/resources/features/common/auth.feature`
- `src/test/resources/features/advanced-exercise-1.feature`

### Dependency On Previous Exercises
- Builds on mock-first routing to test auth flows locally and optionally real env.

### Contribution To Final Functional Project
Establishes secure session flow foundation for all downstream business operations.

## 4) Detailed Step Design

### Step step-advanced-exercise-1-01-setup - Prepare exercise assets
- Guidance: Design end-to-end auth lifecycle scenarios.
- Learner tasks:
  1. Add auth helper feature and main workflow feature.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/advanced-exercise-1.feature', template = '...')
- TrainingActivity.CodeTask('Add auth helper feature and main workflow feature.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/advanced-exercise-1.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-1.feature', snippet = 'Authorization')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-1.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-1.feature', 'Authorization')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-advanced-exercise-1-02-implement - Implement core behavior
- Guidance: Implement token acquisition/use/refresh behavior.
- Learner tasks:
  1. Wire token sharing across steps safely.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Wire token sharing across steps safely.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-1.feature', snippet = 'Authorization')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/common/auth.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-1.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-1.feature', 'Authorization')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-advanced-exercise-1-03-verify - Run and verify
- Guidance: Run and verify auth behavior in mock mode.
- Learner tasks:
  1. Run and assert 200/401 transition behavior.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-1-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-1.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-1-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-1.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-advanced-exercise-1-04-harden - Harden for project continuity
- Guidance: Harden auth helper reusability.
- Learner tasks:
  1. Keep auth helper APIs consistent.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Keep auth helper APIs consistent.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-1.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-1', 'step-advanced-exercise-1-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-1.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A reusable auth workflow validated across protected endpoint scenarios.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-13")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-1: Authentication Workflow
- heading: Authentication Workflow
- subtitle: Project capability: Test full authentication lifecycle
- chips: [`authentication`, `token lifecycle`, `refresh`, `protected APIs`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/advanced-exercise-1.feature
      ├─ src/test/resources/features/common/auth.feature
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Authentication Workflow
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - Auth lifecycle model
2. Implementation pattern - Token propagation pattern
3. Common pitfall - Refresh handling pitfalls
4. Project continuity - Auth workflow reuse in business journey

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
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

## 9) Kotlin Mapping Checklist

- TrainingExercise fields:
  - [ ] id = "advanced-exercise-1"
  - [ ] 	itle = "Authentication Workflow"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "intermediate-exercise-13"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-1-verify") matches TrainingCondition.CommandPassed("advanced-exercise-1-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


