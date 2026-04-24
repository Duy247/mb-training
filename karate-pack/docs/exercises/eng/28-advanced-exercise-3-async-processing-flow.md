# advanced-exercise-3 - Async Processing Flow

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | advanced-exercise-3 |
| Title | Async Processing Flow |
| Level | ADVANCED |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase C |
| Prerequisite Exercise IDs | advanced-exercise-2 |
| Estimated Duration | 50-75 minutes |

## 2) Objective and Outcomes

### Objective
Validate asynchronous API operations using polling with retry-until semantics.

### Learner Outcomes
1. Trigger async job endpoint
2. Poll status endpoint with retry rules
3. Detect completion/failure states safely
4. Assert final output consistency

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/advanced-exercise-3.feature`

### Files/Folders Updated
- `src/test/resources/features/advanced-exercise-3.feature`

### Dependency On Previous Exercises
- Extends synchronous CRUD flow into async operation handling.

### Contribution To Final Functional Project
Adds realistic async validation pattern common in production API ecosystems.

## 4) Detailed Step Design

### Step step-advanced-exercise-3-01-setup - Prepare exercise assets
- Guidance: Create async submission + polling scenario.
- Learner tasks:
  1. Submit job and capture job identifier.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/advanced-exercise-3.feature', template = '...')
- TrainingActivity.CodeTask('Submit job and capture job identifier.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/advanced-exercise-3.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-3.feature', snippet = 'retry until')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-3.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-3.feature', 'retry until')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-advanced-exercise-3-02-implement - Implement core behavior
- Guidance: Implement retry-until logic with sane limits.
- Learner tasks:
  1. Poll status endpoint until done condition.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Poll status endpoint until done condition.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-3.feature', snippet = 'retry until')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/advanced-exercise-3.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-3.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-3.feature', 'retry until')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-advanced-exercise-3-03-verify - Run and verify
- Guidance: Run and verify eventual-completion behavior.
- Learner tasks:
  1. Run and assert completion/output integrity.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-3-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-3.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-3-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-3.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-advanced-exercise-3-04-harden - Harden for project continuity
- Guidance: Tune retry strategy for deterministic CI runs.
- Learner tasks:
  1. Adjust retry count/interval to avoid flakiness.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Adjust retry count/interval to avoid flakiness.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-3.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-3', 'step-advanced-exercise-3-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-3.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A deterministic async test flow with controlled polling and completion assertions.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("advanced-exercise-2")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-3: Async Processing Flow
- heading: Async Processing Flow
- subtitle: Project capability: Validate asynchronous processing APIs
- chips: [`async`, `polling`, `retry until`, `eventual consistency`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/advanced-exercise-3.feature
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Async Processing Flow
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - Async test architecture
2. Implementation pattern - Retry strategy tuning
3. Common pitfall - Failure-state handling
4. Project continuity - Integrating async checks in capstone

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of advanced-exercise-3?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/advanced-exercise-3.feature
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
  4. Q: Why does this exercise depend on 'advanced-exercise-2'?
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
  - [ ] id = "advanced-exercise-3"
  - [ ] 	itle = "Async Processing Flow"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "advanced-exercise-2"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-3-verify") matches TrainingCondition.CommandPassed("advanced-exercise-3-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


