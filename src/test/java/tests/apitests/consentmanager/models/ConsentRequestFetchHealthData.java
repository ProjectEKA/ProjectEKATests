package tests.apitests.consentmanager.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ConsentRequestFetchHealthData {

    public List<Object> requestIds;
    String limit;
    String offset;
}
