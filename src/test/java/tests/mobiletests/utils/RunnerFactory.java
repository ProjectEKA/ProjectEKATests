package tests.mobiletests.utils;

import io.appium.java_client.AppiumDriver;
import tests.mobiletests.flows.NHAFlow;

public class RunnerFactory {
  public static IRunner runner;

  public void instantiateRunner(AppiumDriver driver) {
    String env = System.getenv("env");
    switch (env) {
      case "nhsDev":
        runner = new NHAFlow(driver);
        break;
      default:
        runner = new NHAFlow(driver);
    }
  }
}
