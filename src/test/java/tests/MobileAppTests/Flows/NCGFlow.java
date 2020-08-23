package tests.MobileAppTests.Flows;


import tests.MobileAppTests.Objects.BasePageObjects;
import tests.MobileAppTests.Objects.LoginPageObjects;
import tests.MobileAppTests.Utils.IRunner;
import tests.MobileAppTests.Utils.WaitUtils;
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
