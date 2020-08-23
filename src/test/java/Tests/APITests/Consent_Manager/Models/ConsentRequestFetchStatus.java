package Tests.APITests.Consent_Manager.Models;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ConsentRequestFetchStatus {

    public List<Object> requestIds;

}
