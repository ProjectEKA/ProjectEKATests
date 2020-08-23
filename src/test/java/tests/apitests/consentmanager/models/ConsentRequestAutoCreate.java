package tests.apitests.consentmanager.models;

import tests.apitests.consentmanager.models.grantconsent.Hip;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsentRequestAutoCreate {

    public Hip hip;
    String reloadConsent;

}
