package tests.mobiletests.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    public WebDriverWait wait;
    public AppiumDriver driver;
    public IRunner runner;
    
    public WaitUtils(AppiumDriver driver) {
        this.runner = RunnerFactory.runner;
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public MobileElement waitForElement(MobileElement id) {
        wait.until(ExpectedConditions
                .elementToBeClickable(id));
        return id;
    }

    public MobileElement waitForElementToBeVisible(MobileElement id) {
        wait.until(ExpectedConditions.visibilityOf(id));
        return id;
    }

    public MobileElement refreshAndwaitForElementToBeVisible(MobileElement id) {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(id)));
        return id;
    }

    public MobileElement waitForTextToAppear(MobileElement id) {
        wait.until((ExpectedCondition<Boolean>) d -> id.getText().length() != 0);
        return id;
    }

    public MobileElement waitForSpecificTextToBeDisplayed(MobileElement id, String text) {
        wait.until(ExpectedConditions.refreshed((ExpectedCondition<Boolean>) d -> id.getText().contains(text)));
        return id;
    }

    public MobileElement waitForElementToBeEnabled(MobileElement id) {
        wait.until(ExpectedConditions.elementToBeClickable(id));
        return id;
    }

    public boolean isElementPresent(MobileElement id) {
        try {
            wait.until(ExpectedConditions.visibilityOf(id));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
