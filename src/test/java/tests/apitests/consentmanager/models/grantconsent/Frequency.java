package tests.apitests.consentmanager.models.grantconsent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Frequency {
  public int value;
  public String unit;
  public int repeats;
}
