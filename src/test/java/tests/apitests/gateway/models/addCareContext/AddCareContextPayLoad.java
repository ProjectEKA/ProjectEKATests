package tests.apitests.gateway.models.addCareContext;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCareContextPayLoad {
    String requestId;
    String timestamp;
    Link link;
}
