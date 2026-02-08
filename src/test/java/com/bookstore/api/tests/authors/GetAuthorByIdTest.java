package com.bookstore.api.tests.authors;

import com.bookstore.api.model.Author;
import com.bookstore.api.tests.base.BaseApiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@Epic("Authors API")
@Feature("Get Author By Id")
@ExtendWith(SoftAssertionsExtension.class)
public class GetAuthorByIdTest extends BaseApiTest {

    @InjectSoftAssertions
    SoftAssertions softly;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an author can be successfully retrieved by a valid ID")
    void getAuthorByIdTest() {
        final int authorId = 1;

        Author author = Allure.step(
                String.format("Send GET request to retrieve author by ID: %d", authorId), () ->
                authors().getAuthorById(1)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Author.class)
        );

        Allure.step("Validate returned author fields", () -> {
            softly.assertThat(author.getId())
                    .as("Author ID")
                    .isEqualTo(1);

            softly.assertThat(author.getIdBook())
                    .as("Book ID")
                    .isGreaterThan(0);

            softly.assertThat(author.getFirstName())
                    .as("First name")
                    .isNotBlank();

            softly.assertThat(author.getLastName())
                    .as("Last name")
                    .isNotBlank();
        });
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify 404 is returned for a non-existing author")
    void invalidAuthorIdTest() {
        int authorId = 8888;

        Allure.step(
                String.format("Send GET request for a non-existing author with ID: %d", authorId),
                () -> authors().getAuthorById(authorId)
                        .then()
                        .statusCode(404)
        );
    }
}
