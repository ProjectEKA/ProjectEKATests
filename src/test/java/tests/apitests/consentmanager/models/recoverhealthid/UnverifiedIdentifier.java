package tests.apitests.consentmanager.models.recoverhealthid;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnverifiedIdentifier {

  public String type;
  public String value;
}
