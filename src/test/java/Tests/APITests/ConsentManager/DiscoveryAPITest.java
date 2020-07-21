package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.PropertiesCache;
import com.google.common.collect.ImmutableList;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.DiscoverPatientRequest;
import model.request.HIP;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscoveryAPITest {
    String authToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void listProvidersAPI() {

        //fetches the list of providers
        RequestSpecification request = RestAssured.given();
        Response providersListresponse = request.header("Authorization", authToken)
                .queryParam("name", "Tata").get("/providers");

        assertThat(providersListresponse.getStatusCode()).isEqualTo(200);
        JsonPath jsonPathEvaluator = providersListresponse.jsonPath();
        assertThat(jsonPathEvaluator.getString("identifier[0].name")).isEqualTo("Tata Memorial Hospital");
        assertThat(jsonPathEvaluator.getString("identifier[0].id")).isEqualTo("10000002");
    }

    @Test
    public void discoverPatientsAPI() {

        //discovers the care-contexts for the patient
        HIP hip = HIP.builder().id("10000005").build();
        DiscoverPatientRequest discoverPatientRequest = DiscoverPatientRequest.builder().requestId(String.valueOf(UUID.randomUUID())).unverifiedIdentifiers(ImmutableList.of()).hip(hip).build();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        request.body(discoverPatientRequest);

        Response discoverCareContextResponse = request.post("/v1/care-contexts/discover");
        assertThat(discoverCareContextResponse.getStatusCode()).isEqualTo(200);
        JsonPath jsonPathEvaluator = discoverCareContextResponse.jsonPath();

        assertThat(jsonPathEvaluator.getString("patient.referenceNumber")).isEqualTo("RVH1004");
        assertThat(jsonPathEvaluator.getString("patient.display")).isEqualTo("John Doe");
        assertThat(jsonPathEvaluator.getString("patient.careContexts[0].referenceNumber")).isEqualTo("NCP10091");
    }
}
