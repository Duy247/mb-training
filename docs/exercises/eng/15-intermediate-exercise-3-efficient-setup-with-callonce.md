# intermediate-exercise-3 - Efficient Setup with callonce

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-3 |
| Title | Efficient Setup with callonce |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-2 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Optimize repeated setup by applying callonce where one-time execution is safe.

### Learner Outcomes
1. Recognize expensive repeated setup
2. Apply callonce correctly
3. Keep deterministic behavior across runs
4. Balance performance and test isolation

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/common/bootstrap.feature`

### Files/Folders Updated
- `src/test/resources/features/common/bootstrap.feature`
- `src/test/resources/features/`

### Dependency On Previous Exercises
- Builds on shared setup modularization from intermediate-exercise-2.

### Contribution To Final Functional Project
Improves suite runtime and provides scalable setup strategy for growing project scope.

## 4) Detailed Step Design

### Step step-intermediate-exercise-3-01-setup - Prepare exercise assets
- Guidance: Create or refactor one-time bootstrap feature.
- Learner tasks:
  1. Isolate expensive setup into bootstrap feature.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/common/bootstrap.feature', template = '...')
- TrainingActivity.CodeTask('Isolate expensive setup into bootstrap feature.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/common/bootstrap.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/bootstrap.feature', snippet = 'callonce')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/common/bootstrap.feature')
- TrainingCondition.FileContains('src/test/resources/features/common/bootstrap.feature', 'callonce')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-3-02-implement - Implement core behavior
- Guidance: Apply callonce in safe setup points.
- Learner tasks:
  1. Use callonce in caller flow.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Use callonce in caller flow.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/bootstrap.feature', snippet = 'callonce')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/common/bootstrap.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/common/bootstrap.feature')
- TrainingCondition.FileContains('src/test/resources/features/common/bootstrap.feature', 'callonce')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-3-03-verify - Run and verify
- Guidance: Run and verify setup executes only as intended.
- Learner tasks:
  1. Run and validate behavior/performance consistency.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'intermediate-exercise-3-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/bootstrap.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-3-verify')
- TrainingCondition.FileExists('src/test/resources/features/common/bootstrap.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-3-04-harden - Harden for project continuity
- Guidance: Document where callonce should and should not be used.
- Learner tasks:
  1. Avoid applying callonce to mutable scenario-specific data.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Avoid applying callonce to mutable scenario-specific data.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/bootstrap.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-3', 'step-intermediate-exercise-3-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/common/bootstrap.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A predictable one-time setup pattern that improves test efficiency.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-2")$(if (@{Order=15; Id=intermediate-exercise-3; Title=Efficient Setup with callonce; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-2; Objective=Optimize repeated setup by applying callonce where one-time execution is safe.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on shared setup modularization from intermediate-exercise-2.; Contribution=Improves suite runtime and provides scalable setup strategy for growing project scope.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=callonce; HintPath2=src/test/resources/features/common/bootstrap.feature; Chips=System.Object[]; Subtitle=Optimize setup execution safely; ExpectedOutcome=A predictable one-time setup pattern that improves test efficiency.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-3: Efficient Setup with callonce
- heading: Efficient Setup with callonce
- subtitle: Project capability: Optimize setup execution safely
- chips: [`callonce`, `performance`, `setup`, `isolation`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/common/bootstrap.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Efficient Setup with callonce
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - When callonce is appropriate
2. Implementation pattern - Performance vs isolation tradeoff
3. Common pitfall - Mutable state caveats
4. Project continuity - Impact on larger suite scalability

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-3?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/common/bootstrap.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-2'?
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
  - [ ] id = "intermediate-exercise-3"
  - [ ] 	itle = "Efficient Setup with callonce"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=15; Id=intermediate-exercise-3; Title=Efficient Setup with callonce; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-2; Objective=Optimize repeated setup by applying callonce where one-time execution is safe.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on shared setup modularization from intermediate-exercise-2.; Contribution=Improves suite runtime and provides scalable setup strategy for growing project scope.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=callonce; HintPath2=src/test/resources/features/common/bootstrap.feature; Chips=System.Object[]; Subtitle=Optimize setup execution safely; ExpectedOutcome=A predictable one-time setup pattern that improves test efficiency.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-3-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-3-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

