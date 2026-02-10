package com.bookstore.api.tests.authors;

import com.bookstore.api.model.Author;
import com.bookstore.api.tests.base.BaseApiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bookstore.api.steps.AuthorSteps.getAuthors;
import static com.bookstore.api.steps.AuthorSteps.getMaxAuthorId;


@Feature("Authors API")
@Story("Delete Author")
class DeleteAuthorTest extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an existing author can be successfully deleted by ID")
    void deleteAuthorSuccessTest() {
        int authorId = 1;

        Allure.step(String.format("Send DELETE request to remove author with ID: %d", authorId), () ->
                authors().deleteAuthor(authorId)
                        .then()
                        .statusCode(200)
        );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that deleting a non-existent author returns a 404 error")
    void deleteNonExistingAuthorTest() {
        List<Author> authors = getAuthors();

        int nonExistingAuthorId = getMaxAuthorId(authors) + 800;

        Allure.step("Verify 404 is returned when deleting a non-existent author");
        authors().deleteAuthor(nonExistingAuthorId)
                .then()
                .statusCode(404);
    }
}
