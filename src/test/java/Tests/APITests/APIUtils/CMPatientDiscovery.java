package Tests.APITests.APIUtils;

import java.util.Random;

public class CMPatientDiscovery {

    public String getPatientDiscoverRequestBody() {
        return "{\n" +
                "  \"hip\": {\n" +
                "    \"id\": \"10000004\" \n" +
                "  },\n" +
                "  \"requestId\": \"4a5cede1-ed50-4f30-90ee-db0ebd8"+ generateRandomNo() +"\",\n" +
                "  \"unverifiedIdentifiers\": []\n" +
                "}";
    }

    private String generateRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return String.valueOf(10000 + r.nextInt(20000));
    }

}
