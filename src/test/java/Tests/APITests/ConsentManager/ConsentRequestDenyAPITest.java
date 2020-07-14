package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.*;
import Tests.APITests.APIUtils.CMRequest.LoginUser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ConsentRequestDenyAPITest {

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
        System.out.println(">>>>>>>>>"+consentRequestId);
    }

    @Test(dependsOnMethods = "HIUConsentRequestAPI")
    public void denyConsentRequestAPI() {

        //deny consent request at consent-manager
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + new LoginUser().getCMAuthToken());

        Response denyConsentResponse = request.post("/consent-requests/" + consentRequestId + "/deny");
        Assert.assertEquals(denyConsentResponse.getStatusCode(), 204);
    }

    @Test(dependsOnMethods = "denyConsentRequestAPI")
    public void checkHIUConsentStatusAPI() {

        //fetch the consents list and fetch the status
        RequestSpecification request = RestAssured.given();
        Response consentStatusResponse = request.header("Authorization", new LoginUser().getHIUAuthToken())
                .get("/v1/hiu/consent-requests");
        String actualStatus = new APIUtils().fetchConsentStatus(consentStatusResponse, consentRequestId);

        Assert.assertEquals(consentStatusResponse.getStatusCode(), 200);
        Assert.assertEquals(actualStatus, "DENIED");
    }

}
