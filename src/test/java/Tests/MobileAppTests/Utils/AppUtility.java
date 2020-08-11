package Tests.MobileAppTests.Utils;

import Tests.APITests.APIUtils.PropertiesCache;
import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONArray;

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

        String repoURL = PropertiesCache.getInstance().getProperty("repoURL");
        //Auth Headers added to avoid the rate limiting
        Response response = request.header("Authorization", "token " + System.getenv("Authorization"))
                .get(repoURL);//NCG yml file

        JsonPath jsonPathEvaluator = response.jsonPath();
        String run_id = jsonPathEvaluator.getString("workflow_runs[0].id");

        System.out.println("Getting artifacts for the run id - " + run_id);

        String artifactURL = getArtifactURL(run_id);

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.header("Authorization", "token " + System.getenv("Authorization"));

        requestSpecification.config(new RestAssuredConfig().redirect(new RedirectConfig().followRedirects(false)));
        response = requestSpecification.get(artifactURL);

        System.out.println("Artifact will be downloaded from URL - " + response.getHeader("Location"));

        return response.getHeader("Location");
    }

    private String getArtifactURL(String run_id) {
        Response response;
        JsonPath jsonPathEvaluator;
        RequestSpecification request1 = RestAssured.given();
        response = request1.header("Authorization", "token " + System.getenv("Authorization")).get(String.format(PropertiesCache.getInstance().getProperty("artifactURL") +"actions/runs/%s/artifacts", run_id));
        jsonPathEvaluator = response.jsonPath();
        if (System.getenv("env").equals("ncg"))
            return jsonPathEvaluator.getString("artifacts[0].archive_download_url");
        else {
            JSONArray artifact = com.jayway.jsonpath.JsonPath.read(response.getBody().asString(), "$.artifacts[?(@.name=~ /^.*" + System.getenv("env") + ".*$/)].archive_download_url");
            return artifact.get(0).toString();
        }
    }

    public String getPath() {
        return path;
    }

}
