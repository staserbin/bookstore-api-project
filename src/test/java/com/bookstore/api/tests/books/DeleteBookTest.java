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

import static com.bookstore.api.steps.BookSteps.getBooks;
import static com.bookstore.api.steps.BookSteps.getMaxBookId;


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

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that deleting a non-existent book returns a 404 error")
    void deleteNonExistingBookTest() {
        List<Book> books = getBooks();

        int nonExistingBookId = getMaxBookId(books) + 800;

        Allure.step("Verify 404 is returned when deleting a non-existent book");
        authors().deleteAuthor(nonExistingBookId)
                .then()
                .statusCode(404);
    }
}
