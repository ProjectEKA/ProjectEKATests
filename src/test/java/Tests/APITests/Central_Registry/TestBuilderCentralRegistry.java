package Tests.APITests.Central_Registry;

import Tests.APITests.Central_Registry.Models.TokenPayload;

public class TestBuilderCentralRegistry {

    public static TokenPayload consentManagerTokenPayload() {
        return TokenPayload.builder().clientId("consent-manager")
                .clientSecret(System.getenv("ConsentManagerSecret"))
                .grantType("password")
                .build();
    }

    public static TokenPayload hipTokenPayload() {
        return TokenPayload.builder().clientId("10000005")
                .clientSecret(System.getenv("HIPSecret"))
                .grantType("password")
                .build();
    }

}
