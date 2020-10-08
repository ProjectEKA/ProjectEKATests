package tests.apitests.healthinformationuser.models.CreateConsentRequest;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Consent {

  public Patient patient;
  public Purpose purpose;
  public List<String> hiTypes;
  public Permission permission;
}
