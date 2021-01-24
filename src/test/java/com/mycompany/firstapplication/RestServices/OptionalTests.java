package com.mycompany.firstapplication.RestServices;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTests {

    @Test
    public void getAllUsersTest() throws URISyntaxException {
        RequestSpecification request = getBasicRequest();

    }

    private RequestSpecification getBasicRequest() {
        RequestSpecification request = RestAssured.given();
        request.relaxedHTTPSValidation();
        return request;
    }
}
