package Tests.APITests.APIUtils;

public class HIPPatientDiscovery {

    public String getPatientDiscoverRequestBody() {
        return "{\n" +
                "  \"transactionId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
                "  \"patient\": {\n" +
                "    \"id\": \"1@ncg\",\n" +
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

}
