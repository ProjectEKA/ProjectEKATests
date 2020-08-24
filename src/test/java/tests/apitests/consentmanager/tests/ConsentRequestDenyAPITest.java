package tests.apitests.consentmanager.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.apitests.consentmanager.utils.ConsentRequest;
import tests.apitests.helpers.PropertiesCache;
import tests.apitests.helpers.utils.Login;

public class ConsentRequestDenyAPITest {

    String consentRequestId;

    @Test
    public void HIUConsentRequestAPI() {
        //create consent-request
        String patient = PropertiesCache.getInstance().getProperty("HIUPatient");
        Response createRequestResponse = new ConsentRequest().createConsent(patient);
        Assert.assertEquals(createRequestResponse.getStatusCode(), 202);

        //fetch consent-request id
        RequestSpecification request = RestAssured.given();
        Response fetchConsentsResponse = request.header("Authorization", new Login().getHIUAuthToken())
                .get("/v1/hiu/consent-requests");
        consentRequestId = new ConsentRequest().fetchConsentRequestId(fetchConsentsResponse, patient);
        Assert.assertEquals(fetchConsentsResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "HIUConsentRequestAPI")
    public void denyConsentRequestAPI() {

        //deny consent request at consent-manager
        String authToken = new Login().getCMAuthToken();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);

        Response denyConsentResponse = request.post("/consent-requests/" + consentRequestId + "/deny");

        Assert.assertEquals(denyConsentResponse.getStatusCode(), 204);
    }

    @Test(dependsOnMethods = "denyConsentRequestAPI")
    public void checkHIUConsentStatusAPI() {

        //fetch the consents list and fetch the status
        String authToken = new Login().getHIUAuthToken();
        RequestSpecification request = RestAssured.given();

        Response consentStatusResponse = request.header("Authorization", authToken)
                .get("/v1/hiu/consent-requests");
        String actualStatus = new ConsentRequest().fetchConsentStatus(consentStatusResponse, consentRequestId);

        Assert.assertEquals(consentStatusResponse.getStatusCode(), 200);
        Assert.assertEquals(actualStatus, "DENIED");
    }

}
