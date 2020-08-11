package Tests.APITests.APIUtils.CMRequest;

public class RecoverCMID {

    public String getRecoverInitCMIDRequestBody() {
        return "{\n" +
                "   \"name\": { \n" +
                "       \"first\": \"navjot\", \n" +
                "       \"middle\": \"\", \n" +
                "       \"last\": \"singh\" \n" +
                "   }, \n" +
                "   \"gender\": \"F\", \n" +
                "   \"dateOfBirth\": { \n" +
                "       \"date\": null, \n" +
                "       \"month\": null, \n" +
                "       \"year\": 2001 \n" +
                "   }, \n" +
                "   \"verifiedIdentifiers\": [ \n" +
                "       {\n" +
                "           \"type\": \"MOBILE\", \n" +
                "           \"value\": \"+91-7777777777\" \n" +
                "       } \n" +
                "   ], \n" +
                "   \"unverifiedIdentifiers\": [ \n" +
                "       {\n" +
                "           \"type\": \"ABPMJAYID\", \n" +
                "           \"value\": \"PTEST20NS\" \n" +
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
