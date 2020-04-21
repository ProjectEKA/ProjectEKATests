package Tests.APITests.APIUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class HIULogin {

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

    private String getHIULoginRequestBody() {
        return "{\n" +
                "    \"username\": \"" + PropertiesCache.getInstance().getProperty("HIUuserName") + "\",\n" +
                "    \"password\": \"" + PropertiesCache.getInstance().getProperty("HIUpassword") +  "\"" +"\n" +
                "}";
    }

}
