# basic-exercise-3 - Runner Path Fix (Scenario)

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-3 |
| Title | Runner Path Fix (Scenario) |
| Level | BASIC |
| Type | EXERCISE |
| Mode | SCENARIO |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-2 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Diagnose and fix a broken runner classpath in a scenario sandbox so tests execute successfully.

### Learner Outcomes
1. Understand runner-to-feature classpath linkage
2. Identify path mismatch root cause
3. Apply deterministic fix in runner
4. Validate with targeted Maven runner command

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/java/com/mb/training/scenario/TestRunner.java`
- `src/test/resources/features/scenario-runner-path-fix.feature`

### Files/Folders Updated
- `src/test/java/com/mb/training/scenario/TestRunner.java`

### Dependency On Previous Exercises
- Builds on feature syntax awareness from basic-exercise-2.

### Contribution To Final Functional Project
Strengthens debugging confidence and prepares learners for scenario-mode exercises with temporary workspaces.

## 4) Detailed Step Design

### Step step-basic-exercise-3-01-setup - Prepare exercise assets
- Guidance: Open scenario workspace and inspect failing runner setup.
- Learner tasks:
  1. Set up sandbox and locate TestRunner.java.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/java/com/mb/training/scenario/TestRunner.java', template = '...')
- TrainingActivity.CodeTask('Set up sandbox and locate TestRunner.java.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/java/com/mb/training/scenario/TestRunner.java')
- TrainingHint.ContentHint(filePath = 'src/test/java/com/mb/training/scenario/TestRunner.java', snippet = 'classpath:features/')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/java/com/mb/training/scenario/TestRunner.java')
- TrainingCondition.FileContains('src/test/java/com/mb/training/scenario/TestRunner.java', 'classpath:features/')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-3-02-implement - Implement core behavior
- Guidance: Fix incorrect classpath in runner method.
- Learner tasks:
  1. Replace wrong feature path with correct classpath reference.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Replace wrong feature path with correct classpath reference.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/java/com/mb/training/scenario/TestRunner.java', snippet = 'classpath:features/')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/java/com/mb/training/scenario/TestRunner.java')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/java/com/mb/training/scenario/TestRunner.java')
- TrainingCondition.FileContains('src/test/java/com/mb/training/scenario/TestRunner.java', 'classpath:features/')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-3-03-verify - Run and verify
- Guidance: Run targeted test to verify correction.
- Learner tasks:
  1. Execute mvn test -Dtest=TestRunner and confirm pass.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dtest=TestRunner', commandId = 'basic-exercise-3-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dtest=TestRunner')
- TrainingHint.ContentHint(filePath = 'src/test/java/com/mb/training/scenario/TestRunner.java', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-3-verify')
- TrainingCondition.FileExists('src/test/java/com/mb/training/scenario/TestRunner.java')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-3-04-harden - Harden for project continuity
- Guidance: Capture fix pattern for future runner troubleshooting.
- Learner tasks:
  1. Ensure runner code is clean and explicit.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Ensure runner code is clean and explicit.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/java/com/mb/training/scenario/TestRunner.java', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-3', 'step-basic-exercise-3-03-verify')
- TrainingCondition.FileExists('src/test/java/com/mb/training/scenario/TestRunner.java')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: Scenario runner executes successfully after classpath correction.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-2")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-3: Runner Path Fix (Scenario)
- heading: Runner Path Fix (Scenario)
- subtitle: Project capability: Fix runner classpath in sandbox
- chips: [`Runner`, `Classpath`, `Debug`, `Scenario`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/java/com/mb/training/scenario/TestRunner.java
      ├─ src/test/resources/features/scenario-runner-path-fix.feature
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Runner Path Fix (Scenario)
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Runner-path relationship
2. Implementation pattern - Fast troubleshooting checklist
3. Common pitfall - Typical classpath mistakes
4. Project continuity - How scenario mode fits project progression

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-3?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/java/com/mb/training/scenario/TestRunner.java
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
  4. Q: Why does this exercise depend on 'basic-exercise-2'?
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
  - [ ] id = "basic-exercise-3"
  - [ ] 	itle = "Runner Path Fix (Scenario)"
  - [ ] level = TrainingLevel.BASIC
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.SCENARIO
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "basic-exercise-2"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-3-verify") matches TrainingCondition.CommandPassed("basic-exercise-3-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


