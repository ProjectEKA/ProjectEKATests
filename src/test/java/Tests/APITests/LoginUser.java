package Tests.APITests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static Tests.APITests.Consent_Manager.TestBuildersCM.loginPayload;
import static Tests.APITests.Health_Information_User.TestBuildersHIU.loginDoctorPayload;

public class LoginUser {

    public String getCMAuthToken() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginPayload()).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        System.out.println("CM Login Successful");
        return "Bearer " + jsonPathEvaluator.getString("accessToken");
    }

    public String getCMRefreshToken() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginPayload()).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        System.out.println("CM Login Successful");
        return jsonPathEvaluator.getString("refreshToken");
    }

    public String getHIUAuthToken() {
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBaseURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginDoctorPayload()).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        System.out.println("HIU Login Successful");
        return jsonPathEvaluator.getString("accessToken");
    }

}
