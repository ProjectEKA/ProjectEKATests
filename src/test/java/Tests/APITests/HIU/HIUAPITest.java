package Tests.APITests.HIU;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HIUAPITest {


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://hiu-dev.projecteka.in/api";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void discoverPatientAPI() {
        RequestSpecification request = RestAssured.given();
        String patientID = "1@ncg";
        Response response = request.pathParam("patientID", patientID).get("/patients/{patientID}");

        JsonPath jsonPathEvaluator = response.jsonPath();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonPathEvaluator.getString("patient.id"), patientID);
    }

    @Test
    public void createConsentRequest() {

//        RequestSpecification request = RestAssured.given();
//        request.header("Content-Type", "application/json");
//        request.header("Authorization", "MUBuY2c=");//1@ncg
//
//        JsonObject hip = new JsonObject();
//        JsonObject innerObject = new JsonObject();
//
//        innerObject.addProperty("id", "10000003");
//        hip.add("hip", innerObject);
//
//        request.body(hip.toString());
//
//        Response response = request.post("/patients/discover");
//
//        JsonPath jsonPathEvaluator = response.jsonPath();
//
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(jsonPathEvaluator.getString("patient.referenceNumber"), "1");
//        Assert.assertEquals(jsonPathEvaluator.getString("patient.display"), "John Doee");
//        Assert.assertEquals(jsonPathEvaluator.getString("patient.careContexts[0].referenceNumber"), "123");
    }
}
