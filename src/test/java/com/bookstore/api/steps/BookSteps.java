package com.bookstore.api.steps;

import com.bookstore.api.model.Author;
import com.bookstore.api.model.Book;
import com.bookstore.api.service.BookService;
import io.qameta.allure.Allure;
import java.util.List;

import static com.bookstore.api.tests.base.BaseApiTest.books;


public class BookSteps {

    private final BookService bookService;

    public BookSteps(BookService bookService) {
        this.bookService = bookService;
    }

    public static Book createBook(Book book) {
        return Allure.step("Create a new book via API", () ->
                books().createBook(book)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Book.class)
        );
    }

    public static Book updateBook(int bookId, Book book) {
        return Allure.step(String.format("Update existing book via API with ID: %d", bookId), () ->
                books().updateBook(bookId, book)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Book.class)
        );
    }

    public static Book getBook(int bookId) {
        return Allure.step(String.format("Get existing book via API with ID: %d", bookId), () ->
                books().getBookById(bookId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Book.class)
        );
    }

    public static List<Book> getBooks() {
        return Allure.step("Get all existing books via API", () ->
                books().getAllBooks()
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .jsonPath()
                        .getList("", Book.class)
        );
    }

    public static void createBookExpectingFailure(Book book) {
        Allure.step("Create book with incomplete book data", () ->
                books().createBook(book)
                        .then()
                        .statusCode(400)
        );
    }

    public static int getMaxBookId(List<Book> books) {
        return Allure.step("Get max id of existing authors via API", () ->
                books.stream()
                        .map(Book::getId)
                        .max(Integer::compareTo)
                        .orElseThrow(() -> new IllegalStateException("Author list is empty"))
        );
    }
}
