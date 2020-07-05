package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConsentRequestRevokeAPITest {

    String hiuAuthToken;
    String cmAuthToken;
    String PINToken;
    String consentArtefactId;

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
    public void createCMSessionAPI() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/consent-manager";
        RestAssured.useRelaxedHTTPSValidation();
        cmAuthToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test(dependsOnMethods = "createCMSessionAPI")
    public void fetchConsentArtefactIdAPI() {
        RequestSpecification request = RestAssured.given();

        Response patientDetailsResponse = request.header("Authorization", cmAuthToken)
                .get("/consent-artefacts?limit=10&offset=0&status=GRANTED");
        JsonPath jsonPathEvaluator = patientDetailsResponse.jsonPath();
        consentArtefactId = jsonPathEvaluator.getString("consentArtefacts[0].consentDetail.consentId");
        System.out.println(consentArtefactId);
    }

    @Test(dependsOnMethods = "fetchConsentArtefactIdAPI")
    public void verifyConsentPINAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", cmAuthToken);

        String verifyRevokePINRequestBody = new VerifyConsentPIN().getVerifyRevokePINRequestBody();
        request.body(verifyRevokePINRequestBody);
        System.out.println(verifyRevokePINRequestBody);
        Response response = request.post("/patients/verify-pin");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        PINToken = jsonPathEvaluator.getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyConsentPINAPI")
    public void revokeConsentRequestAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", PINToken);

        String revokeConsentRequestBody = new ConsentRequest().getRevokeConsentRequestBody(consentArtefactId);
        request.body(revokeConsentRequestBody);
        System.out.println(revokeConsentRequestBody);
        Response response = request.post("/consents/revoke");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
