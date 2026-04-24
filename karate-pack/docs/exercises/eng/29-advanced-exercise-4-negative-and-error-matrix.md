# advanced-exercise-4 - Negative and Error Matrix

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | advanced-exercise-4 |
| Title | Negative and Error Matrix |
| Level | ADVANCED |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase C |
| Prerequisite Exercise IDs | advanced-exercise-3 |
| Estimated Duration | 50-75 minutes |

## 2) Objective and Outcomes

### Objective
Create structured negative/error coverage across validation, auth, and server-side failure classes.

### Learner Outcomes
1. Design negative test matrix categories
2. Drive matrix via data patterns
3. Assert status + error contracts
4. Avoid false positives in failure testing

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/advanced-exercise-4.feature`
- `src/test/resources/data/negative-matrix.json`

### Files/Folders Updated
- `src/test/resources/features/advanced-exercise-4.feature`

### Dependency On Previous Exercises
- Uses async/business flows as positive baseline for negative-case inversions.

### Contribution To Final Functional Project
Ensures project validates resilience and error handling, not only happy path behavior.

## 4) Detailed Step Design

### Step step-advanced-exercise-4-01-setup - Prepare exercise assets
- Guidance: Design error matrix data structure.
- Learner tasks:
  1. Add negative matrix JSON definitions.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/advanced-exercise-4.feature', template = '...')
- TrainingActivity.CodeTask('Add negative matrix JSON definitions.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/advanced-exercise-4.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-4.feature', snippet = '400')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-4.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-4.feature', '400')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-advanced-exercise-4-02-implement - Implement core behavior
- Guidance: Implement scenario execution across matrix cases.
- Learner tasks:
  1. Map matrix entries to requests/assertions.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Map matrix entries to requests/assertions.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-4.feature', snippet = '400')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/data/negative-matrix.json')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-4.feature')
- TrainingCondition.FileContains('src/test/resources/features/advanced-exercise-4.feature', '400')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-advanced-exercise-4-03-verify - Run and verify
- Guidance: Run and validate expected error contracts.
- Learner tasks:
  1. Run and verify each case outcome.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test -Dkarate.env=mock', commandId = 'advanced-exercise-4-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test -Dkarate.env=mock')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-4.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('advanced-exercise-4-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-4.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-advanced-exercise-4-04-harden - Harden for project continuity
- Guidance: Refine strictness to prevent accidental passes.
- Learner tasks:
  1. Harden assertions for code/message consistency.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Harden assertions for code/message consistency.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/advanced-exercise-4.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('advanced-exercise-4', 'step-advanced-exercise-4-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/advanced-exercise-4.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A maintainable negative-test matrix with strict error contract checks.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("advanced-exercise-3")$(if (@{Order=29; Id=advanced-exercise-4; Title=Negative and Error Matrix; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=advanced-exercise-3; Objective=Create structured negative/error coverage across validation, auth, and server-side failure classes.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses async/business flows as positive baseline for negative-case inversions.; Contribution=Ensures project validates resilience and error handling, not only happy path behavior.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=400; HintPath2=src/test/resources/data/negative-matrix.json; Chips=System.Object[]; Subtitle=Systematically validate error behavior; ExpectedOutcome=A maintainable negative-test matrix with strict error contract checks.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: advanced-exercise-4: Negative and Error Matrix
- heading: Negative and Error Matrix
- subtitle: Project capability: Systematically validate error behavior
- chips: [`negative testing`, `error matrix`, `validation`, `robustness`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/advanced-exercise-4.feature
      ├─ src/test/resources/data/negative-matrix.json
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Negative and Error Matrix
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [ADVANCED, Project-Building, Karate, Phase C]
- Cards:
1. Core concept - Negative matrix design
2. Implementation pattern - Error contract assertion strategy
3. Common pitfall - False-positive prevention
4. Project continuity - Complementing happy-path business flows

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of advanced-exercise-4?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/advanced-exercise-4.feature
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
  4. Q: Why does this exercise depend on 'advanced-exercise-3'?
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
  - [ ] id = "advanced-exercise-4"
  - [ ] 	itle = "Negative and Error Matrix"
  - [ ] level = TrainingLevel.ADVANCED
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=29; Id=advanced-exercise-4; Title=Negative and Error Matrix; Level=ADVANCED; Mode=GUIDED; Phase=Phase C; Prereq=advanced-exercise-3; Objective=Create structured negative/error coverage across validation, auth, and server-side failure classes.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Uses async/business flows as positive baseline for negative-case inversions.; Contribution=Ensures project validates resilience and error handling, not only happy path behavior.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test -Dkarate.env=mock; KeywordHint=400; HintPath2=src/test/resources/data/negative-matrix.json; Chips=System.Object[]; Subtitle=Systematically validate error behavior; ExpectedOutcome=A maintainable negative-test matrix with strict error contract checks.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "advanced-exercise-4-verify") matches TrainingCondition.CommandPassed("advanced-exercise-4-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

