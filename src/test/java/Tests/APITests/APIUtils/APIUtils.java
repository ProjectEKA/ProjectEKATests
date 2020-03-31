package Tests.APITests.APIUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

    public Response createConsent(String id) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "U2hyZXlhQG5jZw==");//1@ncg

        HIUConsentRequest hiuConsentRequest = new HIUConsentRequest.ConsentRequestBuilder(id).buildConsentReuqest();

        request.body(hiuConsentRequest.getJSONRequestBody().toString());
        return request.post("/consent-requests");
    }
}
