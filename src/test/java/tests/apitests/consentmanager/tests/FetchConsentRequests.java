package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.utils.Login;

public class FetchConsentRequests {
    String authToken;
    RequestSpecification request;
    JsonPath jsonPathEvaluator;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        authToken = new Login().getCMAuthToken();
    }

    @Test
    public void fetchConsentRequestsOfAUser() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        Response fetch = request.get("/patients/requests?consentLimit=5&consentOffset=0&" +
                "lockerSetupLimit=5&lockerSetupOffset=0&subscriptionLimit=5&subscriptionOffset=0" +
                "&authorizationLimit=5&authorizationOffset=0");
        jsonPathEvaluator = fetch.jsonPath();
        fetch.prettyPrint();

        Assert.assertEquals(fetch.getStatusCode(), 200);

    }
}
