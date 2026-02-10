package com.bookstore.api.factory;

import com.bookstore.api.model.Book;


public class BookFactory {
    public static Book buildBook(String title, String description, Integer pageCount, String excerpt,
                                 String publishDate) {
        return Book.builder()
                .title(title)
                .description(description)
                .pageCount(pageCount)
                .excerpt(excerpt)
                .publishDate(publishDate)
                .build();
    }

    public static Book buildBookWithId(Integer id, String title, String description, Integer pageCount, String excerpt,
                                       String publishDate) {
        return Book.builder()
                .id(id)
                .title(title)
                .description(description)
                .pageCount(pageCount)
                .excerpt(excerpt)
                .publishDate(publishDate)
                .build();
    }
}

