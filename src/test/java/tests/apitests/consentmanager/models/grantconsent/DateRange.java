package tests.apitests.consentmanager.models.grantconsent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DateRange {
    public String from;
    public String to;
}
