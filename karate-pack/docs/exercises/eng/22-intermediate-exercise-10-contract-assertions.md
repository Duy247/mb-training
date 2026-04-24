# intermediate-exercise-10 - Contract Assertions

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-10 |
| Title | Contract Assertions |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-9 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Strengthen response contract validation using nested and collection-wide assertions.

### Learner Outcomes
1. Define reusable contract expectations
2. Use match each across lists
3. Validate nested object invariants
4. Separate contract checks from business checks

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/intermediate-exercise-10.feature`
- `src/test/resources/data/contracts/`

### Files/Folders Updated
- `src/test/resources/features/intermediate-exercise-10.feature`

### Dependency On Previous Exercises
- Builds on hook-enabled and parallel-safe test architecture.

### Contribution To Final Functional Project
Creates strong contract safety net before transitioning to local mock API and advanced flows.

## 4) Detailed Step Design

### Step step-intermediate-exercise-10-01-setup - Prepare exercise assets
- Guidance: Create contract-focused feature and data assets.
- Learner tasks:
  1. Add contract fixtures/snippets under data/contracts.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/intermediate-exercise-10.feature', template = '...')
- TrainingActivity.CodeTask('Add contract fixtures/snippets under data/contracts.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/intermediate-exercise-10.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-10.feature', snippet = 'match each')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-10.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-10.feature', 'match each')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-10-02-implement - Implement core behavior
- Guidance: Implement nested and list-wide contract assertions.
- Learner tasks:
  1. Apply match each and nested assertions.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Apply match each and nested assertions.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-10.feature', snippet = 'match each')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/intermediate-exercise-10.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-10.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-10.feature', 'match each')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-10-03-verify - Run and verify
- Guidance: Run and verify strictness without flakiness.
- Learner tasks:
  1. Run and inspect mismatches carefully.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'intermediate-exercise-10-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-10.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-10-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-10.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-10-04-harden - Harden for project continuity
- Guidance: Document reusable contract snippets.
- Learner tasks:
  1. Refactor contract blocks for reuse.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Refactor contract blocks for reuse.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-10.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-10', 'step-intermediate-exercise-10-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-10.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A reusable contract-validation layer for endpoint responses.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-9")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-10: Contract Assertions
- heading: Contract Assertions
- subtitle: Project capability: Establish stronger response contracts
- chips: [`contract`, `match each`, `nested checks`, `schema`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/intermediate-exercise-10.feature
      ├─ src/test/resources/data/contracts/
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Contract Assertions
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Contract vs behavior validation
2. Implementation pattern - Nested assertion strategy
3. Common pitfall - Collection-wide checks
4. Project continuity - Foundation for mock parity validation

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-10?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/intermediate-exercise-10.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-9'?
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
  - [ ] id = "intermediate-exercise-10"
  - [ ] 	itle = "Contract Assertions"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "intermediate-exercise-9"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-10-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-10-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


