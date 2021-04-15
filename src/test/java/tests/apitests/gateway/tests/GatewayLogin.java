package tests.apitests.gateway.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.PropertiesCache;

import static tests.apitests.gateway.TestBuilders.gatewayPayload;

public class GatewayLogin {
    RequestSpecification specification;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("gatewayURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        specification = builder.build();
    }

    @Test
    public void loginWithAPI() {
        RequestSpecification request = RestAssured.given().spec(specification);
        Response response = request.body(gatewayPayload()).post("/v0.5/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        Assert.assertNotNull(jsonPathEvaluator.getString("accessToken"));
    }
}
