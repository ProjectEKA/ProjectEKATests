package Tests.APITests.Consent_Manager.Tests;

import Tests.APITests.LoginUser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Tests.APITests.Consent_Manager.TestBuildersCM.consentPINPayload;

public class CreatePINAPITest {

    String authToken;

    @BeforeClass
    public void setup() {
        authToken = new LoginUser().getCMAuthToken();
    }

    @Test
    public void createPINAPI() {

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
            request.body(consentPINPayload());
            Response generatePINResponse = request.post("/patients/pin");
            Assert.assertEquals(generatePINResponse.getStatusCode(), 204);
        }
    }

}
