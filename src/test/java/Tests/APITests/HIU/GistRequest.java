package Tests.APITests.HIU;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GistRequest {

    @JsonProperty("public")
    boolean pub;

    @JsonProperty("files")
    FileDetails files;

    @JsonProperty("description")
    private String description;

    public GistRequest() {

    }

    public GistRequest(String description, boolean pub, FileDetails files) {
        this.description = description;
        this.pub = pub;
        this.files = files;
    }

    public String toString() {
        return "  " + this.description + " has an ID of: " + this.pub + ", and a review score of: " + this.files;
    }


    public static class FileDetails {
        @JsonProperty("workshop.txt")
        FileContent filename;

        public FileDetails(FileContent filename) {
            this.filename = filename;
        }


    }

    static class FileContent {
        @JsonProperty("content")
        String content;

        public FileContent(String filename) {
            this.content = filename;
        }

    }
}
