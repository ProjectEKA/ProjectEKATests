package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMPatientDiscovery;
import Tests.APITests.APIUtils.LoginUser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DiscoveryAPITest {
    String authToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/consent-manager";
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void listProvidersAPI() {
        RequestSpecification request = RestAssured.given();

        Response response = request.header("Authorization", authToken).queryParam("name", "Tata").get("/providers");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].name"), "Tata Memorial Hospital");
        Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].id"), "10000002");
    }

    @Test
    public void discoverPatientsAPI() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        request.header("Authorization", authToken);

        String patientDiscoverRequestBody = new CMPatientDiscovery().getPatientDiscoverRequestBody();
        request.body(patientDiscoverRequestBody);

        Response response = request.post("/v1/care-contexts/discover");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("patient.referenceNumber"), "RVH1004");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.display"), "John Doe");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.careContexts[0].referenceNumber"), "NCP10091");
    }
}
