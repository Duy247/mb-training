# Karate Training Roadmap (Basic Scripting to Functional API Project)

Last updated: April 23, 2026

## 1) Purpose

Design a complete exercise roadmap for this training framework so learners can progress from:

1. Basic Karate scripting
2. Core API testing patterns
3. Reusable and maintainable test architecture
4. Full end-to-end API business flow validation
5. CI-ready execution, reporting, and security-aware coverage
6. A functional project built incrementally through exercises (not isolated throwaway tasks)

This document is planning-only. No Kotlin exercise code is implemented yet.

Project-based intent:

1. Learners continue in one project across the full roadmap.
2. Each exercise adds or refines real project assets (features, test data, config, runners, mock server).
3. Final outcome is a runnable, maintainable Karate test project that can execute locally and in CI.

## 2) Design Principles For This Roadmap

1. Progressive complexity: each phase introduces one new cognitive layer.
2. Practice-first: every concept is tied to a concrete executable exercise.
3. Production realism: include auth, state transitions, async jobs, and negative cases.
4. Parallel-safe by default: avoid inter-scenario shared mutable state.
5. Contract + behavior + security: validate payload shape, business outcomes, and risk hotspots.
6. Framework-fit: roadmap aligns with current `TrainingExercise` model (`intro`, `steps`, `hints`, `doneWhen`, quiz gating).
7. Project accumulation: every exercise contributes to the same end-state repository structure.

## 3) Convention Baseline (Researched)

These conventions will be used when authoring upcoming exercises:

1. Organize tests using a clear project structure and classpath-based resource loading.
2. Use `karate-config.js` and `karate.env` for environment configuration, not hard-coded URLs.
3. Use `Background` for per-scenario setup; use `callonce` / `karate.callSingle()` for expensive one-time setup.
4. Keep scenarios independent to support Karate parallel execution.
5. Use tags (`@smoke`, `@regression`, `@env=...`, `@parallel=false` only when unavoidable).
6. Use `match`, fuzzy markers, and schema-like assertions for resilient validation.
7. Support data-driven tests via `Scenario Outline`, `table`, JSON/CSV sources, and dynamic feature calls.
8. Cover async API behavior with `retry until` and explicit retry config.
9. Publish HTML + JUnit XML + Cucumber JSON reports for CI consumption.
10. Align status-code expectations with HTTP semantics and include API security checks informed by OWASP API Top 10.
11. Reduce external dependency risk by teaching a local Karate mock API workflow.

## 4) Program Structure

Total planned exercises: 31  
Levels: BASIC (12), INTERMEDIATE (13), ADVANCED (6)

Expected final project state:

1. Domain-organized API test suite with reusable setup and data assets.
2. Local Karate mock API module to support deterministic development/testing.
3. End-to-end business flow scenarios executable against mock and environment targets.
4. Tag strategy and report outputs ready for CI pipelines.

### Phase A: Foundation (BASIC) - Karate DSL and Single-Endpoint Testing

| ID | Title | Core Skills | Deliverable |
|---|---|---|---|
| basic-exercise-1 | Project bootstrap | Maven + Karate project readiness | Working test project skeleton |
| basic-exercise-2 | First feature script | Feature/Background/Scenario syntax | Valid feature file with `print` |
| basic-exercise-3 | Runner path fix (scenario) | Debug classpath runner issue | Passing `mvn test -Dtest=...` |
| basic-exercise-4 | Core request flow | `url`, `path`, `method`, `status` | GET scenario with assertions |
| basic-exercise-5 | Request body basics | JSON request payloads | POST scenario with body validation |
| basic-exercise-6 | Variables and expressions | `def`, interpolation, simple transforms | Dynamic request data |
| basic-exercise-7 | Response handling | `responseStatus`, `responseHeaders`, `responseTime` | Extract + assert response metadata |
| basic-exercise-8 | Match fundamentals | `match ==`, `contains`, `contains only` | Stable payload assertions |
| basic-exercise-9 | Fuzzy validation | `#string`, `#number`, `#[]`, predicates | Type-robust assertions |
| basic-exercise-10 | Query + headers | params, headers, cookies basics | Query-filtered request coverage |
| basic-exercise-11 | Basic auth patterns | Bearer/API key setup | Authenticated endpoint test |
| basic-exercise-12 | Mini checkpoint mission | Combine A1-A11 into one mini flow | CRUD-lite mini API test pack |

### Phase B: Reusability, Configuration, and Local Mock API (INTERMEDIATE)

| ID | Title | Core Skills | Deliverable |
|---|---|---|---|
| intermediate-exercise-1 | `karate-config.js` mastery | env switching, config object | Multi-env base URL setup |
| intermediate-exercise-2 | Shared setup feature | `call read()` isolated/shared scope | Reusable setup module |
| intermediate-exercise-3 | Efficient setup | `callonce` patterns | Cached auth/data seed flow |
| intermediate-exercise-4 | Global one-time setup | `karate.callSingle()` constraints | Global bootstrap with simple JSON return |
| intermediate-exercise-5 | Data-driven outline | `Scenario Outline`, `Examples`, typed params | Multi-row API validation |
| intermediate-exercise-6 | External datasets | JSON/CSV + dynamic calling | Dataset-driven feature execution |
| intermediate-exercise-7 | Tags and execution slices | smoke/regression/env tagging | Selective run strategy |
| intermediate-exercise-8 | Parallel-safe design | unique data strategy, anti-dependency | Parallel-ready scenarios |
| intermediate-exercise-9 | Hooks and lifecycle | `afterScenario`, cleanup safety | Deterministic setup/teardown |
| intermediate-exercise-10 | Contract assertions | deeper `match each`, nested checks | Contract-focused validation pack |
| intermediate-exercise-11 | Mock API bootstrap | Karate mock feature fundamentals | Local mock API serving core endpoints |
| intermediate-exercise-12 | Stateful mock behavior | route logic + in-memory state transitions | Mock supports realistic CRUD/auth behavior |
| intermediate-exercise-13 | Mock-first execution switch | env-driven target switching (mock vs real) | Existing tests run locally without public APIs |

