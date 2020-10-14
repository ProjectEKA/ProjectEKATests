package tests.mobiletests.utils;

import io.appium.java_client.AppiumDriver;
import tests.mobiletests.flows.NCGFlow;
import tests.mobiletests.flows.NHAFlow;

public class RunnerFactory {
  public static IRunner runner;

  public void instantiateRunner(AppiumDriver driver) {
    System.out.println("ENVIRONMENT ---> " + System.getenv("env"));
    System.out.println("DRIVER ---> " + driver.getClass().getName());
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
