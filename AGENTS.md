# Project instructions for Codex

## Mission

This repository is an interview-ready SDET/QA Automation pet project. It must
demonstrate practical test automation skills, not merely collect examples.

The overall preparation plan is:

1. Study each required SDET/QA Automation topic and apply it in the pet project
   in parallel.
2. Prepare a concise project walkthrough and questions.
3. Only then move to the follow-up call about the additional stack.

The target role is SDET/QA Automation. Java is the project language. Keep all
parts of the pet project in this repository.

Read `README.md` and `docs/ROADMAP.md` before making project changes. Keep the
roadmap in sync when a task is completed or its scope changes.

## Learning scope

The learning curriculum is limited to the topics explicitly listed in
`docs/INTERVIEW_CHECKLIST.md`. Do not add adjacent patterns, test-design
techniques, tools, or interview topics unless the user asks for them.

Implementation details may be introduced only when they are necessary to make
the requested pet-project feature work. Do not turn such details into new study
goals.

## Collaboration mode

- The user personally writes and edits all source code, build scripts, and test
  configuration.
- Codex may inspect the project read-only, explain the current topic, propose a
  concrete exercise, and review changes made by the user.
- Codex must not edit source code, build scripts, or test configuration unless
  the user explicitly requests that exact edit.
- Codex must not run tests or project commands unless the user explicitly asks.
- Codex may create and update learning notes under `docs/study/` and keep the
  learning documentation current.

## Priorities

Work in this order unless the user asks otherwise:

1. Reliable REST Assured and Selenide tests.
2. Clean Java automation architecture.
3. Page Object, test isolation, parallel execution, and flaky-test prevention.
4. Allure reporting and smoke/regression suites.
5. CI with Jenkins, Docker, and Selenoid.
6. SQL/database checks and mocks.
7. Kafka and load testing as optional extensions.

Do not add a technology only to make the stack look larger. Every dependency
must support a real, documented test scenario.

## Test design rules

- Tests must be independent, order-free, repeatable, and safe to run in
  parallel.
- A test must create or own its data and must not depend on another test's
  result. Clean up created data when the tested service supports it.
- Prefer API or direct setup for test preconditions. Use UI steps only for the
  behavior that the UI test actually verifies.
- Keep one clear behavior and one reason to fail per test. Use
  Arrange/Act/Assert or Given/When/Then structure.
- Assert the meaningful contract: status, response data, visible user outcome,
  or persisted state. Do not assert implementation details without a reason.
- Never use fixed sleeps. Use Selenide conditions, polling, or bounded explicit
  waits for observable state.
- Do not hide instability with automatic retries. Find the cause first. A retry
  is allowed only for a documented infrastructure limitation and must be
  visible in reporting.
- Prefer stable selectors in this order: dedicated `data-*` test attributes,
  stable IDs, accessible roles/names, then scoped CSS. Avoid brittle XPath,
  indexes, generated classes, and text unrelated to the behavior.
- Quarantine or disable a test only with a linked bug or issue and a concise
  explanation. Do not silently delete a failing test.
- Do not run destructive tests against shared or production environments.

## UI architecture

- Use Selenide for browser tests.
- Page Objects own locators and page-level actions/assertions. They must not own
  complete business scenarios or hard-coded test data.
- Keep locators private and expose intent-oriented methods such as
  `submitValidForm` or `shouldShowValidationError`.
- Return the current or destination page object when it makes the test easier
  to read; avoid long generic utility chains.
- Capture screenshots and page source in Allure on failures.

## API architecture

- Use REST Assured and central request/response specifications.
- Keep base URLs, credentials, and environment-specific values configurable;
  never hard-code secrets.
- Prefer typed request and response models with fields matching the actual
  contract.
- Validate HTTP status, content type when applicable, and business-relevant
  fields. Add schema checks where they improve contract coverage.
- Use WireMock for deterministic error cases, rare states, and integration
  boundaries. Verify both stubbed responses and important outgoing requests.

## Suites, reports, and concurrency

- Mark critical happy paths with `smoke` and broader coverage with
  `regression`. A smoke test may also belong to regression.
- All new user-facing tests should have readable display names and useful
  Allure Epic/Feature/Story/Severity metadata.
- Attach request/response details for API failures and browser evidence for UI
  failures, while redacting secrets and personal data.
- Shared configuration and fixtures must be thread-safe. Avoid mutable global
  state and fixed ports in parallel tests.
- Do not reduce coverage or disable parallel execution merely to make a flaky
  test pass.

## Code conventions

- Use Java for new test code.
- Prefer small, explicit fixtures/builders over inheritance-heavy frameworks.
- Use descriptive behavior names, for example `shouldReturnProductById`.
- Keep formatting consistent and files newline-terminated.
- Add comments only when they explain a non-obvious decision or external-system
  constraint.
- Keep dependencies on stable compatible versions. Do not perform unrelated
  upgrades while implementing a test.

## Verification

Run the narrowest relevant check first, then the appropriate suite:

```bash
./gradlew test
./gradlew smokeTest
./gradlew regressionTest
./gradlew allureReport
```

If a check cannot run because an external service, browser, Docker daemon, or
credential is unavailable, report that limitation precisely. Do not claim the
change is verified.

## Git and change discipline

- Inspect the working tree before editing and preserve unrelated user changes.
- Make small, reviewable changes tied to one roadmap item.
- Use branches named `codex/<short-topic>` when creating a branch for Codex
  work, unless the user requests another name.
- Do not commit, push, open a pull request, or change remote infrastructure
  unless the user explicitly asks.
- Never commit secrets, local IDE state, generated build output, or Allure
  results.
- Update documentation when commands, architecture, test suites, or known
  limitations change.

## Definition of done

A project task is done when:

- the requested behavior is implemented;
- relevant tests pass locally, or a concrete external blocker is documented;
- tests remain independent and parallel-safe;
- Allure output is useful for diagnosing failures;
- configuration and secrets are externalized;
- README and roadmap are current;
- no generated or unrelated files are included in the change.
