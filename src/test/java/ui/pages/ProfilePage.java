package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {

    private final SelenideElement usernameValue = $("#userName-value");

    private final ElementsCollection bookTitles =
            $$("span[id^='see-book-']");

    public ProfilePage shouldBeOpenedFor(String username) {
        usernameValue.shouldBe(visible);
        usernameValue.shouldHave(text(username));
        return this;
    }

    public ProfilePage shouldHaveBook(String title) {
        SelenideElement element = bookTitles.findBy(text(title));
        element.shouldBe(visible);
        return this;
    }
}
