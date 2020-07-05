package Tests.APITests.APIUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class LoginUser {

    public String getCMAuthToken() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(getCMLoginRequestBody()).post("/sessions");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        System.out.println("CM Login Successful");
        return jsonPathEvaluator.getString("accessToken");
    }

    public String getCMRefreshToken() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(getCMLoginRequestBody()).post("/sessions");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        System.out.println("CM Login Successful");
        return jsonPathEvaluator.getString("refreshToken");
    }

    private String getCMLoginRequestBody() {
        return "{\n" +
                "    \"username\": \"" + PropertiesCache.getInstance().getProperty("userName") + "\",\n" +
                "    \"password\": \"" + PropertiesCache.getInstance().getProperty("password") + "\",\n" +
                "    \"grantType\": \"password\"\n" +
                "}";
    }

    private String getHIULoginRequestBody() {
        return "{\n" +
                "    \"username\": \"" + PropertiesCache.getInstance().getProperty("HIUuserName") + "\",\n" +
                "    \"password\": \"" + PropertiesCache.getInstance().getProperty("HIUpassword") + "\"" +
                "}";
    }

    public String getHIUAuthToken() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBackendURL");
        RestAssured.useRelaxedHTTPSValidation();

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(getHIULoginRequestBody()).post("/sessions");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        System.out.println("HIU Login Successful");
        return jsonPathEvaluator.getString("accessToken");
    }

    public String getCMLogoutRequestBody(String refreshToken) {
        return "{\n" +
                "    \"refreshToken\": \"" + refreshToken + "\"" +
                "}";
    }

}
