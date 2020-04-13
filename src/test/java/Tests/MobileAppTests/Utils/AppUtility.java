package Tests.MobileAppTests.Utils;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class AppUtility {

    private static AppUtility instance = null;
    private final String path = System.getProperty("user.dir") + "/target/apk";

    private AppUtility() {
        try {
            UnzipUtility.unpackArchive(new URL(getArtifactURL()), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // static method to create instance of Singleton class
    public static AppUtility getInstance() {
        if (instance == null)
            instance = new AppUtility();


        return instance;
    }

    public static String generateRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return String.valueOf(10000 + r.nextInt(20000));
    }

    private String getArtifactURL() {
        RestAssured.baseURI = "https://api.github.com";

        RequestSpecification request = RestAssured.given();

        //Auth Headers added to avoid the rate limiting
        Response response = request.header("Authorization", System.getenv("Authorization")).get("/repos/ProjectEKA/Jataayu/actions/runs");

        JsonPath jsonPathEvaluator = response.jsonPath();
        String run_id = jsonPathEvaluator.getString("workflow_runs[0].id");

        System.out.println("Getting artifacts for the run id - " + run_id);

        RequestSpecification request1 = RestAssured.given();
        response = request1.header("Authorization", System.getenv("Authorization")).get(String.format("/repos/ProjectEKA/Jataayu/actions/runs/%s/artifacts", run_id));
        jsonPathEvaluator = response.jsonPath();
        String artifactURL = jsonPathEvaluator.getString("artifacts[0].archive_download_url");

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.header("Authorization", System.getenv("Authorization"));

        requestSpecification.config(new RestAssuredConfig().redirect(new RedirectConfig().followRedirects(false)));
        response = requestSpecification.get(artifactURL);

        System.out.println("Artifact will be downloaded from URL - " + response.getHeader("Location"));

        return response.getHeader("Location");
    }

    public String getPath() {
        return path;
    }

}
