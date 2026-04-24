# basic-exercise-12 - Mini Checkpoint Mission

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-12 |
| Title | Mini Checkpoint Mission |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-11 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Combine all basic skills into a mini end-to-end API mission inside the same project.

### Learner Outcomes
1. Chain multiple API steps coherently
2. Reuse data across mini flow
3. Validate both status and business assertions
4. Produce a checkpoint-ready feature pack

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-12.feature`
- `src/test/resources/features/common/`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-12.feature`

### Dependency On Previous Exercises
- Composes all basic exercises into one integrated mission.

### Contribution To Final Functional Project
Serves as phase checkpoint before moving to reusable architecture and mock API strategy.

## 4) Detailed Step Design

### Step step-basic-exercise-12-01-setup - Prepare exercise assets
- Guidance: Design a mini flow scenario sequence for checkpoint.
- Learner tasks:
  1. Create checkpoint feature with multi-step mission.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-12.feature', template = '...')
- TrainingActivity.CodeTask('Create checkpoint feature with multi-step mission.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-12.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-12.feature', snippet = 'Scenario:')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-12.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-12.feature', 'Scenario:')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-12-02-implement - Implement core behavior
- Guidance: Implement chained steps with shared context data.
- Learner tasks:
  1. Chain create/read/update-like actions in one storyline.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Chain create/read/update-like actions in one storyline.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-12.feature', snippet = 'Scenario:')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-12.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-12.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-12.feature', 'Scenario:')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-12-03-verify - Run and verify
- Guidance: Run full checkpoint and verify all assertions.
- Learner tasks:
  1. Run and confirm deterministic mission pass.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-12-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-12.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-12-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-12.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-12-04-harden - Harden for project continuity
- Guidance: Refine flow structure for transition to intermediate level.
- Learner tasks:
  1. Clean scenario naming and split helper pieces if needed.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Clean scenario naming and split helper pieces if needed.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-12.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-12', 'step-basic-exercise-12-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-12.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A functional mini-flow feature proving readiness for intermediate phase.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-11")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-12: Mini Checkpoint Mission
- heading: Mini Checkpoint Mission
- subtitle: Project capability: Integrate basic skills into one mission
- chips: [`checkpoint`, `mini flow`, `integration`, `reuse`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-12.feature
      ├─ src/test/resources/features/common/
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Mini Checkpoint Mission
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Flow composition strategy
2. Implementation pattern - Context handoff between steps
3. Common pitfall - Checkpoint pass criteria
4. Project continuity - Bridge to reusable architecture phase

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-12?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-12.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-11'?
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
  - [ ] id = "basic-exercise-12"
  - [ ] 	itle = "Mini Checkpoint Mission"
  - [ ] level = TrainingLevel.BASIC
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "basic-exercise-11"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-12-verify") matches TrainingCondition.CommandPassed("basic-exercise-12-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


