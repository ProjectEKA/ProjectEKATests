package Tests.APITests.Consent_Manager.Models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyOTP {

    String sessionId;
    String value;
}
