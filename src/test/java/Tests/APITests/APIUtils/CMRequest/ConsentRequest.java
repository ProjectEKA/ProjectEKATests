package Tests.APITests.APIUtils.CMRequest;

public class ConsentRequest {

    public String getGrantConsentRequestBody() {
        return " {                                                          \n" +
                "    \"consents\": [                                        \n" +
                "       {                                                   \n" +
                "           \"careContexts\": [                             \n" +
                "                {                                          \n" +
                "                    \"careContextReference\": \"NCP1007\",    \n" +
                "                    \"patientReference\": \"RVH1002\"      \n" +
                "                },                                         \n" +
                "                {                                          \n" +
                "                    \"careContextReference\": \"RV-MHD-01.17.0024\",    \n" +
                "                    \"patientReference\": \"RVH1002\"      \n" +
                "                }                                          \n" +
                "           ],                                              \n" +
                "           \"hiTypes\": [                                  \n" +
                "               \"Prescription\"                            \n" +
                "           ],                                              \n" +
                "           \"hip\": {                                      \n" +
                "               \"id\": \"10000005\"                        \n" +
                "           },                                              \n" +
                "           \"permission\": {                               \n" +
                "               \"accessMode\": \"VIEW\",                   \n" +
                "               \"dataEraseAt\": \"2020-12-27T10:45:54.688\",    \n" +
                "               \"dateRange\": {                            \n" +
                "                   \"from\": \"1992-06-25T18:30:00\",      \n" +
                "                   \"to\": \"2020-06-26T10:45:54.688\"     \n" +
                "               },                                          \n" +
                "               \"frequency\": {                            \n" +
                "                   \"value\": 1,                           \n" +
                "                   \"unit\": \"HOUR\",                     \n" +
                "                   \"repeats\": 0                          \n" +
                "               }                                           \n" +
                "           }                                               \n" +
                "       }                                                   \n" +
                "   ]                                                       \n" +
                "}                                                          ";
    }

    public String getRevokeConsentRequestBody(String consentArtefactId) {
        return " {                                        \n" +
                "   \"consents\": [                       \n" +
                "           \""+ consentArtefactId +"\"   \n" +
                "    ]                                    \n" +
                "}                                        ";
    }
}
