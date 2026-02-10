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

import static com.bookstore.api.factory.AuthorFactory.buildAuthor;
import static com.bookstore.api.steps.AuthorSteps.createAuthor;


@Feature("Authors API")
@Story("Create Author")
@ExtendWith(SoftAssertionsExtension.class)
class CreateAuthorTest extends BaseApiTest {

    @InjectSoftAssertions
    SoftAssertions softly;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new author can be successfully created")
    void createAuthorSuccessTest() {
        Author author = buildAuthor(
                1,
                "Harry",
                "Potter"
        );

        Author createdAuthor = createAuthor(author);

        Allure.step("Validate that the author was created correctly", () -> {
            softly.assertThat(createdAuthor.getIdBook())
                    .as("Book id")
                    .isEqualTo(author.getIdBook());

            softly.assertThat(createdAuthor.getFirstName())
                    .as("First name")
                    .isEqualTo(author.getFirstName());

            softly.assertThat(createdAuthor.getLastName())
                    .as("Last name")
                    .isEqualTo(author.getLastName());
        });
    }
}
