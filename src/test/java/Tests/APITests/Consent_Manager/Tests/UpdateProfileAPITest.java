package Tests.APITests.Consent_Manager.Tests;

import Tests.APITests.Helpers.LoginUser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Tests.APITests.Consent_Manager.TestBuildersCM.*;

public class UpdateProfileAPITest {

    String authToken;
    String pinAuthToken;

    @BeforeClass
    public void setup() {
        authToken = new LoginUser().getCMAuthToken();
    }

    @Test
    public void updatePasswordAPI() {

        //update password from profile
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        request.body(updatePasswordPayload());

        Response updatePasswordResponse = request.put("/patients/profile/update-password");
        Assert.assertEquals(updatePasswordResponse.getStatusCode(), 200);
    }

    @Test
    public void verifyPINAPI() {

        //verify-pin for update-pin
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        request.body(verifyUpdatePINPayload());

        Response verifyPINResponse = request.post("/patients/verify-pin");
        Assert.assertEquals(verifyPINResponse.getStatusCode(), 200);
        pinAuthToken = verifyPINResponse.jsonPath().getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyPINAPI")
    public void updatePINAPI() {

        //update PIN from profile
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", pinAuthToken);
        request.body(consentPINPayload());

        Response updatePINResponse = request.post("/patients/change-pin");
        Assert.assertEquals(updatePINResponse.getStatusCode(), 200);
    }
    

//    @Test(dependsOnMethods = "updatePINAPI")
//    public void logoutAPI() {
//        RequestSpecification request = RestAssured.given();
//
//        request.header("Content-Type", "application/json");
//        request.header("Authorization", authToken);
//        String logoutRequestBody = new LoginUser().getCMLogoutRequestBody(new LoginUser().getCMRefreshToken());
//        request.body(logoutRequestBody);
//        Response response = request.post("/logout");
//        Assert.assertEquals(response.getStatusCode(), 200);
//    }

}
