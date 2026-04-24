# basic-exercise-1 - Project Bootstrap

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-1 |
| Title | Project Bootstrap |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | none |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Initialize a Maven Karate project foundation with required structure, dependencies, and baseline run capability.

### Learner Outcomes
1. Create mandatory Maven test folders for Karate
2. Configure Karate + Surefire in pom.xml
3. Run a baseline Maven test command successfully
4. Understand how this baseline enables every later exercise

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `pom.xml`
- `src/test/java/`
- `src/test/resources/features/`

### Files/Folders Updated
- `pom.xml`

### Dependency On Previous Exercises
- This is the first exercise and establishes base project conventions.

### Contribution To Final Functional Project
Creates the project skeleton and build pipeline required for all subsequent API scripting and mock/server exercises.

## 4) Detailed Step Design

### Step step-basic-exercise-1-01-setup - Prepare exercise assets
- Guidance: Set up foundational project files and folder structure.
- Learner tasks:
  1. Create src/test/java and src/test/resources/features.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'pom.xml', template = '...')
- TrainingActivity.CodeTask('Create src/test/java and src/test/resources/features.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'pom.xml')
- TrainingHint.ContentHint(filePath = 'pom.xml', snippet = '<artifactId>karate-junit5</artifactId>')
- Suggested doneWhen:
- TrainingCondition.FileExists('pom.xml')
- TrainingCondition.FileContains('pom.xml', '<artifactId>karate-junit5</artifactId>')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-1-02-implement - Implement core behavior
- Guidance: Configure Maven dependencies and plugin configuration correctly.
- Learner tasks:
  1. Add karate-junit5 dependency and maven-surefire-plugin.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Add karate-junit5 dependency and maven-surefire-plugin.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'pom.xml', snippet = '<artifactId>karate-junit5</artifactId>')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'pom.xml > dependencies/build')
- Suggested doneWhen:
- TrainingCondition.FileExists('pom.xml')
- TrainingCondition.FileContains('pom.xml', '<artifactId>karate-junit5</artifactId>')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-1-03-verify - Run and verify
- Guidance: Run Maven to validate bootstrap integrity.
- Learner tasks:
  1. Execute baseline build/test command.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-1-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'pom.xml', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-1-verify')
- TrainingCondition.FileExists('pom.xml')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-1-04-harden - Harden for project continuity
- Guidance: Clean up naming/content so future exercises build on stable baseline.
- Learner tasks:
  1. Ensure pom.xml and structure remain minimal and reusable.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Ensure pom.xml and structure remain minimal and reusable.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'pom.xml', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-1', 'step-basic-exercise-1-03-verify')
- TrainingCondition.FileExists('pom.xml')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A runnable Maven Karate project baseline ready for feature authoring.
- startWhen proposal:
  - TrainingCondition.Always

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-1: Project Bootstrap
- heading: Project Bootstrap
- subtitle: Project capability: Bootstrap Maven + Karate baseline
- chips: [`Maven`, `Karate`, `Surefire`, `Project Structure`]
- structureTitle: Assets touched in this exercise
- structureTree:
```text
.
├─ pom.xml
└─ src
   └─ test
      ├─ pom.xml
      ├─ src/test/java/
      ├─ src/test/resources/features/
```
- tasksTitle: Exercise tasks
- tasks:
```text
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
```

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- title: Knowledge Summary - Project Bootstrap
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Why bootstrap consistency matters
2. Implementation pattern - Minimal pom pattern for Karate tests
3. Common pitfall - Common dependency/plugin misconfigurations
4. Project continuity - How this baseline supports roadmap continuity

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-1?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: pom.xml
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
  4. Q: Why does this exercise depend on 'none'?
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
  - [ ] id = "basic-exercise-1"
  - [ ] 	itle = "Project Bootstrap"
  - [ ] level = TrainingLevel.BASIC
  - [ ] type = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds = emptyList() for the first exercise
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, theoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-1-verify") matches TrainingCondition.CommandPassed("basic-exercise-1-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.


