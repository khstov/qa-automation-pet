# QA Automation Pet Project

Автотесты на Java для Fake Store API и DemoQA.

## Что проверяется

- API: получение, создание, изменение и удаление товаров; WireMock.
- UI: формы DemoQA и Book Store.
- Сценарии разделены на `smoke` и `regression`; включён параллельный запуск.
- Allure собирает результаты тестов.

## Стек

Java, Gradle, JUnit 5, REST Assured, Selenide, Allure, WireMock, Jenkins, Docker, Selenoid.

## Запуск тестов

```bash
./gradlew test
./gradlew smokeTest
./gradlew regressionTest
./gradlew allureReport
```

Адреса стендов можно переопределить переменными `FAKE_STORE_BASE_URL` и
`DEMOQA_BASE_URL`. По умолчанию используются публичные адреса Fake Store API и
DemoQA.

Для запуска UI-тестов нужен установленный Chrome. Для удалённого браузера через Selenoid:

```bash
docker compose up -d selenoid
SELENOID_URL=http://localhost:4444/wd/hub BROWSER_VERSION=128.0 ./gradlew test --tests ui.RadioButtonTest
```

Jenkins запускается командой `docker compose up -d jenkins` и открывается по адресу [http://localhost:8080](http://localhost:8080). Pipeline забирает проект из Git, запускает `smoke`, затем `regression` и публикует Allure-отчёт.

Тесты используют внешние стенды Fake Store API и DemoQA, поэтому для них нужен интернет.
