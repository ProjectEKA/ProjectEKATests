package Tests.MobileAppTests.Flows;

import Tests.MobileAppTests.Objects.BasePageObjects;
import Tests.MobileAppTests.Utils.IRunner;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class NHAFlow implements IRunner {

    public NHAFlow(AppiumDriver driver) {
        System.out.println("In NHA flow");
    }

    @Override
    public void moveSplashScreens(BasePageObjects basePageObjects) {

    }
}
