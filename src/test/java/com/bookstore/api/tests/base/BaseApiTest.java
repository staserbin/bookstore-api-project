package com.bookstore.api.tests.base;

import com.bookstore.api.service.AuthorService;
import com.bookstore.api.service.BookService;
import com.bookstore.api.steps.AuthorSteps;
import com.bookstore.api.steps.BookSteps;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public abstract class BaseApiTest {

    protected BookSteps bookSteps;
    protected AuthorSteps authorSteps;

    @BeforeEach
    void initSteps() {
        bookSteps = new BookSteps(books());
        authorSteps = new AuthorSteps(authors());
    }

    private static final ThreadLocal<BookService> bookService =
            ThreadLocal.withInitial(BookService::new);

    private static final ThreadLocal<AuthorService> authorService =
            ThreadLocal.withInitial(AuthorService::new);

    public static BookService books() {
        return bookService.get();
    }

    public static AuthorService authors() {
        return authorService.get();
    }

    protected String currentDateTime() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
    }
}
