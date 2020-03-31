package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.RegistrationPageObjects;
import Tests.MobileAppTests.Utils.Gestures;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class RegistrationPage {
    RegistrationPageObjects registrationPageObjects;
    AppiumDriver driver;

    public RegistrationPage(AppiumDriver driver) {
        this.driver = driver;
        registrationPageObjects = new RegistrationPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), registrationPageObjects);
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.continueButton);
    }

    public RegistrationPage enterContactNoAndContinue() {
        registrationPageObjects.mobileNo.sendKeys("9999999999");
        registrationPageObjects.continueButton.click();
        return this;
    }

    public RegistrationPage enterOTP() {
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.otpField).sendKeys("666666");
        ;
        registrationPageObjects.continueButton.click();
        return this;
    }

    public SearchLinkProviderPage enterUserDetails() {
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.userName).sendKeys("TestUser" + generateRandomNo());
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

    private String generateRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return String.valueOf(10000 + r.nextInt(20000));
    }


}
