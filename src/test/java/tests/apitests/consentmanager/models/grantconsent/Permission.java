package tests.apitests.consentmanager.models.grantconsent;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Permission {
  public String accessMode;
  public String dataEraseAt;
  public DateRange dateRange;
  public Frequency frequency;
}
