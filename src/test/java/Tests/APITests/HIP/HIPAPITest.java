package Tests.APITests.HIP;

import Tests.APITests.APIUtils.HIPPatientDiscovery;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HIPAPITest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://hip-dev.projecteka.in";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void discoverPatient() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String patientDiscoverRequestBody = new HIPPatientDiscovery().getPatientDiscoverRequestBody();

        request.body(patientDiscoverRequestBody);
        Response response = request.post("/patients/discover");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("patient.referenceNumber"), "123");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.display"), "TestFirstName TestLastName");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.careContexts[0].referenceNumber"), "121");
        jsonPathEvaluator.getString("patient.matchedBy").equals("[Mobile, Gender]");
    }
}
