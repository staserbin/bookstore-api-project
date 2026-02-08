package com.bookstore.api.tests.authors;

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

        Author author = Allure.step("Build a new valid author payload for the request", () ->
                Author.builder()
                        .id(authorId)
                        .idBook(2)
                        .firstName("UpdatedFirstName")
                        .lastName("UpdatedLastName")
                        .build()
        );

        Author updatedAuthor = Allure.step(String.format("Send PUT request to update an existing author with ID: %d",
                authorId),
                () -> authors().updateAuthor(authorId, author)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Author.class)
        );

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
}
