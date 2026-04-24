# advanced-exercise-2 - Stateful CRUD Business Journey

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | advanced-exercise-2 |
| Title | Stateful CRUD Business Journey |
| Level | ADVANCED |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase C |
| Prerequisite Exercise IDs | advanced-exercise-1 |
| Estimated Duration | 50-75 minutes |

## 2) Objective and Outcomes

### Objective
Implement realistic business entity lifecycle testing across create, read, update, and delete operations.

### Learner Outcomes
1. Chain CRUD requests with shared entity context
2. Validate persisted state transitions
3. Check list/search side effects
4. Verify delete + idempotency behavior

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/advanced-exercise-2.feature`

### Files/Folders Updated
- `src/test/resources/features/advanced-exercise-2.feature`

### Dependency On Previous Exercises
- Uses authenticated workflow from advanced-exercise-1.

### Contribution To Final Functional Project
Forms the core of final project business-flow validation.

## 4) Detailed Step Design

### Step step-advanced-exercise-2-01-setup - Prepare exercise assets
- Guidance: Design full CRUD journey scenario.
- Learner tasks:
  1. Capture entity ID from create response.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/advanced-exercise-2.feature', template = '...')
- TrainingActivity.CodeTask('Capture entity ID from create response.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/advanced-exercise-2.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-2.feature', snippet = 'method post')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-2.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-2.feature', 'method post')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-advanced-exercise-2-02-implement - Implement core behavior
- Guidance: Implement chained create/read/update/delete steps.
- Learner tasks:
  1. Use ID in read/update/delete operations.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Use ID in read/update/delete operations.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-2.feature', snippet = 'method post')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/advanced-exercise-2.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-2.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-2.feature', 'method post')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-advanced-exercise-2-03-verify - Run and verify
- Guidance: Run and verify state transition correctness.
- Learner tasks:
  1. Run and verify each lifecycle stage.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-2-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-2.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-2-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-2.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-advanced-exercise-2-04-harden - Harden for project continuity
- Guidance: Stabilize journey assertions for reuse in capstone.
- Learner tasks:
  1. Add idempotent cleanup assertion.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Add idempotent cleanup assertion.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-2.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-2', 'step-advanced-exercise-2-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-2.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A reliable entity lifecycle flow with strong state-transition assertions.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("advanced-exercise-1")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-2: Stateful CRUD Business Journey
- heading: Stateful CRUD Business Journey
- subtitle: Project capability: Validate end-to-end CRUD journey
- chips: [`CRUD`, `state transitions`, `entity lifecycle`, `business flow`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/advanced-exercise-2.feature
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Stateful CRUD Business Journey
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - CRUD chain design
2. Implementation pattern - Entity context handling
3. Common pitfall - Transition validation strategy
4. Project continuity - Bridge to async and negative testing

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of advanced-exercise-2?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/advanced-exercise-2.feature
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
  4. Q: Why does this exercise depend on 'advanced-exercise-1'?
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
  - [ ] id = "advanced-exercise-2"
  - [ ] 	itle = "Stateful CRUD Business Journey"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "advanced-exercise-1"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-2-verify") matches TrainingCondition.CommandPassed("advanced-exercise-2-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


