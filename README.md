# QA Automation Pet Project

Учебный проект для подготовки к выходу на позицию **SDET / QA Automation**.
Язык проекта — **Java**. Все части pet-проекта находятся в одном репозитории.

Главный план подготовки: изучить обязательный материал, применить его в
pet-проекте и после этого провести созвон для обсуждения дополнительной
информации о стеке.

## Что уже есть

- API-тесты Fake Store API на REST Assured;
- UI-тесты DemoQA на Selenide;
- Page Objects для Text Box и Radio Button;
- независимый UI+API-сценарий DemoQA Book Store;
- smoke- и regression-наборы через JUnit 5 tags;
- параллельный запуск JUnit 5;
- Allure-интеграция для API и UI;
- WireMock-тест;
- пример баг-репорта `FKS-001`.

Последний сохранённый прогон в `build/test-results` был успешным: WireMock —
1 тест, smoke — 2 теста, regression — 4 теста, из них 1 отключён из-за
задокументированного дефекта Fake Store API.

## Стек

- Java 17+;
- Gradle Wrapper;
- JUnit 5;
- REST Assured;
- Selenide;
- Allure;
- WireMock;
- Fake Store API и DemoQA как тестовые стенды.

## Запуск

Все тесты:

```bash
./gradlew test
```

Smoke:

```bash
./gradlew smokeTest
```

Regression:

```bash
./gradlew regressionTest
```

Отчёт Allure:

```bash
./gradlew allureReport
```

Проект использует внешние учебные сервисы. Для API- и UI-тестов нужен доступ в
интернет, а для UI-тестов — установленный поддерживаемый браузер.

## Структура

```text
src/test/java/api/          API-тесты и модели
src/test/java/ui/           UI-тесты
src/test/java/ui/pages/     Page Objects
src/test/resources/         настройки JUnit
docs/bugs/                  найденные дефекты
docs/study/                 конспекты только по темам из чата
docs/TASKS.md               подробные учебные и практические задачи
docs/ROADMAP.md             план развития проекта
docs/INTERVIEW_CHECKLIST.md темы для подготовки к собеседованию
AGENTS.md                   правила работы Codex в репозитории
```

## Основные принципы

- тесты не зависят от порядка запуска и друг от друга;
- каждый тест управляет собственными предусловиями и данными;
- UI используется для проверки UI, а подготовка данных по возможности идёт
  через API;
- ожидания основаны на состоянии системы, без `sleep`;
- нестабильность исправляется в причине, а не маскируется ретраями;
- smoke запускается первым, затем regression;
- падение должно быть понятно по Allure без повторного локального запуска.

Подробные правила находятся в [AGENTS.md](AGENTS.md), последовательность работ —
в [docs/ROADMAP.md](docs/ROADMAP.md), полный список задач — в
[docs/TASKS.md](docs/TASKS.md).

Пройденные блоки: [Selenide](docs/study/01-selenide.md),
[REST Assured](docs/study/02-rest-assured.md),
[паттерны автоматизации с Page Object](docs/study/03-page-object.md) и
[flaky-тесты](docs/study/04-flaky-tests.md), а также
[Allure Report](docs/study/05-allure.md).

Текущий учебный блок: Jenkins, CI/CD и Selenoid.

## Учебные ресурсы

- [ThreadQA на YouTube](https://www.youtube.com/@threadqa)
- [Bootcamp QA в Telegram](https://t.me/bootcampqa)
- [Fake Store API](https://fakestoreapi.com/docs#tag/Products)
- [DemoQA](https://demoqa.com/)
- [Референсный pet-проект](https://github.com/Mamalazer/qa_guru_final_project)