package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CreateConsentPIN;
import Tests.APITests.APIUtils.LoginUser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreatePINAPITest {

    String authToken;
    String refreshToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/consent-manager";
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void updatePasswordAPI() {
        RequestSpecification request = RestAssured.given();

        Response patientDetailsResponse = request.header("Authorization", authToken).get("/patients/me");
        JsonPath jsonPathEvaluator = patientDetailsResponse.jsonPath();
        String hasPIN = jsonPathEvaluator.getString("hasTransactionPin");
        if(hasPIN.equalsIgnoreCase("true")) {
            System.out.println("Consent PIN already created for this user");
        }
        else if(hasPIN.equalsIgnoreCase("true")) {

            //post call
            request.header("Content-Type", "application/json");
            request.header("Authorization", authToken);
            String createPINRequestBody = new CreateConsentPIN().getCreatePINRequestBody();
            request.body(createPINRequestBody);
            Response response = request.post("/patients/pin");
            Assert.assertEquals(response.getStatusCode(), 201);
        }
    }

    @Test(dependsOnMethods = "updatePasswordAPI")
    public void logoutAPI() {
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        String logoutRequestBody = new LoginUser().getCMLogoutRequestBody(new LoginUser().getCMRefreshToken());
        request.body(logoutRequestBody);
        Response response = request.post("/logout");
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
