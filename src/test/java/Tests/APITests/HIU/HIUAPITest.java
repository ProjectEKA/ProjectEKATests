package Tests.APITests.HIU;

import Tests.APITests.APIUtils.HIUConsentRequest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HIUAPITest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/hiu-api";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void discoverPatientAPI() {
        RequestSpecification request = RestAssured.given();
        String patientID = "shreya@ncg";

        Response response = request.pathParam("patientID", patientID).get("/patients/{patientID}");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("patient.id"), patientID);
    }

    @Test
    public void createConsentRequest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "U2hyZXlhQG5jZw==");//1@ncg

        HIUConsentRequest hiuConsentRequest = new HIUConsentRequest.ConsentRequestBuilder("Shreya@ncg").buildConsentReuqest();

        request.body(hiuConsentRequest.getJSONRequestBody().toString());
        Response response = request.post("/consent-requests");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Consent request created with ID " + jsonPathEvaluator.getString("id"));
        Assert.assertTrue(jsonPathEvaluator.getString("id").length() > 1);
    }
}
