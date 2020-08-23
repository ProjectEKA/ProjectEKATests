package tests.apitests.consentmanager.models;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class RevokeConsent {

    public List<String> consents;

}
