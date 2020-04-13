package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.ConsentArtifactPageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class ConsentArtifactPage extends WaitUtils {

    ConsentArtifactPageObjects consentArtifactPageObjects;
    AppiumDriver driver;

    public ConsentArtifactPage(AppiumDriver driver) {
        this.driver = driver;
        consentArtifactPageObjects = new ConsentArtifactPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), consentArtifactPageObjects);
        new WaitUtils().waitForElement(driver, consentArtifactPageObjects.grantButton);
    }

    public OTPPage grantConsent() {
        consentArtifactPageObjects.grantButton.click();
        return new OTPPage(driver);
    }
}
