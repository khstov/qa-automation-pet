package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement usernameInput = $("#userName");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login");

    public LoginPage openPage() {
        open("/login");
        usernameInput.shouldBe(visible);
        return this;
    }

    public void loginAs(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.shouldBe(visible);
        loginButton.click();
    }
}
