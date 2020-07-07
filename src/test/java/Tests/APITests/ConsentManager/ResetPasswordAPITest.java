package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.CMRequest.ResetPassword;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResetPasswordAPITest {

    String authToken;
    String sessionId;
    String OTPToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/consent-manager";
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void generateOTPAPI() {
        RequestSpecification request = RestAssured.given();

        //get call
        //Response response = request.header("Authorization", authToken).pathParam("patientID", patientID).get("/v1/patients/{patientID}");

        //post call
        request.header("Content-Type", "application/json");
        //request.header("Authorization", authToken);
        String generateOTPRequestBody = new ResetPassword().getGenerateOTPRequestBody();
        request.body(generateOTPRequestBody);
        Response response = request.post("/patients/generateotp");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 201);
        sessionId = jsonPathEvaluator.getString("sessionId");
        System.out.println(sessionId);
    }

    @Test(dependsOnMethods = "generateOTPAPI")
    public void verifyOTPAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        String verifyOTPRequestBody = new ResetPassword().getVerifyOTPRequestBody(sessionId);
        request.body(verifyOTPRequestBody);
        Response response = request.post("/patients/verifyotp");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        OTPToken = jsonPathEvaluator.getString("temporaryToken");
        System.out.println(jsonPathEvaluator.getString("temporaryToken"));
    }

    @Test(dependsOnMethods = "verifyOTPAPI")
    public void resetPasswordAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", OTPToken);
        String resetPasswordRequestBody = new ResetPassword().getResetPasswordRequestBody();
        request.body(resetPasswordRequestBody);
        Response response = request.put("/patients/profile/reset-password");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
