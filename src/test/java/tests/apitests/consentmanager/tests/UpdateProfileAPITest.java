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

import static tests.apitests.consentmanager.TestBuilders.*;

public class UpdateProfileAPITest {

  RequestSpecification specification;
  String authToken;
  String pinAuthToken;

  @BeforeClass(alwaysRun = true)
  public void setup() {
    RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
    RestAssured.useRelaxedHTTPSValidation();
    RequestSpecBuilder builder = new RequestSpecBuilder();
    builder.setContentType(ContentType.JSON);
    specification = builder.build();
    authToken = new Login().getCMAuthToken();
  }

  @Test(groups = {"nhaDev"})
  public void updatePasswordAPI() {

    // update password from profile
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header("Authorization", authToken);
    request.body(updatePasswordPayload());

    Response updatePasswordResponse = request.relaxedHTTPSValidation().put("/patients/profile/update-password");
    Assert.assertEquals(updatePasswordResponse.getStatusCode(), 200);
  }

  @Test(groups = {"nhaDev"})
  public void verifyPINAPI() {

    // verify-pin for update-pin
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header("Authorization", authToken);
    request.body(verifyUpdatePINPayload());

    Response verifyPINResponse = request.relaxedHTTPSValidation().post("/patients/verify-pin");
    Assert.assertEquals(verifyPINResponse.getStatusCode(), 200);
    pinAuthToken = verifyPINResponse.jsonPath().getString("temporaryToken");
  }

  @Test(
      dependsOnMethods = "verifyPINAPI",
      groups = {"nhaDev"})
  public void updatePINAPI() {

    // update PIN from profile
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header("Authorization", pinAuthToken);
    request.body(consentPINPayload());

    Response updatePINResponse = request.relaxedHTTPSValidation().post("/patients/change-pin");
    Assert.assertEquals(updatePINResponse.getStatusCode(), 200);
  }

  //    @Test(dependsOnMethods = "updatePINAPI")
  //    public void logoutAPI() {
  //        RequestSpecification request = RestAssured.given();
  //
  //        request.header("Content-Type", "application/json");
  //        request.header("Authorization", authToken);
  //        String logoutRequestBody = new LoginUser().getCMLogoutRequestBody(new
  // LoginUser().getCMRefreshToken());
  //        request.body(logoutRequestBody);
  //        Response response = request.post("/logout");
  //        Assert.assertEquals(response.getStatusCode(), 200);
  //    }

}
