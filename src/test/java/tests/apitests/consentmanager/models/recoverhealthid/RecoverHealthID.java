package tests.apitests.consentmanager.models.recoverhealthid;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecoverHealthID {

    public Name name;
    public String gender;
    public DateOfBirth dateOfBirth;
    public List<VerifiedIdentifier> verifiedIdentifiers;
    public List<UnverifiedIdentifier> unverifiedIdentifiers;

}
