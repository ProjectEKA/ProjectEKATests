package tests.mobiletests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tests.mobiletests.pageobjects.ConsentArtifactPageObjects;
import tests.mobiletests.utils.WaitUtils;

public class ConsentArtifactPage extends WaitUtils {

  ConsentArtifactPageObjects consentArtifactPageObjects;

  public ConsentArtifactPage(AppiumDriver driver) {
    super(driver);
    consentArtifactPageObjects = new ConsentArtifactPageObjects();
    PageFactory.initElements(new AppiumFieldDecorator(driver), consentArtifactPageObjects);
    waitForElement(consentArtifactPageObjects.grantButton);
  }

  public OTPPage grantConsent() {
    consentArtifactPageObjects.grantButton.click();
    return new OTPPage(driver);
  }
}
