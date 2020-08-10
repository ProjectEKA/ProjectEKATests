package Tests.MobileAppTests.Utils;


import Tests.MobileAppTests.Pages.BasePage;
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

import java.io.File;
import java.io.FileFilter;

public class BaseDriver {
    private static String path = null;
    public AppiumDriver<WebElement> driver;
    private AppiumDriverLocalService service;

    @BeforeClass
    public void setUp() {
        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                .usingAnyFreePort()
                .usingDriverExecutable(new File(
                        "/Users/shridhk/.nvm/versions/node/v12.4.0/bin/node")).withAppiumJS(

                        new File("/usr/local/bin/appium")));

//        service =AppiumDriverLocalService.buildDefaultService();

        service.start();

        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException(
                    "Failed to start the Appium Server");
        }
        path = AppUtility.getInstance().getPath();
    }

    @BeforeMethod
    public void beforeMethod() {
        File dir = new File(path);
        FileFilter fileFilter = new WildcardFileFilter("*.apk");
        File[] files = dir.listFiles(fileFilter);
        File app = new File(String.valueOf(files[0]));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 700000);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "in.projecteka.jataayu.LauncherActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "in.projecteka.jataayu.debug");
//        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "in.ndhm.phr.LauncherActivity");
//        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "in.ndhm.phr.debug");
        driver = new AndroidDriver<>(service.getUrl(), capabilities);

        new RunnerFactory().instantiateRunner(driver);
        handleSplashScreen();
    }

    private void handleSplashScreen() {
        new BasePage(driver).moveSplashScreens();
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

