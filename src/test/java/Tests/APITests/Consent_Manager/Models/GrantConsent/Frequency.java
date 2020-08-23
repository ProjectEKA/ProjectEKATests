package Tests.APITests.Consent_Manager.Models.GrantConsent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Frequency{
        public int value;
        public String unit;
        public int repeats;
    }
