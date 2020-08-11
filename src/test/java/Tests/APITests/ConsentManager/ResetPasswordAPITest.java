package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.CMRequest.ResetPassword;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResetPasswordAPITest {

    private String authToken;
    private String sessionId;
    private String otpAuthToken;

    @BeforeClass
    public void setup() {
        authToken = new LoginUser().getCMAuthToken();
    }

    @Test(priority = 0)
    public void generateOTPAPI() {
        System.out.println("generateOTPAPI");
        //generate otp for enter phone #
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(new ResetPassword().getGenerateOTPRequestBody());
        Response generateOTPResponse = request.post("/patients/generateotp");

        Assert.assertEquals(generateOTPResponse.getStatusCode(), 201);
        sessionId = generateOTPResponse.jsonPath().getString("sessionId");
    }

    @Test(dependsOnMethods = "generateOTPAPI", priority = 1)
    public void verifyOTPAPI() {
        System.out.println("verifyOTPAPI");
        //verify the otp
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(new ResetPassword().getVerifyOTPRequestBody(sessionId));
        Response verifyOTPResponse = request.post("/patients/verifyotp");

        Assert.assertEquals(verifyOTPResponse.getStatusCode(), 200);
        otpAuthToken = verifyOTPResponse.jsonPath().getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyOTPAPI", priority = 2)
    public void resetPasswordAPI() {

        //reset-password after otp confirmation
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", otpAuthToken);
        request.body(new ResetPassword().getResetPasswordRequestBody());
        System.out.println(new ResetPassword().getResetPasswordRequestBody() + "ppppp");
        Response resetPasswordResponse = request.put("/patients/profile/reset-password");

        System.out.println(resetPasswordResponse.getBody().asString() + "mmmmmm");

        Assert.assertEquals(resetPasswordResponse.getStatusCode(), 200);
    }

}
