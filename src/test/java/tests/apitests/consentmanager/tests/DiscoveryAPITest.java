package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.helpers.utils.Login;

import static tests.apitests.consentmanager.TestBuilders.discoverProviderPayload;

public class DiscoveryAPITest {
  String authToken;

  @BeforeClass(alwaysRun = true)
  public void setup() {
    authToken = new Login().getCMAuthToken();
  }

  //@Test
  public void listProvidersAPI() {

    // fetches the list of providers
    RequestSpecification request = RestAssured.given();
    Response providersListResponse =
        request.header("Authorization", authToken).queryParam("name", "Tata").get("/providers");

    Assert.assertEquals(providersListResponse.getStatusCode(), 200);
    JsonPath jsonPathEvaluator = providersListResponse.jsonPath();
    Assert.assertEquals(
        jsonPathEvaluator.getString("identifier[0].name"), "Tata Memorial Hospital");
    Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].id"), "10000002");
  }

  //@Test
  public void discoverPatientsAPI() {

    // discovers the care-contexts for the patient
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header("Authorization", authToken);
    request.body(discoverProviderPayload());

    Response discoverCareContextResponse = request.post("/v1/care-contexts/discover");
    Assert.assertEquals(discoverCareContextResponse.getStatusCode(), 200);
    JsonPath jsonPathEvaluator = discoverCareContextResponse.jsonPath();
    Assert.assertEquals(jsonPathEvaluator.getString("patient.referenceNumber"), "RVH1002");
    Assert.assertEquals(jsonPathEvaluator.getString("patient.display"), "Navjot Singh");
  }
}
