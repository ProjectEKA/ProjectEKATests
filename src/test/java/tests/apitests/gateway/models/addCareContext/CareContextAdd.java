package tests.apitests.gateway.models.addCareContext;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CareContextAdd {
    String referenceNumber;
    String display;
}
