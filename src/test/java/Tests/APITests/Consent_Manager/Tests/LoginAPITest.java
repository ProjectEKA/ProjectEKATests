package Tests.APITests.Consent_Manager.Tests;

import Tests.APITests.Helpers.Utils.LoginUtil;
import Tests.APITests.Helpers.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Tests.APITests.Consent_Manager.TestBuildersCM.loginRefreshTokenPayload;

public class LoginAPITest {
    String refreshToken;
    RequestSpecification specification;

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        specification = builder.build();
    }

    @Test(groups= {"All"})
    public void loginWithRefreshTokenAPI() {
        LoginUtil loginUser = new LoginUtil();
        refreshToken = loginUser.getCMRefreshToken();
        RequestSpecification request = RestAssured.given().spec(specification);
        request.header("Content-Type", "application/json");
        Response response = request.body(loginRefreshTokenPayload(refreshToken)).post("/sessions");

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertNotNull(jsonPathEvaluator.getString("accessToken"));
    }
}
