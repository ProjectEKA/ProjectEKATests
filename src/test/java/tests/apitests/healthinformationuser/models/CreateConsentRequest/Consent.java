package tests.apitests.healthinformationuser.models.CreateConsentRequest;

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
