package com.bookstore.api.tests.books;

import com.bookstore.api.model.Book;
import com.bookstore.api.tests.base.BaseApiTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Epic("Books API")
@Feature("Get Books")
public class GetBooksTest extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that all books can be successfully retrieved via GET request")
    void getAllBooksTest() {
        Allure.step("Send GET request to retrieve all books");
        List<Book> books = books().getAllBooks()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", Book.class);

        Allure.step("Validate that the list of books is not empty");
        assertThat(books)
                .as("Books list")
                .isNotEmpty();
    }
}
