# Mẫu đặc tả bài tập (Sẵn sàng cho plugin)

Dùng mẫu này cho mỗi file đặc tả bài tập.  
Goal: make translation to `TrainingExercise` Kotlin code straightforward and deterministic.

## 1) Thông tin bài tập

| Trường | Giá trị |
|---|---|
| Exercise ID | `<example: basic-exercise-4>` |
| Tiêu đề | `<exercise title>` |
| Cấp độ | `BASIC | INTERMEDIATE | ADVANCED` |
| Loại | `EXERCISE` |
| Mode | `GUIDED | SCENARIO` |
| Giai đoạn | `<Phase A/B/C>` |
| Bài phụ thuộc | `<none or comma-separated ids>` |
| Thời lượng ước tính | `<minutes>` |

## 2) Mục tiêu và kết quả học tập

### Mục tiêu
`<single sentence objective>`

### Kết quả người học đạt được
1. `<outcome>`
2. `<outcome>`
3. `<outcome>`
4. `<outcome>`

## 3) Đóng góp vào dự án (Project-based Training)

### File/Thư mục được tạo
- `<path>`

### File/Thư mục được cập nhật
- `<path>`

### Phụ thuộc vào bài trước
- `<what is reused from prerequisites>`

### Đóng góp cho dự án hoàn chỉnh cuối cùng
`<explicitly explain how this exercise output is used later>`

## 4) Thiết kế chi tiết từng bước

Create 3-6 focused steps.  
Each step must map cleanly to `TrainingStep` + `TrainingActivity` + `TrainingHint` + `doneWhen`.

### Step `<step-id>` - `<step-title>`
- Hướng dẫn: `<text shown to learner>`
- Nhiệm vụ người học:
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
- Dấu hiệu hoàn thành mong đợi:
  - `<what should exist/pass>`

Repeat section for all steps.

## 5) Thiết kế điều kiện hoàn thành bài

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

## 9) Checklist ánh xạ Kotlin

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

## 10) Checklist nghiệm thu

1. Exercise is completable in the same ongoing project (no hidden setup).
2. Step conditions are deterministic and plugin-detectable.
3. Content is enough to implement Kotlin exercise definition directly.
4. Output artifacts are reusable in subsequent exercises.

