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
        new WaitUtils().waitForElement(driver, homePageObjects.patientName);
    }

    public SearchLinkProviderPage clickAddNewProvider() {
        homePageObjects.addProvider.click();
        return new SearchLinkProviderPage(driver);
    }
}
