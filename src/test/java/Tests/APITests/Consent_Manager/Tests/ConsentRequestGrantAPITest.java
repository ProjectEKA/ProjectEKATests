package Tests.APITests.Consent_Manager.Tests;

import Tests.APITests.APIUtils;
import Tests.APITests.LoginUser;
import Tests.APITests.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Tests.APITests.Consent_Manager.TestBuildersCM.grantConsentPayload;

public class ConsentRequestGrantAPITest {

    String consentRequestId;
    String hiuAuthToken;

    @BeforeClass
    public void setup() {
        hiuAuthToken = new LoginUser().getHIUAuthToken();
    }

    @Test
    public void HIUConsentRequestAPI() {

        //create consent-request
        String patient = PropertiesCache.getInstance().getProperty("HIUPatient");
        Response createRequestResponse = new APIUtils().createConsent(patient);
        Assert.assertEquals(createRequestResponse.getStatusCode(), 202);

        //fetch consent-request id
            RequestSpecification request = RestAssured.given();
            Response fetchConsentsResponse = request.header("Authorization", hiuAuthToken)
                    .get("/v1/hiu/consent-requests");
            consentRequestId = new APIUtils().fetchConsentRequestId(fetchConsentsResponse, patient);
            Assert.assertNotNull(consentRequestId);
            Assert.assertEquals(fetchConsentsResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "HIUConsentRequestAPI")
    public void grantConsentRequestAPI() {

        //grant consent-request
        String pinAuth = new APIUtils().verifyConsentPIN("grant");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", pinAuth);

        request.body(grantConsentPayload());
        Response grantConsentResponse = request.post("/consent-requests/" + consentRequestId + "/approve");
        Assert.assertEquals(grantConsentResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "grantConsentRequestAPI")
    public void checkHIUConsentStatusAPI() {

        //fetch the consents list and fetch the status
        String authToken = new LoginUser().getHIUAuthToken();
        RequestSpecification request = RestAssured.given();
        Response consentStatusResponse = request.header("Authorization", authToken)
                .get("/v1/hiu/consent-requests");
        String actualStatus = new APIUtils().fetchConsentStatus(consentStatusResponse, consentRequestId);

        Assert.assertEquals(consentStatusResponse.getStatusCode(), 200);
        Assert.assertEquals(actualStatus, "GRANTED");
    }

//    @Test(dependsOnMethods = "checkHIUConsentStatusAPI")
//    public void fetchHealthDataAPI() {
//
//        //fetch the health-data of the patient
//        RequestSpecification request = RestAssured.given();
//        Response patientHealthDataResponse = request.header("Authorization", hiuAuthToken)
//                .get("/health-information/fetch/" + consentRequestId);
//
//        Assert.assertEquals(patientHealthDataResponse.getStatusCode(), 200);
//        JsonPath jsonPathEvaluator = patientHealthDataResponse.jsonPath();
//        Assert.assertEquals(jsonPathEvaluator.getString("size"), "2");
//        Assert.assertEquals(jsonPathEvaluator.getString("entries[0].hipId"), "10000005");
//        Assert.assertEquals(jsonPathEvaluator.getString("entries[0].hipName"), "Max Health Care");
//        Assert.assertEquals(jsonPathEvaluator.getString("entries[0].status"), "SUCCEEDED");
//        Assert.assertEquals(jsonPathEvaluator.getString("entries[1].hipId"), "10000005");
//        Assert.assertEquals(jsonPathEvaluator.getString("entries[1].hipName"), "Max Health Care");
//        Assert.assertEquals(jsonPathEvaluator.getString("entries[1].status"), "SUCCEEDED");
//    }

}
