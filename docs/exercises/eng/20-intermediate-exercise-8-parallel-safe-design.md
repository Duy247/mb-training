# intermediate-exercise-8 - Parallel-Safe Design

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | intermediate-exercise-8 |
| Title | Parallel-Safe Design |
| Level | INTERMEDIATE |
| Type | EXERCISE |
| Mode | GUIDED |
| Phase | Phase B |
| Prerequisite Exercise IDs | intermediate-exercise-7 |
| Estimated Duration | 40-60 minutes |

## 2) Objective and Outcomes

### Objective
Refactor tests to run safely in parallel without shared-state collisions.

### Learner Outcomes
1. Identify shared mutable state risks
2. Introduce unique test data strategy
3. Isolate setup/cleanup boundaries
4. Validate consistent results under parallel execution

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `src/test/resources/features/intermediate-exercise-8.feature`
- `src/test/java/com/mb/training/runner/ParallelRunner.java`

### Files/Folders Updated
- `src/test/resources/features/`
- `src/test/java/com/mb/training/runner/ParallelRunner.java`

### Dependency On Previous Exercises
- Builds on tagged suite organization to enable high-throughput execution.

### Contribution To Final Functional Project
Critical for CI speed and reliability as project test volume increases.

## 4) Detailed Step Design

### Step step-intermediate-exercise-8-01-setup - Prepare exercise assets
- Guidance: Audit scenarios for shared-state problems.
- Learner tasks:
  1. Detect and remove shared mutable dependencies.
  2. Create or update required files under the same project.
- Suggested TrainingActivity:
- TrainingActivity.CreateFile(relativePath = 'src/test/resources/features/intermediate-exercise-8.feature', template = '...')
- TrainingActivity.CodeTask('Detect and remove shared mutable dependencies.')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'file', suggestedPath = 'src/test/resources/features/intermediate-exercise-8.feature')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-8.feature', snippet = 'parallel')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-8.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-8.feature', 'parallel')
- Expected evidence:
  - Core file exists and includes the expected DSL/pattern anchor.
### Step step-intermediate-exercise-8-02-implement - Implement core behavior
- Guidance: Implement data isolation and parallel-safe patterns.
- Learner tasks:
  1. Use unique IDs/data per scenario.
  2. Apply the target capability in a way reusable by upcoming exercises.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Use unique IDs/data per scenario.')
- TrainingActivity.CodeTask('Refactor repeated logic into common location when applicable')
- Suggested TrainingHint:
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-8.feature', snippet = 'parallel')
- TrainingHint.LocationHint(targetType = 'section', suggestedPath = 'src/test/java/com/mb/training/runner/ParallelRunner.java')
- Suggested doneWhen:
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-8.feature')
- TrainingCondition.FileContains('src/test/resources/features/intermediate-exercise-8.feature', 'parallel')
- Expected evidence:
  - Main exercise behavior is implemented and readable.
### Step step-intermediate-exercise-8-03-verify - Run and verify
- Guidance: Run in parallel and verify stability.
- Learner tasks:
  1. Execute parallel run and inspect failures.
  2. Investigate failures and fix deterministic issues before re-running.
- Suggested TrainingActivity:
- TrainingActivity.RunTestTask(commandHint = 'mvn test', commandId = 'intermediate-exercise-8-verify')
- TrainingActivity.CodeTask('Align assertions with deterministic expected behavior')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'command', suggestedPath = 'mvn test')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-8.feature', snippet = 'status / match assertions')
- Suggested doneWhen:
- TrainingCondition.CommandPassed('intermediate-exercise-8-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-8.feature')
- Expected evidence:
  - Verification command passes and plugin can track command completion.
### Step step-intermediate-exercise-8-04-harden - Harden for project continuity
- Guidance: Harden flaky spots until deterministic.
- Learner tasks:
  1. Refactor fragile sections to deterministic patterns.
  2. Ensure artifacts are cleanly reusable by the next exercise.
- Suggested TrainingActivity:
- TrainingActivity.CodeTask('Refactor fragile sections to deterministic patterns.')
- TrainingActivity.CodeTask('Document assumptions inline where they affect later exercises')
- Suggested TrainingHint:
- TrainingHint.LocationHint(targetType = 'project', suggestedPath = 'src/test/resources')
- TrainingHint.ContentHint(filePath = 'src/test/resources/features/intermediate-exercise-8.feature', snippet = 'clear naming + stable assertions')
- Suggested doneWhen:
- TrainingCondition.StepCompleted('intermediate-exercise-8', 'step-intermediate-exercise-8-03-verify')
- TrainingCondition.FileExists('src/test/resources/features/intermediate-exercise-8.feature')
- Expected evidence:
  - Exercise artifacts are maintainable and ready for downstream dependency.

## 5) Exercise Completion Design

