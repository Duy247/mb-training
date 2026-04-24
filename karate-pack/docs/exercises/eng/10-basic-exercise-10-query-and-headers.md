# basic-exercise-10 - Query and Headers

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-10 |
| Title | Query and Headers |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-9 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Control endpoint behavior with query parameters, headers, and optional cookie context.

### Learner Outcomes
1. Set query params with Karate DSL
2. Set custom headers/cookies
3. Assert behavior differences by request metadata
4. Cover one missing-header negative case

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-10.feature`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-10.feature`

### Dependency On Previous Exercises
- Reuses request/assertion patterns and fuzzy checks from prior basic exercises.

### Contribution To Final Functional Project
Enables realistic API request shaping needed for filtering, versioning, and auth-driven behavior.

## 4) Detailed Step Design

### Step step-basic-exercise-10-01-setup - Prepare exercise assets
- Guidance: Build request variants using params and headers.
- Learner tasks:
  1. Add param and header blocks.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-10.feature', template = '...')
- TrainingActivity.CodeTask('Add param and header blocks.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-10.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-10.feature', snippet = 'And param')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-10.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-10.feature', 'And param')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-10-02-implement - Implement core behavior
- Guidance: Implement scenarios that prove metadata-driven behavior.
- Learner tasks:
  1. Assert response changes with different metadata.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Assert response changes with different metadata.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-10.feature', snippet = 'And param')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-10.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-10.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-10.feature', 'And param')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-10-03-verify - Run and verify
- Guidance: Run and verify both positive and negative variants.
- Learner tasks:
  1. Run command and verify deterministic outcomes.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-10-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-10.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-10-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-10.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-10-04-harden - Harden for project continuity
- Guidance: Document metadata conventions for later exercises.
- Learner tasks:
  1. Normalize header/query naming patterns.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Normalize header/query naming patterns.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-10.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-10', 'step-basic-exercise-10-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-10.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A set of scenarios that validate query/header-driven endpoint behavior.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-9")$(if (@{Order=10; Id=basic-exercise-10; Title=Query and Headers; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-9; Objective=Control endpoint behavior with query parameters, headers, and optional cookie context.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Reuses request/assertion patterns and fuzzy checks from prior basic exercises.; Contribution=Enables realistic API request shaping needed for filtering, versioning, and auth-driven behavior.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=And param; HintPath2=src/test/resources/features/basic-exercise-10.feature; Chips=System.Object[]; Subtitle=Drive API behavior with request metadata; ExpectedOutcome=A set of scenarios that validate query/header-driven endpoint behavior.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-10: Query and Headers
- heading: Query and Headers
- subtitle: Project capability: Drive API behavior with request metadata
- chips: [`query`, `headers`, `cookies`, `metadata-driven behavior`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-10.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Query and Headers
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Param/header DSL patterns
2. Implementation pattern - Metadata-driven behavior validation
3. Common pitfall - Negative case design
4. Project continuity - Preparing for auth flows

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-10?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-10.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-9'?
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
  - [ ] id = "basic-exercise-10"
  - [ ] 	itle = "Query and Headers"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=10; Id=basic-exercise-10; Title=Query and Headers; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-9; Objective=Control endpoint behavior with query parameters, headers, and optional cookie context.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Reuses request/assertion patterns and fuzzy checks from prior basic exercises.; Contribution=Enables realistic API request shaping needed for filtering, versioning, and auth-driven behavior.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=And param; HintPath2=src/test/resources/features/basic-exercise-10.feature; Chips=System.Object[]; Subtitle=Drive API behavior with request metadata; ExpectedOutcome=A set of scenarios that validate query/header-driven endpoint behavior.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-10-verify") matches TrainingCondition.CommandPassed("basic-exercise-10-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

