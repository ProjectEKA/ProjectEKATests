package tests.apitests.healthinformationprovider.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.centralregistry.CentralRegistry;
import tests.apitests.healthinformationprovider.models.HIPPatientDiscovery;

public class HIPAPITest {

  @BeforeClass
  public void setup() {
    RestAssured.baseURI = "https://ncg-dev.projecteka.in/hip";
    RestAssured.useRelaxedHTTPSValidation();
  }

  @Test(enabled = false)
  public void discoverPatient() {

    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header("Authorization", "Bearer " + new CentralRegistry().getAuthTokenForHIP());

    String patientDiscoverRequestBody = new HIPPatientDiscovery().getPatientDiscoverRequestBody();

    request.body(patientDiscoverRequestBody);
    Response response = request.post("/patients/discover/carecontexts");

    JsonPath jsonPathEvaluator = response.jsonPath();

    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertEquals(jsonPathEvaluator.getString("patient.referenceNumber"), "RVH1004");
    Assert.assertEquals(jsonPathEvaluator.getString("patient.display"), "John Doe");
    Assert.assertEquals(
        jsonPathEvaluator.getString("patient.careContexts[0].referenceNumber"), "NCP10091");
    jsonPathEvaluator.getString("patient.matchedBy").equals("[Mobile, Gender]");
  }
}
