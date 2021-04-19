package tests.apitests.gateway.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionRequestPayLoad {
    String requestId;
    String timestamp;
    Subscription subscription;
}
