package com.bookstore.api.tests.books;

import com.bookstore.api.model.Book;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Epic("Books API")
@Feature("Get Book By Id")
@ExtendWith(SoftAssertionsExtension.class)
public class GetBookByIdTest extends BaseApiTest {

    @InjectSoftAssertions
    SoftAssertions softly;

    @ParameterizedTest
    @CsvSource({"1"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a book can be successfully retrieved by a valid ID")
    void getBookByIdTest(int bookId) {
        Book book = Allure.step(
                String.format("Send GET request to retrieve book by ID: %d", bookId),
                () -> books().getBookById(bookId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Book.class)
        );

        Allure.step("Validate returned book fields", () -> {
            softly.assertThat(book.getId())
                    .as("Book ID")
                    .isEqualTo(bookId);

            softly.assertThat(book.getTitle())
                    .as("Book title")
                    .isNotBlank();

            softly.assertThat(book.getPageCount())
                    .as("Page count")
                    .isGreaterThan(0);

            softly.assertThat(book.getPublishDate())
                    .as("Publish date")
                    .isNotNull();
        });
    }

    @ParameterizedTest
    @CsvSource({"7777777"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify 404 is returned for a non-existent book")
    void invalidBookIdTest(int bookId) {
        Allure.step(
                String.format("Send GET request for non-existent book with ID: %d", bookId),
                () -> books().getBookById(bookId)
                        .then()
                        .statusCode(404)
        );
    }
}