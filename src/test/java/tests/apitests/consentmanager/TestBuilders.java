package tests.apitests.consentmanager;

import tests.apitests.consentmanager.models.*;
import tests.apitests.consentmanager.models.fetchData.ConsentRequestFromPHRHIUPayLoad;
import tests.apitests.consentmanager.models.fetchData.FetchDataUsingPHRHIUPayLoad;
import tests.apitests.consentmanager.models.grantconsent.*;
import tests.apitests.consentmanager.models.recoverhealthid.*;
import tests.apitests.consentmanager.models.shareProfile.HIPDetails;
import tests.apitests.consentmanager.models.shareProfile.PatientDetails;
import tests.apitests.consentmanager.models.shareProfile.ShareProfilePayLoad;
import tests.apitests.consentmanager.models.shareProfile.VerifiedIdentifiers;
import tests.apitests.helpers.PropertiesCache;
import tests.apitests.helpers.TestDataLiterals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static tests.apitests.helpers.TestDataLiterals.*;
import static tests.apitests.helpers.models.GenderType.F;
import static tests.apitests.helpers.models.HealthInfoType.Prescription;
import static tests.apitests.helpers.models.ProvidersList.MAX;
import static tests.apitests.helpers.models.PurposeType.PUBHLTH;

public class TestBuilders {

    public static LoginPatient loginPayload() {
        return LoginPatient.builder()
                .username(PropertiesCache.getInstance().getProperty("username"))
                .password(PropertiesCache.getInstance().getProperty("password"))
                .grantType(GRANT_TYPE_PASSWORD)
                .build();
    }

    public static LoginWithRefreshToken loginRefreshTokenPayload(String refreshToken) {
        return LoginWithRefreshToken.builder()
                .username(PropertiesCache.getInstance().getProperty("username"))
                .grantType(GRANT_TYPE_REFRESH_TOKEN)
                .refreshToken(refreshToken)
                .build();
    }

    public static ConsentPIN consentPINPayload() {
        return ConsentPIN.builder().pin(TEST_CONSENT_PIN).build();
    }

    public static VerifyConsentPIN verifyUpdatePINPayload() {
        return VerifyConsentPIN.builder()
                .pin(TEST_CONSENT_PIN)
                .requestId(generateUUID())
                .scope(TestDataLiterals.UPDATE_CONSENT_PIN_SCOPE)
                .build();
    }

    public static VerifyConsentPIN grantPINPayload() {
        return VerifyConsentPIN.builder()
                .pin(TEST_CONSENT_PIN)
                .requestId(generateUUID())
                .scope(TestDataLiterals.GRANT_CONSENT_PIN_SCOPE)
                .build();
    }

    public static VerifyConsentPIN revokePINPayload() {
        return VerifyConsentPIN.builder()
                .pin(TEST_CONSENT_PIN)
                .requestId(generateUUID())
                .scope(TestDataLiterals.REVOKE_CONSENT_PIN_SCOPE)
                .build();
    }

    public static UpdatePassword updatePasswordPayload() {
        return UpdatePassword.builder()
                .newPassword("Test135@")
                .oldPassword(PropertiesCache.getInstance().getProperty("password"))
                .build();
    }

    public static VerifyOTP verifyOTPPayload(String sessionId) {
        return VerifyOTP.builder().sessionId(sessionId).value(TEST_RECEIVED_OTP).build();
    }

    public static VerifyOTP.VerifyOTPBuilder verifyOTP() {
        return VerifyOTP.builder().value(TEST_RECEIVED_OTP);
    }

    public static ResetPasswordInit resetPasswordInitPayload() {
        return ResetPasswordInit.builder()
                .username(PropertiesCache.getInstance().getProperty("username"))
                .build();
    }

    public static ResetPassword resetPasswordPayload() {
        return ResetPassword.builder().password("Test135@").build();
    }

    public static ResetConsentPIN resetPINPayload() {
        return ResetConsentPIN.builder().pin(TEST_CONSENT_PIN).build();
    }

    // START of Sub-Objects List
    // ---------------------------------------------------------------------------->
    // grant consent-request
    public static Frequency frequency() {
        return Frequency.builder()
                .value(FREQUENCY_VALUE)
                .unit(FREQUENCY_HOUR)
                .repeats(FREQUENCY_REPEATS)
                .build();
    }

    public static DateRange dateRange() {
        return DateRange.builder()
                .from("1992-04-03T10:05:26.352")
                .to("2020-07-30T10:05:26.352")
                .build();
    }

    public static Permission permission() {
        return Permission.builder()
                .accessMode(VIEW)
                .dataEraseAt("2020-10-30T12:30:00.352")
                .dateRange(dateRange())
                .frequency(frequency())
                .build();
    }

    public static Hip hip() {
        return Hip.builder().id(MAX.getValue()).build();
    }

    public static tests.apitests.consentmanager.models.grantconsent.Purpose purpose() {
        return Purpose.builder().code(PUBHLTH.toString()).build();
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
        return Name.builder().first("navjot").middle("").last("singh").build();
    }

    public static DateOfBirth dateOfBirth() {
        return DateOfBirth.builder().year(2001).build();
    }

    public static VerifiedIdentifier verifiedIdentifiers() {
        return VerifiedIdentifier.builder().type(MOBILE).value("+91-7777777777").build();
    }

    public static UnverifiedIdentifier unverifiedIdentifiers() {
        return UnverifiedIdentifier.builder().type(PMJAY_KEY).value("PTEST20NS").build();
    }

