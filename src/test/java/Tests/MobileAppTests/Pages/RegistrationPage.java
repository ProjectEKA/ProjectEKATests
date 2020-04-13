package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.RegistrationPageObjects;
import Tests.MobileAppTests.Utils.Gestures;
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
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.userName).sendKeys(userName);
        registrationPageObjects.password.sendKeys("Test@135");
        registrationPageObjects.firstName.sendKeys("Test");
        new Gestures().verticalSwipe(driver, registrationPageObjects.firstName);
        registrationPageObjects.lastName.sendKeys("Test");

        registrationPageObjects.genderMale.click();
        registrationPageObjects.dateOfBirth.click();

        registrationPageObjects.calendarPrev.click();
        registrationPageObjects.date.click();
        registrationPageObjects.okCalendar.click();
        registrationPageObjects.registerButton.click();

        return new SearchLinkProviderPage(driver);
    }
}
