package Tests.APITests.APIUtils;

import java.util.UUID;

public class UpdateProfile {

    public String getUpdatePasswordRequestBody() {
        return "{\n" +
                "   \"newPassword\": \"Test135@\" ,\n" +
                "   \"oldPassword\": \"Test135@\" \n" +
                "}";
    }

    public String getVerifyPINRequestBody() {
        return "{\n" +
                "   \"pin\": \"1234\",\n" +
                "   \"requestId\":\"" + generateUUID() +"\""+",\n" +
                "   \"scope\": \"profile.changepin\" \n" +
                "}";
    }

    public String getUpdatePINRequestBody() {
        return "{\n" +
                "   \"pin\": \"1234\" \n" +
                "}";
    }

    private String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

}
