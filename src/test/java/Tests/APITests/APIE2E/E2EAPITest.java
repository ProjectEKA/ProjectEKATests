//package Tests.APITests.APIE2E;
//
//import Tests.APITests.APIUtils.HIUConsentRequest;
//import Tests.APITests.APIUtils.PropertiesCache;
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//public class E2EAPITest {
//    RequestSpecification specification;
//
//    @BeforeClass
//    public void setup() {
//        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
//        RestAssured.useRelaxedHTTPSValidation();
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//        builder.setContentType(ContentType.JSON);
//        specification = builder.build();
//    }
//
//    @Test
//    public void createPatient() throws InterruptedException {
//
//        RequestSpecification request = RestAssured.given().spec(specification);
//        String body = "{\n" +
//                "\t\"identifierType\": \"mobile\",\n" +
//                "\t\"identifier\": \"+91-8888888888\"\n" +
//                "}";
//        Response response = request.body(body).post("/users/verify");
//        JsonPath jsonPathEvaluator = response.jsonPath();
//        Assert.assertEquals(response.getStatusCode(), 201);
//        String id = jsonPathEvaluator.getString("sessionId");
//        System.out.println(id);
//
//        String userPermitRequestBody = "{\n" +
//                "\t\"sessionId\": \"" + id + "\",\n" +
//                "\t\"value\": \"666666\"\n" +
//                "}";
//
//        Response userPermitResponse = request.body(userPermitRequestBody).post("/users/permit");
//        jsonPathEvaluator = userPermitResponse.jsonPath();
//        System.out.println(jsonPathEvaluator.getString("temporaryToken"));
//
//
//
//    }
//
//    //    @Test
//    public void createConsentRequest() {
//
//        RestAssured.baseURI = "https://hiu-dev.projecteka.in/api";
//        RestAssured.useRelaxedHTTPSValidation();
//
//        RequestSpecification request = RestAssured.given();
//        request.header("Content-Type", "application/json");
//        request.header("Authorization", "MUBuY2c=");//1@ncg
//
//        HIUConsentRequest hiuConsentRequest = new HIUConsentRequest.ConsentRequestBuilder("1@ncg").buildConsentReuqest();
//
//        request.body(hiuConsentRequest.getJSONRequestBody().toString());
//        Response response = request.post("/consent-requests");
//
//        JsonPath jsonPathEvaluator = response.jsonPath();
//
//        Assert.assertEquals(response.getStatusCode(), 200);
//        String id = jsonPathEvaluator.getString("id");
//
//        System.out.println(id);
//
//    }
//
//
//}
