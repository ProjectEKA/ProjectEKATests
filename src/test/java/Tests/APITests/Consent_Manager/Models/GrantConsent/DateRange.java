package Tests.APITests.Consent_Manager.Models.GrantConsent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DateRange {
    public String from;
    public String to;
}
