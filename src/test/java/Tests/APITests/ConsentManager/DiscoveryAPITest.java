package Tests.APITests.ConsentManager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DiscoveryAPITest {


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://consent-manager-dev.projecteka.in";
    }

    @Test
    public void listProvidersAPI() {
        RequestSpecification request = RestAssured.given();

        Response response = request.queryParam("name", "Tata").get("/providers");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].name"), "Tata Memorial Hospital");
        Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].id"), "10000002");
    }
}
