package Tests.MobileAppTests.Pages;

import Tests.APITests.APIUtils.PropertiesCache;
import Tests.MobileAppTests.Objects.LoginPageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    LoginPageObjects loginPageObjects;
    AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        loginPageObjects = new LoginPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObjects);
        new WaitUtils().waitForElement(driver, loginPageObjects.userName);
    }

    public HomePage loginUser() {
        String userName = PropertiesCache.getInstance().getProperty("mobileUser");
        String password = PropertiesCache.getInstance().getProperty("mobilePassword");

        loginPageObjects.userName.sendKeys(userName);
        new WaitUtils().waitForElementToBeEnabled(driver, loginPageObjects.nextButton).click();

        new WaitUtils().waitForElement(driver, loginPageObjects.password).sendKeys(password);
        loginPageObjects.loginButton.click();

        return new HomePage(driver);
    }

    public HomePage loginUser(String username, String password) {
        loginPageObjects.userName.sendKeys(username);
        new WaitUtils().waitForElementToBeEnabled(driver, loginPageObjects.nextButton).click();

        new WaitUtils().waitForElement(driver, loginPageObjects.password).sendKeys(password);
        loginPageObjects.loginButton.click();

        return new HomePage(driver);

    }

    public RegistrationPage navigateToCreateAccount() {
        driver.hideKeyboard();
        loginPageObjects.registerButton.click();
        return new RegistrationPage(driver);
    }
}
