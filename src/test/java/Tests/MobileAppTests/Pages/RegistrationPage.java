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
//        new WaitUtils().waitForElement(driver, registrationPageObjects.continueButton);
    }

    public void enterContactNoAndContinue() {
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.continueButton);
    }
}
