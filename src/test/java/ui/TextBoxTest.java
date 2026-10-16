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
import ui.pages.TextBoxPage;

@Epic("DemoQA")
@Feature("Text Box")
@Owner("Iaroslav")
class TextBoxTest extends BaseUiTest {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Story("Form submission")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Submit Text Box form with valid data")
    @Tag("smoke")
    @Tag("regression")
    @Test
    void shouldSubmitTextBoxForm() {
        String fullName = "Iaroslav QA";
        String email = "iaroslav@example.com";
        String currentAddress = "Moscow";
        String permanentAddress = "Saint Petersburg";

        textBoxPage
                .openPage()
                .setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit()
                .shouldHaveSubmittedData(
                        fullName,
                        email,
                        currentAddress,
                        permanentAddress
                );
    }

    @Story("Email validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Do not submit form with invalid email")
    @Tag("regression")
    @Test
    void shouldNotSubmitFormWithInvalidEmail() {
        String invalidEmail = "iaroslav.example.com";

        textBoxPage
                .openPage()
                .setFullName("Iaroslav QA")
                .setEmail(invalidEmail)
                .submit()
                .shouldRejectInvalidEmail();
    }
}
