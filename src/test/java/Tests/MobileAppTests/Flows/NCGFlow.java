package Tests.MobileAppTests.Flows;


import Tests.MobileAppTests.Objects.BasePageObjects;
import Tests.MobileAppTests.Objects.LoginPageObjects;
import Tests.MobileAppTests.Utils.BaseDriver;
import Tests.MobileAppTests.Utils.IRunner;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;

public class NCGFlow extends WaitUtils implements IRunner {

    public NCGFlow(AppiumDriver driver) {
        super(driver);
        System.out.println("In NCG flow");
    }

    @Override
    public void moveSplashScreens(BasePageObjects basePageObjects) {
        if (isElementPresent(basePageObjects.nextButton)) {
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
        }
    }

    @Override
    public void login(LoginPageObjects loginPageObjects, String username, String password) {
        loginPageObjects.userName.sendKeys(username);
        waitForElementToBeEnabled(loginPageObjects.nextButton).click();
        waitForElement(loginPageObjects.password).sendKeys(password);
        loginPageObjects.loginButton.click();
    }
}
