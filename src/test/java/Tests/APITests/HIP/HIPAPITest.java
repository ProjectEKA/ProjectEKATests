package Tests.APITests.HIP;

import Tests.APITests.APIUtils.CentralRegistry;
import Tests.APITests.APIUtils.HIPRequest.HIPPatientDiscovery;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HIPAPITest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/hip";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(enabled = false)
    public void discoverPatient() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization","Bearer "+ new CentralRegistry().getAuthTokenForHIP());

        String patientDiscoverRequestBody = new HIPPatientDiscovery().getPatientDiscoverRequestBody();

        request.body(patientDiscoverRequestBody);
        Response response = request.post("/patients/discover/carecontexts");

        JsonPath jsonPathEvaluator = response.jsonPath();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(jsonPathEvaluator.getString("patient.referenceNumber")).isEqualTo("RVH1004");
        assertThat(jsonPathEvaluator.getString("patient.display")).isEqualTo("John Doe");
        assertThat(jsonPathEvaluator.getString("patient.careContexts[0].referenceNumber")).isEqualTo("NCP10091");
        jsonPathEvaluator.getString("patient.matchedBy").equals("[Mobile, Gender]");
    }
}
