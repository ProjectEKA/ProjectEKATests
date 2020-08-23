package tests.apitests.consentmanager.models.grantconsent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CareContext{
    public String careContextReference;
    public String patientReference;
}
