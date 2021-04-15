package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.utils.Login;

import java.util.LinkedHashMap;
import java.util.List;

import static tests.apitests.consentmanager.TestBuilders.grantAuthPayLoad;

public class GrantAuthRequest {
    RequestSpecification request;
    JsonPath jsonPathEvaluator;
    String authToken;
    public static String authRequestID = null;
    List<LinkedHashMap> authRequestsList;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        authToken = new Login().getCMAuthToken();
    }

    @Test(priority = 1)
    public void getAuthRequestToGrant() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        Response fetch = request.get("/patients/requests?consentLimit=5&consentOffset=0&" +
                "lockerSetupLimit=5&lockerSetupOffset=0&subscriptionLimit=5&subscriptionOffset=0" +
                "&authorizationLimit=5&authorizationOffset=0");
        jsonPathEvaluator = fetch.jsonPath();
        authRequestsList = jsonPathEvaluator.get("authorizations.requests");
        for (LinkedHashMap object : authRequestsList) {
            if (object.get("status").toString().equals("REQUESTED")) {
                authRequestID = object.get("requestId").toString();
                break;
            }
        }
    }

    @Test(priority = 2)
    public void grantAuthRequest() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        if (authRequestID != null) {
            Response response = request.body(grantAuthPayLoad()).post("/authorization-requests/" + authRequestID + "/approve");
            response.prettyPrint();
            Assert.assertEquals(response.getStatusCode(), 200);
        } else {
            System.out.println("No authorization request found to grant");
        }
    }
}
