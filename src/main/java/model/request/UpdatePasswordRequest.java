package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePasswordRequest {
  @JsonProperty String oldPassword;
  @JsonProperty String newPassword;
}
