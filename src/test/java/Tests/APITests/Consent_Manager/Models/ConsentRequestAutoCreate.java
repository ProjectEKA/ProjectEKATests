package Tests.APITests.Consent_Manager.Models;

import Tests.APITests.Consent_Manager.Models.GrantConsent.Hip;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsentRequestAutoCreate {

    public Hip hip;
    String reloadConsent;

}
