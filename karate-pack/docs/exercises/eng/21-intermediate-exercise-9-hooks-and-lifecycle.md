# intermediate-exercise-9 - Hooks and Lifecycle

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-9 |
| Title | Hooks and Lifecycle |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-8 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Use lifecycle hooks for controlled diagnostics and cleanup behavior.

### Learner Outcomes
1. Implement hook functions in config
2. Capture useful scenario diagnostics
3. Apply safe cleanup logic
4. Avoid side effects from global hook code

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/intermediate-exercise-9.feature`

### Files/Folders Updated
- `src/test/java/karate-config.js`
- `src/test/resources/features/intermediate-exercise-9.feature`

### Dependency On Previous Exercises
- Uses stable parallel-ready setup from intermediate-exercise-8.

### Contribution To Final Functional Project
Adds operational observability and cleanup discipline for long-running project suites.

## 4) Detailed Step Design

### Step step-intermediate-exercise-9-01-setup - Prepare exercise assets
- Guidance: Add lifecycle behavior in config and exercise feature.
- Learner tasks:
  1. Define afterScenario style callback logic.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/intermediate-exercise-9.feature', template = '...')
- TrainingActivity.CodeTask('Define afterScenario style callback logic.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/intermediate-exercise-9.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-9.feature', snippet = 'afterScenario')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-9.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-9.feature', 'afterScenario')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-9-02-implement - Implement core behavior
- Guidance: Implement diagnostics/cleanup callbacks.
- Learner tasks:
  1. Log key debug context and cleanup safe artifacts.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Log key debug context and cleanup safe artifacts.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-9.feature', snippet = 'afterScenario')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/java/karate-config.js')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-9.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-9.feature', 'afterScenario')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-9-03-verify - Run and verify
- Guidance: Run and verify hook triggering behavior.
- Learner tasks:
  1. Run suite and verify hooks fire as expected.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'intermediate-exercise-9-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-9.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-9-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-9.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-9-04-harden - Harden for project continuity
- Guidance: Keep hook logic lightweight and deterministic.
- Learner tasks:
  1. Remove noisy or side-effect-prone hook code.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Remove noisy or side-effect-prone hook code.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-9.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-9', 'step-intermediate-exercise-9-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-9.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: Hook-enabled suite with predictable diagnostics and cleanup behavior.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-8")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-9: Hooks and Lifecycle
- heading: Hooks and Lifecycle
- subtitle: Project capability: Control diagnostics and cleanup with hooks
- chips: [`hooks`, `lifecycle`, `diagnostics`, `cleanup`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/intermediate-exercise-9.feature
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Hooks and Lifecycle
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Hook responsibilities
2. Implementation pattern - Lightweight diagnostics strategy
3. Common pitfall - Cleanup safety
4. Project continuity - Avoiding hook-induced flakiness

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-9?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/intermediate-exercise-9.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-8'?
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
  - [ ] id = "intermediate-exercise-9"
  - [ ] 	itle = "Hooks and Lifecycle"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "intermediate-exercise-8"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-9-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-9-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


