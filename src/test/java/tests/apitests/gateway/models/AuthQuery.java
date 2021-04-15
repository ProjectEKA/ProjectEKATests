package tests.apitests.gateway.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthQuery {
    String id;
    String purpose;
    String authMode;
    AuthRequester requester;

}
