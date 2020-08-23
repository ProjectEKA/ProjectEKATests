package Tests.APITests.Health_Information_User;

import Tests.APITests.Consent_Manager.Models.GrantConsent.DateRange;
import Tests.APITests.Consent_Manager.Models.GrantConsent.Permission;
import Tests.APITests.Consent_Manager.Models.GrantConsent.Purpose;
import Tests.APITests.Health_Information_User.Models.CreateConsentRequest.Consent;
import Tests.APITests.Health_Information_User.Models.CreateConsentRequest.CreateConsentRequest;
import Tests.APITests.Health_Information_User.Models.CreateConsentRequest.Patient;
import Tests.APITests.Health_Information_User.Models.LoginDoctor;
import Tests.APITests.Helpers.Models.HealthInfoType;
import Tests.APITests.Helpers.Models.PurposeType;
import Tests.APITests.Helpers.PropertiesCache;
import Tests.APITests.Helpers.TestDataLiterals;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestBuildersHIU {

    public static LoginDoctor loginDoctorPayload() {
        return LoginDoctor.builder().username(PropertiesCache.getInstance().getProperty("HIUUsername"))
                .password(PropertiesCache.getInstance().getProperty("HIUPassword"))
                .build();
    }

    // START of Sub-Objects List ---------------------------------------------------------------------------->
    public static DateRange dateRange() {
        return DateRange.builder().from("1992-04-03T10:05:26.352")
                .to("2020-07-30T10:05:26.352")
                .build();
    }

    public static Permission permission() {
        return Permission.builder()
                .accessMode(TestDataLiterals.VIEW)
                .dataEraseAt("2020-10-30T12:30:00.352")
                .dateRange(dateRange())
                .build();
    }

    public static Patient patient(String patientId) {
        return Patient.builder().id(patientId)
                .build();
    }

    public static Purpose purpose() {
        return Purpose.builder().code(PurposeType.PUBHLTH.toString()).build();
    }

    // END of Sub-Objects List ---------------------------------------------------------------------------->

    public static CreateConsentRequest createConsentRequestPayload(String id) {
        List<String> hiTypes = List.of(HealthInfoType.Prescription.toString());
        Consent consent = Consent.builder().patient(patient(id))
                .purpose(purpose())
                .hiTypes(hiTypes)
                .permission(permission())
                .build();
        return CreateConsentRequest.builder().consent(consent).build();
    }

    // METHODS -------------------------------------------------------------------------------------------->
    private static String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    private String dateFormatter() {

        Date currentDate = new Date();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TestDataLiterals.DATE_FORMAT);
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return formatter.format(localDateTime);
    }

}
