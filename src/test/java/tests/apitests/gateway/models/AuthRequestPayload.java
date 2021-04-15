package tests.apitests.gateway.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequestPayload {
    String requestId;
    String timestamp;
    AuthQuery query;
}
