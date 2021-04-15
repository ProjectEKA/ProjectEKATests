package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.utils.Login;

import static tests.apitests.consentmanager.TestBuilders.shareProfilePayLoad;

public class ShareProfile {
    String authToken;
    RequestSpecification request;
    JsonPath jsonPathEvaluator;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        authToken = new Login().getCMAuthToken();
    }

    @Test
    public void shareProfileDetailsToHip() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        Response response = request.body(shareProfilePayLoad()).post("/patients/profile/share");
        jsonPathEvaluator = response.jsonPath();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
