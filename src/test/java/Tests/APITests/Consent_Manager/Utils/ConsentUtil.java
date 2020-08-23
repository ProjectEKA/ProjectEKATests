package Tests.APITests.Consent_Manager.Utils;

import Tests.APITests.Helpers.PropertiesCache;
import Tests.APITests.Helpers.Utils.LoginUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import static Tests.APITests.Consent_Manager.TestBuildersCM.grantPINPayload;
import static Tests.APITests.Consent_Manager.TestBuildersCM.revokePINPayload;
import static Tests.APITests.Health_Information_User.TestBuildersHIU.createConsentRequestPayload;

public class ConsentUtil {

    public Response createConsent(String id) {

        //create consent-request at HIU
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBaseURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", new LoginUtil().getHIUAuthToken());

        request.body(createConsentRequestPayload(id));
        Response response = request.post("/v1/hiu/consent-requests");
        return response;
    }

    public String verifyConsentPIN(String consent) {

        //verify consent-pin and generate pin-authorization token
        String authToken = new LoginUtil().getCMAuthToken();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);

        if(consent.equalsIgnoreCase("grant")) {
            request.body(grantPINPayload());
        }
        else if(consent.equalsIgnoreCase("revoke")) {
            request.body(revokePINPayload());
        }
        Response response = request.post("/patients/verify-pin");
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.getString("temporaryToken");
    }

    public String fetchConsentRequestId(Response response, String patient) {

        //identifies the consent-request-id of patient's consent-request in the GET consent-requests at HIU
        String consentRequestId = "";
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> patientList = jsonPathEvaluator.getList("patient.id");
        for(int i=0; i<(patientList.size()-1);i++) {
            if(patientList.get(i).equalsIgnoreCase(patient)) {
                if((jsonPathEvaluator.getString("status[" + i + "]")).equalsIgnoreCase("REQUESTED")) {
                    consentRequestId = jsonPathEvaluator.getString("consentRequestId[" + i + "]");
                    break;
                }
            }
        }
        return consentRequestId;
    }

    public String fetchConsentStatus(Response response, String consentRequestId) {

        //identifies the status of consent in the GET consent-requests at HIU
        String status="";
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> consentRequestIds = jsonPathEvaluator.getList("consentRequestId");
        for(int i=0; i<(consentRequestIds.size()-1);i++) {
            if(consentRequestIds.get(i).equalsIgnoreCase(consentRequestId)) {
                status = jsonPathEvaluator.getString("status[" + i + "]");
                break;
            }
        }
        return status;
    }




}
