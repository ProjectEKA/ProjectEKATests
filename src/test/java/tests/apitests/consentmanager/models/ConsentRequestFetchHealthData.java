package tests.apitests.consentmanager.models;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsentRequestFetchHealthData {

  public List<Object> requestIds;
  String limit;
  String offset;
}
