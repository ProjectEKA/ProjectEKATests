package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.BasePageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    BasePageObjects basePageObjects;
    AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        basePageObjects = new BasePageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), basePageObjects);
    }

    public LoginPage moveSplashScreens() {
        if (new WaitUtils().isElementPresent(driver, basePageObjects.nextButton)) {
            new WaitUtils().refreshAndwaitForElementToBeVisible(driver, basePageObjects.nextButton).click();
            new WaitUtils().refreshAndwaitForElementToBeVisible(driver, basePageObjects.nextButton).click();
            new WaitUtils().refreshAndwaitForElementToBeVisible(driver, basePageObjects.nextButton).click();
            new WaitUtils().refreshAndwaitForElementToBeVisible(driver, basePageObjects.nextButton).click();
        }
        return new LoginPage(driver);
    }
}
