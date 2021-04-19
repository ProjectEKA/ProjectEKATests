package tests.apitests.consentmanager.tests;

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
import tests.apitests.helpers.utils.Login;

import static tests.apitests.consentmanager.TestBuilders.loginPayload;

public class LoginAPITest {
  String refreshToken;
  RequestSpecification specification;

  @BeforeClass(alwaysRun = true)
  public void setup() {

    RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
    RestAssured.useRelaxedHTTPSValidation();
    RequestSpecBuilder builder = new RequestSpecBuilder();
    builder.setContentType(ContentType.JSON);
    specification = builder.build();
  }

  @Test
  public void loginWithAPI() {
    Login loginUser = new Login();
    refreshToken = loginUser.getCMAuthToken();
    RequestSpecification request = RestAssured.given().spec(specification);
    request.header("Content-Type", "application/json");
    Response response = request.body(loginPayload()).post("/sessions");

    Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
    JsonPath jsonPathEvaluator = response.jsonPath();
    Assert.assertNotNull(jsonPathEvaluator.getString("token"));
  }
}
