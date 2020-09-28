package tests.apitests.healthinformationuser.models.CreateConsentRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Frequency {
  public int value;
  public String unit;
  public int repeats;
}
