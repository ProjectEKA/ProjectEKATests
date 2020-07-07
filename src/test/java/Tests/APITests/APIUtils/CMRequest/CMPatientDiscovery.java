package Tests.APITests.APIUtils.CMRequest;

import java.util.UUID;

public class CMPatientDiscovery {

    public String getPatientDiscoverRequestBody() {
        return "{\n" +
                "\"requestId\":\"" + generateUUID() +"\""+"," +
                "    \"hip\": {\n" +
                "        \"id\": \"10000005\",\n" +
                "        \"name\":\"Max Health Care\"\n" +
                "    }\n" +
                "}";
    }

    private String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

}
