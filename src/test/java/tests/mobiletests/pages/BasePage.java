package tests.mobiletests.pages;

import tests.mobiletests.pageobjects.BasePageObjects;
import tests.mobiletests.utils.WaitUtils;
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
