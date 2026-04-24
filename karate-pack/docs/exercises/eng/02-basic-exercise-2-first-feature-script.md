# basic-exercise-2 - First Feature Script

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-2 |
| Title | First Feature Script |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-1 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Create the first valid Karate feature file using Feature, Background, and Scenario blocks with simple executable DSL.

### Learner Outcomes
1. Write valid Gherkin structure for Karate
2. Use Background for shared setup lines
3. Execute simple print steps
4. Store first feature under correct classpath

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-2.feature`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-2.feature`

### Dependency On Previous Exercises
- Reuses project structure and Maven setup from basic-exercise-1.

### Contribution To Final Functional Project
Introduces the first executable test artifact; later exercises extend this syntax into real HTTP flows.

## 4) Detailed Step Design

### Step step-basic-exercise-2-01-setup - Prepare exercise assets
- Guidance: Create the first feature file in the expected path.
- Learner tasks:
  1. Add basic-exercise-2.feature under src/test/resources/features.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-2.feature', template = '...')
- TrainingActivity.CodeTask('Add basic-exercise-2.feature under src/test/resources/features.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-2.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-2.feature', snippet = 'Scenario:')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-2.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-2.feature', 'Scenario:')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-2-02-implement - Implement core behavior
- Guidance: Implement valid Feature/Background/Scenario sections.
- Learner tasks:
  1. Write simple Background and Scenario with print.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Write simple Background and Scenario with print.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-2.feature', snippet = 'Scenario:')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-2.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-2.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-2.feature', 'Scenario:')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-2-03-verify - Run and verify
- Guidance: Run tests to confirm feature parsing/execution.
- Learner tasks:
  1. Run the verification command and ensure pass.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-2-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-2.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-2-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-2.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-2-04-harden - Harden for project continuity
- Guidance: Polish script readability for reuse as syntax reference.
- Learner tasks:
  1. Keep indentation and naming clear for later copy/reuse.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Keep indentation and naming clear for later copy/reuse.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-2.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-2', 'step-basic-exercise-2-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-2.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A valid and runnable first Karate feature file in project classpath.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-1")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-2: First Feature Script
- heading: First Feature Script
- subtitle: Project capability: Create your first executable Karate feature
- chips: [`Feature`, `Background`, `Scenario`, `Print`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-2.feature
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - First Feature Script
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Feature file anatomy
2. Implementation pattern - When to use Background
3. Common pitfall - Indentation and syntax pitfalls
4. Project continuity - Using this file as base for API request scripts

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-2?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-2.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-1'?
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
  - [ ] id = "basic-exercise-2"
  - [ ] 	itle = "First Feature Script"
  - [ ] level = TrainingLevel.BASIC
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "basic-exercise-1"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-2-verify") matches TrainingCondition.CommandPassed("basic-exercise-2-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


