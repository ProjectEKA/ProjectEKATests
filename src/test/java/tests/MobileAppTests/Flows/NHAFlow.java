package tests.MobileAppTests.Flows;

import tests.MobileAppTests.Objects.BasePageObjects;
import tests.MobileAppTests.Objects.LoginPageObjects;
import tests.MobileAppTests.Utils.IRunner;
import tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;

public class NHAFlow extends WaitUtils implements IRunner {

    public NHAFlow(AppiumDriver driver) {
        super(driver);
        System.out.println("In NHA flow");
    }

    @Override
    public void moveSplashScreens(BasePageObjects basePageObjects) {

        if (isElementPresent(basePageObjects.skipButton)) {
            refreshAndwaitForElementToBeVisible(basePageObjects.skipButton).click();
        }
    }

    @Override
    public void login(LoginPageObjects loginPageObjects, String username, String password) {
        loginPageObjects.userName.sendKeys(username);
        loginPageObjects.password.sendKeys(password);
        loginPageObjects.loginButton.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(driver.getPageSource());
        waitForElement(loginPageObjects.allowPermission).click();
    }
}
