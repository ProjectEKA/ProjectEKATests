package tests.MobileAppTests.Pages;

import tests.MobileAppTests.Objects.RegistrationPageObjects;
import tests.MobileAppTests.Utils.Patient;
import tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

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
        waitForElementToBeVisible(registrationPageObjects.patientName).sendKeys(patient.getPatientName());
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
