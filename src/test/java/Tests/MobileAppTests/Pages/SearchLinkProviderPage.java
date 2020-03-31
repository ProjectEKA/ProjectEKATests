package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.SearchLinkProviderObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SearchLinkProviderPage extends WaitUtils {

    SearchLinkProviderObjects searchLinkProviderObjects;
    AppiumDriver driver;

    public SearchLinkProviderPage(AppiumDriver driver) {
        this.driver = driver;
        searchLinkProviderObjects = new SearchLinkProviderObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), searchLinkProviderObjects);
        new WaitUtils().waitForElement(driver, searchLinkProviderObjects.providerInput);
    }

    public SearchLinkProviderPage searchAndSelectProvider(String providerName) {
        searchLinkProviderObjects.providerInput.sendKeys(providerName);
        waitForElement(driver,searchLinkProviderObjects.providerName).click();
        return this;
    }

    public LinkAccountsPage clickConfirmProvider() {
        waitForElement(driver, searchLinkProviderObjects.verifiedMobileNo);
        searchLinkProviderObjects.confirmProvider.click();
        return new LinkAccountsPage(driver);
    }
}

