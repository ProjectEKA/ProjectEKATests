package Tests.APITests.ConsentManager;

import com.google.gson.JsonObject;
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
        RestAssured.baseURI = "https://consent-manager-dev.projecteka.in";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void listProvidersAPI() {
        RequestSpecification request = RestAssured.given();

        Response response = request.header("Authorization", "MUBuY2c=").queryParam("name", "Tata").get("/providers");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].name"), "Tata Memorial Hospital");
        Assert.assertEquals(jsonPathEvaluator.getString("identifier[0].id"), "10000002");
    }

    @Test
    public void discoverPatientsAPI() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "MUBuY2c=");//1@ncg

        JsonObject hip = new JsonObject();
        JsonObject innerObject = new JsonObject();

        innerObject.addProperty("id", "10000003");
        hip.add("hip", innerObject);

        request.body(hip.toString());

        Response response = request.post("/patients/discover");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("patient.referenceNumber"), "1");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.display"), "John Doee");
        Assert.assertEquals(jsonPathEvaluator.getString("patient.careContexts[0].referenceNumber"), "123");
    }
}
