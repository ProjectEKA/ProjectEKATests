package Tests.APITests.APIUtils.CMRequest;

public class RecoverCMID {

    public String getRecoverInitCMIDRequestBody() {
        return "{\n" +
                "   \"name\": { \n" +
                "       \"first\": \"John\", \n" +
                "       \"last\": \"\", \n" +
                "       \"middle\": \"Doe\" \n" +
                "   }, \n" +
                "   \"gender\": \"M\", \n" +
                "   \"dateOfBirth\": { \n" +
                "       \"date\": 1, \n" +
                "       \"month\": 1, \n" +
                "       \"year\": 1966 \n" +
                "   }, \n" +
                "   \"verifiedIdentifiers\": [ \n" +
                "       {\n" +
                "           \"type\": \"MOBILE\", \n" +
                "           \"value\": \"+91-9999999999\" \n" +
                "       } \n" +
                "   ], \n" +
                "   \"unverifiedIdentifiers\": [ \n" +
                "       {\n" +
                "           \"type\": \"ABPMJAYID\", \n" +
                "           \"value\": \"PAPITEST1\" \n" +
                "       }\n" +
                "   ]\n" +
                "}";
    }
}
