package tests.mobiletests.flows;

import io.appium.java_client.AppiumDriver;
import tests.mobiletests.pageobjects.BasePageObjects;
import tests.mobiletests.pageobjects.LoginPageObjects;
import tests.mobiletests.utils.IRunner;
import tests.mobiletests.utils.WaitUtils;

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
