package tests.apitests.helpers.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import tests.apitests.helpers.PropertiesCache;

import static tests.apitests.consentmanager.TestBuilders.loginPayload;
import static tests.apitests.gateway.TestBuilders.gatewayPayload;
import static tests.apitests.healthinformationuser.TestBuilders.loginDoctorPayload;

public class Login {

    public String getCMAuthToken() {

        String authToken = "";
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginPayload()).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");

        if ((PropertiesCache.getInstance().getProperty("ENVIRONMENT")).equalsIgnoreCase("nhaDev")) {
            authToken =
                    jsonPathEvaluator.getString(PropertiesCache.getInstance().getProperty("accessKey"));
        } else if ((PropertiesCache.getInstance().getProperty("ENVIRONMENT")).equalsIgnoreCase("ncg")) {
            authToken =
                    "Bearer "
                            + jsonPathEvaluator.getString(PropertiesCache.getInstance().getProperty("accessKey"));
        }
        return authToken;
    }

    public String getCMRefreshToken() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginPayload()).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");

        return jsonPathEvaluator.getString("refreshToken");
    }

    public static String getHIUAuthToken() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBaseURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginDoctorPayload()).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        return jsonPathEvaluator.getString("accessToken");
    }

    public String getGatewayAuthToken() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("gatewayURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(gatewayPayload()).post("/v0.5/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        Assert.assertNotNull(jsonPathEvaluator.getString("accessToken"));
        return "Bearer "+jsonPathEvaluator.getString("accessToken");
    }
}
