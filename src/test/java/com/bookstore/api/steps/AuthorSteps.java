package com.bookstore.api.steps;

import com.bookstore.api.model.Author;
import com.bookstore.api.service.AuthorService;
import io.qameta.allure.Allure;
import java.util.List;

import static com.bookstore.api.tests.base.BaseApiTest.authors;


public class AuthorSteps {

    private final AuthorService authorService;

    public AuthorSteps(AuthorService authorService) {
        this.authorService = authorService;
    }

    public static Author createAuthor(Author author) {
        return Allure.step("Create a new author via API", () ->
                authors().createAuthor(author)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Author.class)
        );
    }

    public static Author updateAuthor(int authorId, Author author) {
        return Allure.step(String.format("Update existing author via API with ID: %d", authorId), () ->
                authors().updateAuthor(authorId, author)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Author.class)
        );
    }

    public static Author getAuthor(int authorId) {
        return Allure.step(String.format("Get existing author via API with ID: %d", authorId), () ->
                authors().getAuthorById(authorId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Author.class)
        );
    }

    public static List<Author> getAuthors() {
        return Allure.step("Get all existing authors via API", () ->
                authors().getAllAuthors()
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .jsonPath()
                        .getList("", Author.class)
        );
    }

    public static int getMaxAuthorId(List<Author> authors) {
        return Allure.step("Get max id of existing authors via API", () ->
                authors.stream()
                .map(Author::getId)
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("Author list is empty"))
        );
    }
}
