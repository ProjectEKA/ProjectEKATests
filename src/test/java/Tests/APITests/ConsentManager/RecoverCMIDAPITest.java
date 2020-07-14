package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.CMRequest.RecoverCMID;
import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RecoverCMIDAPITest {

    String authToken;
    String sessionId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        authToken = "Bearer " + new LoginUser().getCMAuthToken();
    }

    @Test
    public void initRecoveryAPI() {

        //initiate the cm-id recovery process
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(new RecoverCMID().getRecoverInitCMIDRequestBody());
        Response initRecoveryResponse = request.post("/patients/profile/recovery-init");

        Assert.assertEquals(initRecoveryResponse.getStatusCode(), 200);
        sessionId = initRecoveryResponse.jsonPath().getString("sessionId");
    }

    @Test(dependsOnMethods = "initRecoveryAPI")
    public void confirmCMIDAPI() {

        //confirm recovered cm-id by entering otp
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(new RecoverCMID().getRecoverConfirmCMIDRequestBody(sessionId));
        Response confirmRecoveryResponse = request.post("/patients/profile/recovery-confirm");

        Assert.assertEquals(confirmRecoveryResponse.getStatusCode(), 200);
        JsonPath jsonPathEvaluator = confirmRecoveryResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.getString("cmId"), "apidemotest101@ncg");
        System.out.println(jsonPathEvaluator.getString("cmId"));
    }

}
