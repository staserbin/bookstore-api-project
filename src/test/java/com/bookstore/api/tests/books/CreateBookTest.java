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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@Epic("Books API")
@Feature("Create Book")
@ExtendWith(SoftAssertionsExtension.class)
public class CreateBookTest extends BaseApiTest {

    @InjectSoftAssertions
    SoftAssertions softly;

    private static final String DESCRIPTION = "Test Description";
    private static final String EXCERPT = "Test Excerpt";

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new book can be successfully created with valid data")
    void createBookSuccessTest() {
        Book book = Allure.step("Build a valid book for the request", () ->
                Book.builder()
                        .title("Test Book")
                        .description(DESCRIPTION)
                        .pageCount(777)
                        .excerpt(EXCERPT)
                        .publishDate(currentDateTime())
                        .build()
        );

        Book createdBook = Allure.step("Create a new book with a POST request", () ->
                books().createBook(book)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Book.class)
        );

        Allure.step("Validate that the book was created correctly", () -> {
            softly.assertThat(createdBook.getTitle())
                    .as("Book title")
                    .isEqualTo(book.getTitle());

            softly.assertThat(createdBook.getDescription())
                    .as("Book description")
                    .isEqualTo(book.getDescription());

            softly.assertThat(createdBook.getPageCount())
                    .as("Page count")
                    .isEqualTo(book.getPageCount());

            softly.assertThat(createdBook.getExcerpt())
                    .as("Book excerpt")
                    .isEqualTo(book.getExcerpt());
        });
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that book can't be created w/o the required field 'publishDate'")
    void missingRequiredFieldTest() {
        Book invalidBook = Allure.step("Build a Book object for the request", () ->
                Book.builder()
                        .title("Test Book W/O Required Fields")
                        .description(DESCRIPTION)
                        .pageCount(30)
                        .excerpt(EXCERPT)
                        .build()
        );

        Allure.step("Send POST request with incomplete book data", () -> {
            books().createBook(invalidBook)
                    .then()
                    .statusCode(400);
        });
    }
}
