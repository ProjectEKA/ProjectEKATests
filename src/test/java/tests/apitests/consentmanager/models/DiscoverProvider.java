package tests.apitests.consentmanager.models;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import tests.apitests.consentmanager.models.grantconsent.Hip;

@Data
@Builder
public class DiscoverProvider {

  public String requestId;
  public List<Object> unverifiedIdentifiers;
  public Hip hip;
}
