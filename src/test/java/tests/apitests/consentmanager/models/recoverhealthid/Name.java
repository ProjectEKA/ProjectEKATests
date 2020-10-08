package tests.apitests.consentmanager.models.recoverhealthid;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Name {

  public String first;
  public String middle;
  public String last;
}
