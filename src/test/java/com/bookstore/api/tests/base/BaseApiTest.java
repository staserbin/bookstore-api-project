package com.bookstore.api.tests.base;

import com.bookstore.api.service.AuthorService;
import com.bookstore.api.service.BookService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public abstract class BaseApiTest {

    private static final ThreadLocal<BookService> bookService =
            ThreadLocal.withInitial(BookService::new);

    private static final ThreadLocal<AuthorService> authorService =
            ThreadLocal.withInitial(AuthorService::new);

    protected BookService books() {
        return bookService.get();
    }

    protected AuthorService authors() {
        return authorService.get();
    }

    protected String currentDateTime() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
    }
}
