package Tests.APITests.APIUtils.CMRequest;

import java.util.UUID;

public class VerifyConsentPIN {

    public String getVerifyGrantPINRequestBody() { //TODO Remove these code
        return "{\n" +
                "   \"pin\": \"1234\",\n" +
                "   \"requestId\": \"" + generateUUID() +"\""+",\n" +
                "   \"scope\": \"consentrequest.approve\" \n" +
                "}";
    }

    public String getVerifyRevokePINRequestBody() { //TODO Remove these code
        return "{\n" +
                "   \"pin\": \"1234\",\n" +
                "   \"requestId\": \"" + generateUUID() +"\""+",\n" +
                "   \"scope\": \"consent.revoke\" \n" +
                "}";
    }

    private String generateUUID() { //TODO Remove these code
        return String.valueOf(UUID.randomUUID());
    }

}
