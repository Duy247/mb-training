# advanced-exercise-6 - Capstone Whole API Flow

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | advanced-exercise-6 |
| Title | Capstone Whole API Flow |
| Level | ADVANCED |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase C |
| Prerequisite Exercise IDs | advanced-exercise-5 |
| Estimated Duration | 50-75 minutes |

## 2) Objective and Outcomes

### Objective
Assemble a full CI-ready end-to-end API flow suite runnable on local mock and optional real environment.

### Learner Outcomes
1. Execute complete authenticated business flow
2. Run flow against mock-first target strategy
3. Generate test reports for CI consumption
4. Deliver a functional maintainable project outcome

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/capstone/`
- `src/test/java/com/mb/training/runner/CapstoneRunner.java`

### Files/Folders Updated
- `src/test/resources/features/capstone/`
- `src/test/java/com/mb/training/runner/CapstoneRunner.java`
- `src/test/java/karate-config.js`

### Dependency On Previous Exercises
- Composes all previous capabilities: config, reuse, mock, auth, CRUD, async, negative, security.

### Contribution To Final Functional Project
Delivers the final functional project objective: a complete, runnable, maintainable Karate API automation suite.

## 4) Detailed Step Design

### Step step-advanced-exercise-6-01-setup - Prepare exercise assets
- Guidance: Design and assemble capstone flow modules.
- Learner tasks:
  1. Create capstone feature package and runner.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/capstone/', template = '...')
- TrainingActivity.CodeTask('Create capstone feature package and runner.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/capstone/')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/capstone/', snippet = 'capstone')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/capstone/')
- TrainingCondition.FileContains('src/test/resources/features/capstone/', 'capstone')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-advanced-exercise-6-02-implement - Implement core behavior
- Guidance: Integrate auth + CRUD + async + negative/security checks.
- Learner tasks:
  1. Wire all reusable modules into unified flow.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Wire all reusable modules into unified flow.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/capstone/', snippet = 'capstone')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/capstone/')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/capstone/')
- TrainingCondition.FileContains('src/test/resources/features/capstone/', 'capstone')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-advanced-exercise-6-03-verify - Run and verify
- Guidance: Run full capstone suite and validate reporting outputs.
- Learner tasks:
  1. Run and verify deterministic end-to-end pass.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-6-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/capstone/', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-6-verify')
- TrainingCondition.FileExists('src/test/resources/features/capstone/')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-advanced-exercise-6-04-harden - Harden for project continuity
- Guidance: Finalize project structure/documentation for handoff.
- Learner tasks:
  1. Ensure artifacts are CI-ready and readable.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Ensure artifacts are CI-ready and readable.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/capstone/', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-6', 'step-advanced-exercise-6-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/capstone/')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A complete API testing project runnable locally (mock) and adaptable for integration environments.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("advanced-exercise-5")

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-6: Capstone Whole API Flow
- heading: Capstone Whole API Flow
- subtitle: Project capability: Deliver complete functional API project
- chips: [`capstone`, `end-to-end`, `CI-ready`, `functional project`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/capstone/
      ├─ src/test/java/com/mb/training/runner/CapstoneRunner.java
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Capstone Whole API Flow
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - Capstone architecture
2. Implementation pattern - Module composition strategy
3. Common pitfall - CI/reporting readiness
4. Project continuity - Final project maintainability checklist

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of advanced-exercise-6?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/capstone/
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
  4. Q: Why does this exercise depend on 'advanced-exercise-5'?
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
  - [ ] id = "advanced-exercise-6"
  - [ ] 	itle = "Capstone Whole API Flow"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes "advanced-exercise-5"
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-6-verify") matches TrainingCondition.CommandPassed("advanced-exercise-6-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


