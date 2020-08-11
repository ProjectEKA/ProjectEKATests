package Tests.APITests.APIUtils.CMRequest;

import java.util.UUID;

public class CMPatientDiscovery {

    public String getPatientDiscoverRequestBody() {
        return "{\n" +
                "\"requestId\":\"" + generateUUID() +"\""+",\n" +
                "\"unverifiedIdentifiers\": [],\n" +
                "    \"hip\": {\n" +
                "        \"id\": \"10019843\"\n" +
                "    }\n" +
               "}";
    }

    private String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

}
