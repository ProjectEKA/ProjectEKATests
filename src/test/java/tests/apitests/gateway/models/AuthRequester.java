package tests.apitests.gateway.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequester {
    String type;
    String id;
}
