package Tests.APITests.APIUtils;

public class CMPatientDiscovery {

    public String getPatientDiscoverRequestBody() {
        return "{\n" +
                "  \"hip\": {\n" +
                "    \"id\": \"10000005\",\n" +
                "    \"name\": \"Max Health Care\"\n" +
                "  },\n" +
                "    \"unverifiedIdentifiers\": [\n" +
                "    ]\n" +
                "}";

    }

}
