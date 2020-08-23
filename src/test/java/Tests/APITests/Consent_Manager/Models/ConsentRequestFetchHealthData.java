package Tests.APITests.Consent_Manager.Models;

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
