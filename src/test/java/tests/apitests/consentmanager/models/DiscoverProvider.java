package tests.apitests.consentmanager.models;

import tests.apitests.consentmanager.models.grantconsent.Hip;
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
