package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.*;
import Tests.APITests.APIUtils.CMRequest.ConsentRequest;
import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.CMRequest.VerifyConsentPIN;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ConsentRevokeAPITest {

    String hiuAuthToken;
    String cmAuthToken;
    String PINToken;
    String consentArtefactId;
    String consentRequestId;
    String status;

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
    public void verifyGrantConsentPINAPI() {

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

    @Test(dependsOnMethods = "verifyGrantConsentPINAPI")
    public void grantConsentRequestAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", PINToken);

        String grantConsentRequestBody = new ConsentRequest().getGrantConsentRequestBody();
        request.body(grantConsentRequestBody);
        System.out.println(grantConsentRequestBody);
        Response response = request.post("/consent-requests/" + consentRequestId + "/approve");
        JsonPath jsonPathEvaluator = response.jsonPath();
    }

    @Test(dependsOnMethods = "grantConsentRequestAPI")
    public void fetchConsentArtefactIdAPI() {
        RequestSpecification request = RestAssured.given();

        Response patientDetailsResponse = request.header("Authorization", cmAuthToken)
                .get("/consent-artefacts?limit=10&offset=0&status=GRANTED");
        JsonPath jsonPathEvaluator = patientDetailsResponse.jsonPath();
        consentArtefactId = jsonPathEvaluator.getString("consentArtefacts[0].consentDetail.consentId");
        System.out.println(consentArtefactId);
    }

    @Test(dependsOnMethods = "fetchConsentArtefactIdAPI")
    public void verifyRevokeConsentPINAPI() {

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

    @Test(dependsOnMethods = "verifyRevokeConsentPINAPI")
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

    @Test(dependsOnMethods = "revokeConsentRequestAPI")
    public void checkHIUConsentStatusAPI() {

        //create HIU session
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBackendURL");
        RestAssured.useRelaxedHTTPSValidation();
        hiuAuthToken = new LoginUser().getHIUAuthToken();

        RequestSpecification request = RestAssured.given();
        Response patientDetailsResponse = request.header("Authorization", hiuAuthToken)
                .get("/v1/hiu/consent-requests");
        System.out.println(patientDetailsResponse.asString());
        JsonPath jsonPathEvaluator = patientDetailsResponse.jsonPath();
        System.out.println(consentRequestId);
        //fetching consent-status on the basis of consent-request-id
        List<String> consentRequestIds = jsonPathEvaluator.getList("consentRequestId");
        for(int i=0; i<(consentRequestIds.size()-1);i++) {
            status="";
            if(consentRequestIds.get(i).equalsIgnoreCase(consentRequestId)) {
                status = jsonPathEvaluator.getString("status[" + i + "]");
                break;
            }
        }
        Assert.assertEquals(patientDetailsResponse.getStatusCode(), 200);
        Assert.assertEquals(status, "REVOKED");
    }

}
