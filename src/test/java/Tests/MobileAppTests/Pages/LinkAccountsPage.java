package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.LinkAccountsPageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LinkAccountsPage {

    LinkAccountsPageObjects linkAccountsPageObjects;
    AppiumDriver driver;

    public LinkAccountsPage(AppiumDriver driver) {
        this.driver = driver;
        linkAccountsPageObjects = new LinkAccountsPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), linkAccountsPageObjects);
        new WaitUtils().waitForElement(driver, linkAccountsPageObjects.patientName);
    }

    public String getPatientName() {
        return linkAccountsPageObjects.patientName.getText();
    }
}
