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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class ConsentRevokeAPITest {

    String consentArtefactId;
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
        Assert.assertEquals(fetchConsentsResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "HIUConsentRequestAPI")
    public void grantConsentRequestAPI() {

        //grant consent-request
        String grantPINAuth = new APIUtils().verifyConsentPIN("grant");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", grantPINAuth);

        request.body(new ConsentRequest().getGrantConsentRequestBody());
        Response grantConsentResponse = request.post("/consent-requests/" + consentRequestId + "/approve");
        consentArtefactId = grantConsentResponse.jsonPath().getString("consents[0].id");
        Assert.assertEquals(grantConsentResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "grantConsentRequestAPI")
    public void revokeConsentRequestAPI() {

        //revoke consent-request
        String revokePINAuth = new APIUtils().verifyConsentPIN("revoke");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", revokePINAuth);

        request.body(new ConsentRequest().getRevokeConsentRequestBody(consentArtefactId));
        Response revokeConsentResponse = request.post("/consents/revoke");
        Assert.assertEquals(revokeConsentResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "revokeConsentRequestAPI")
    public void checkHIUConsentStatusAPI() {

        //fetch the consents list and fetch the status
        String authToken = new LoginUser().getHIUAuthToken();
        RequestSpecification request = RestAssured.given();
        Response consentStatusResponse = request.header("Authorization", authToken)
                .get("/v1/hiu/consent-requests");
        String actualStatus = new APIUtils().fetchConsentStatus(consentStatusResponse, consentRequestId);

        Assert.assertEquals(consentStatusResponse.getStatusCode(), 200);
        Assert.assertEquals(actualStatus, "REVOKED");
    }

}
