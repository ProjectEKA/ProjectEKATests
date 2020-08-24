package tests.MobileAppTests.Pages;

import tests.MobileAppTests.Objects.BasePageObjects;
import tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends WaitUtils {

    BasePageObjects basePageObjects;

    public BasePage(AppiumDriver driver) {
        super(driver);
        basePageObjects = new BasePageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), basePageObjects);
    }

    public LoginPage moveSplashScreens() {
        runner.moveSplashScreens(basePageObjects);
        return new LoginPage(driver);
    }
}