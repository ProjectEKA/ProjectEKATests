package tests.mobiletests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tests.mobiletests.pageobjects.OTPPageObjects;
import tests.mobiletests.utils.WaitUtils;

public class OTPPage extends WaitUtils {

  OTPPageObjects otpPageObjects;

  public OTPPage(AppiumDriver driver) {
    super(driver);
    otpPageObjects = new OTPPageObjects();
    PageFactory.initElements(new AppiumFieldDecorator(driver), otpPageObjects);
    refreshAndwaitForElementToBeVisible(otpPageObjects.continueButton);
  }

  public <T> T enterOTP(T nextPage) {
    waitForElement(otpPageObjects.otpField).sendKeys("666666");
    otpPageObjects.continueButton.click();
    return nextPage;
  }

  public <T> T enterPin(T nextPage, String text) {
    waitForSpecificTextToBeDisplayed(otpPageObjects.consentPinLabel, text);
    otpPageObjects.consentPin.sendKeys("1234");
    otpPageObjects.continueButton.click();
    return nextPage;
  }
}
