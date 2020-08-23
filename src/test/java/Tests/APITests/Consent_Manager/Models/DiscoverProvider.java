package Tests.APITests.Consent_Manager.Models;

import Tests.APITests.Consent_Manager.Models.GrantConsent.Hip;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class DiscoverProvider {

    public String requestId;
    public List<Object> unverifiedIdentifiers;
    public Hip hip;

}
