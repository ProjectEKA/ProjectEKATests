package tests.apitests.consentmanager.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Logout {

    String refreshToken;

}
