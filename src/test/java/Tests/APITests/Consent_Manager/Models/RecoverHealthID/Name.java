package Tests.APITests.Consent_Manager.Models.RecoverHealthID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Name {

    public String first;
    public String middle;
    public String last;

}
