package Tests.APITests.APIUtils.CMRequest;

public class RecoverCMID {

    public String getRecoverInitCMIDRequestBody() {
        return "{\n" +
                "   \"name\": \"John Doe\", \n" +
                "   \"gender\": \"M\", \n" +
                "   \"yearOfBirth\": 1966, \n" +
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

    public String getRecoverConfirmCMIDRequestBody(String sessionId) {
        return "{\n" +
                "   \"sessionId\": \""+ sessionId +"\",\n" +
                "   \"value\": \"666666\" \n" +
                "}";
    }

}
