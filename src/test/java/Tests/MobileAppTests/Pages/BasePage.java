package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.BasePageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
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
//        runner.moveSplashScreens(basePageObjects);
        if (isElementPresent(basePageObjects.nextButton)) {
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
        }
        return new LoginPage(driver);
    }
}
