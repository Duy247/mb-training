# Exercise Specification Template (Plugin-Ready)

Use this template for one exercise spec file.  
Goal: make translation to `TrainingExercise` Kotlin code straightforward and deterministic.

## 1) Metadata

| Field | Value |
|---|---|
| Exercise ID | `<example: basic-exercise-4>` |
| Title | `<exercise title>` |
| Level | `BASIC | INTERMEDIATE | ADVANCED` |
| Type | `EXERCISE` |
| Mode | `GUIDED | SCENARIO` |
| Phase | `<Phase A/B/C>` |
| Prerequisite Exercise IDs | `<none or comma-separated ids>` |
| Estimated Duration | `<minutes>` |

## 2) Objective and Outcomes

### Objective
`<single sentence objective>`

### Learner Outcomes
1. `<outcome>`
2. `<outcome>`
3. `<outcome>`
4. `<outcome>`

## 3) Project Contribution (Project-Based Training)

### Files/Folders Added
- `<path>`

### Files/Folders Updated
- `<path>`

### Dependency On Previous Exercises
- `<what is reused from prerequisites>`

### Contribution To Final Functional Project
`<explicitly explain how this exercise output is used later>`

## 4) Detailed Step Design

Create 3-6 focused steps.  
Each step must map cleanly to `TrainingStep` + `TrainingActivity` + `TrainingHint` + `doneWhen`.

### Step `<step-id>` - `<step-title>`
- Guidance: `<text shown to learner>`
- Learner tasks:
  1. `<task>`
  2. `<task>`
- Suggested `TrainingActivity`:
  - `<TrainingActivity.CreateFile(...) | CodeTask(...) | RunTestTask(...) | ...>`
  - `<...>`
- Suggested `TrainingHint`:
  - `<LocationHint(...)>`
  - `<ContentHint(...)>`
- Suggested `doneWhen`:
  - `<TrainingCondition.FileExists(...)>`
  - `<TrainingCondition.FileContains(...)>`
  - `<TrainingCondition.CommandPassed(...)>`
- Expected evidence:
  - `<what should exist/pass>`

Repeat section for all steps.

## 5) Exercise Completion Design

- `completionPolicy`: `<ALL_STEPS_DONE | ANY_STEP_DONE>`
- `expectedOutcome`: `<single sentence>`
- `startWhen` proposal:
  - `<TrainingCondition.Always or ExerciseCompleted(...)>`

## 6) Intro Dialog Draft (`TrainingExerciseIntro`)

- `dialogTitle`: `<...>`
- `heading`: `<...>`
- `subtitle`: `<...>`
- `chips`: `<list>`
- `structureTitle`: `<...>`
- `structureTree`:
```text
<project tree>
```
- `tasksTitle`: `<...>`
- `tasks`:
```text
1) ...
2) ...
3) ...
```

## 7) Knowledge Summary Draft (`TrainingKnowledgeSummary`)

- `title`: `<...>`
- `subtitle`: `<...>`
- `labels`: `<...>`
- Cards (recommend 4-6):
  1. `<card title>` - `<core takeaway>`
  2. `<card title>` - `<core takeaway>`
  3. `<card title>` - `<core takeaway>`
  4. `<card title>` - `<core takeaway>`

## 8) Theory Quiz Draft (`TrainingTheoryQuiz`)

- `questionsToAsk`: `<n>`
- `passThreshold`: `<n>`
- Question pool (>= questionsToAsk):
  1. Q: `<question>`
     - A: `<option>`
     - B: `<option>`
     - C: `<option>`
     - D: `<option>`
     - Correct: `<A|B|C|D>`
     - Hint: `<hint>`
  2. Q: `<question>`
     - A: `<option>`
     - B: `<option>`
     - C: `<option>`
     - D: `<option>`
     - Correct: `<A|B|C|D>`
     - Hint: `<hint>`

## 9) Kotlin Mapping Checklist

- `TrainingExercise` fields:
  - [ ] `id`, `title`, `level`, `type`, `mode`, `objective`
  - [ ] `preconditionExerciseIds`
  - [ ] `startWhen`
  - [ ] `steps`
  - [ ] `expectedOutcome`
  - [ ] `completionPolicy`
  - [ ] `intro`
  - [ ] `knowledgeSummary`
  - [ ] `theoryQuiz`
- Runtime checks:
  - [ ] At least one deterministic completion signal per step
  - [ ] `commandId` and `syncId` consistency between activity and condition
- Curriculum wiring:
  - [ ] Add exercise into `TrainingCurriculumRepository` in sequence

## 10) Acceptance Checklist

1. Exercise is completable in the same ongoing project (no hidden setup).
2. Step conditions are deterministic and plugin-detectable.
3. Content is enough to implement Kotlin exercise definition directly.
4. Output artifacts are reusable in subsequent exercises.
