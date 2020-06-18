package Tests.APITests.APIUtils;

import java.util.Random;
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

    private String generateRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return String.valueOf(10000 + r.nextInt(20000));
    }

    private String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

}
