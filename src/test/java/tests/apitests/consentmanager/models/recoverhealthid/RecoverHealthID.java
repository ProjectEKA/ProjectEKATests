package tests.apitests.consentmanager.models.recoverhealthid;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecoverHealthID {

  public Name name;
  public String gender;
  public DateOfBirth dateOfBirth;
  public List<VerifiedIdentifier> verifiedIdentifiers;
  public List<UnverifiedIdentifier> unverifiedIdentifiers;
}
