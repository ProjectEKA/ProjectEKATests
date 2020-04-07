package Tests.APITests.APIUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class APIUtils {

    public Response createConsent(String id) {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/hiu-api";
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "U2hyZXlhQG5jZw==");//1@ncg

        HIUConsentRequest hiuConsentRequest = new HIUConsentRequest.ConsentRequestBuilder(id.toLowerCase()).buildConsentReuqest();

        request.body(hiuConsentRequest.getJSONRequestBody().toString());
        Response response = request.post("/consent-requests");


        Assert.assertEquals(response.getStatusCode(), 200);
//        System.out.println("Consent request created with ID " + jsonPathEvaluator.getString("id"));
//        Assert.assertTrue(jsonPathEvaluator.getString("id").length() > 1);

        return response;
    }
}
