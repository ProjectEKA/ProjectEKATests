package tests.apitests.gateway.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.utils.Login;

import static tests.apitests.gateway.TestBuilders.subscriptionRequestPayLoad;

public class CreateSubscriptionRequest {
    String authToken;
    RequestSpecification request;
    JsonPath jsonPathEvaluator;
    @BeforeClass
    public void setup() {
        authToken = new Login().getGatewayAuthToken();
    }

    @Test
    public void createSubscriptionRequest() {
        request = RestAssured.given();
        request.header("Authorization", authToken);
        request.header("X-CM-ID", "ndhm");
        Response response = request.body(subscriptionRequestPayLoad()).post("/v0.5/subscription-requests/cm/init");
        jsonPathEvaluator = response.jsonPath();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 202);
    }


}
