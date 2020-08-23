package tests.apitests.consentmanager.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginWithRefreshToken {

    String username;
    String grantType;
    String refreshToken;

}
