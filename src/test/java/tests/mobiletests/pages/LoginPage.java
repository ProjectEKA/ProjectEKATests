package tests.mobiletests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tests.apitests.helpers.PropertiesCache;
import tests.mobiletests.pageobjects.LoginPageObjects;
import tests.mobiletests.utils.WaitUtils;

public class LoginPage extends WaitUtils {
  LoginPageObjects loginPageObjects;

  public LoginPage(AppiumDriver driver) {
    super(driver);
    loginPageObjects = new LoginPageObjects();
    PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObjects);
    waitForElement(loginPageObjects.userName);
  }

  public HomePage loginUser() {
    String userName = PropertiesCache.getInstance().getProperty("mobileUser");
    String password = PropertiesCache.getInstance().getProperty("mobilePassword");
    runner.login(loginPageObjects, userName, password);
    return new HomePage(driver);
  }

  public RegistrationPage navigateToCreateAccount() {
    driver.hideKeyboard();
    loginPageObjects.registerButton.click();
    return new RegistrationPage(driver);
  }
}
