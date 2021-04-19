package tests.apitests.consentmanager.models.shareProfile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifiedIdentifiers {
    String type;
    String value;
}
