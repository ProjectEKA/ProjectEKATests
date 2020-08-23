package tests.apitests.consentmanager.models.grantconsent;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class Consent {
    public List<CareContext> careContexts;
    public Purpose purpose;
    public List<String> hiTypes;
    public Hip hip;
    public Permission permission;
}
