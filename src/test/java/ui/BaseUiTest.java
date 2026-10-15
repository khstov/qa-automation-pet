package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseUiTest {

    @BeforeAll
    static void configureBrowser() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";

        String selenoidUrl = System.getenv("SELENOID_URL");

        if (selenoidUrl != null && !selenoidUrl.isBlank()) {
            Configuration.remote = selenoidUrl;
            Configuration.browser = "chrome";
            Configuration.browserVersion =
                    System.getenv().getOrDefault("BROWSER_VERSION", "128.0");
        }

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .includeSelenideSteps(true)
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}