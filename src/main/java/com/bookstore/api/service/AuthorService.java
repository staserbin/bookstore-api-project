package com.bookstore.api.service;

import com.bookstore.api.client.RestClient;
import com.bookstore.api.config.Endpoints;
import com.bookstore.api.model.Author;
import io.restassured.response.Response;


public class AuthorService extends RestClient {

    public Response getAllAuthors() {
        return defaultRequest().get(Endpoints.Authors.BASE);
    }

    public Response getAuthorById(int id) {
        return defaultRequest().get(String.format(Endpoints.Authors.BY_ID, id));
    }

    public Response createAuthor(Author author) {
        return requestWithBody(author).post(Endpoints.Authors.BASE);
    }

    public Response updateAuthor(int id, Author author) {
        return requestWithBody(author).put(String.format(Endpoints.Authors.BY_ID, id));
    }

    public Response deleteAuthor(int id) {
        return defaultRequest().delete(String.format(Endpoints.Authors.BY_ID, id));
    }
}
