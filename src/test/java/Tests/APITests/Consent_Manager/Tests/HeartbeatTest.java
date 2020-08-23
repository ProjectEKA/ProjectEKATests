package Tests.APITests.Consent_Manager.Tests;

import Tests.APITests.Helpers.Utils.LoginUtil;
import Tests.APITests.Helpers.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HeartbeatTest {

    RequestSpecification specification;

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        specification = builder.build();
    }

    @Test
    public void heartbeatAPI() {

        //check status of redis cache and db with heartbeat call
        RequestSpecification request = RestAssured.given();
        Response heartbeatResponse = request.header("Authorization", new LoginUtil().getCMAuthToken())
                .get("/v0.5/heartbeat");
        Assert.assertEquals(heartbeatResponse.getStatusCode(), 200);
        Assert.assertEquals(heartbeatResponse.jsonPath().getString("status"), "UP");
    }

}
