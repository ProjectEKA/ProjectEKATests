package Tests.APITests.Consent_Manager.Tests;

import Tests.APITests.Helpers.LoginUser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Tests.APITests.Consent_Manager.TestBuildersCM.recoverHealthIDPayload;
import static Tests.APITests.Consent_Manager.TestBuildersCM.verifyOTPPayload;

public class RecoverHealthIDAPITest {

    String authToken;
    String sessionId;

    @BeforeClass
    public void setup() {
        authToken = new LoginUser().getCMAuthToken();
    }

    @Test(enabled = false)
    public void initRecoveryAPI() {

        //initiate the cm-id recovery process
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(recoverHealthIDPayload());
        Response initRecoveryResponse = request.post("/patients/profile/recovery-init");

        Assert.assertEquals(initRecoveryResponse.getStatusCode(), 200);
        sessionId = initRecoveryResponse.jsonPath().getString("sessionId");
    }

    @Test(enabled = false)
    //@Test(dependsOnMethods = "initRecoveryAPI")
    public void confirmHealthIDAPI() {

        //confirm recovered cm-id by entering otp
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(verifyOTPPayload(sessionId));
        Response confirmRecoveryResponse = request.post("/patients/profile/recovery-confirm");

        Assert.assertEquals(confirmRecoveryResponse.getStatusCode(), 200);
        JsonPath jsonPathEvaluator = confirmRecoveryResponse.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.getString("cmId"), "apidemotest30@ncg");
    }

}
