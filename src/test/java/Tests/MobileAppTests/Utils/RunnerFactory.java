package Tests.MobileAppTests.Utils;

import Tests.MobileAppTests.Flows.NCGFlow;
import Tests.MobileAppTests.Flows.NHAFlow;
import io.appium.java_client.AppiumDriver;

public class RunnerFactory {
    public static IRunner runner;

    public void instantiateRunner(AppiumDriver driver) {
        String env = System.getenv("env");
        switch (env) {
            case "ncg":
                runner = new NCGFlow(driver);
                break;
            case "nha":
                runner = new NHAFlow(driver);
                break;
            default:
                runner = new NHAFlow(driver);
        }
    }
}
