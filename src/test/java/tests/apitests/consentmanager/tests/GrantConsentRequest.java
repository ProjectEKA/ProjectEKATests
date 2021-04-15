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

import static tests.apitests.consentmanager.TestBuilders.grantConsentPayload;
import static tests.apitests.consentmanager.TestBuilders.grantPINPayload;

public class GrantConsentRequest {
    String authToken;
    public static String tempToken = "";
    RequestSpecification request;
    JsonPath jsonPathEvaluator;
    public static String consentRequestID ;
    List<LinkedHashMap> consentRequestsList;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        authToken = new Login().getCMAuthToken();
    }

    @Test(priority = 1)
    public void getConsentRequestToGrant() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        Response fetch = request.get("/patients/requests?consentLimit=5&consentOffset=0&" +
                "lockerSetupLimit=5&lockerSetupOffset=0&subscriptionLimit=5&subscriptionOffset=0" +
                "&authorizationLimit=5&authorizationOffset=0");
        jsonPathEvaluator = fetch.jsonPath();
        consentRequestsList = jsonPathEvaluator.get("consents.requests");
        for (LinkedHashMap object : consentRequestsList) {
            if (object.get("status").toString().equals("REQUESTED")) {

                consentRequestID = object.get("id").toString();
                break;
            }
        }
    }

    @Test(priority = 2)
    public void verifyPinAPI() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        Response response = request.body(grantPINPayload()).post("/patients/verify-pin");
        Assert.assertEquals(response.getStatusCode(), 200, "Pin failed");
        jsonPathEvaluator = response.jsonPath();
        tempToken = jsonPathEvaluator.getString("temporaryToken");
        Assert.assertNotNull(jsonPathEvaluator.getString("temporaryToken"));

    }

    @Test(priority = 3)
    public void grantConsent() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", tempToken);
        if (consentRequestID != null) {
            Response response = request.body(grantConsentPayload()).post("/consent-requests/" + consentRequestID + "/approve");
            response.prettyPrint();
            Assert.assertEquals(response.getStatusCode(), 200);
        } else {
            System.out.println("No consent request found to grant");
        }

    }
}
