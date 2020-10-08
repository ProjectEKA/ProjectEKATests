package tests.apitests.healthinformationuser.models.CreateConsentRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DateRange {
  public String from;
  public String to;
}
