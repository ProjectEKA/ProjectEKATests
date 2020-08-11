package Tests.MobileAppTests.Flows;

import Tests.MobileAppTests.Objects.BasePageObjects;
import Tests.MobileAppTests.Objects.LoginPageObjects;
import Tests.MobileAppTests.Utils.IRunner;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class NHAFlow extends WaitUtils implements IRunner {

    public NHAFlow(AppiumDriver driver) {
        super(driver);
        System.out.println("In NHA flow");
    }

    @Override
    public void moveSplashScreens(BasePageObjects basePageObjects) {
        System.out.println(driver.getPageSource());
        if (isElementPresent(basePageObjects.skipButton)) {
            System.out.println("Element is found");
            refreshAndwaitForElementToBeVisible(basePageObjects.skipButton).click();
        }
    }

    @Override
    public void login(LoginPageObjects loginPageObjects, String username, String password) {
        loginPageObjects.userName.sendKeys(username);
        loginPageObjects.password.sendKeys(password);
        loginPageObjects.loginButton.click();
        waitForElement(loginPageObjects.allowPermission).click();
    }
}
