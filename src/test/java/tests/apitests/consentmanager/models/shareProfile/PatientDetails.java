package tests.apitests.consentmanager.models.shareProfile;

import lombok.Builder;
import lombok.Data;
import tests.apitests.consentmanager.models.recoverhealthid.DateOfBirth;
import tests.apitests.consentmanager.models.recoverhealthid.Name;

import java.util.List;

@Data
@Builder
public class PatientDetails {
    boolean aadhaarVerified;
    DateOfBirth dateOfBirth;
    String districtCode;
    String districtName;
    String gender;
    boolean hasTransactionPin;
    String healthId;
    String id;
    Name name;
    String stateCode;
    String stateName;
    List<VerifiedIdentifiers> verifiedIdentifiers;


}
