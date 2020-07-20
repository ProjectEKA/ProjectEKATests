package Tests.APITests.APIUtils.CMRequest;

import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.request.LoginRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginUser {

    private final String userName = PropertiesCache.getInstance().getProperty("userName");
    private final String password = PropertiesCache.getInstance().getProperty("password");
    private final String hiuUserName = PropertiesCache.getInstance().getProperty("HIUuserName");
    private final String hiuPassword = PropertiesCache.getInstance().getProperty("HIUpassword");

    public String getCMAuthToken() {

        LoginRequest loginRequest = LoginRequest.builder().username(userName).password(password).grantType("password").build();
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginRequest).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(response.getStatusCode()).withFailMessage("Login failed").isEqualTo(200);
        System.out.println("CM Login Successful");
        return jsonPathEvaluator.getString("accessToken");
    }

    public String getCMRefreshToken() {

        LoginRequest loginRequest = LoginRequest.builder().username(userName).password(password).grantType("password").build();
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("consentManagerURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginRequest).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(response.getStatusCode()).withFailMessage("Login failed").isEqualTo(200);
        System.out.println("CM Login Successful");
        return jsonPathEvaluator.getString("refreshToken");
    }

    private String getCMLoginRequestBody() { //TODO Remove these code
        return "{\n" +
                "    \"username\": \"" + userName + "\",\n" +
                "    \"password\": \"" + password + "\",\n" +
                "    \"grantType\": \"password\"\n" +
                "}";
    }

    private String getHIULoginRequestBody() { //TODO Remove these code
        return "{\n" +
                "    \"username\": \"" + hiuUserName + "\",\n" +
                "    \"password\": \"" + hiuPassword + "\"" +
                "}";
    }

    public String getHIUAuthToken() {
        LoginRequest loginRequest = LoginRequest.builder().username(hiuUserName).password(hiuPassword).grantType("password").build();
        RestAssured.baseURI = PropertiesCache.getInstance().getProperty("HIUBackendURL");
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.body(loginRequest).post("/sessions");
        JsonPath jsonPathEvaluator = response.jsonPath();

        assertThat(response.getStatusCode()).withFailMessage("Login failed").isEqualTo(200);
        System.out.println("HIU Login Successful");
        return jsonPathEvaluator.getString("accessToken");
    }

    public String getCMLogoutRequestBody(String refreshToken) {
        return "{\n" +
                "    \"refreshToken\": \"" + refreshToken + "\"" +
                "}";
    }

}
