package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.LoginUser;
import Tests.APITests.APIUtils.RecoverCMID;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ForgetCMIDAPITest {

    String authToken;
    String sessionId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://ncg-dev.projecteka.in/consent-manager";
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void initRecoveryAPI() {
        RequestSpecification request = RestAssured.given();

        //get call
        //Response response = request.header("Authorization", authToken).pathParam("patientID", patientID).get("/v1/patients/{patientID}");

        //post call
        request.header("Content-Type", "application/json");
        //request.header("Authorization", authToken);
        String recoverInitCMIDRequestBody = new RecoverCMID().getRecoverInitCMIDRequestBody();
        request.body(recoverInitCMIDRequestBody);
        Response response = request.post("/patients/profile/recovery-init");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        sessionId = jsonPathEvaluator.getString("sessionId");
        System.out.println(sessionId);
    }

    @Test(dependsOnMethods = "initRecoveryAPI")
    public void confirmCMIDAPI() {

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        String recoverConfirmCMIDRequestBody = new RecoverCMID().getRecoverConfirmCMIDRequestBody(sessionId);
        request.body(recoverConfirmCMIDRequestBody);
        Response response = request.post("/patients/profile/recovery-confirm");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("cmId"), "apidemotest101@ncg");
        System.out.println(jsonPathEvaluator.getString("cmId"));
    }

}
