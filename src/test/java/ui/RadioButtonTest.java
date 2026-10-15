package ui;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.RadioButtonPage;


public class RadioButtonTest extends BaseUiTest {

    private final RadioButtonPage radioButtonPage = new RadioButtonPage();

    @Story("Radio button selection")
    @DisplayName("Select Yes radio button")
    @Test
    void shouldSelectYesRadioButton() {
        radioButtonPage
                .openPage()
                .selectYes()
                .shouldHaveSelectedYes();
    }
}