### Phase C: Full API Business Flow (ADVANCED)

| ID | Title | Core Skills | Deliverable |
|---|---|---|---|
| advanced-exercise-1 | Authentication workflow | login/token refresh lifecycle | End-to-end authenticated session flow |
| advanced-exercise-2 | Stateful CRUD business journey | create-read-update-delete chaining | Full entity lifecycle verification |
| advanced-exercise-3 | Async processing flow | `retry until`, timeout strategy | Job submission + polling completion |
| advanced-exercise-4 | Negative and error matrix | 4xx/5xx expectations, validation errors | Error-handling suite |
| advanced-exercise-5 | Security-focused API checks | OWASP API risk-oriented tests | Authorization + abuse-path checks |
| advanced-exercise-6 | Capstone: whole API flow | Combine auth + CRUD + async + validation + reporting | CI-ready full flow test suite |

## 5) Capstone Definition (Target Outcome)

The final capstone (`advanced-exercise-6`) should validate a realistic user journey:

1. Run the full flow against the local mock API (and optionally a real integration environment).
2. Authenticate and store access token.
3. Create primary resource.
4. Verify read model and related list/search endpoints.
5. Update resource and confirm persistence.
6. Trigger async processing and poll until complete.
7. Validate business constraints and response schema.
8. Validate at least one negative authorization/validation path.
9. Delete/cleanup and verify idempotent behavior.
10. Generate reports consumable in CI.

## 6) Exercise Authoring Blueprint (For Future Kotlin Implementation)

For each planned exercise, keep this structure:

1. `intro`: exact task framing, structure tree, acceptance checklist.
2. `steps`: 1-5 focused steps (avoid oversized step definitions).
3. `activities`: include runnable actions where useful (`RunCommandTask`, `RunTestTask`, `RefreshMavenProjects`, scenario setup).
4. `doneWhen`: objective conditions (`FileExists`, `FileContains`, `CommandPassed`, `MavenSyncSucceeded`, dependencies).
5. `knowledgeSummary`: concise cards with practical patterns and pitfalls.
6. `theoryQuiz`: short, high-signal questions tied to real mistakes.

Recommended policy:

1. Most exercises: `CompletionPolicy.ALL_STEPS_DONE`.
2. Use `ANY_STEP_DONE` only for exploratory/lab-style tasks.
3. Keep one stable `commandId`/`syncId` per validation step.

## 7) Suggested Milestones and Assessment Gates

1. Gate 1 (after BASIC-12): learner can create stable single-endpoint tests and basic mini flows.
2. Gate 2 (after INTERMEDIATE-13): learner can build reusable, environment-aware, parallel-safe suites and run tests via local mock APIs.
3. Gate 3 (after ADVANCED-6): learner can own a full API business-flow automation pack suitable for CI.

## 8) Scope For "Not Yet" (Backlog After Core Roadmap)

1. GraphQL-focused flow pack.
2. SOAP-specific integration pack.
3. Consumer-driven contract workflow on top of the local mock foundation.
4. Gatling performance reuse track.
5. Hybrid API + UI flows.

## 9) Internet Research References

Primary references used for conventions and sequencing:

1. Karate documentation home: https://docs.karatelabs.io/
2. Karate Quick Start: https://docs.karatelabs.io/getting-started/quick-start/
3. Karate Project Structure: https://docs.karatelabs.io/core-syntax/project-structure/
4. Karate Configuration: https://docs.karatelabs.io/core-syntax/configuration/
5. Karate Making Requests: https://docs.karatelabs.io/http-requests/making-requests/
6. Karate Headers and Authentication: https://docs.karatelabs.io/http-requests/headers-auth/
7. Karate Response Handling: https://docs.karatelabs.io/http-responses/response-handling/
8. Karate Match Keyword: https://docs.karatelabs.io/assertions/match-keyword/
9. Karate Schema Validation: https://docs.karatelabs.io/assertions/schema-validation/
10. Karate Calling Features: https://docs.karatelabs.io/reusability/calling-features/
11. Karate Data-Driven Tests: https://docs.karatelabs.io/reusability/data-driven-tests/
12. Karate Hooks: https://docs.karatelabs.io/advanced/hooks/
13. Karate Best Practices: https://docs.karatelabs.io/advanced/best-practices/
14. Karate Parallel Execution: https://docs.karatelabs.io/running-tests/parallel-execution/
15. Karate Tags: https://docs.karatelabs.io/running-tests/tags/
16. Karate Test Reports: https://docs.karatelabs.io/running-tests/test-reports/
17. Karate CI/CD: https://docs.karatelabs.io/running-tests/ci-cd/
18. Karate examples repository: https://github.com/karatelabs/karate-examples
19. Maven Surefire JUnit Platform docs: https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html
20. OpenAPI specification index (latest listed versions): https://spec.openapis.org/oas/
21. OpenAPI 3.1 spec: https://spec.openapis.org/oas/v3.1.0.html
22. HTTP Semantics (RFC 9110): https://www.rfc-editor.org/rfc/rfc9110
23. OWASP API Security Top 10 (2023): https://owasp.org/API-Security/editions/2023/en/0x11-t10/
