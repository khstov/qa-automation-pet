package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RadioButtonPage {

    private final SelenideElement yesRadioLabel = $("label[for='yesRadio']");
    private final SelenideElement selectedValue = $("p.mt-3 .text-success");

    public RadioButtonPage openPage() {
        open("/radio-button");
        yesRadioLabel.shouldBe(visible);
        return this;
    }

    public RadioButtonPage selectYes() {
        yesRadioLabel.click();
        return this;
    }

    public RadioButtonPage shouldHaveSelectedYes() {
        selectedValue
                .shouldBe(visible)
                .shouldHave(text("Yes"));
        return this;
    }
}
