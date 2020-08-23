package Tests.APITests.Consent_Manager.Tests;

import Tests.APITests.Consent_Manager.Utils.ConsentUtil;
import Tests.APITests.Helpers.Utils.LoginUtil;
import Tests.APITests.Helpers.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Tests.APITests.Consent_Manager.TestBuildersCM.grantConsentPayload;
import static Tests.APITests.Consent_Manager.TestBuildersCM.revokeConsentPayload;

public class ConsentRevokeAPITest {

    String consentArtefactId;
    String consentRequestId;
    String hiuAuthToken;

    @BeforeClass
    public void setup() {
        hiuAuthToken = new LoginUtil().getHIUAuthToken();
    }

    @Test(groups= {"All"})
    public void HIUConsentRequestAPI() {

        //create consent-request
        String patient = PropertiesCache.getInstance().getProperty("HIUPatient");
        Response createRequestResponse = new ConsentUtil().createConsent(patient);
        Assert.assertEquals(createRequestResponse.getStatusCode(), 202);

        //fetch consent-request id
        RequestSpecification request = RestAssured.given();
        Response fetchConsentsResponse = request.header("Authorization", hiuAuthToken)
                .get("/v1/hiu/consent-requests");
        consentRequestId = new ConsentUtil().fetchConsentRequestId(fetchConsentsResponse, patient);
        Assert.assertEquals(fetchConsentsResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "HIUConsentRequestAPI", groups= {"All"})
    public void grantConsentRequestAPI() {

        //grant consent-request
        String grantPINAuth = new ConsentUtil().verifyConsentPIN("grant");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", grantPINAuth);

        request.body(grantConsentPayload());
        Response grantConsentResponse = request.post("/consent-requests/" + consentRequestId + "/approve");
        consentArtefactId = grantConsentResponse.jsonPath().getString("consents[0].id");
        Assert.assertEquals(grantConsentResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "grantConsentRequestAPI", groups= {"All"})
    public void revokeConsentRequestAPI() {

        //revoke consent-request
        String revokePINAuth = new ConsentUtil().verifyConsentPIN("revoke");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", revokePINAuth);

        request.body(revokeConsentPayload(consentArtefactId));
        Response revokeConsentResponse = request.post("/consents/revoke");
        Assert.assertEquals(revokeConsentResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "revokeConsentRequestAPI")
    public void checkHIUConsentStatusAPI() {

        //fetch the consents list and fetch the status
        String authToken = new LoginUtil().getHIUAuthToken();
        RequestSpecification request = RestAssured.given();
        Response consentStatusResponse = request.header("Authorization", authToken)
                .get("/v1/hiu/consent-requests");
        String actualStatus = new ConsentUtil().fetchConsentStatus(consentStatusResponse, consentRequestId);

        Assert.assertEquals(consentStatusResponse.getStatusCode(), 200);
        Assert.assertEquals(actualStatus, "REVOKED");
    }

}
