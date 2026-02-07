package com.bookstore.api.config;

public class Endpoints {

    public static class Books {
        public static final String BASE = "/api/v1/Books";
        public static final String BY_ID = "/api/v1/Books/%d";
    }

    public static class Authors {
        public static final String BASE = "/api/v1/Authors";
        public static final String BY_ID = "/api/v1/Authors/%d";
    }
}
