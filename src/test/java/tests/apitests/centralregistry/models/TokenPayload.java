package tests.apitests.centralregistry.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenPayload {

  String clientId;
  String clientSecret;
  String grantType;
}
