package Tests.APITests.APIUtils.CMRequest;

public class ResetConsentPIN {

    public String getValidateOTPRequestBody(String sessionId) {
        return "{\n" +
                "   \"sessionId\": \""+ sessionId +"\",\n" +
                "   \"value\": \"666666\" \n" +
                "}";
    }

    public String getResetConsentPINRequestBody() {
        return "{\n" +
                "   \"pin\": \"1234\" \n" +
                "}";
    }


}
