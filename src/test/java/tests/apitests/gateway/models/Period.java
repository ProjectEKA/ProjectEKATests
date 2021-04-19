package tests.apitests.gateway.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Period {
    String from;
    String to;
}
