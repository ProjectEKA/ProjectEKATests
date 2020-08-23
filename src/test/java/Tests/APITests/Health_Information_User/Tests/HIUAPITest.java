package Tests.APITests.Health_Information_User.Tests;

import Tests.APITests.Helpers.LoginUser;
import Tests.APITests.Helpers.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HIUAPITest {

    String authToken;

    @BeforeClass
    public void setup() {
        authToken = new LoginUser().getHIUAuthToken();
    }

    @Test(groups= {"All"})
    public void discoverPatientAPI() {

        //identifies patient by cm-id
        RequestSpecification request = RestAssured.given();
        String patientID = PropertiesCache.getInstance().getProperty("HIUPatient");
        Response findPatientResponse = request.header("Authorization", authToken).pathParam("patientID", patientID)
                .get("/v1/patients/{patientID}");

        Assert.assertEquals(findPatientResponse.getStatusCode(), 200);
        Assert.assertEquals(findPatientResponse.jsonPath().getString("patient.id"), patientID);
    }
}
