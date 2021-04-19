package tests.apitests.gateway;

import tests.apitests.gateway.models.*;
import tests.apitests.gateway.models.addCareContext.AddCareContextPayLoad;
import tests.apitests.gateway.models.addCareContext.CareContextAdd;
import tests.apitests.gateway.models.addCareContext.Link;
import tests.apitests.gateway.models.addCareContext.PatientCarecontext;
import tests.apitests.helpers.PropertiesCache;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static tests.apitests.helpers.TestDataLiterals.DATE_FORMAT;

public class TestBuilders {
    private static final String ACCESSTOKEN = "eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiI2Ny02MjA2LTA2MDctMjUzMSIsInJlcXVlc3RlclR5cGUiOiJISVAiLCJyZXF1ZXN0ZXJJZCI6IlRFU1RfQlJJREdFX1FBIiwicGF0aWVudElkIjoiNjctNjIwNi0wNjA3LTI1MzEiLCJzZXNzaW9uSWQiOiJmMzAyZmQyNC1kMWEwLTRmYWQtYTViMi1lYTljZjFhNjg2M2IiLCJleHAiOjE2MTg2NDI5MDIsImlhdCI6MTYxODU1NjUwMn0.Cuqs4e55il86lf8JTOM9LhQhbxN0fUP7lD7LpnPag6Y_xRLUi7QoYyJfU8WMnC0fHGnMXeCwO3hgORsgatzWq_RfpL2580d9mRps1ZeYtdfddYzvxG6de_FxM_soClqb5rA8vLcpHOqO0H-5E4GFDN4W-VyWH2tZmftoyArJb6qE21s89-PpiYxI72TXJrPn4euZ3NWoh-09c09tRMrMlOUcnKnwvGuASFO_zxXnAtQUvW3WZIwS9ELcqqOprT4o-fAY1m7TexGYClvTcV7piSvD-dqWJmcRxlSiy-_GxXEH5tZbnhYoAEeLCa3kulVIpUVkicTkwbQ2a8Mb59hE4w";

    public static LoginPayLoad gatewayPayload() {
        return LoginPayLoad.builder()
                .clientId("TEST_BRIDGE_QA")
                .clientSecret(System.getenv("clientSecret"))
                .grantType("client_credentials")
                .build();
    }

    public static AuthRequestPayload authRequestPayload() {
        String uid = generateUUID();
        String date = dateFormatter();
        return AuthRequestPayload.builder()
                .requestId(uid)
                .timestamp(date)
                .query(AuthQuery.builder().id(PropertiesCache.getInstance().getProperty("username"))
                        .purpose("KYC_AND_LINK")
                        .authMode("DIRECT")
                        .requester(AuthRequester.builder().type("HIP").id("TEST_BRIDGE_QA").build()).build())
                .build();
    }

    public static AddCareContextPayLoad addCareContextPayLoad(){
        return AddCareContextPayLoad.builder()
                .requestId(generateUUID())
                .timestamp(dateFormatter())
                .link(Link.builder()
                        .accessToken(ACCESSTOKEN)
                        .patient(PatientCarecontext.builder()
                                .referenceNumber("001")
                                .display("001")
                                .careContexts(List.of(CareContextAdd.builder()
                                        .referenceNumber("1")
                                        .display("1").build())).build()).build()).build();
    }

    public static SubscriptionRequestPayLoad subscriptionRequestPayLoad() {
        String uid = generateUUID();
        String date = dateFormatter();
        String from = date.replace("2021", "2000");
        String to = date.replace("2021", "2022");
        String[] cat = new String[1];
        cat[0]="DATA";
//        cat[1]="LINK";
        return SubscriptionRequestPayLoad.builder()
                .requestId(uid)
                .timestamp(date)
                .subscription(Subscription.builder()
                        .purpose(Purpose.builder().text("Care Management").code("CAREMGT").build())
                        .patient(Patient.builder().id(PropertiesCache.getInstance().getProperty("username")).build())
                        .hiu(HIU.builder().id("10000010").build())
                        .categories(Categories.builder().categories(List.of(cat)).build())
                        .period(Period.builder().from(from).to(to).build()).build()).build();
    }

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
