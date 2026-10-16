package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    private final SelenideElement fullNameInput = $("#userName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");

    private final SelenideElement output = $("#output");
    private final SelenideElement outputName = $("#output #name");
    private final SelenideElement outputEmail = $("#output #email");
    private final SelenideElement outputCurrentAddress = $("#output #currentAddress");
    private final SelenideElement outputPermanentAddress = $("#output #permanentAddress");

    private final SelenideElement invalidEmailInput = $("#userEmail:invalid");

    public TextBoxPage openPage() {
        open("/text-box");
        fullNameInput.shouldBe(visible);
        return this;
    }

    public TextBoxPage setFullName(String value) {
        fullNameInput.setValue(value);
        return this;
    }

    public TextBoxPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage submit() {
        submitButton.scrollTo().click();
        return this;
    }

    public TextBoxPage shouldHaveSubmittedData(
            String fullName,
            String email,
            String currentAddress,
            String permanentAddress
    ) {
        output.shouldBe(visible);
        outputName.shouldHave(text(fullName));
        outputEmail.shouldHave(text(email));
        outputCurrentAddress.shouldHave(text(currentAddress));
        outputPermanentAddress.shouldHave(text(permanentAddress));
        return this;
    }

    public TextBoxPage shouldRejectInvalidEmail() {
        invalidEmailInput.shouldBe(visible);
        output.shouldBe(hidden);
        return this;
    }
}
