package Tests.APITests.Consent_Manager.Models.GrantConsent;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@Builder
@ToString
public class GrantConsent {
    public List<Consent> consents;
}
