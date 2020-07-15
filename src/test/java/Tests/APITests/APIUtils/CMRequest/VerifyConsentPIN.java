package Tests.APITests.APIUtils.CMRequest;

import java.util.UUID;

public class VerifyConsentPIN {

    public String getVerifyGrantPINRequestBody() {
        return "{\n" +
                "   \"pin\": \"1234\",\n" +
                "   \"requestId\": \"" + generateUUID() +"\""+",\n" +
                "   \"scope\": \"consentrequest.approve\" \n" +
                "}";
    }

    public String getVerifyRevokePINRequestBody() {
        return "{\n" +
                "   \"pin\": \"1234\",\n" +
                "   \"requestId\": \"" + generateUUID() +"\""+",\n" +
                "   \"scope\": \"consent.revoke\" \n" +
                "}";
    }

    private String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

}
