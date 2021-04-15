package tests.apitests.gateway.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginPayLoad {

  String clientId;
  String clientSecret;
  String grantType;
}
