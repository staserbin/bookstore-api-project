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
@Feature("Update Book")
@ExtendWith(SoftAssertionsExtension.class)
public class UpdateBookTest extends BaseApiTest {

    @InjectSoftAssertions
    SoftAssertions softly;

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an existing book can be successfully updated via PUT request")
    void updateBookSuccessTest() {
        final int bookId = 1;

        Book book = Allure.step("Build a new valid book for the request", () ->
                Book.builder()
                    .id(bookId)
                    .title("Updated Test Book")
                    .description("Updated Description")
                    .pageCount(333)
                    .excerpt("Updated Excerpt")
                    .publishDate(currentDateTime())
                    .build()
        );

        Book updatedBook = Allure.step(String.format("Send PUT request to update an existing book with ID: %d", bookId), () ->
                books().updateBook(bookId, book)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Book.class)
        );

        Allure.step("Validate updated book fields", () -> {
            softly.assertThat(updatedBook.getId())
                    .as("Book ID")
                    .isEqualTo(bookId);

            softly.assertThat(updatedBook.getTitle())
                    .as("Book title")
                    .isEqualTo(book.getTitle());

            softly.assertThat(updatedBook.getDescription())
                    .as("Book description")
                    .isEqualTo(book.getDescription());

            softly.assertThat(updatedBook.getPageCount())
                    .as("Page count")
                    .isEqualTo(book.getPageCount());

            softly.assertThat(updatedBook.getExcerpt())
                    .as("Book excerpt")
                    .isEqualTo(book.getExcerpt());

            softly.assertThat(updatedBook.getPublishDate())
                    .as("Publish date")
                    .isEqualTo(book.getPublishDate());
        });
    }
}