package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.CMPatientDiscovery;
import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.PropertiesCache;
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
        authToken = new LoginUser().getCMAuthToken();
    }

    @Test
    public void listProvidersAPI() {

        //fetches the list of providers
        RequestSpecification request = RestAssured.given();
        Response providersListResponse = request.header("Authorization", authToken)
                .queryParam("name", "Tata").get("/providers");

        Assert.assertEquals(providersListResponse.getStatusCode(), 200);
        JsonPath jsonPathEvaluator = providersListResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].name"), "Tata Memorial Hospital");
        Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].id"), "10000002");
    }

    @Test
    public void discoverPatientsAPI() {

        //discovers the care-contexts for the patient
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        request.body(new CMPatientDiscovery().getPatientDiscoverRequestBody());

        Response discoverCareContextResponse = request.post("/v1/care-contexts/discover");
        Assert.assertEquals(discoverCareContextResponse.getStatusCode(), 200);
        JsonPath jsonPathEvaluator = discoverCareContextResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.getString("patient.referenceNumber"), "RVH/WBH-10002");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.display"), "Navjot Singh");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.careContexts[0].referenceNumber"), "WBCP1007");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.careContexts[1].referenceNumber"), "WB-MHD-01.17.0024");
    }
}
