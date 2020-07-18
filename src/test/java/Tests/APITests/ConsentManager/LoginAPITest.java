package Tests.APITests.ConsentManager;

import Tests.APITests.APIUtils.CMRequest.LoginUser;
import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginAPITest {
    String refreshToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void loginWithRefreshToken() {
        LoginUser loginUser = new LoginUser();
        refreshToken = "Bearer " + loginUser.getCMRefreshToken();

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginUser.getCMLoginRequestBody_RefreshToken(refreshToken)).post("/sessions");
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertNotNull(jsonPathEvaluator.getString("accessToken"));

    }
}
