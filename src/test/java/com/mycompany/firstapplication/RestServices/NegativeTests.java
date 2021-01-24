package com.mycompany.firstapplication.RestServices;

import com.mycompany.firstapplication.Security.JWTGeneratorVerifier;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.identitystore.CredentialValidationResult;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class NegativeTests {

    private final String token = JWTGeneratorVerifier
            .generateJWTString(new CredentialValidationResult("aAdamski", new HashSet<>(
                    Arrays.asList("Admin"))));

    @Test
    public void resourcesValidationCheck() throws URISyntaxException {
        String JSON = "{\n" +
                "        \"basePriceForHour\": -1,\n" +
                "        \"maxNumberOfChildrenInTheFamily\": 5,\n" +
                "        \"minChildAge\": 1,\n" +
                "        \"name\": \"Anna\",\n" +
                "        \"surname\": \"Panna\"\n" +
                "    }";

        RequestSpecification requestPost = getBasicRequest();
        requestPost.contentType("application/json");
        requestPost.body(JSON);

        Response response = requestPost.post(new URI("https://localhost:8181/PAS/rest/resources/standard"));
        assertEquals(422, response.statusCode());
    }

    @Test
    public void usersValidationCheck() throws URISyntaxException {
        String JSON = "{\n" +
                "        \"name\": \"1\",\n" +
                "        \"role\": \"Admin\",\n" +
                "        \"password\": \"testTEST\",\n" +
                "        \"surname\": \"testSurname\",\n" +
                "        \"login\": \"testLogin\"\n" +
                "    }";

        RequestSpecification requestPost = getBasicRequest();
        requestPost.contentType("application/json");
        requestPost.body(JSON);

        Response response = requestPost.post(new URI("https://localhost:8181/PAS/rest/users/admin"));
        assertEquals(422, response.statusCode());
    }

    @Test
    public void authorizationFailedLogin() throws URISyntaxException {
        RequestSpecification requestPost = RestAssured.given();
        requestPost.relaxedHTTPSValidation();
        requestPost.contentType("application/json");

        String JSON = "{\n" +
                "        \"login\": \"nieMaTakiegoLoginu\",\n" +
                "        \"password\": \"adamski\"\n" +
                "    }";
        requestPost.body(JSON);

        Response responseFromAuth = requestPost.post(new URI("https://localhost:8181/PAS/rest/auth"));
        assertEquals(401, responseFromAuth.statusCode());

    }

    @Test
    public void authorizationFailedPassword() throws URISyntaxException {
        RequestSpecification requestPost = RestAssured.given();
        requestPost.relaxedHTTPSValidation();
        requestPost.contentType("application/json");

        String JSON = "{\n" +
                "        \"login\": \"aAdamski\",\n" +
                "        \"password\": \"nieMaTakiegoHasła\"\n" +
                "    }";
        requestPost.body(JSON);

        Response responseFromAuth = requestPost.post(new URI("https://localhost:8181/PAS/rest/auth"));
        assertEquals(401, responseFromAuth.statusCode());
    }

    @Test
    public void authorizationFailedAccountActivity() throws URISyntaxException {
        //1. wejsc na klienta, pokazac, ze autoryzacja zwrocila kod 202
        //2. wejsc na admina, putem zmienic temu klientowi active na false
        //3. sprobowac ponownie zalogowac się na admina, status = 401
    }

    @Test
    public void employingEmployedBabysitter() throws URISyntaxException {
        //1. wejsc na klienta, ktory ma jakies alokacje
        //2. getem pobrac wszystkie alokacje i matchem wyciagnac z responsa uuid zatrudnionej opiekunnki
        //3. sprobowac dodac zatrudnienie z tym uuid zwroci status 500 chyba?
    }

    @Test
    public void httpsTest() throws URISyntaxException {
        //1. sprobowac wyslac jakiegos geta uzywajac w URI http i 8080
        //2. powinno zwrocic status (403?) = redirect
    }

    @Test
    public void JWTTest() throws URISyntaxException {
        //1. sprobowac wbic na jakis endpoint (oprocz auth) bez tokenu albo dodac naglowek, ale pusty
        //2. powinno zwrocic status (401?) = unauthorized
    }

    @Test
    public void roleTest() throws URISyntaxException {
        //1. wbic z admina na jakis endpoint userow i pokazac, ze status 200
        //2. sprobowac wbic na ten sam endpoint z clienta/superUsera i pokazac, ze status 403 forbidden
    }

    private RequestSpecification getBasicRequest() {
        RequestSpecification request = RestAssured.given();
        request.relaxedHTTPSValidation();
        request.header(new Header("Authorization", "Bearer " + token));
        return request;
    }
}
