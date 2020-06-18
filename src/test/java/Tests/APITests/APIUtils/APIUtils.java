package Tests.APITests.APIUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

    public Response createConsent(String id) {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBackendURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", new LoginUser().getHIUAuthToken());

        HIUConsentRequest hiuConsentRequest = new HIUConsentRequest.ConsentRequestBuilder(id.toLowerCase()).buildConsentReuqest();
        request.body(hiuConsentRequest.getJSONRequestBody().toString());
        Response response = request.post("/v1/hiu/consent-requests");

        return response;
    }
}
