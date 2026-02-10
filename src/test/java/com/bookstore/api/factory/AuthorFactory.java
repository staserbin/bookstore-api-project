package com.bookstore.api.factory;

import com.bookstore.api.model.Author;


public class AuthorFactory {
    public static Author buildAuthor(int bookId, String firstName, String lastName) {
        return Author.builder()
                .idBook(bookId)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
