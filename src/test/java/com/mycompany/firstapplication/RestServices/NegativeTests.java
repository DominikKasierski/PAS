package com.mycompany.firstapplication.RestServices;

import com.mycompany.firstapplication.Security.JWTGeneratorVerifier;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.identitystore.CredentialValidationResult;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeTests {

    private final String token = JWTGeneratorVerifier
            .generateJWTString(new CredentialValidationResult("aAdamski", new HashSet<>(
                    Arrays.asList("Admin"))));

    @Test
    public void resourceValidationCheck() throws URISyntaxException {
//        String JSON = "{\n" +
//                "        \"basePriceForHour\": 123,\n" +
//                "        \"maxNumberOfChildrenInTheFamily\": 5,\n" +
//                "        \"minChildAge\": 1,\n" +
//                "        \"name\": \"ZTatiana\",\n" +
//                "        \"surname\": \"Alabaster\"\n" +
//                "    }";
//
//        RequestSpecification requestAll = getBasicRequest();
//        RequestSpecification requestPost = getBasicRequest();
//
//        requestPost.contentType("application/json");
//        requestPost.body(JSON);
//
//        String getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
//        int initialSize = getAllResponseString.split("},").length;
//
//        requestPost.post(new URI("https://localhost:8181/PAS/rest/resources/standard"));
//
//        getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
//        assertEquals(initialSize + 1, getAllResponseString.split("},").length);
//
//        assertTrue(getAllResponseString.contains("\"name\":\"ZTatiana\""));
    }

    private RequestSpecification getBasicRequest() {
        RequestSpecification request = RestAssured.given();
        request.relaxedHTTPSValidation();
        return request;
    }
}
