package tests.apitests.consentmanager.models.grantconsent;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Consent {
  public List<CareContext> careContexts;
  public Purpose purpose;
  public List<String> hiTypes;
  public Hip hip;
  public Permission permission;
}
