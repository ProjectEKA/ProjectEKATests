package tests.mobiletests.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import tests.apitests.helpers.PropertiesCache;
import tests.mobiletests.pages.BasePage;

import java.io.File;
import java.io.FileFilter;

public class BaseDriver {
  private static String path = null;
  public AppiumDriver<WebElement> driver;
  private AppiumDriverLocalService service;

  @BeforeClass
  public void setUp() {
    //        service =
    //            AppiumDriverLocalService.buildService(
    //                new AppiumServiceBuilder()
    //                    .usingAnyFreePort()
    //                    .usingDriverExecutable(
    //                        new
    //     File("/Users/gopishankarharidas/.nvm/versions/node/v10.16.3/bin/node"))
    //                    .withAppiumJS(
    //                        new
    //     File("/Users/gopishankarharidas/.nvm/versions/node/v10.16.3/bin/appium")));

    service = AppiumDriverLocalService.buildDefaultService();

    service.start();

    if (service == null || !service.isRunning()) {
      throw new AppiumServerHasNotBeenStartedLocallyException("Failed to start the Appium Server");
    }
    path = AppUtility.getInstance().getPath();
  }

  @BeforeMethod
  public void beforeMethod() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("deviceName", "Android Emulator");
    capabilities.setCapability("app", getAppFromPath().getAbsolutePath());
    capabilities.setCapability("automationName", "UIAutomator2");
    capabilities.setCapability("autoGrantPermissions", "true");
    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 700000);

    capabilities.setCapability(
        AndroidMobileCapabilityType.APP_ACTIVITY,
        PropertiesCache.getInstance().getProperty("APP_ACTIVITY"));
    capabilities.setCapability(
        AndroidMobileCapabilityType.APP_PACKAGE,
        PropertiesCache.getInstance().getProperty("APP_PACKAGE"));

    driver = new AndroidDriver<>(service.getUrl(), capabilities);

    new RunnerFactory().instantiateRunner(driver);
    handleSplashScreen();
  }

  @AfterMethod
  public void quitApp() {
    if (driver != null) {
      driver.quit();
    }
  }

  @AfterClass
  public void afterClass() {
    if (service != null) {
      service.stop();
    }
  }

  private File getAppFromPath() {
    File dir = new File(path);
    FileFilter fileFilter = new WildcardFileFilter("*.apk");
    File[] files = dir.listFiles(fileFilter);
    return new File(String.valueOf(files[0]));
  }

  private void handleSplashScreen() {
    new BasePage(driver).moveSplashScreens();
  }
}
