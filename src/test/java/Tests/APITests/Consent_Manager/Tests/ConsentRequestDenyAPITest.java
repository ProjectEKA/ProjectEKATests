package Tests.APITests.Consent_Manager.Tests;

import Tests.APITests.Consent_Manager.Utils.ConsentUtil;
import Tests.APITests.Helpers.Utils.LoginUtil;
import Tests.APITests.Helpers.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConsentRequestDenyAPITest {

    String consentRequestId;

    @Test(groups= {"All"})
    public void HIUConsentRequestAPI() {

        //create consent-request
        String patient = PropertiesCache.getInstance().getProperty("HIUPatient");
        Response createRequestResponse = new ConsentUtil().createConsent(patient);
        Assert.assertEquals(createRequestResponse.getStatusCode(), 202);

        //fetch consent-request id
        RequestSpecification request = RestAssured.given();
        Response fetchConsentsResponse = request.header("Authorization", new LoginUtil().getHIUAuthToken())
                .get("/v1/hiu/consent-requests");
        consentRequestId = new ConsentUtil().fetchConsentRequestId(fetchConsentsResponse, patient);
        Assert.assertEquals(fetchConsentsResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "HIUConsentRequestAPI",groups= {"All"})
    public void denyConsentRequestAPI() {

        //deny consent request at consent-manager
        String authToken = new LoginUtil().getCMAuthToken();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);

        Response denyConsentResponse = request.post("/consent-requests/" + consentRequestId + "/deny");
        Assert.assertEquals(denyConsentResponse.getStatusCode(), 204);
    }

    @Test(dependsOnMethods = "denyConsentRequestAPI",groups= {"All"})
    public void checkHIUConsentStatusAPI() {

        //fetch the consents list and fetch the status
        String authToken = new LoginUtil().getHIUAuthToken();
        RequestSpecification request = RestAssured.given();
        Response consentStatusResponse = request.header("Authorization", authToken)
                .get("/v1/hiu/consent-requests");
        String actualStatus = new ConsentUtil().fetchConsentStatus(consentStatusResponse, consentRequestId);

        Assert.assertEquals(consentStatusResponse.getStatusCode(), 200);
        Assert.assertEquals(actualStatus, "DENIED");
    }

}
