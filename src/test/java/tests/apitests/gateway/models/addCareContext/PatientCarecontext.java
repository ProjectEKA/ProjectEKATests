package tests.apitests.gateway.models.addCareContext;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PatientCarecontext {
    String referenceNumber;
    String display;
    List<CareContextAdd> careContexts;
}
