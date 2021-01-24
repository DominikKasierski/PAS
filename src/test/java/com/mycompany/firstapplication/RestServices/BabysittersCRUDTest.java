package com.mycompany.firstapplication.RestServices;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Security.JWTGeneratorVerifier;
import com.mycompany.firstapplication.utils.EntityIdentitySignerVerifier;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.security.enterprise.identitystore.CredentialValidationResult;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

//TODO: PRZEJRZEC CZY W TYM TESCIE NIE MA STATUSOW, ZROBIC COS, ZEBY NIE BYLO BLEDOW PO USUNIECIU ORDER()

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BabysittersCRUDTest {
    private final String token = JWTGeneratorVerifier.generateJWTString(new CredentialValidationResult("aAdamski", new HashSet<>(
            Arrays.asList("Admin"))));

    @Test
    @Order(1)
    public void getAllBabysittersTest() throws URISyntaxException {
        RequestSpecification request = getBasicRequest();
        Response response = request.get(new URI("https://localhost:8181/PAS/rest/resources"));
        String responseString = response.asString();

        assertTrue(responseString.contains("\"surname\":\"Kwiatkowska\""));
        assertTrue(responseString.contains("\"surname\":\"Rusin\""));
        assertTrue(responseString.contains("\"surname\":\"Krupa\""));
        assertTrue(responseString.contains("\"surname\":\"Sprzątająca\""));
        assertTrue(responseString.contains("\"surname\":\"Ucząca\""));
        assertTrue(responseString.contains("\"surname\":\"Taczka\""));
        assertTrue(responseString.contains("\"surname\":\"Jajko\""));
    }

    @Test
    @Order(2)
    public void getBabysitterTest() throws URISyntaxException {
        Babysitter babysitter = new Babysitter("Anna", "Kwiatkowska", 123, 12, 4);

        RequestSpecification request = getBasicRequest();
        String firstUUID = getFirstUUID();
        Response response = request.get(new URI("https://localhost:8181/PAS/rest/resources/" + firstUUID));

        babysitter.setUuid(firstUUID);
        String expectedETag = EntityIdentitySignerVerifier.calculateETag(babysitter);

        String responseString = response.asString();
        String ETag = response.header("ETag");

        assertFalse(ETag.isEmpty());
        assertEquals(expectedETag, ETag);
        assertTrue(responseString.contains("Anna"));
        assertTrue(responseString.contains("Kwiatkowska"));
        assertTrue(responseString.contains("123"));
        assertTrue(responseString.contains("12"));
        assertTrue(responseString.contains("4"));
    }

    @Test
    @Order(3)
    public void createBabysitterTest() throws URISyntaxException {
        String JSON = "{\n" +
                "        \"basePriceForHour\": 123,\n" +
                "        \"maxNumberOfChildrenInTheFamily\": 4,\n" +
                "        \"minChildAge\": 12,\n" +
                "        \"name\": \"Złocista\",\n" +
                "        \"surname\": \"Chryzantema\"\n" +
                "    }";

        RequestSpecification requestAll = getBasicRequest();
        RequestSpecification requestPost = getBasicRequest();

        requestPost.contentType("application/json");
        requestPost.body(JSON);

        String getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
        int initialSize = getAllResponseString.split("},").length;

        requestPost.post(new URI("https://localhost:8181/PAS/rest/resources/standard"));

        getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
        assertEquals(initialSize + 1, getAllResponseString.split("},").length);

        assertTrue(getAllResponseString.contains("\"name\":\"Złocista\""));
    }

    @Test
    @Order(4)
    public void createTeachingSitterTest() throws URISyntaxException {
        String JSON = "{\n" +
                "        \"basePriceForHour\": 123,\n" +
                "        \"maxNumberOfChildrenInTheFamily\": 4,\n" +
                "        \"minChildAge\": 12,\n" +
                "        \"name\": \"Złocista\",\n" +
                "        \"yearsOfExperienceInTeaching\": 10,\n" +
                "        \"surname\": \"Chryzantema\"\n" +
                "    }";

        RequestSpecification requestAll = getBasicRequest();
        RequestSpecification requestPost = getBasicRequest();

        requestPost.contentType("application/json");
        requestPost.body(JSON);

        String getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
        int initialSize = getAllResponseString.split("},").length;

        requestPost.post(new URI("https://localhost:8181/PAS/rest/resources/teaching"));

        getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
        assertEquals(initialSize + 1, getAllResponseString.split("},").length);

        assertTrue(getAllResponseString.contains("\"yearsOfExperienceInTeaching\":10"));
    }

    @Test
    @Order(5)
    public void createTidingSitterTest() throws URISyntaxException {
        String JSON = "{\n" +
                "        \"basePriceForHour\": 123,\n" +
                "        \"maxNumberOfChildrenInTheFamily\": 4,\n" +
                "        \"minChildAge\": 12,\n" +
                "        \"name\": \"Złocista\",\n" +
                "        \"valueOfCleaningEquipment\": 40.0,\n" +
                "        \"surname\": \"Chryzantema\"\n" +
                "    }";

        RequestSpecification requestAll = getBasicRequest();
        RequestSpecification requestPost = getBasicRequest();

        requestPost.contentType("application/json");
        requestPost.body(JSON);

        String getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
        int initialSize = getAllResponseString.split("},").length;

        requestPost.post(new URI("https://localhost:8181/PAS/rest/resources/tiding"));

        getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
        assertEquals(initialSize + 1, getAllResponseString.split("},").length);

        assertTrue(getAllResponseString.contains("\"valueOfCleaningEquipment\":40.0"));
    }

    @Test
    @Order(6)
    public void updateBabysitter() throws URISyntaxException {
        String firstUUID = getFirstUUID();
        RequestSpecification requestGet = getBasicRequest();
        Response getResponse = requestGet.get(new URI("https://localhost:8181/PAS/rest/resources/" + firstUUID));

        Pattern surnamePattern = Pattern.compile("(?<=\"surname\":\")[^\"]+");
        Matcher m = surnamePattern.matcher(getResponse.asString());
        String originalSurname = m.find() ? m.group() : "";
        String ETag = getResponse.header("ETag");

        RequestSpecification requestPut = getBasicRequest();
        requestPut.contentType("application/json");
        requestPut.header("If-Match", ETag);

        String randomSurname = RandomStringUtils.randomAlphabetic(8);
        String JSON = "{\n" +
                "        \"basePriceForHour\": 123,\n" +
                "        \"maxNumberOfChildrenInTheFamily\": 4,\n" +
                "        \"minChildAge\": 12,\n" +
                "        \"name\": \"Złocista\",\n" +
                "        \"uuid\": \"" + firstUUID + "\",\n" +
                "        \"surname\": \"" + randomSurname + "\"\n" +
                "    }";
        requestPut.body(JSON);

        requestPut.put("https://localhost:8181/PAS/rest/resources/standard/" + firstUUID);

        getResponse = requestGet.get(new URI("https://localhost:8181/PAS/rest/resources/" + firstUUID));
        m = surnamePattern.matcher(getResponse.asString());
        String changedSurname = m.find() ? m.group() : "";
        assertNotEquals(originalSurname, changedSurname);
    }

    @Test
    @Order(7)
    public void deleteBabysitter() throws URISyntaxException {
        String firstUUID = getFirstUUID();
        RequestSpecification requestAll = getBasicRequest();
        RequestSpecification requestDelete = getBasicRequest();

        requestDelete.contentType("application/json");

        String getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
        int initialSize = getAllResponseString.split("},").length;

        Pattern surnamePattern = Pattern.compile("(?<=\"surname\":\")[^\"]+");
        Matcher m = surnamePattern.matcher(getAllResponseString);
        String originalSurname = m.find() ? m.group() : "";

        requestDelete.delete(new URI("https://localhost:8181/PAS/rest/resources/" + firstUUID));

        getAllResponseString = requestAll.get(new URI("https://localhost:8181/PAS/rest/resources")).asString();
        assertEquals(initialSize - 1, getAllResponseString.split("},").length);
        assertFalse(getAllResponseString.contains(originalSurname));
    }

    private RequestSpecification getBasicRequest() {
        RequestSpecification request = RestAssured.given();
        request.relaxedHTTPSValidation();
        request.header(new Header("Authorization", "Bearer " + token));
        return request;
    }

    private String getFirstUUID() throws URISyntaxException {
        RequestSpecification request = getBasicRequest();

        Response getAllResponse = request.get(new URI("https://localhost:8181/PAS/rest/resources"));
        String responseString = getAllResponse.asString();
        Pattern pattern = Pattern.compile("(?<=\"uuid\":\")\\d{8}");
        Matcher m = pattern.matcher(responseString);
        if (m.find()) {
            return m.group();
        }
        return "";
    }

}
