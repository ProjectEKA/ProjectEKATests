package Tests.MobileAppTests.Pages;

import Tests.APITests.APIUtils.PropertiesCache;
import Tests.MobileAppTests.Objects.LoginPageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

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

        loginPageObjects.userName.sendKeys(userName);
        waitForElementToBeEnabled(loginPageObjects.nextButton).click();
        waitForElement(loginPageObjects.password).sendKeys(password);

        //TODO - To enable the login button. Temp fix to pass test
        loginPageObjects.showPasswordButton.click();
        loginPageObjects.loginButton.click();

        return new HomePage(driver);
    }

    public HomePage loginUser(String username, String password) {
        loginPageObjects.userName.sendKeys(username);
        waitForElementToBeEnabled(loginPageObjects.nextButton).click();
        waitForElement(loginPageObjects.password).sendKeys(password);
        loginPageObjects.loginButton.click();
        return new HomePage(driver);
    }

    public RegistrationPage navigateToCreateAccount() {
        driver.hideKeyboard();
        loginPageObjects.registerButton.click();
        return new RegistrationPage(driver);
    }
}
