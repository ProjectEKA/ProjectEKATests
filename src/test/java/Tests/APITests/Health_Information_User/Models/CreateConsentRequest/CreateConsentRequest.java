package Tests.APITests.Health_Information_User.Models.CreateConsentRequest;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CreateConsentRequest {

    public Consent consent;

}
