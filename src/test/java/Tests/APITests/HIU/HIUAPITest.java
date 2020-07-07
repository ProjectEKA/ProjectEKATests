package Tests.APITests.HIU;

import Tests.APITests.APIUtils.APIUtils;
import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HIUAPITest {

    String authToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBackendURL");
        RestAssured.useRelaxedHTTPSValidation();
        authToken = new LoginUser().getHIUAuthToken();
    }

    @Test
    public void discoverPatientAPI() {
        RequestSpecification request = RestAssured.given();
        String patientID = PropertiesCache.getInstance().getProperty("HIUPatient");

        Response response = request.header("Authorization", authToken).pathParam("patientID", patientID)
                .get("/v1/patients/{patientID}");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("patient.id"), patientID);
    }

    @Test
    public void createConsentRequest() {
        Response response = new APIUtils().createConsent(PropertiesCache.getInstance().getProperty("HIUPatient"));
        Assert.assertEquals(response.getStatusCode(), 202);
    }

    @Test
    public void fetchConsentRequestId() {
        RequestSpecification request = RestAssured.given();

        Response patientDetailsResponse = request.header("Authorization", authToken).get("/v1/hiu/consent-requests");
        JsonPath jsonPathEvaluator = patientDetailsResponse.jsonPath();
        String consentRequestId = jsonPathEvaluator.getString("consentRequestId[0]");
        System.out.println(consentRequestId);

    }
}
