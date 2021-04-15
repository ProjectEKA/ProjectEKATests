package tests.apitests.consentmanager.models.fetchData;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FetchDataUsingPHRHIUPayLoad {
    int limit;
    int offset;
    List<String> requestIds;

}
