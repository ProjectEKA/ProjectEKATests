package tests.apitests.consentmanager.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginPatient {

  String username;
  String password;
  String grantType;
}
