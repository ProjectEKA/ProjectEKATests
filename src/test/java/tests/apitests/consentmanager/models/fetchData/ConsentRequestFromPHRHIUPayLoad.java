package tests.apitests.consentmanager.models.fetchData;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ConsentRequestFromPHRHIUPayLoad {
    List<String> hipIds;
    boolean reloadConsent;
}
