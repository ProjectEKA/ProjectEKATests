package tests.mobiletests.flows;

import io.appium.java_client.AppiumDriver;
import tests.mobiletests.pageobjects.BasePageObjects;
import tests.mobiletests.pageobjects.LoginPageObjects;
import tests.mobiletests.utils.IRunner;
import tests.mobiletests.utils.WaitUtils;

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
  }
}
