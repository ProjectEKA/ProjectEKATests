package Tests.APITests.HIU;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HIUAPITest {

    String authToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBackendURL");
        RestAssured.useRelaxedHTTPSValidation();
        authToken = new LoginUser().getHIUAuthToken();
    }

    @Test
    public void discoverPatientAPI() {

        //identifies patient by cm-id
        RequestSpecification request = RestAssured.given();
        String patientID = PropertiesCache.getInstance().getProperty("HIUPatient");
        Response findPatientResponse = request.header("Authorization", authToken).pathParam("patientID", patientID)
                .get("/v1/patients/{patientID}");

        assertThat(findPatientResponse.getStatusCode()).isEqualTo(200);
        assertThat(findPatientResponse.jsonPath().getString("patient.id")).isEqualTo(patientID);
    }
}
