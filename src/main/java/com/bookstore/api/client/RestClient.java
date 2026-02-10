package com.bookstore.api.client;

import com.bookstore.api.config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public abstract class RestClient {

    static {
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 10000)
                        .setParam("http.socket.timeout", 10000)
                );
    }

    protected RequestSpecification defaultRequest() {
        return RestAssured.given()
                .relaxedHTTPSValidation()
                .baseUri(TestConfig.BASE_URL)
                .accept(ContentType.JSON)
                .header("Connection", "close");
    }

    protected RequestSpecification requestWithBody(Object body) {
        return defaultRequest()
                .contentType(ContentType.JSON)
                .body(body);
    }
}
