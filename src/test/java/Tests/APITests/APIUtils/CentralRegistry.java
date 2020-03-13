package Tests.APITests.APIUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class CentralRegistry {
    public String getAuthToken() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("centralRegistryURL");
        RestAssured.useRelaxedHTTPSValidation();

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(getClientRegistryRequestBody()).post("/api/1.0/sessions");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        return jsonPathEvaluator.getString("accessToken");
    }


    private String getClientRegistryRequestBody() {
        return "{\n" +
                "        \"clientId\": \"consent-manager\",\n" +
                "        \"clientSecret\": \"" + System.getenv("ConsentManagerSecret") + "\",\n" +
                "        \"grantType\": \"password\"\n" +
                "        }";
    }

}