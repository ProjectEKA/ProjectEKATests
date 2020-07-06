package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.LoginUser;
import Tests.APITests.APIUtils.UpdateProfile;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateProfileAPITest {

    String authToken;
    String PINToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/consent-manager";
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void updatePasswordAPI() {
        RequestSpecification request = RestAssured.given();

        //get call
        //Response response = request.header("Authorization", authToken).pathParam("patientID", patientID).get("/v1/patients/{patientID}");

        //post call
        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);
        String updatePasswordRequestBody = new UpdateProfile().getUpdatePasswordRequestBody();
        request.body(updatePasswordRequestBody);
        Response response = request.put("/patients/profile/update-password");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void verifyPINAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", authToken);

        String verifyPINRequestBody = new UpdateProfile().getVerifyPINRequestBody();
        request.body(verifyPINRequestBody);
        System.out.println(verifyPINRequestBody);
        Response response = request.post("/patients/verify-pin");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        PINToken = jsonPathEvaluator.getString("temporaryToken");
    }

    @Test(dependsOnMethods = "verifyPINAPI")
    public void updatePINAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", PINToken);
        String updatePINRequestBody = new UpdateProfile().getUpdatePINRequestBody();
        request.body(updatePINRequestBody);
        Response response = request.post("/patients/change-pin");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
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
