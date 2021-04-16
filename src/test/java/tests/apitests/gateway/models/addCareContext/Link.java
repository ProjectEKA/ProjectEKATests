package tests.apitests.gateway.models.addCareContext;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Link {
String accessToken;
PatientCarecontext patient;

}
