package Tests.APITests.Central_Registry.Models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenPayload {

    String clientId;
    String clientSecret;
    String grantType;

}
