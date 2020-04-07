package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.HomePageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends WaitUtils {

    HomePageObjects homePageObjects;
    AppiumDriver driver;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        homePageObjects = new HomePageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), homePageObjects);
    }

    public SearchLinkProviderPage clickAddNewProvider() {
        homePageObjects.addProvider.click();
        return new SearchLinkProviderPage(driver);
    }

    public String getPatientName() {
        return homePageObjects.patientName.getText();
    }

    public HomePage navigateToConsentsTab() {
        new WaitUtils().waitForElement(driver, homePageObjects.consentTab).click();
        new WaitUtils().waitForTextToAppear(driver, homePageObjects.requestedDate);
        return this;
    }

    public ConsentArtifactPage clickConsent() {
        homePageObjects.nameInConsent.click();
        return new ConsentArtifactPage(driver);
    }

    public String getSnackBarText() {
        return new WaitUtils().waitForTextToAppear(driver, homePageObjects.snackBar).getText();
    }

    public boolean isFilterDisplayed() {
        return new WaitUtils().isElementPresent(driver, homePageObjects.requestFilter);
    }
}
