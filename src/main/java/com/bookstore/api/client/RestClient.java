package com.bookstore.api.client;

import com.bookstore.api.config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    protected RequestSpecification defaultRequest() {
        return RestAssured.given()
                .baseUri(TestConfig.BASE_URL)
                .accept(ContentType.JSON)
                .log().all();
    }

    protected RequestSpecification requestWithBody(Object body) {
        return defaultRequest()
                .contentType(ContentType.JSON)
                .body(body);
    }

}
