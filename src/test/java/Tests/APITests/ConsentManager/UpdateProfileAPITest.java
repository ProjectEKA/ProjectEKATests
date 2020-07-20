package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.CMRequest.UpdateProfile;
import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.PINRequest;
import model.request.UpdatePasswordRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

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
        String password = "Test135@";
        UpdatePasswordRequest updatePasswordRequest = UpdatePasswordRequest.builder().oldPassword(password).newPassword(password).build();

        //update password from profile
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        request.body(updatePasswordRequest);

        Response updatePasswordResponse = request.put("/patients/profile/update-password");
        assertThat(updatePasswordResponse.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void verifyPINAPI() {

        String pin = "1234";
        String scope = "profile.changepin";
        PINRequest pinRequest = PINRequest.builder().pin(pin).requestId(String.valueOf(UUID.randomUUID())).scope(scope).build();
        //verify-pin for update-pin
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        request.body(pinRequest);

        Response verifyPINResponse = request.post("/patients/verify-pin");
        assertThat(verifyPINResponse.getStatusCode()).isEqualTo(200);
        pinAuthToken = verifyPINResponse.jsonPath().getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyPINAPI")
    public void updatePINAPI() {

        String pin = "1234";
        PINRequest pinRequest = PINRequest.builder().pin(pin).build();
        //update PIN from profile
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", pinAuthToken);
        request.body(pinRequest);

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
