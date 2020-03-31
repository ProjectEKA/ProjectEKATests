package Tests.APITests.APIUtils;

import java.util.Random;

public class HIPPatientDiscovery {

    public String getPatientDiscoverRequestBody() {
        return "{\n" +
                "  \"transactionId\": \"3fa85f64-5717-4562-b3fc-"+generateRandomNo()+"\",\n" +
                "  \"patient\": {\n" +
                "    \"id\": \""+PropertiesCache.getInstance().getProperty("userName")+"\",\n" +
                "    \"verifiedIdentifiers\": [\n" +
                "      {\n" +
                "        \"type\": \"MOBILE\",\n" +
                "        \"value\": \"+91-9999999999\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unverifiedIdentifiers\": [\n" +
                "      {\n" +
                "        \"type\": \"MOBILE\",\n" +
                "        \"value\": \"+919999999999\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"firstName\": \"abc\",\n" +
                "    \"lastName\": \"Doee\",\n" +
                "    \"gender\": \"M\",\n" +
                "    \"dateOfBirth\": \"2020-02-11\"\n" +
                "  }\n" +
                "}";

    }
    private String generateRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return String.valueOf(10000 + r.nextInt(20000));
    }

}