    // END of Sub-Objects List
    // ---------------------------------------------------------------------------->

    public static GrantConsent grantConsentPayload() {
        List<String> hiTypes = List.of(Prescription.toString());
        Consent consent =
                Consent.builder()
                        .careContexts(List.of(careContext1(), careContext2()))
                        .purpose(purpose())
                        .hiTypes(hiTypes)
                        .hip(hip())
                        .permission(permission())
                        .build();
        return GrantConsent.builder().consents(List.of(consent)).build();
    }

    public static ConsentRequestFromPHRHIUPayLoad consentRequestFromPHRHIUPayLoad(){
        List<String> l=new ArrayList<>();
        l.add("IN0410000183");
        return ConsentRequestFromPHRHIUPayLoad.builder()
                .hipIds(l)
                .reloadConsent(false).build();
    }

    public static FetchDataUsingPHRHIUPayLoad fetchDataUsingPHRHIUPayLoad(String id){
        List<String> l=new ArrayList<>();
        l.add(id);
        return FetchDataUsingPHRHIUPayLoad.builder()
                .limit(1)
                .offset(0)
                .requestIds(l).build();
    }

    public static ShareProfilePayLoad shareProfilePayLoad() {
        return ShareProfilePayLoad.builder()
                .hipDetails(HIPDetails.builder().code("12345").hipId("IN0410000183").build())
                .patientDetails(PatientDetails.builder()
                        .aadhaarVerified(false)
                        .dateOfBirth(DateOfBirth.builder().date(0).month(0).year(1996).build())
                        .districtCode("632")
                        .districtName("North And Middle Andaman")
                        .gender("M")
                        .hasTransactionPin(true)
                        .healthId(PropertiesCache.getInstance().getProperty("username"))
                        .id("67-6206-0607-2531")
                        .name(Name.builder().first("Mrityunjay").last("Dubey").build())
                        .stateCode("35")
                        .stateName("Andaman And Nicobar Islands")
                        .verifiedIdentifiers(List.of(new VerifiedIdentifiers[]{
                                VerifiedIdentifiers.builder().type(MOBILE).value("918904653362").build(),
                                VerifiedIdentifiers.builder().type("NDHM_HEALTH_NUMBER").value("67-6206-0607-2531").build()}))
                        .build())
                .requestId(generateUUID()).build();
    }


//    public static LockerRequestPayLoad lockerRequestPayLoad() {
//        String date = dateFormatter();
//        String from = date.replace("2021", "2000");
//        String to = date.replace("2021", "2022");
//        return LockerRequestPayLoad.builder()
//                .authorization(AuthorizationLocker.builder()
//                        .generatePerpetualToken("true")
//                        .requestId(generateUUID()).build())
//                .autoApproval(Autoapproval.builder()
//                        .hiu(HIU.builder().id("10000010").build())
//                        .includedSources(List.of(IncludedSourcesAutoApproval.builder()
//                                .hiTypes(List.of("DiagnosticReport",
//                                        "Prescription",
//                                        "ImmunizationRecord",
//                                        "DischargeSummary",
//                                        "OPConsultation",
//                                        "HealthDocumentRecord",
//                                        "WellnessRecord"))
//                                .period(Period.builder().from(from).to(to).build())
//                                .purpose(tests.apitests.gateway.models.Purpose.builder().code("PATRQT").text("Self Requested").build()).build()))
//                        .isApplicableForAllHIPs(true).build())
//                .subscription(SubscriptionLocker.builder()
//                        .includedSources(List.of(IncludeSourcesSubscription.builder()
//                                .categories(List.of("LINK"))
//                                .hiTypes(List.of("DiagnosticReport",
//                                        "Prescription",
//                                        "ImmunizationRecord",
//                                        "DischargeSummary",
//                                        "OPConsultation",
//                                        "HealthDocumentRecord",
//                                        "WellnessRecord"))
//                                .period(Period.builder().from(from).to(to).build())
//                                .purpose(tests.apitests.gateway.models.Purpose.builder().code("PATRQT").text("Self Requested").build()).build()))
//                        .isApplicableForAllHIPs(true)
//                        .requestId(generateUUID()).build()).build();
//    }

    public static authorizationRequest grantAuthPayLoad() {
        return authorizationRequest.builder().generatePerpetualToken("true").build();
    }

    public static RecoverHealthID recoverHealthIDPayload() {
        return RecoverHealthID.builder()
                .name(name())
                .gender(F.toString())
                .dateOfBirth(dateOfBirth())
                .verifiedIdentifiers(List.of(verifiedIdentifiers()))
                .unverifiedIdentifiers(List.of(unverifiedIdentifiers()))
                .build();
    }

    public static RevokeConsent revokeConsentPayload(String consentArtefactId) {
        return RevokeConsent.builder().consents(List.of(consentArtefactId)).build();
    }

    public static DiscoverProvider discoverProviderPayload() {
        return DiscoverProvider.builder()
                .requestId(generateUUID())
                .unverifiedIdentifiers(List.of())
                .hip(hip())
                .build();
    }

    public static Logout logoutSessionPayload(String refreshToken) {
        return Logout.builder().refreshToken(refreshToken).build();
    }

    // METHODS ---------------------------------------------------------------------------->
    private static String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    private static String dateFormatter() {

        Date currentDate = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime localDateTime =
                currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return formatter.format(localDateTime);
    }
}
