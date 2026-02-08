package com.bookstore.api.model;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String title;
    private String description;
    private int pageCount;
    private String excerpt;
    private String publishDate;
}
