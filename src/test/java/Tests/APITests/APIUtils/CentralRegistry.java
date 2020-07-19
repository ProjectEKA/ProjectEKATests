package Tests.APITests.APIUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class CentralRegistry {

    public String getAuthTokenForHIP() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("centralRegistryURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(getHIPRequestBody()).post("/api/1.0/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(response.getStatusCode()).withFailMessage("Login failed").isEqualTo(200);
        return jsonPathEvaluator.getString("accessToken");
    }

  public String getAuthTokenForConsentManager() {

        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("centralRegistryURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(getConsentManagerRequestBody()).post("/api/1.0/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(response.getStatusCode()).withFailMessage("Login failed").isEqualTo(200);
        return jsonPathEvaluator.getString("accessToken");
    }

    private String getConsentManagerRequestBody() {
        return "{\n" +
                "        \"clientId\": \"consent-manager\",\n" +
                "        \"clientSecret\": \"" + System.getenv("ConsentManagerSecret") + "\",\n" +
                "        \"grantType\": \"password\"\n" +
                "        }";
    }

    private String getHIPRequestBody() {
        return "{\n" +
                "        \"clientId\": \"10000005\",\n" +
                "        \"clientSecret\": \"" + System.getenv("HIPSecret") + "\",\n" +
                "        \"grantType\": \"password\"\n" +
                "        }";
    }

}