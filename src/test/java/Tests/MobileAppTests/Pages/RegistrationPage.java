package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.RegistrationPageObjects;
import Tests.MobileAppTests.Utils.Patient;
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

    public OTPPage enterContactNoAndContinue(String phoneNumber) {
        registrationPageObjects.mobileNo.sendKeys(phoneNumber);
        new WaitUtils().waitForElement(driver, registrationPageObjects.continueButton).click();
        return new OTPPage(driver);
    }

    public SearchLinkProviderPage enterUserDetails(Patient patient, String username) {
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.patientName).sendKeys(patient.getPatientName());
        if (patient.getGender().equals("male")) {
            registrationPageObjects.genderMale.click();
        } else if (patient.getGender().equals("female")) {
            registrationPageObjects.genderFemale.click();
        }

        registrationPageObjects.registerButton.click();
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.password).sendKeys("Test@135");
        registrationPageObjects.cmID.clear();
        registrationPageObjects.cmID.sendKeys(username);
        registrationPageObjects.confirmPassword.sendKeys("Test@135");
        registrationPageObjects.confirmRegisterButton.click();
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.confirmationMessage);
        registrationPageObjects.confirmRegisterButton.click();
        return new SearchLinkProviderPage(driver);
    }
}
