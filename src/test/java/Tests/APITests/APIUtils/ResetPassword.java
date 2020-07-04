package Tests.APITests.APIUtils;

public class ResetPassword {

    public String getGenerateOTPRequestBody() {
        return "{\n" +
                "   \"username\": \"apidemotest10@ncg\" \n" +
                "}";
    }

    public String getVerifyOTPRequestBody(String sessionId) {
        return "{\n" +
                "   \"sessionId\": \""+ sessionId +"\",\n" +
                "   \"value\": \"666666\" \n" +
                "}";
    }

    public String getResetPasswordRequestBody() {
        return "{\n" +
                "   \"password\": \"Test135@\" \n" +
                "}";
    }

}
