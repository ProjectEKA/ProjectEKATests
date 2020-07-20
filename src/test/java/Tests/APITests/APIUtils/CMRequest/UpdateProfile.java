package Tests.APITests.APIUtils.CMRequest;

import java.util.UUID;

public class UpdateProfile {

    public String getUpdatePasswordRequestBody() { //TODO Remove these code
        return "{\n" +
                "   \"newPassword\": \"Test135@\" ,\n" +
                "   \"oldPassword\": \"Test135@\" \n" +
                "}";
    }

    public String getVerifyPINRequestBody() { //TODO Remove these code
        return "{\n" +
                "   \"pin\": \"1234\",\n" +
                "   \"requestId\":\"" + generateUUID() +"\""+",\n" +
                "   \"scope\": \"profile.changepin\" \n" +
                "}";
    }

    public String getUpdatePINRequestBody() { //TODO Remove these code
        return "{\n" +
                "   \"pin\": \"1234\" \n" +
                "}";
    }

    private String generateUUID() { //TODO Remove these code
        return String.valueOf(UUID.randomUUID());
    }

}
