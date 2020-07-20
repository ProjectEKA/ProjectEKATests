package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyOTPRequest {
    @JsonProperty String sessionId;
    @JsonProperty String value;
}