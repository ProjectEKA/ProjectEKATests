package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.CMRequest.ResetPassword;
import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.VerifyOTPRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResetPasswordAPITest {

    String authToken;
    String sessionId;
    String otpAuthToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void generateOTPAPI() {

        //generate otp for enter phone #
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(new ResetPassword().getGenerateOTPRequestBody());
        Response generateOTPResponse = request.post("/patients/generateotp");

        assertThat(generateOTPResponse.getStatusCode()).isEqualTo(201);
        sessionId = generateOTPResponse.jsonPath().getString("sessionId");
    }

    @Test(dependsOnMethods = "generateOTPAPI")
    public void verifyOTPAPI() {

        //verify the otp
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(VerifyOTPRequest.builder().sessionId(sessionId).value("666666").build());
        Response verifyOTPResponse = request.post("/patients/verifyotp");

        assertThat(verifyOTPResponse.getStatusCode()).isEqualTo(200);
        otpAuthToken = verifyOTPResponse.jsonPath().getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyOTPAPI")
    public void resetPasswordAPI() {

        //reset-password after otp confirmation
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", otpAuthToken);
        request.body(new ResetPassword().getResetPasswordRequestBody());

        Response resetPasswordResponse = request.put("/patients/profile/reset-password");
        assertThat(resetPasswordResponse.getStatusCode()).isEqualTo(200);
    }

}
