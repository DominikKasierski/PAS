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
    public void updateToken() throws URISyntaxException {
        //1. zalogowac sie, dodac token do naglowka, zapisac token do zmiennej, pokazac, ze mozna wejsc na jakis endpoint
        //2. odswiezyc token, dodac go do naglowka, zapisac go do zmiennej, pokazac, ze mozna tez chodzic po endpointach
        //3. pokazac, ze tokeny sa rozne
        //4. przekonwertowac tokeny (JWTAuthenticationMechanism, linie 37 - 44)
        //5. pokazac, ze odswiezony ma pozniejsza date wygasniecia

    }

    @Test
    public void resourcesETag() throws URISyntaxException {
        //1. zalogowac sie, pobrac ETag z geta, pobrac uuid jakiejs opiekunki
        //2. utworzyc jakies nowe uuid, pokazac, ze sie rozni z tym co miala opiekunka
        //3. sprobowac putnac ze zmienionym uuid, w naglowku If-Match = ETag
        //4. status odpowiedzi = 400
    }

    @Test
    public void usersETag() throws URISyntaxException {
        //1. zalogowac sie, pobrac ETag z geta, pobrac uuid jakiegos usera
        //2. utworzyc jakies nowe uuid, pokazac, ze sie rozni z tym co mial user
        //3. sprobowac putnac ze zmienionym uuid, w naglowku If-Match = ETag
        //4. status odpowiedzi = 400
    }

    @Test
    public void updateTokenInactiveUser() throws URISyntaxException {
        //1. wejsc na klienta, pokazac, ze autoryzacja zwrocila kod 202
        //1. pokazac, ze endpoint updateToken zwraca status 202
        //2. wejsc na admina, putem zmienic temu klientowi active na false
        //3. sprobowac ponownie na kliencie odswiezyc token, status = 401
    }

    private RequestSpecification getBasicRequest() {
        RequestSpecification request = RestAssured.given();
        request.relaxedHTTPSValidation();
        return request;
    }
}
