package tests.apitests.healthinformationuser.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDoctor {

  String username;
  String password;
}
