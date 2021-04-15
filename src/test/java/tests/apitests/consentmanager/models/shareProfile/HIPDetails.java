package tests.apitests.consentmanager.models.shareProfile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HIPDetails {
    String code;
    String hipId;
}