- completionPolicy: ALL_STEPS_DONE
- expectedOutcome: A suite segment that runs in parallel with stable, repeatable outcomes.
- startWhen proposal:
  - TrainingCondition.ExerciseCompleted("intermediate-exercise-7")$(if (@{Order=20; Id=intermediate-exercise-8; Title=Parallel-Safe Design; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-7; Objective=Refactor tests to run safely in parallel without shared-state collisions.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on tagged suite organization to enable high-throughput execution.; Contribution=Critical for CI speed and reliability as project test volume increases.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=parallel; HintPath2=src/test/java/com/mb/training/runner/ParallelRunner.java; Chips=System.Object[]; Subtitle=Make suite parallel-safe; ExpectedOutcome=A suite segment that runs in parallel with stable, repeatable outcomes.; Cards=System.Object[]}.Prereq -eq "none") { " or TrainingCondition.Always for the very first exercise." } else { "." })

## 6) Intro Dialog Draft (TrainingExerciseIntro)

- dialogTitle: intermediate-exercise-8: Parallel-Safe Design
- heading: Parallel-Safe Design
- subtitle: Project capability: Make suite parallel-safe
- chips: [`parallel`, `isolation`, `stability`, `CI speed`]
- structureTitle: Assets touched in this exercise
- structureTree:
`	ext
.
├─ pom.xml
└─ src
   └─ test
      ├─ src/test/resources/features/intermediate-exercise-8.feature
      ├─ src/test/java/com/mb/training/runner/ParallelRunner.java
`
- 	asksTitle: Exercise tasks
- 	asks:
`	ext
1) Implement the exercise objective in project files.
2) Verify behavior using deterministic checks and run command.
3) Keep outputs reusable for subsequent exercises.
`

## 7) Knowledge Summary Draft (TrainingKnowledgeSummary)

- 	itle: Knowledge Summary - Parallel-Safe Design
- subtitle: Key takeaways to keep this project functional and maintainable
- labels: [INTERMEDIATE, Project-Building, Karate, Phase B]
- Cards:
1. Core concept - Parallel safety checklist
2. Implementation pattern - Unique data generation patterns
3. Common pitfall - Flakiness diagnosis
4. Project continuity - Parallel readiness for capstone suite

## 8) Theory Quiz Draft (TrainingTheoryQuiz)

- questionsToAsk: 5
- passThreshold: 3
- Question pool (6 questions):
  1. Q: What is the primary goal of intermediate-exercise-8?
     - A: Implement the exercise objective in project assets
     - B: Skip implementation and only write notes
     - C: Delete previous exercise artifacts
     - D: Run unrelated tooling tasks
     - Correct: A
     - Hint: Match objective and deliverable.
  2. Q: Which file/path is central in this exercise?
     - A: src/test/resources/features/intermediate-exercise-8.feature
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
  4. Q: Why does this exercise depend on 'intermediate-exercise-7'?
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
  - [ ] id = "intermediate-exercise-8"
  - [ ] 	itle = "Parallel-Safe Design"
  - [ ] level = TrainingLevel.INTERMEDIATE
  - [ ] 	ype = TrainingType.EXERCISE
  - [ ] mode = TrainingMode.GUIDED
  - [ ] objective, startWhen, steps, expectedOutcome
  - [ ] preconditionExerciseIds includes $(@{Order=20; Id=intermediate-exercise-8; Title=Parallel-Safe Design; Level=INTERMEDIATE; Mode=GUIDED; Phase=Phase B; Prereq=intermediate-exercise-7; Objective=Refactor tests to run safely in parallel without shared-state collisions.; Outcomes=System.Object[]; FilesAdded=System.Object[]; FilesUpdated=System.Object[]; DependencyNotes=Builds on tagged suite organization to enable high-throughput execution.; Contribution=Critical for CI speed and reliability as project test volume increases.; StepGuidance=System.Object[]; StepTasks=System.Object[]; VerifyCommand=mvn test; KeywordHint=parallel; HintPath2=src/test/java/com/mb/training/runner/ParallelRunner.java; Chips=System.Object[]; Subtitle=Make suite parallel-safe; ExpectedOutcome=A suite segment that runs in parallel with stable, repeatable outcomes.; Cards=System.Object[]}.Prereq) when not 
one
  - [ ] completionPolicy = CompletionPolicy.ALL_STEPS_DONE
  - [ ] intro, knowledgeSummary, 	heoryQuiz
- Runtime checks:
  - [ ] RunTestTask(commandId = "intermediate-exercise-8-verify") matches TrainingCondition.CommandPassed("intermediate-exercise-8-verify")
  - [ ] File-based conditions target real project paths used in this exercise
- Curriculum wiring:
  - [ ] Register exercise in TrainingCurriculumRepository at roadmap sequence position

## 10) Acceptance Checklist

1. Exercise can be completed in the same ongoing project.
2. All doneWhen checks are deterministic and plugin-detectable.
3. Resulting assets are reused by subsequent exercises.
4. Verification command can be executed repeatedly with stable results.

