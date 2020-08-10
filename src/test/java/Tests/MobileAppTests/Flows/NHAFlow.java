package Tests.MobileAppTests.Flows;

import Tests.MobileAppTests.Objects.BasePageObjects;
import Tests.MobileAppTests.Utils.IRunner;
import Tests.MobileAppTests.Utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class NHAFlow extends WaitUtils implements IRunner {

    public NHAFlow(AppiumDriver driver) {
        super(driver);
        System.out.println("In NHA flow");
    }

    @Override
    public void moveSplashScreens(BasePageObjects basePageObjects) {
        if (isElementPresent(basePageObjects.nextButton)) {
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
            refreshAndwaitForElementToBeVisible(basePageObjects.nextButton).click();
        }
    }
}
