# basic-exercise-4 - Core Request Flow

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | basic-exercise-4 |
| Title | Core Request Flow |
| Level | BASIC |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase A |
| Prerequisite Exercise IDs | basic-exercise-3 |
| Estimated Duration | 30-45 minutes |

## 2) Objective and Outcomes

### Objective
Implement a basic API GET flow using url, path, method, and status assertions.

### Learner Outcomes
1. Send first HTTP request with Karate DSL
2. Compose URL and path safely
3. Validate status code response
4. Create baseline API interaction scenario

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/basic-exercise-4.feature`

### Files/Folders Updated
- `src/test/resources/features/basic-exercise-4.feature`

### Dependency On Previous Exercises
- Depends on feature syntax and runner understanding from earlier exercises.

### Contribution To Final Functional Project
Starts real API testing layer; all business flow tests later build on this request-response skeleton.

## 4) Detailed Step Design

### Step step-basic-exercise-4-01-setup - Prepare exercise assets
- Guidance: Create dedicated feature for first GET endpoint flow.
- Learner tasks:
  1. Create basic-exercise-4.feature.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/basic-exercise-4.feature', template = '...')
- TrainingActivity.CodeTask('Create basic-exercise-4.feature.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/basic-exercise-4.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-4.feature', snippet = 'Given url')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-4.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-4.feature', 'Given url')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-basic-exercise-4-02-implement - Implement core behavior
- Guidance: Implement request steps with url/path/method/status.
- Learner tasks:
  1. Write GET scenario with status assertions.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Write GET scenario with status assertions.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-4.feature', snippet = 'Given url')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/basic-exercise-4.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-4.feature')
- TrainingCondition.FileContains('src/test/resources/features/basic-exercise-4.feature', 'Given url')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-basic-exercise-4-03-verify - Run and verify
- Guidance: Run and verify endpoint interaction.
- Learner tasks:
  1. Execute verification command and inspect output.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'basic-exercise-4-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-4.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('basic-exercise-4-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-4.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-basic-exercise-4-04-harden - Harden for project continuity
- Guidance: Refine assertions for stability and readability.
- Learner tasks:
  1. Keep request blocks reusable for later endpoints.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Keep request blocks reusable for later endpoints.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/basic-exercise-4.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('basic-exercise-4', 'step-basic-exercise-4-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/basic-exercise-4.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A passing GET API scenario with clear request and status validation.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("basic-exercise-3")$(if (@{Order=4; Id=basic-exercise-4; Title=Core Request Flow; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-3; Objective=Implement a basic API GET flow using url, path, method, and status assertions.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Depends on feature syntax and runner understanding from earlier exercises.; Contribution=Starts real API testing layer; all business flow tests later build on this request-response skeleton.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=Given url; HintPath2=src/test/resources/features/basic-exercise-4.feature; Chips=System.Object[]; Subtitle=Implement first API request flow; ExpectedOutcome=A passing GET API scenario with clear request and status validation.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: basic-exercise-4: Core Request Flow
- heading: Core Request Flow
- subtitle: Project capability: Implement first API request flow
- chips: [`HTTP GET`, `url/path`, `method`, `status`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/basic-exercise-4.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Core Request Flow
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [BASIC, Project-Building, Karate, Phase A]
- Cards:
1. Core concept - Request flow essentials
2. Implementation pattern - URL vs path composition
3. Common pitfall - Status assertion basics
4. Project continuity - Using this pattern for new endpoints

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of basic-exercise-4?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/basic-exercise-4.feature
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
  4. Q: Why does this exercise depend on 'basic-exercise-3'?
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
  - [ ] id = "basic-exercise-4"
  - [ ] 	itle = "Core Request Flow"
  - [ ] level = TrainingLevel.BASIC
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=4; Id=basic-exercise-4; Title=Core Request Flow; Level=BASIC; Mode=GUIDED; Phase=Phase A; Prereq=basic-exercise-3; Objective=Implement a basic API GET flow using url, path, method, and status assertions.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Depends on feature syntax and runner understanding from earlier exercises.; Contribution=Starts real API testing layer; all business flow tests later build on this request-response skeleton.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=Given url; HintPath2=src/test/resources/features/basic-exercise-4.feature; Chips=System.Object[]; Subtitle=Implement first API request flow; ExpectedOutcome=A passing GET API scenario with clear request and status validation.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "basic-exercise-4-verify") matches TrainingCondition.CommandPassed("basic-exercise-4-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

