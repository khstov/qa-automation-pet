package ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.pages.RadioButtonPage;

@Epic("DemoQA")
@Feature("Radio Button")
@Owner("khstov")
public class RadioButtonTest extends BaseUiTest {

    private final RadioButtonPage radioButtonPage = new RadioButtonPage();

    @Story("Radio button selection")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Select Yes radio button")
    @Tag("smoke")
    @Tag("regression")
    @Test
    void shouldSelectYesRadioButton() {
        radioButtonPage
                .openPage()
                .selectYes()
                .shouldHaveSelectedYes();
    }
}
