package com.bookstore.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {
    private Integer id;
    private Integer idBook;
    private String firstName;
    private String lastName;
}
