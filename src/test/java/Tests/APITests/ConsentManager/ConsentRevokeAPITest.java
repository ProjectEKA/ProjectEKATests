package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.APIUtils;
import Tests.APITests.APIUtils.CMRequest.ConsentRequest;
import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.PropertiesCache;
import com.google.common.collect.ImmutableList;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.ConsentRequestForConsentId;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsentRevokeAPITest {

    String consentArtefactId;
    String consentRequestId;

    @Test
    public void HIUConsentRequestAPI() {

        //create consent-request
        String patient = PropertiesCache.getInstance().getProperty("HIUPatient");
        Response createRequestResponse = new APIUtils().createConsent(patient);
        assertThat(createRequestResponse.getStatusCode()).isEqualTo(202);

        //fetch consent-request id
        RequestSpecification request = RestAssured.given();
        Response fetchConsentsResponse = request.header("Authorization", new LoginUser().getHIUAuthToken())
                .get("/v1/hiu/consent-requests");
        consentRequestId = new APIUtils().fetchConsentRequestId(fetchConsentsResponse, patient);
        assertThat(fetchConsentsResponse.getStatusCode()).isEqualTo(200);
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
        assertThat(grantConsentResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dependsOnMethods = "grantConsentRequestAPI")
    public void revokeConsentRequestAPI() {

        ConsentRequestForConsentId consentRequest = ConsentRequestForConsentId.builder().consents(ImmutableList.of(consentArtefactId)).build();
        //revoke consent-request
        String revokePINAuth = new APIUtils().verifyConsentPIN("revoke");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", revokePINAuth);

        request.body(consentRequest);
        Response revokeConsentResponse = request.post("/consents/revoke");
        assertThat(revokeConsentResponse.getStatusCode()).isEqualTo(200);
    }

    @Test(dependsOnMethods = "revokeConsentRequestAPI")
    public void checkHIUConsentStatusAPI() {

        //fetch the consents list and fetch the status
        String authToken = new LoginUser().getHIUAuthToken();
        RequestSpecification request = RestAssured.given();
        Response consentStatusResponse = request.header("Authorization", authToken)
                .get("/v1/hiu/consent-requests");
        String actualStatus = new APIUtils().fetchConsentStatus(consentStatusResponse, consentRequestId);

        assertThat(consentStatusResponse.getStatusCode()).isEqualTo(200);
        assertThat(actualStatus).isEqualTo("REVOKED");
    }

}
