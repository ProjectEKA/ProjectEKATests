package tests.mobiletests.pages;

import tests.mobiletests.pageobjects.LinkAccountsPageObjects;
import tests.mobiletests.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LinkAccountsPage extends WaitUtils {

    LinkAccountsPageObjects linkAccountsPageObjects;

    public LinkAccountsPage(AppiumDriver driver) {
        super(driver);
        linkAccountsPageObjects = new LinkAccountsPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), linkAccountsPageObjects);
        waitForElement(linkAccountsPageObjects.patientName);
    }

    public String getPatientName() {
        return linkAccountsPageObjects.patientName.getText();
    }

    public OTPPage linkCareContext() {
        linkAccountsPageObjects.linkSelected.click();
        return new OTPPage(driver);
    }
}
