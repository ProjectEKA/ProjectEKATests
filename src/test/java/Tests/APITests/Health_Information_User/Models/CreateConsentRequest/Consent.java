package Tests.APITests.Health_Information_User.Models.CreateConsentRequest;

import Tests.APITests.Consent_Manager.Models.GrantConsent.Permission;
import Tests.APITests.Consent_Manager.Models.GrantConsent.Purpose;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class Consent {

    public Patient patient;
    public Purpose purpose;
    public List<String> hiTypes;
    public Permission permission;

}
