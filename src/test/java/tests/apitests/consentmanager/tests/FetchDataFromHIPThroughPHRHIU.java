package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.utils.Login;

import static tests.apitests.consentmanager.TestBuilders.consentRequestFromPHRHIUPayLoad;
import static tests.apitests.consentmanager.TestBuilders.fetchDataUsingPHRHIUPayLoad;

public class FetchDataFromHIPThroughPHRHIU {
    String authToken;
    RequestSpecification request;
    JsonPath jsonPathEvaluator;
    public static String dataRequestId = "";


    @BeforeClass(alwaysRun = true)
    public void setup() {
        authToken = new Login().getCMAuthToken();
    }

    @Test(priority = 1)
    public void consentRequestFromPHRHIU() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        Response fetch = request.body(consentRequestFromPHRHIUPayLoad()).post("/v1/patient/consent-request");
        jsonPathEvaluator = fetch.jsonPath();
        dataRequestId = jsonPathEvaluator.getString("IN0410000183");
        Assert.assertEquals(fetch.getStatusCode(), 202);
    }

    @Test(priority = 2)
    public void getDataFromHIPUsingPHRHIU() {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        if (dataRequestId == null) System.out.println("Data already fetched");
        else {
            Response fetch = request.body(fetchDataUsingPHRHIUPayLoad(dataRequestId)).post("/v1/patient/health-information/fetch");
            jsonPathEvaluator = fetch.jsonPath();
            fetch.prettyPrint();

            Assert.assertEquals(fetch.getStatusCode(), 200);
        }
    }
}
