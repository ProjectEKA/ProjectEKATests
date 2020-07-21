package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DiscoverPatientRequest {
    @JsonProperty String requestId;
    @JsonProperty List<String> unverifiedIdentifiers;
    @JsonProperty HIP hip;
}
