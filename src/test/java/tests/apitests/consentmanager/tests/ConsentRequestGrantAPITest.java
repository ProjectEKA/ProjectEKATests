package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.apitests.consentmanager.utils.ConsentRequest;
import tests.apitests.helpers.PropertiesCache;
import tests.apitests.helpers.utils.Login;
import static tests.apitests.consentmanager.TestBuilders.grantConsentPayload;
import static tests.apitests.helpers.TestDataLiterals.AUTHORIZATION;

public class ConsentRequestGrantAPITest {

  String consentRequestId;
  String hiuAuthToken;

  @BeforeClass(alwaysRun = true)
  public void setup() {
    hiuAuthToken = new Login().getHIUAuthToken();
  }

  //@Test
  public void HIUConsentRequestAPI() {

    // create consent-request
    String patient = PropertiesCache.getInstance().getProperty("HIUPatient");
    Response createRequestResponse = new ConsentRequest().createConsent(patient);
    Assert.assertEquals(createRequestResponse.getStatusCode(), 202);

    // fetch consent-request id
    RequestSpecification request = RestAssured.given();
    Response fetchConsentsResponse =
        request.header(AUTHORIZATION, hiuAuthToken).get("/v1/hiu/consent-requests");

    consentRequestId = new ConsentRequest().fetchConsentRequestId(fetchConsentsResponse, patient);

    Assert.assertNotNull(consentRequestId);
    Assert.assertEquals(fetchConsentsResponse.getStatusCode(), 200);
  }

  //@Test(dependsOnMethods = "HIUConsentRequestAPI")
  public void grantConsentRequestAPI() {
    // grant consent-request
    String pinAuth = new ConsentRequest().verifyConsentPIN("grant");
    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.header(AUTHORIZATION, pinAuth);
    request.body(grantConsentPayload());

    Response grantConsentResponse =
        request.post("/consent-requests/" + consentRequestId + "/approve");

    Assert.assertEquals(grantConsentResponse.getStatusCode(), 200);
  }

  //@Test(dependsOnMethods = "grantConsentRequestAPI")
  public void checkHIUConsentStatusAPI() {

    // fetch the consents list and fetch the status
    String authToken = new Login().getHIUAuthToken();
    RequestSpecification request = RestAssured.given();

    Response consentStatusResponse =
        request.header(AUTHORIZATION, authToken).get("/v1/hiu/consent-requests");
    String actualStatus =
        new ConsentRequest().fetchConsentStatus(consentStatusResponse, consentRequestId);

    Assert.assertEquals(consentStatusResponse.getStatusCode(), 200);
    Assert.assertEquals(actualStatus, "GRANTED");
  }

  //    @Test(dependsOnMethods = "checkHIUConsentStatusAPI")
  //    public void fetchHealthDataAPI() {
  //
  //        //fetch the health-data of the patient
  //        RequestSpecification request = RestAssured.given();
  //        Response patientHealthDataResponse = request.header(AUTHORIZATION, hiuAuthToken)
  //                .get("/health-information/fetch/" + consentRequestId);
  //
  //        Assert.assertEquals(patientHealthDataResponse.getStatusCode(), 200);
  //        JsonPath jsonPathEvaluator = patientHealthDataResponse.jsonPath();
  //        Assert.assertEquals(jsonPathEvaluator.getString("size"), "2");
  //        Assert.assertEquals(jsonPathEvaluator.getString("entries[0].hipId"), "10000005");
  //        Assert.assertEquals(jsonPathEvaluator.getString("entries[0].hipName"), "Max Health
  // Care");
  //        Assert.assertEquals(jsonPathEvaluator.getString("entries[0].status"), "SUCCEEDED");
  //        Assert.assertEquals(jsonPathEvaluator.getString("entries[1].hipId"), "10000005");
  //        Assert.assertEquals(jsonPathEvaluator.getString("entries[1].hipName"), "Max Health
  // Care");
  //        Assert.assertEquals(jsonPathEvaluator.getString("entries[1].status"), "SUCCEEDED");
  //    }

}
