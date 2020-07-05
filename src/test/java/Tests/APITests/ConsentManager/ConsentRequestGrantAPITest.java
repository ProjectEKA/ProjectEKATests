package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConsentRequestGrantAPITest {

    String hiuAuthToken;
    String cmAuthToken;
    String PINToken;
    String consentRequestId;

    @Test
    public void createHIUSessionAPI() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBackendURL");
        RestAssured.useRelaxedHTTPSValidation();
        hiuAuthToken = new LoginUser().getHIUAuthToken();
    }

    @Test(dependsOnMethods = "createHIUSessionAPI")
    public void createConsentRequestAPI() {
        Response response = new APIUtils().createConsent(PropertiesCache.getInstance().getProperty("HIUPatient"));
        Assert.assertEquals(response.getStatusCode(), 202);
    }

    @Test(dependsOnMethods = "createConsentRequestAPI")
    public void fetchConsentRequestIdAPI() {
        RequestSpecification request = RestAssured.given();

        Response patientDetailsResponse = request.header("Authorization", hiuAuthToken).get("/v1/hiu/consent-requests");
        JsonPath jsonPathEvaluator = patientDetailsResponse.jsonPath();
        consentRequestId = jsonPathEvaluator.getString("consentRequestId[0]");
        System.out.println(consentRequestId);
    }

    @Test(dependsOnMethods = "fetchConsentRequestIdAPI")
    public void createCMSessionAPI() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/consent-manager";
        RestAssured.useRelaxedHTTPSValidation();
        cmAuthToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test(dependsOnMethods = "createCMSessionAPI")
    public void verifyConsentPINAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", cmAuthToken);

        String verifyGrantPINRequestBody = new VerifyConsentPIN().getVerifyGrantPINRequestBody();
        request.body(verifyGrantPINRequestBody);
        System.out.println(verifyGrantPINRequestBody);
        Response response = request.post("/patients/verify-pin");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        PINToken = jsonPathEvaluator.getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyConsentPINAPI")
    public void grantConsentRequestAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", PINToken);

        String grantConsentRequestBody = new ConsentRequest().getGrantConsentRequestBody();
        request.body(grantConsentRequestBody);
        System.out.println(grantConsentRequestBody);
        Response response = request.post("/consent-requests/"+consentRequestId+"/approve");
        JsonPath jsonPathEvaluator = response.jsonPath();

    }



}
