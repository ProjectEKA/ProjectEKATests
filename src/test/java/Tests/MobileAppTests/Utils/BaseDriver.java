package Tests.MobileAppTests.Utils;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;

public class BaseDriver {
    public AppiumDriver<WebElement> driver;
    private AppiumDriverLocalService service;
    private static String path = null;

    @BeforeClass
    public void setUp() {
//        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//                .usingAnyFreePort()
//                .usingDriverExecutable(new File(
//                        "/Users/shridhk/.nvm/versions/node/v12.4.0/bin/node"))
//                .withAppiumJS(new File("/usr/local/bin/appium")));

        service = AppiumDriverLocalService.buildDefaultService();

        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "Failed to start the Appium Server");
        }
        path = AppUtility.getInstance().getPath();
    }

    @BeforeMethod
    public void beforeMethod() {

        File app = new File(path + "/app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 700000);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "in.projecteka.jataayu.ui.LauncherActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "in.projecteka.jataayu.debug");
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
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
}

