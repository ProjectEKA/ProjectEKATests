package tests.apitests.consentmanager.models.recoverhealthid;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DateOfBirth {

  public int date;
  public int month;
  public int year;
}
