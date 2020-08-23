package Tests.APITests.Consent_Manager.Models.RecoverHealthID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifiedIdentifier {

    public String type;
    public String value;

}
