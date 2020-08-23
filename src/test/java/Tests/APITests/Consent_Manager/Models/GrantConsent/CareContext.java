package Tests.APITests.Consent_Manager.Models.GrantConsent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CareContext{
    public String careContextReference;
    public String patientReference;
}
