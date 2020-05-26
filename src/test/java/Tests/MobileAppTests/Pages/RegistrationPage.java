package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.RegistrationPageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    RegistrationPageObjects registrationPageObjects;
    AppiumDriver driver;

    public RegistrationPage(AppiumDriver driver) {
        this.driver = driver;
        registrationPageObjects = new RegistrationPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), registrationPageObjects);
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.continueButton);
    }

    public OTPPage enterContactNoAndContinue() {
        registrationPageObjects.mobileNo.sendKeys("9999999999");
        new WaitUtils().waitForElement(driver, registrationPageObjects.continueButton).click();
        return new OTPPage(driver);
    }

    public SearchLinkProviderPage enterUserDetails(String userName) {
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.patientName).sendKeys("John Doe"); //
        registrationPageObjects.genderMale.click();
        registrationPageObjects.registerButton.click();
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.password).sendKeys("Test@135");
        registrationPageObjects.userName.clear();
        registrationPageObjects.userName.sendKeys(userName);
        registrationPageObjects.confirmPassword.sendKeys("Test@135");
        registrationPageObjects.confirmRegisterButton.click();
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.confirmationMessage);
        registrationPageObjects.confirmRegisterButton.click();
        return new SearchLinkProviderPage(driver);
    }
}
