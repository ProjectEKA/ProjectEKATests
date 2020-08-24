package tests.apitests.consentmanager.tests;

import tests.apitests.helpers.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static tests.apitests.consentmanager.TestBuilders.*;

public class ResetPasswordAPITest {

    private static String sessionId;
    private static String otpAuthToken;
    RequestSpecification request;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        request = RestAssured.given();
    }

    @Test
    public void generateOTPAPI() {
        //generate otp for enter phone #
        request.header("Content-Type", "application/json");
        request.body(resetPasswordInitPayload());
        Response generateOTPResponse = request.post("/patients/generateotp");
        Assert.assertEquals(generateOTPResponse.getStatusCode(), 201);
        sessionId = generateOTPResponse.jsonPath().getString("sessionId");
    }

    @Test(dependsOnMethods = "generateOTPAPI")
    public void verifyOTPAPI() {
        //verify the otp
        request.header("Content-Type", "application/json");
        request.body(verifyOTPPayload(sessionId));
        Response verifyOTPResponse = request.post("/patients/verifyotp");
        Assert.assertEquals(verifyOTPResponse.getStatusCode(), 200);
        otpAuthToken = verifyOTPResponse.jsonPath().getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyOTPAPI")
    public void resetPasswordAPI() {
        //reset-password after otp confirmation
        request.header("Content-Type", "application/json");
        request.header("Authorization", otpAuthToken);
        request.body(resetPasswordPayload());
        Response resetPasswordResponse = request.put("/patients/profile/reset-password");

        Assert.assertEquals(resetPasswordResponse.getStatusCode(), 200);
    }

}
