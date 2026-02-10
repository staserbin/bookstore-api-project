package com.bookstore.api.tests.authors;

import com.bookstore.api.factory.AuthorFactory;
import com.bookstore.api.model.Author;
import com.bookstore.api.tests.base.BaseApiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.bookstore.api.steps.AuthorSteps.*;


@Feature("Authors API")
@Story("Update Author")
@ExtendWith(SoftAssertionsExtension.class)
class UpdateAuthorTest extends BaseApiTest {

    @InjectSoftAssertions
    SoftAssertions softly;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an existing author details can be successfully updated")
    void updateAuthorSuccessTest() {
        int authorId = 1;

        Author author = AuthorFactory.buildAuthor(
                2,
                "UpdatedFirstName",
                "UpdatedLastName"
        );

        Author updatedAuthor = updateAuthor(authorId, author);

        Allure.step("Validate updated author fields", () -> {
            softly.assertThat(updatedAuthor.getIdBook())
                    .as("Book ID")
                    .isEqualTo(author.getIdBook());

            softly.assertThat(updatedAuthor.getFirstName())
                    .as("First name")
                    .isEqualTo(author.getFirstName());

            softly.assertThat(updatedAuthor.getLastName())
                    .as("Last name")
                    .isEqualTo(author.getLastName());
        });
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that updating a non-existent author returns a 404 error")
    void updateNonExistingAuthorTest() {
        List<Author> authors = getAuthors();

        int nonExistingAuthorId = getMaxAuthorId(authors) + 999;

        Author author = AuthorFactory.buildAuthor(
                5,
                "UpdatedFirstName",
                "UpdatedLastName"
        );

        Author updatedAuthor = updateAuthor(nonExistingAuthorId, author);

        Allure.step("Verify 404 is returned when deleting a non-existent author");
        authors().deleteAuthor(nonExistingAuthorId)
                .then()
                .statusCode(404);
    }
}
