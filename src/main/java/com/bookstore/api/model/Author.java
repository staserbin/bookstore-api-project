package com.bookstore.api.model;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private Integer id;
    private Integer idBook;
    private String firstName;
    private String lastName;
}
