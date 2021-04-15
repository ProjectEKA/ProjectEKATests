package tests.apitests.gateway;

import tests.apitests.gateway.models.*;
import tests.apitests.helpers.PropertiesCache;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static tests.apitests.helpers.TestDataLiterals.DATE_FORMAT;

public class TestBuilders {
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
                        .purpose("KYC")
                        .authMode("DIRECT")
                        .requester(AuthRequester.builder().type("HEALTH_LOCKER").id("10000010").build()).build())
                .build();
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
