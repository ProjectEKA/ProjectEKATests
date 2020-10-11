package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.PropertiesCache;
import tests.apitests.helpers.utils.Login;

public class HeartbeatTest {

  RequestSpecification specification;

  @BeforeClass(alwaysRun = true)
  public void setup() {

    RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
    RestAssured.useRelaxedHTTPSValidation();
    RequestSpecBuilder builder = new RequestSpecBuilder();
    builder.setContentType(ContentType.JSON);
    specification = builder.build();
  }

  @Test(groups = {"nhsDev"})
  public void heartbeatAPI() {
    // check status of redis cache and db with heartbeat call
    RequestSpecification request = RestAssured.given();

    Response heartbeatResponse =
        request.header("Authorization", new Login().getCMAuthToken()).get("/v0.5/heartbeat");

    Assert.assertEquals(heartbeatResponse.getStatusCode(), 200);
    Assert.assertEquals(heartbeatResponse.jsonPath().getString("status"), "UP");
  }
}
