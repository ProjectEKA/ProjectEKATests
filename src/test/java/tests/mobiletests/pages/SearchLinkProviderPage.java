package tests.mobiletests.pages;

import tests.mobiletests.pageobjects.SearchLinkProviderObjects;
import tests.mobiletests.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SearchLinkProviderPage extends WaitUtils {

    SearchLinkProviderObjects searchLinkProviderObjects;

    public SearchLinkProviderPage(AppiumDriver driver) {
        super(driver);
        searchLinkProviderObjects = new SearchLinkProviderObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), searchLinkProviderObjects);
        waitForElement(searchLinkProviderObjects.providerInput);
    }

    public SearchLinkProviderPage searchAndSelectProvider(String providerName) {
        searchLinkProviderObjects.providerInput.sendKeys(providerName);
        waitForElement(searchLinkProviderObjects.providerName).click();
        return this;
    }

    public LinkAccountsPage clickConfirmProvider() {
        waitForElementToBeVisible(searchLinkProviderObjects.confirmProvider).click();
        return new LinkAccountsPage(driver);
    }
}

