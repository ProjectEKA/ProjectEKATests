package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.CreateConsentPIN;
import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.PINRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatePINAPITest {

    String authToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void createPINAPI() {

        String pin = "1234";
        PINRequest pinRequest = PINRequest.builder().pin(pin).build();
        //checks if the patient has consent-pin created
        RequestSpecification request = RestAssured.given();
        Response patientDetailsResponse = request.header("Authorization", authToken).get("/patients/me");
        String hasPIN = patientDetailsResponse.jsonPath().getString("hasTransactionPin");

        if(hasPIN.equalsIgnoreCase("true")) {
            System.out.println("Consent PIN already created for this user");
        }
        else if(hasPIN.equalsIgnoreCase("false")) {

            //if consent-pin not created for patient
            request.header("Content-Type", "application/json");
            request.header("Authorization", authToken);
            request.body(pinRequest);
            Response generatePINResponse = request.post("/patients/pin");
            assertThat(generatePINResponse.getStatusCode()).isEqualTo(204);
        }
    }

}
