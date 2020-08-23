package Tests.APITests.Consent_Manager.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginPatient {

    String username;
    String password;
    String grantType;

}