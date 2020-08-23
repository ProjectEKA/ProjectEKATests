package tests.apitests.helpers.Models;

public enum ProvidersList {

    MAX("10000005");

    private final String providerId;

    ProvidersList(String providerId) {
        this.providerId = providerId;
    }

    public String getValue() {
        return providerId;
    }
}
