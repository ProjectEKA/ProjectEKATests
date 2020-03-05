package Tests.APITests.APIUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class LoginUser {

    public String getAuthToken() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(getLoginRequestBody()).post("/sessions");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        System.out.println("Login Successful");
        return jsonPathEvaluator.getString("accessToken");
    }

    private String getLoginRequestBody() {
        return "{\n" +
                "    \"userName\": \"" + PropertiesCache.getInstance().getProperty("userName") + "\",\n" +
                "    \"password\": \"" + PropertiesCache.getInstance().getProperty("password") + "\",\n" +
                "    \"grantType\": \"password\"\n" +
                "}";
    }

}
