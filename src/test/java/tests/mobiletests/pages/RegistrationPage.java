package tests.mobiletests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tests.mobiletests.pageobjects.RegistrationPageObjects;
import tests.mobiletests.utils.Patient;
import tests.mobiletests.utils.WaitUtils;

public class RegistrationPage extends WaitUtils {
  RegistrationPageObjects registrationPageObjects;

  public RegistrationPage(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
    registrationPageObjects = new RegistrationPageObjects();
    PageFactory.initElements(new AppiumFieldDecorator(driver), registrationPageObjects);
    refreshAndwaitForElementToBeVisible(registrationPageObjects.continueButton);
  }

  public OTPPage enterContactNoAndContinue(String phoneNumber) {
    registrationPageObjects.mobileNo.sendKeys(phoneNumber);
    waitForElement(registrationPageObjects.continueButton).click();
    return new OTPPage(driver);
  }

  public HomePage enterUserDetails(Patient patient, String username) {
    waitForElementToBeVisible(registrationPageObjects.firstName).sendKeys(patient.getFirstName());
    if (patient.getGender().equals("male")) {
      registrationPageObjects.genderMale.click();
    } else if (patient.getGender().equals("female")) {
      registrationPageObjects.genderFemale.click();
    }

    registrationPageObjects.registerButton.click();
    waitForElementToBeVisible(registrationPageObjects.password).sendKeys("Test@135");
    registrationPageObjects.cmID.clear();
    registrationPageObjects.cmID.sendKeys(username);
    registrationPageObjects.confirmPassword.sendKeys("Test@135");
    registrationPageObjects.confirmRegisterButton.click();
    waitForElementToBeVisible(registrationPageObjects.confirmationMessage);
    registrationPageObjects.confirmRegisterButton.click();
    return new HomePage(driver);
  }
}
