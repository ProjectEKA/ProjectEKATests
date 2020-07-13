package Tests.APITests.APIUtils;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.CMRequest.VerifyConsentPIN;
import Tests.APITests.APIUtils.HIURequest.HIUConsentRequest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class APIUtils {

    public Response createConsent(String id) {

        //create consent-request at HIU
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBaseURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", new LoginUser().getHIUAuthToken());

        HIUConsentRequest hiuConsentRequest = new HIUConsentRequest.ConsentRequestBuilder(id.toLowerCase())
                .buildConsentReuqest();
        request.body(hiuConsentRequest.getJSONRequestBody().toString());
        Response response = request.post("/v1/hiu/consent-requests");

        return response;
    }

    @Test
    public String verifyConsentPIN(String consent) {

        //verify consent-pin and generate pin-authorization token
        String authToken = "Bearer " + new LoginUser().getCMAuthToken();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);

        if(consent.equalsIgnoreCase("grant")) {
            request.body(new VerifyConsentPIN().getVerifyGrantPINRequestBody());
        }
        else if(consent.equalsIgnoreCase("revoke")) {
            request.body(new VerifyConsentPIN().getVerifyRevokePINRequestBody());
        }
        Response response = request.post("/patients/verify-pin");
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.getString("temporaryToken");
    }


    public String fetchConsentStatus(Response response, String consentRequestId) {

        //identifies the status of consent in the GET consent-requests at HIU
        String status="";
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> consentRequestIds = jsonPathEvaluator.getList("consentRequestId");
        for(int i=0; i<(consentRequestIds.size()-1);i++) {
            if(consentRequestIds.get(i).equalsIgnoreCase(consentRequestId)) {
                status = jsonPathEvaluator.getString("status[" + i + "]");
                System.out.println(status + i);
                break;
            }
        }
        return status;
    }


}
