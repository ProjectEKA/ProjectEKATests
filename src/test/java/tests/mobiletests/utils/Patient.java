package tests.mobiletests.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Patient {
  public String firstName;
  public String lastName;
  public String mobile;
  public String gender;
}
