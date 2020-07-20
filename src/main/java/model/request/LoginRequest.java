package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    @JsonProperty String username;
    @JsonProperty String password;
    @JsonProperty String grantType;
}
