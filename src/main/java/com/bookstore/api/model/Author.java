package com.bookstore.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private int id;
    private int idBook;
    private String firstName;
    private String lastName;
}
