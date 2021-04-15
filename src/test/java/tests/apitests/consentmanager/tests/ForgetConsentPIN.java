package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.utils.Login;

import static tests.apitests.consentmanager.TestBuilders.resetPINPayload;
import static tests.apitests.consentmanager.TestBuilders.verifyOTP;

public class ForgetConsentPIN {

  String authToken;
  String temperoryToken;
  RequestSpecification request;
  JsonPath jsonPathEvaluator;

  //@BeforeClass(alwaysRun = true)
  public void setup() {
    authToken = new Login().getCMAuthToken();
  }

  //@Test
  public void generateVerifyOTPAPI() {

    // generate otp for reset consent-pin
    request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header("Authorization", authToken);

    Response generateOTPResponse = request.post("/patients/forgot-pin/generate-otp");
    jsonPathEvaluator = generateOTPResponse.jsonPath();
    String sessionId = jsonPathEvaluator.getString("sessionId");
    Assert.assertEquals(generateOTPResponse.getStatusCode(), 201);
    Assert.assertEquals(jsonPathEvaluator.getString("otpMediumValue"), "+**-******7777");

    // validate otp
    request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header("Authorization", authToken);
    request.body(verifyOTP().sessionId(sessionId).build());

    Response validateOTPResponse = request.post("/patients/forgot-pin/validate-otp");
    jsonPathEvaluator = validateOTPResponse.jsonPath();
    Assert.assertEquals(validateOTPResponse.getStatusCode(), 200);
    temperoryToken = jsonPathEvaluator.getString("temporaryToken");
  }

  //@Test(dependsOnMethods = "generateVerifyOTPAPI")
  public void resetConsentPINAPI() {

    // reset consent-pin
    request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header("Authorization", temperoryToken);
    request.body(resetPINPayload());

    Response resetConsentPINResponse = request.put("/patients/reset-pin");
    Assert.assertEquals(resetConsentPINResponse.getStatusCode(), 204);
  }
}
