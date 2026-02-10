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
import static org.assertj.core.api.Assertions.assertThat;


@Feature("Authors API")
@Story("Get Authors")
class GetAuthorsTest extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that all authors can be successfully retrieved via GET request")
    void getAllAuthorsTest() {
        List<Author> authorsList = getAuthors();

        Allure.step("Validate authors list is not empty");
        assertThat(authorsList)
                .as("Authors list")
                .isNotEmpty();
    }
}
