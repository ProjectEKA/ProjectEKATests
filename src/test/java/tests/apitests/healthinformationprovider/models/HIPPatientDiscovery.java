package tests.apitests.healthinformationprovider.models;

import tests.apitests.helpers.PropertiesCache;

import java.util.Random;

public class HIPPatientDiscovery {

    public String getPatientDiscoverRequestBody() {
        return  "{\n" +
                "  \"requestId\": \"3fa85f64-5717-4562-b3fc-"+generateRandomNo()+"\",\n" +
                "  \"transactionId\": \"3fa85f64-5717-4562-b3fc-"+generateRandomNo()+"\",\n" +
                "  \"timestamp\": \"2020-05-19T11:24:47.429Z\",\n" +
                "  \"patient\": {\n" +
                "    \"id\": \""+ PropertiesCache.getInstance().getProperty("username")+"\",\n" +
                "    \"verifiedIdentifiers\": [\n" +
                "      {\n" +
                "        \"type\": \"MOBILE\",\n" +
                "        \"value\": \"+91-9999999999\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unverifiedIdentifiers\": [\n" +
                "      {\n" +
                "        \"type\": \"MOBILE\",\n" +
                "        \"value\": \"+91-9999999999\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"name\": \"John Doe\",\n" +
                "    \"gender\": \"M\",\n" +
                "    \"yearOfBirth\": \"1966\"\n" +
                "  }\n" +
                "}";
    }

    private String generateRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return String.valueOf(10000 + r.nextInt(20000));
    }

}
