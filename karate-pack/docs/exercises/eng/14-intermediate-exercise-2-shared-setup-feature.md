# intermediate-exercise-2 - Shared Setup Feature

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-2 |
| Title | Shared Setup Feature |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-1 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Extract repeated setup logic into reusable called features.

### Learner Outcomes
1. Identify duplicated setup across tests
2. Create reusable setup feature
3. Call setup via call read()
4. Preserve clear data scope boundaries

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/common/setup.feature`

### Files/Folders Updated
- `src/test/resources/features/common/setup.feature`
- `src/test/resources/features/`

### Dependency On Previous Exercises
- Relies on environment config from intermediate-exercise-1.

### Contribution To Final Functional Project
Reduces duplication and creates shared building blocks for larger flows.

## 4) Detailed Step Design

### Step step-intermediate-exercise-2-01-setup - Prepare exercise assets
- Guidance: Create common setup feature artifact.
- Learner tasks:
  1. Add common/setup.feature.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/common/setup.feature', template = '...')
- TrainingActivity.CodeTask('Add common/setup.feature.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/common/setup.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/setup.feature', snippet = 'call read(')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/common/setup.feature')
- TrainingCondition.FileContains('src/test/resources/features/common/setup.feature', 'call read(')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-2-02-implement - Implement core behavior
- Guidance: Replace duplicated setup with feature call pattern.
- Learner tasks:
  1. Refactor scenarios to use call read().
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Refactor scenarios to use call read().')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/setup.feature', snippet = 'call read(')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/resources/features/common/setup.feature')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/common/setup.feature')
- TrainingCondition.FileContains('src/test/resources/features/common/setup.feature', 'call read(')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-2-03-verify - Run and verify
- Guidance: Run suites to validate scope and behavior.
- Learner tasks:
  1. Run and verify consistent behavior across callers.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'intermediate-exercise-2-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/setup.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-2-verify')
- TrainingCondition.FileExists('src/test/resources/features/common/setup.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-2-04-harden - Harden for project continuity
- Guidance: Harden shared setup contract for later exercises.
- Learner tasks:
  1. Keep setup return structure explicit.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Keep setup return structure explicit.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/common/setup.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-2', 'step-intermediate-exercise-2-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/common/setup.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A common setup feature consumed by multiple scenarios.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-1")$(if (@{Order=14; Id=intermediate-exercise-2; Title=Shared Setup Feature; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-1; Objective=Extract repeated setup logic into reusable called features.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Relies on environment config from intermediate-exercise-1.; Contribution=Reduces duplication and creates shared building blocks for larger flows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=call read(; HintPath2=src/test/resources/features/common/setup.feature; Chips=System.Object[]; Subtitle=Extract reusable setup feature; ExpectedOutcome=A common setup feature consumed by multiple scenarios.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-2: Shared Setup Feature
- heading: Shared Setup Feature
- subtitle: Project capability: Extract reusable setup feature
- chips: [`reuse`, `call read`, `common setup`, `maintainability`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/common/setup.feature
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Shared Setup Feature
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Reuse boundaries
2. Implementation pattern - Call semantics and scope
3. Common pitfall - Avoiding hidden side effects
4. Project continuity - How shared setup accelerates roadmap progress

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-2?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/common/setup.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-1'?
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
  - [ ] id = "intermediate-exercise-2"
  - [ ] 	itle = "Shared Setup Feature"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=14; Id=intermediate-exercise-2; Title=Shared Setup Feature; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-1; Objective=Extract repeated setup logic into reusable called features.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Relies on environment config from intermediate-exercise-1.; Contribution=Reduces duplication and creates shared building blocks for larger flows.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=call read(; HintPath2=src/test/resources/features/common/setup.feature; Chips=System.Object[]; Subtitle=Extract reusable setup feature; ExpectedOutcome=A common setup feature consumed by multiple scenarios.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-2-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-2-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

