package tests.apitests.gateway.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subscription {
    Purpose purpose;
    Patient patient;
    HIU hiu;
    Categories categories;
    Period period;
}
