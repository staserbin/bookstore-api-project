package com.bookstore.api.tests.books;

import com.bookstore.api.factory.BookFactory;
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

import static com.bookstore.api.steps.BookSteps.createBook;
import static com.bookstore.api.steps.BookSteps.createBookExpectingFailure;


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
        Book book = BookFactory.buildBook(
                "Test Book",
                DESCRIPTION,
                777,
                EXCERPT,
                currentDateTime()
        );

        Book createdBook = createBook(book);

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
    @Description("Verify that book can't be created if the required field 'publishDate' is empty")
    void emptyPublishDateFieldTest() {
        Book invalidBook = BookFactory.buildBook(
                "Test Book With Empty Field",
                DESCRIPTION,
                30,
                EXCERPT,
                ""
        );

        createBookExpectingFailure(invalidBook);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that book can't be created if the required field 'pageCount' is missed")
    void missingPageCountFieldTest() {
        Book invalidBook = BookFactory.buildBook(
                "Test Book W/O Required Field",
                DESCRIPTION,
                null,
                EXCERPT,
                currentDateTime()
        );

        createBookExpectingFailure(invalidBook);
    }
}
