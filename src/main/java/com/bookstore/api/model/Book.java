package com.bookstore.api.model;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private String title;
    private String description;
    private Integer pageCount;
    private String excerpt;
    private String publishDate;
}
