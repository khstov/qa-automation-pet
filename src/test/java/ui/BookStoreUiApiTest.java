package ui;

import java.util.List;
import java.util.UUID;

import api.clients.DemoQaBookStoreApi;
import api.models.demoqa.AddBooksRequest;
import api.models.demoqa.Book;
import api.models.demoqa.BooksResponse;
import api.models.demoqa.CreateUserRequest;
import api.models.demoqa.CreateUserResponse;
import api.models.demoqa.IsbnRequest;
import api.models.demoqa.TokenResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;
import ui.pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("DemoQA")
@Feature("Book Store")
@Owner("khstov")
public class BookStoreUiApiTest extends BaseUiTest {

    private final DemoQaBookStoreApi bookStoreApi = new DemoQaBookStoreApi();
    private final LoginPage loginPage = new LoginPage();
    private final ProfilePage profilePage = new ProfilePage();

    private CreateUserRequest createdUserRequest;
    private String createdUserId;

    @Story("Book prepared through API")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Display an API-prepared book in the user profile")
    @Tag("smoke")
    @Tag("regression")
    @Test
    void shouldDisplayApiPreparedBookInProfile() {
        String username = "qa_" + UUID.randomUUID().toString().replace("-", "");
        String password = "Qa!12345";

        createdUserRequest = new CreateUserRequest(username, password);
        CreateUserResponse createUserResponse = bookStoreApi.createUser(createdUserRequest);
        createdUserId = createUserResponse.userId();
        assertNotNull(createdUserId);

        TokenResponse tokenResponse = bookStoreApi.generateToken(createdUserRequest);
        assertEquals("Success", tokenResponse.status());

        String token = tokenResponse.token();
        assertNotNull(token);
        assertFalse(token.isBlank());

        BooksResponse booksResponse = bookStoreApi.getBooks();

        assertNotNull(booksResponse.books());
        assertFalse(booksResponse.books().isEmpty());

        Book book = booksResponse.books().get(0);

        AddBooksRequest addBooksRequest = new AddBooksRequest(
                createdUserId,
                List.of(new IsbnRequest(book.isbn()))
        );
        bookStoreApi.addBooks(token, addBooksRequest);

        loginPage.openPage();
        loginPage.loginAs(username, password);

        profilePage
                .shouldBeOpenedFor(username)
                .shouldHaveBook(book.title());
    }

    @AfterEach
    void deleteCreatedUser() {
        if (createdUserId == null) {
            return;
        }

        String token = bookStoreApi.generateToken(createdUserRequest).token();

        bookStoreApi.deleteUser(token, createdUserId);
    }
}
