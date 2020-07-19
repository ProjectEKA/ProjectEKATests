package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.CMRequest.UpdateProfile;
import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateProfileAPITest {

    String authToken;
    String pinAuthToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void updatePasswordAPI() {

        //update password from profile
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        request.body(new UpdateProfile().getUpdatePasswordRequestBody());

        Response updatePasswordResponse = request.put("/patients/profile/update-password");
        assertThat(updatePasswordResponse.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void verifyPINAPI() {

        //verify-pin for update-pin
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        request.body(new UpdateProfile().getVerifyPINRequestBody());

        Response verifyPINResponse = request.post("/patients/verify-pin");
        assertThat(verifyPINResponse.getStatusCode()).isEqualTo(200);
        pinAuthToken = verifyPINResponse.jsonPath().getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyPINAPI")
    public void updatePINAPI() {

        //update PIN from profile
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", pinAuthToken);
        request.body(new UpdateProfile().getUpdatePINRequestBody());

        Response updatePINResponse = request.post("/patients/change-pin");
        assertThat(updatePINResponse.getStatusCode()).isEqualTo(200);
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
