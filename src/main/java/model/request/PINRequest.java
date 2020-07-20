package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PINRequest {
  @JsonProperty String pin;
  @JsonProperty String requestId;
  @JsonProperty String scope;
}
