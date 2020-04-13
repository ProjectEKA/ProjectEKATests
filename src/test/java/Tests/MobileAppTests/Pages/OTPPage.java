package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.OTPPageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class OTPPage {


    OTPPageObjects otpPageObjects;
    AppiumDriver driver;

    public OTPPage(AppiumDriver driver) {
        this.driver = driver;
        otpPageObjects = new OTPPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), otpPageObjects);
        new WaitUtils().waitForElementToBeVisible(driver, otpPageObjects.continueButton);
    }

    public <T> T enterOTP(T nextPage) {
        new WaitUtils().waitForElement(driver, otpPageObjects.otpField).sendKeys("666666");
        otpPageObjects.continueButton.click();
        return nextPage;
    }

    public <T> T enterPin(T nextPage, String text) {
        new WaitUtils().waitForSpecificTextToBeDisplayed(driver, otpPageObjects.consentPinLabel, text);
        otpPageObjects.consentPin.sendKeys("1234");
        otpPageObjects.continueButton.click();
        return nextPage;
    }

}
