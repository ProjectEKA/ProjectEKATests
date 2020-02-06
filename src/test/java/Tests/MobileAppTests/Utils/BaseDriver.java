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
import org.testng.annotations.BeforeClass;

import java.io.File;

public class BaseDriver {
    private static AppiumDriverLocalService service;
    public AppiumDriver<WebElement> driver;

    @BeforeClass
    public void setUp() {
//        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
//                .usingAnyFreePort()
//                .usingDriverExecutable(new File(
//                        "/Users/shridhk/.nvm/versions/node/v12.4.0/bin/node"))
//                .withAppiumJS(new File("/usr/local/bin/appium"))
//        );
        service = AppiumDriverLocalService.buildDefaultService();

        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "Failed to start the Appium Server");
        }
        String path = new AppUtility().getAPP();
        File app = new File(path + "/app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability( "automationName", "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 700000);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "in.org.projecteka.jataayu.ui.LauncherActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "in.org.projecteka.jataayu.debug");

        driver = new AndroidDriver<>(service.getUrl(), capabilities);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}

