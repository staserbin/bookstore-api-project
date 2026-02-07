package com.bookstore.api.service;

import com.bookstore.api.client.RestClient;
import com.bookstore.api.config.Endpoints;
import com.bookstore.api.model.Book;
import io.restassured.response.Response;

public class BookService extends RestClient {

    public Response getAllBooks() {
        return defaultRequest().get(Endpoints.Books.BASE);
    }

    public Response getBookById(int id) {
        return defaultRequest().get(String.format(Endpoints.Books.BY_ID, id));
    }

    public Response createBook(Book book) {
        return requestWithBody(book).post(Endpoints.Books.BASE);
    }

    public Response updateBook(int id, Book book) {
        return requestWithBody(book).put(String.format(Endpoints.Books.BY_ID, id));
    }

    public Response deleteBook(int id) {
        return defaultRequest().delete(String.format(Endpoints.Books.BY_ID, id));
    }
}
