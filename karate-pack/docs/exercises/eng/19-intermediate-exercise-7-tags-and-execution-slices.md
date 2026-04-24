# intermediate-exercise-7 - Tags and Execution Slices

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-7 |
| Title | Tags and Execution Slices |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-6 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Organize scenarios by tags for smoke/regression/environment-specific execution slices.

### Learner Outcomes
1. Define tag taxonomy
2. Annotate scenarios consistently
3. Run targeted suites by tag filters
4. Prepare suite segmentation for CI jobs

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/intermediate-exercise-7.feature`
- `src/test/java/com/mb/training/runner/TagRunner.java`

### Files/Folders Updated
- `src/test/resources/features/intermediate-exercise-7.feature`
- `src/test/java/com/mb/training/runner/TagRunner.java`

### Dependency On Previous Exercises
- Uses growing scenario set from previous exercises to create execution slices.

### Contribution To Final Functional Project
Enables selective pipeline execution and faster feedback loops in project CI workflows.

## 4) Detailed Step Design

### Step step-intermediate-exercise-7-01-setup - Prepare exercise assets
- Guidance: Define and apply tag conventions in feature files.
- Learner tasks:
  1. Tag scenarios with @smoke, @regression, etc.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/intermediate-exercise-7.feature', template = '...')
- TrainingActivity.CodeTask('Tag scenarios with @smoke, @regression, etc.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/intermediate-exercise-7.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-7.feature', snippet = '@smoke')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-7.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-7.feature', '@smoke')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-7-02-implement - Implement core behavior
- Guidance: Implement runner strategy for tag filters.
- Learner tasks:
  1. Create or update runner with tag options.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Create or update runner with tag options.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-7.feature', snippet = '@smoke')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/intermediate-exercise-7.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-7.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-7.feature', '@smoke')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-7-03-verify - Run and verify
- Guidance: Run smoke/regression slices to verify grouping.
- Learner tasks:
  1. Execute filtered command and verify selected tests.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.options="--tags @smoke"', commandId = 'intermediate-exercise-7-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.options="--tags @smoke"')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-7.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-7-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-7.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-7-04-harden - Harden for project continuity
- Guidance: Document tag usage standards for project team.
- Learner tasks:
  1. Normalize tag naming and avoid overlaps.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Normalize tag naming and avoid overlaps.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-7.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-7', 'step-intermediate-exercise-7-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-7.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A tagged suite that can run targeted subsets deterministically.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-6")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-7: Tags and Execution Slices
- heading: Tags and Execution Slices
- subtitle: Project capability: Slice suite execution with tags
- chips: [`tags`, `smoke`, `regression`, `selective execution`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/intermediate-exercise-7.feature
      ├─ src/test/java/com/mb/training/runner/TagRunner.java
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Tags and Execution Slices
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Tag taxonomy design
2. Implementation pattern - Selective run strategies
3. Common pitfall - Tag misuse pitfalls
4. Project continuity - CI pipeline alignment

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-7?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/intermediate-exercise-7.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-6'?
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
  - [ ] id = "intermediate-exercise-7"
  - [ ] 	itle = "Tags and Execution Slices"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "intermediate-exercise-6"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-7-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-7-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


