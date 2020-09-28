package tests.apitests.consentmanager.models.grantconsent;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GrantConsent {
  public List<Consent> consents;
}
