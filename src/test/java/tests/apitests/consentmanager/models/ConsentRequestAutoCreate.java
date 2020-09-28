package tests.apitests.consentmanager.models;

import lombok.Builder;
import lombok.Data;
import tests.apitests.consentmanager.models.grantconsent.Hip;

@Data
@Builder
public class ConsentRequestAutoCreate {

  public Hip hip;
  String reloadConsent;
}
