package Tests.APITests.APIUtils.CMRequest;

public class AutoCreateConsentRequest {

    public String createConsentRequestRequestBody() {
        return "{                              \n" +
                "    \"hipIds\": [                \n" +
                "    \"10000005\"                 \n" +
                "    ],                         \n" +
                "    \"reloadConsent\": true      \n" +
                "}                                " ;
    }

    public String fetchConsentStatusRequestBody() {
        return "{                              \n" +
                "    \"requestIds\": [\"d42ba15f-f64f-41b3-81d9-bfba25c7776b\"]      \n" +
                "}                                " ;
    }

    public String fetchHalthDataRequestBody() {
        return "{                              \n" +
                "    \"requestIds\": [\"d42ba15f-f64f-41b3-81d9-bfba25c7776b\"]      \n" +
                "    \"limit\": 10,                         \n" +
                "    \"offset\": 0                          \n" +
                "}                                          " ;
    }

}
