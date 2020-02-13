package Tests.APITests.HIU;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GitHubAPITest {


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://www.mocky.io/v2/5e4537e430000059006149c0";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void discoverPatientAPI() {

        JsonObject obj = new JsonObject();
        obj.addProperty("content","asdasd");

//        GistRequest gistRequest = new GistRequest("Created from API", true, new GistRequest
//                .FileDetails(new GistRequest.FileContent("Gist created from API")));



        RequestSpecification request = RestAssured.given();
//
//        request.header("Content-Type", "application/json");
//        request.header("Authorization", "Basic c2hyaWRoYXJrYWxhZ2k6NjAzZDdiMzA3YWExZWFiODFiODAxNTYyOTFjMTVmM2M1NWNlMDQwZQ==");//1@ncg


        Response response = request.get("/");

        GistRequest gistRequest = response.as(GistRequest.class);

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Consent request created with ID " + jsonPathEvaluator.getString("id"));
        Assert.assertTrue(jsonPathEvaluator.getString("id").length() > 1);
    }
}
