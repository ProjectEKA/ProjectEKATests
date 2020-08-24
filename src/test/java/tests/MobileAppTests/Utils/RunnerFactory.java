package tests.MobileAppTests.Utils;

import tests.MobileAppTests.Flows.NCGFlow;
import tests.MobileAppTests.Flows.NHAFlow;
import io.appium.java_client.AppiumDriver;

public class RunnerFactory {
    public static IRunner runner;

    public void instantiateRunner(AppiumDriver driver) {
        String env = System.getenv("env");
        switch (env) {
            case "ncg":
                runner = new NCGFlow(driver);
                break;
            case "nhsDev":
                runner = new NHAFlow(driver);
                break;
            default:
                runner = new NHAFlow(driver);
        }
    }
}
