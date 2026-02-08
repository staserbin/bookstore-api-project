package com.bookstore.api.tests.books;

import com.bookstore.api.tests.base.BaseApiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;


@Epic("Books API")
@Feature("Delete Book")
public class DeleteBookTest extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an existing book can be successfully deleted by ID")
    void deleteBookSuccessTest() {
        final int bookId = 88;

        Allure.step(String.format("Send DELETE request to remove book with ID: %d", bookId), () ->
                books().deleteBook(bookId)
                        .then()
                        .statusCode(200)
        );
    }
}
