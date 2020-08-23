package Tests.APITests.Consent_Manager;

import Tests.APITests.Consent_Manager.Models.*;
import Tests.APITests.Consent_Manager.Models.GrantConsent.*;
import Tests.APITests.Consent_Manager.Models.RecoverHealthID.*;
import Tests.APITests.Helpers.PropertiesCache;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestBuildersCM {

    public static LoginPatient loginPayload() {
        return LoginPatient.builder().username(PropertiesCache.getInstance().getProperty("username"))
                .password(PropertiesCache.getInstance().getProperty("password"))
                .grantType("password")
                .build();
    }

    public static LoginWithRefreshToken loginRefreshTokenPayload(String refreshToken) {
        return LoginWithRefreshToken.builder().username(PropertiesCache.getInstance().getProperty("username"))
                .grantType("refresh_token")
                .refreshToken(refreshToken)
                .build();
    }

    public static ConsentPIN consentPINPayload() {
        return ConsentPIN.builder().pin("1234")
                .build();
    }

    public static VerifyConsentPIN verifyUpdatePINPayload() {
        return VerifyConsentPIN.builder().pin("1234")
                .requestId(generateUUID())
                .scope("profile.changepin")
                .build();
    }

    public static VerifyConsentPIN grantPINPayload() {
        return VerifyConsentPIN.builder().pin("1234")
                .requestId(generateUUID())
                .scope("consentrequest.approve")
                .build();
    }

    public static VerifyConsentPIN revokePINPayload() {
        return VerifyConsentPIN.builder().pin("1234")
                .requestId(generateUUID())
                .scope("consent.revoke")
                .build();
    }

    public static UpdatePassword updatePasswordPayload() {
        return UpdatePassword.builder().newPassword("Test135@")
                .oldPassword("Test135@")
                .build();
    }

    public static VerifyOTP verifyOTPPayload(String sessionId) {
        return VerifyOTP.builder().sessionId(sessionId)
                .value("666666")
                .build();
    }

    public static ResetPasswordInit resetPasswordInitPayload() {
        return ResetPasswordInit.builder().username("apidemotest30@ncg")
                .build();
    }

    public static ResetPassword resetPasswordPayload() {
        return ResetPassword.builder().password("Test135@")
                .build();
    }

    public static ResetConsentPIN resetPINPayload() {
        return ResetConsentPIN.builder().pin("1234")
                .build();
    }

    // START of Sub-Objects List ---------------------------------------------------------------------------->
    // grant consent-request
    public static Frequency frequency() {
        return Frequency.builder().value(1)
                .unit("HOUR").repeats(0)
                .build();
    }

    public static DateRange dateRange() {
        return DateRange.builder().from("1992-04-03T10:05:26.352")
                .to("2020-07-30T10:05:26.352")
                .build();
    }

    public static Permission permission() {
        return Permission.builder()
                .accessMode("VIEW")
                .dataEraseAt("2020-10-30T12:30:00.352")
                .dateRange(dateRange())
                .frequency(frequency())
                .build();
    }

    public static Hip hip() {
        return Hip.builder().id("10000005").build();
    }

    public static Purpose purpose() {
        return Purpose.builder().code("PUBHLTH").build();
    }

    public static CareContext careContext1() {
        return CareContext.builder()
                .careContextReference("NCP1007")
                .patientReference("RVH1002")
                .build();
    }

    public static CareContext careContext2() {
        return CareContext.builder()
                .careContextReference("RV-MHD-01.17.0024")
                .patientReference("RVH1002")
                .build();
    }

    // recover health-id
    public static Name name() {
        return Name.builder().first("navjot").middle("").last("singh")
                .build();
    }

    public static DateOfBirth dateOfBirth() {
        return DateOfBirth.builder().year(2001)
                .build();
    }

    public static VerifiedIdentifier verifiedIdentifiers() {
        return VerifiedIdentifier.builder().type("MOBILE").value("+91-7777777777")
                .build();
    }

    public static UnverifiedIdentifier unverifiedIdentifiers() {
        return UnverifiedIdentifier.builder().type("ABPMJAYID").value("PTEST20NS")
                .build();
    }

    // END of Sub-Objects List ---------------------------------------------------------------------------->

    public static GrantConsent grantConsentPayload() {
        List<String> hiTypes = List.of("Prescription");
        Consent consent = Consent.builder().careContexts(List.of(careContext1(), careContext2()))
                .purpose(purpose())
                .hiTypes(hiTypes)
                .hip(hip())
                .permission(permission())
                .build();
        return GrantConsent.builder().consents(List.of(consent)).build();
    }

    public static RecoverHealthID recoverHealthIDPayload() {
        return RecoverHealthID.builder().name(name()).gender("F").dateOfBirth(dateOfBirth())
                .verifiedIdentifiers(List.of(verifiedIdentifiers()))
                .unverifiedIdentifiers(List.of(unverifiedIdentifiers()))
                .build();
    }

    public static RevokeConsent revokeConsentPayload(String consentArtefactId) {
        return RevokeConsent.builder().consents(List.of(consentArtefactId))
                .build();
    }

    public static DiscoverProvider discoverProviderPayload() {
        return DiscoverProvider.builder().requestId(generateUUID())
                .unverifiedIdentifiers(List.of())
                .hip(hip())
                .build();
    }

    public static Logout logoutSessionPayload(String refreshToken) {
        return Logout.builder().refreshToken(refreshToken)
                .build();
    }



    // METHODS ---------------------------------------------------------------------------->
    private static String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    private String dateFormatter() {

        Date currentDate = new Date();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return formatter.format(localDateTime);
    }


}
