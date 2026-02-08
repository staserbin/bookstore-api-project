package com.bookstore.api.tests.authors;

import com.bookstore.api.tests.base.BaseApiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;


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
}
