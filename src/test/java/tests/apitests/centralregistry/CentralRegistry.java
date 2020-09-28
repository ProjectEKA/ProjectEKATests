package tests.apitests.centralregistry;

import static tests.apitests.centralregistry.TestBuilderCentralRegistry.consentManagerTokenPayload;
import static tests.apitests.centralregistry.TestBuilderCentralRegistry.hipTokenPayload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import tests.apitests.helpers.PropertiesCache;

public class CentralRegistry {

  public String getAuthTokenForHIP() {

    RestAssured.baseURI = PropertiesCache.getInstance().getProperty("centralRegistryURL");
    RestAssured.useRelaxedHTTPSValidation();
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");

    Response response = request.body(hipTokenPayload()).post("/api/1.0/sessions");
    JsonPath jsonPathEvaluator = response.jsonPath();
    Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
    return jsonPathEvaluator.getString("accessToken");
  }

  public String getAuthTokenForConsentManager() {

    RestAssured.baseURI = PropertiesCache.getInstance().getProperty("centralRegistryURL");
    RestAssured.useRelaxedHTTPSValidation();
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");

    Response response = request.body(consentManagerTokenPayload()).post("/api/1.0/sessions");
    JsonPath jsonPathEvaluator = response.jsonPath();
    Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
    return jsonPathEvaluator.getString("accessToken");
  }

  //    private String getConsentManagerRequestBody() {
  //        return "{\n" +
  //                "        \"clientId\": \"consent-manager\",\n" +
  //                "        \"clientSecret\": \"" + System.getenv("ConsentManagerSecret") + "\",\n"
  // +
  //                "        \"grantType\": \"password\"\n" +
  //                "        }";
  //    }

  //    private String getHIPRequestBody() {
  //        return "{\n" +
  //                "        \"clientId\": \"10000005\",\n" +
  //                "        \"clientSecret\": \"" + System.getenv("HIPSecret") + "\",\n" +
  //                "        \"grantType\": \"password\"\n" +
  //                "        }";
  //    }

}
