package Tests.APITests.APIUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HIUConsentRequest {

    private String id;
    private String expiryDate;
    private String fromDate;
    private String toDate;

    public HIUConsentRequest(ConsentRequestBuilder consentRequestBuilder) {
        this.id = consentRequestBuilder.id;
        this.expiryDate = consentRequestBuilder.expiryDate;
        this.toDate = consentRequestBuilder.toDate;
        this.fromDate = consentRequestBuilder.fromDate;

    }

    public JsonObject getJSONRequestBody() {
        String json = "{\n" +
                "  \"consent\": {\n" +
                "    \"patient\": {\n" +
                "      \"id\": \"" + id + "\"\n" +
                "    },\n" +
                "    \"purpose\": {\n" +
                "      \"code\": \"Encounter\"\n" +
                "    },\n" +
                "    \"hiTypes\": [\n" +
                "      \"Condition\"\n" +
                "    ],\n" +
                "    \"permission\": {\n" +
                "      \"dateRange\": {\n" +
                "        \"from\": \"" + fromDate + "\",\n" +
                "        \"to\": \"" + toDate + "\"\n" +
                "      },\n" +
                "      \"dataEraseAt\": \"" + expiryDate + "\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Gson gson = new Gson();
        JsonElement element = gson.fromJson(json, JsonElement.class);
        return element.getAsJsonObject();
    }

    public static class ConsentRequestBuilder {

        private String id;
        private String expiryDate;
        private String toDate;
        private String fromDate;

        public ConsentRequestBuilder(String id) {
            this.id = id;

            Date currentDate = new Date();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            this.toDate = formatter.format(localDateTime);

            localDateTime = localDateTime.minusDays(2);
            this.fromDate = formatter.format(localDateTime);

            localDateTime = localDateTime.plusDays(5);
            this.expiryDate = formatter.format(localDateTime);


        }

        public ConsentRequestBuilder withOptionalExpiryDate(String date) {
            this.expiryDate = date;
            return this;
        }


        public HIUConsentRequest buildConsentReuqest() {
            isValidData();
            return new HIUConsentRequest(this);
        }

        private boolean isValidData() {
            //Do some basic validations to check
            return true;
        }
    }
}
