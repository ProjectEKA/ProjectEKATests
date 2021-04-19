package tests.apitests.consentmanager.models.shareProfile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShareProfilePayLoad {
    HIPDetails hipDetails;
    PatientDetails patientDetails;
    String requestId;
}
