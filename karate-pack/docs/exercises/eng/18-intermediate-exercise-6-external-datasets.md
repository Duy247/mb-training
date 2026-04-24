# intermediate-exercise-6 - External Datasets

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-6 |
| Title | External Datasets |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-5 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Drive scenario execution from external JSON/CSV datasets for broader, maintainable coverage.

### Learner Outcomes
1. Create external test dataset files
2. Load datasets with Karate read utilities
3. Iterate data into requests/assertions
4. Separate test logic from test data

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/data/intermediate-exercise-6.json`
- `src/test/resources/features/intermediate-exercise-6.feature`

### Files/Folders Updated
- `src/test/resources/features/intermediate-exercise-6.feature`

### Dependency On Previous Exercises
- Extends in-file examples toward externalized data management.

### Contribution To Final Functional Project
Improves scalability and maintainability of large test matrices required for enterprise API testing.

## 4) Detailed Step Design

### Step step-intermediate-exercise-6-01-setup - Prepare exercise assets
- Guidance: Create external dataset and feature consumer.
- Learner tasks:
  1. Add JSON/CSV dataset under resources.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/data/intermediate-exercise-6.json', template = '...')
- TrainingActivity.CodeTask('Add JSON/CSV dataset under resources.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/data/intermediate-exercise-6.json')
- TrainingHint.ContentHint(filePath = 'src/test/resources/data/intermediate-exercise-6.json', snippet = 'read(')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/data/intermediate-exercise-6.json')
- TrainingCondition.FileContains('src/test/resources/data/intermediate-exercise-6.json', 'read(')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-6-02-implement - Implement core behavior
- Guidance: Implement data loading and iteration mapping.
- Learner tasks:
  1. Read dataset and map rows in scenario.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Read dataset and map rows in scenario.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/data/intermediate-exercise-6.json', snippet = 'read(')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/data/intermediate-exercise-6.json')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/data/intermediate-exercise-6.json')
- TrainingCondition.FileContains('src/test/resources/data/intermediate-exercise-6.json', 'read(')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-6-03-verify - Run and verify
- Guidance: Run and verify dataset-driven outcomes.
- Learner tasks:
  1. Run and validate all rows deterministically.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'intermediate-exercise-6-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/data/intermediate-exercise-6.json', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-6-verify')
- TrainingCondition.FileExists('src/test/resources/data/intermediate-exercise-6.json')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-6-04-harden - Harden for project continuity
- Guidance: Organize dataset structure for long-term maintainability.
- Learner tasks:
  1. Separate stable fixtures from temporary data.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Separate stable fixtures from temporary data.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/data/intermediate-exercise-6.json', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-6', 'step-intermediate-exercise-6-03-verify')
- TrainingCondition.FileExists('src/test/resources/data/intermediate-exercise-6.json')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A feature that executes against external datasets with clear data/logic separation.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-5")$(if (@{Order=18; Id=intermediate-exercise-6; Title=External Datasets; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-5; Objective=Drive scenario execution from external JSON/CSV datasets for broader, maintainable coverage.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends in-file examples toward externalized data management.; Contribution=Improves scalability and maintainability of large test matrices required for enterprise API testing.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=read(; HintPath2=src/test/resources/data/intermediate-exercise-6.json; Chips=System.Object[]; Subtitle=Externalize test datasets; ExpectedOutcome=A feature that executes against external datasets with clear data/logic separation.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-6: External Datasets
- heading: External Datasets
- subtitle: Project capability: Externalize test datasets
- chips: [`external data`, `JSON/CSV`, `read`, `data separation`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/data/intermediate-exercise-6.json
      ├─ src/test/resources/features/intermediate-exercise-6.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - External Datasets
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Data externalization benefits
2. Implementation pattern - Dataset design principles
3. Common pitfall - Row mapping pitfalls
4. Project continuity - Supporting broader coverage sustainably

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-6?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/data/intermediate-exercise-6.json
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-5'?
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
  - [ ] id = "intermediate-exercise-6"
  - [ ] 	itle = "External Datasets"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=18; Id=intermediate-exercise-6; Title=External Datasets; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-5; Objective=Drive scenario execution from external JSON/CSV datasets for broader, maintainable coverage.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Extends in-file examples toward externalized data management.; Contribution=Improves scalability and maintainability of large test matrices required for enterprise API testing.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=read(; HintPath2=src/test/resources/data/intermediate-exercise-6.json; Chips=System.Object[]; Subtitle=Externalize test datasets; ExpectedOutcome=A feature that executes against external datasets with clear data/logic separation.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-6-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-6-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

