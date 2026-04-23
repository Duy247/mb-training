# intermediate-exercise-1 - karate-config.js Mastery

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-1 |
| Title | karate-config.js Mastery |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | basic-exercise-12 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Centralize environment configuration with karate-config.js and runtime karate.env switching.

### Learner Outcomes
1. Create maintainable config object
2. Switch baseUrl by environment
3. Inject common values into scenarios
4. Avoid hard-coded endpoint settings

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/java/karate-config.js`

### Files/Folders Updated
- `src/test/java/karate-config.js`
- `src/test/resources/features/`

### Dependency On Previous Exercises
- Uses functional mini-flow baseline from basic checkpoint.

### Contribution To Final Functional Project
Makes the project environment-aware and CI-ready for mock vs real target execution.

## 4) Detailed Step Design

### Step step-intermediate-exercise-1-01-setup - Prepare exercise assets
- Guidance: Create central configuration file for environments.
- Learner tasks:
  1. Add karate-config.js with default/env blocks.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/java/karate-config.js', template = '...')
- TrainingActivity.CodeTask('Add karate-config.js with default/env blocks.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/java/karate-config.js')
- TrainingHint.ContentHint(filePath = 'src/test/java/karate-config.js', snippet = 'karate.env')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/java/karate-config.js')
- TrainingCondition.FileContains('src/test/java/karate-config.js', 'karate.env')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-1-02-implement - Implement core behavior
- Guidance: Implement env-based routing and shared values.
- Learner tasks:
  1. Expose baseUrl and reusable settings.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Expose baseUrl and reusable settings.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/java/karate-config.js', snippet = 'karate.env')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/java/karate-config.js')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/java/karate-config.js')
- TrainingCondition.FileContains('src/test/java/karate-config.js', 'karate.env')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-1-03-verify - Run and verify
- Guidance: Run with specific env to verify switching.
- Learner tasks:
  1. Run with -Dkarate.env=... and validate behavior.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=dev', commandId = 'intermediate-exercise-1-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=dev')
- TrainingHint.ContentHint(filePath = 'src/test/java/karate-config.js', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-1-verify')
- TrainingCondition.FileExists('src/test/java/karate-config.js')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-1-04-harden - Harden for project continuity
- Guidance: Document config conventions for future exercises.
- Learner tasks:
  1. Keep config keys stable and explicit.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Keep config keys stable and explicit.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/java/karate-config.js', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-1', 'step-intermediate-exercise-1-03-verify')
- TrainingCondition.FileExists('src/test/java/karate-config.js')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A stable config layer allowing tests to run across environments.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-12")$(if (@{Order=13; Id=intermediate-exercise-1; Title=karate-config.js Mastery; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=basic-exercise-12; Objective=Centralize environment configuration with karate-config.js and runtime karate.env switching.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses functional mini-flow baseline from basic checkpoint.; Contribution=Makes the project environment-aware and CI-ready for mock vs real target execution.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=dev; KeywordHint=karate.env; HintPath2=src/test/java/karate-config.js; Chips=System.Object[]; Subtitle=Enable environment-driven configuration; ExpectedOutcome=A stable config layer allowing tests to run across environments.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-1: karate-config.js Mastery
- heading: karate-config.js Mastery
- subtitle: Project capability: Enable environment-driven configuration
- chips: [`config`, `karate.env`, `baseUrl`, `environment`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/java/karate-config.js
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - karate-config.js Mastery
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Config file responsibilities
2. Implementation pattern - Environment switch design
3. Common pitfall - Common config anti-patterns
4. Project continuity - Preparing for mock-first routing

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-1?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/java/karate-config.js
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
  4. Q: Why does this exercise depend on 'basic-exercise-12'?
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
  - [ ] id = "intermediate-exercise-1"
  - [ ] 	itle = "karate-config.js Mastery"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=13; Id=intermediate-exercise-1; Title=karate-config.js Mastery; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=basic-exercise-12; Objective=Centralize environment configuration with karate-config.js and runtime karate.env switching.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses functional mini-flow baseline from basic checkpoint.; Contribution=Makes the project environment-aware and CI-ready for mock vs real target execution.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=dev; KeywordHint=karate.env; HintPath2=src/test/java/karate-config.js; Chips=System.Object[]; Subtitle=Enable environment-driven configuration; ExpectedOutcome=A stable config layer allowing tests to run across environments.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-1-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-1-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

