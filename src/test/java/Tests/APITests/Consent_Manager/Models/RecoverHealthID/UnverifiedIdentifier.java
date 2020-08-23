package Tests.APITests.Consent_Manager.Models.RecoverHealthID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnverifiedIdentifier {

    public String type;
    public String value;

}
