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

public class ConsentRequestGrantAPITest {

    String consentRequestId;

    @Test
    public void HIUConsentRequestAPI() {

        //create consent-request
        Response createRequestResponse = new APIUtils()
                .createConsent(PropertiesCache.getInstance().getProperty("HIUPatient"));
        Assert.assertEquals(createRequestResponse.getStatusCode(), 202);

        //fetch consent-request id
        RequestSpecification request = RestAssured.given();
        Response fetchConsentsResponse = request.header("Authorization", new LoginUser().getHIUAuthToken())
                .get("/v1/hiu/consent-requests");
        consentRequestId = fetchConsentsResponse.jsonPath().getString("consentRequestId[0]");
        Assert.assertEquals(fetchConsentsResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "HIUConsentRequestAPI")
    public void grantConsentRequestAPI() {

        //grant consent-request
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", new APIUtils().verifyConsentPIN("grant"));

        request.body(new ConsentRequest().getGrantConsentRequestBody());
        Response grantConsentResponse = request.post("/consent-requests/" + consentRequestId + "/approve");
        Assert.assertEquals(grantConsentResponse.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "grantConsentRequestAPI")
    public void checkHIUConsentStatusAPI() {

        //fetch the consents list and fetch the status
        RequestSpecification request = RestAssured.given();
        Response consentStatusResponse = request.header("Authorization", new LoginUser().getHIUAuthToken())
                .get("/v1/hiu/consent-requests");
        String actualStatus = new APIUtils().fetchConsentStatus(consentStatusResponse, consentRequestId);

        Assert.assertEquals(consentStatusResponse.getStatusCode(), 200);
        Assert.assertEquals(actualStatus, "GRANTED");
    }

}
