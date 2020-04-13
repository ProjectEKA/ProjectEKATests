package Tests.MobileAppTests.Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    public WebDriverWait wait;

    public MobileElement waitForElement(AppiumDriver driver, MobileElement id) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions
                .elementToBeClickable(id));
        return id;
    }

    public MobileElement waitForElementToBeVisible(AppiumDriver driver, MobileElement id) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(id));
        return id;
    }
    public MobileElement refreshAndwaitForElementToBeVisible(AppiumDriver driver, MobileElement id) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(id)));
        return id;
    }

    public MobileElement waitForTextToAppear(AppiumDriver driver, MobileElement id) {
        wait = new WebDriverWait(driver, 30);
        wait.until((ExpectedCondition<Boolean>) d -> id.getText().length() != 0);
        return id;
    }

    public MobileElement waitForSpecificTextToBeDisplayed(AppiumDriver driver, MobileElement id, String text) {
        wait = new WebDriverWait(driver, 30);
        wait.until((ExpectedCondition<Boolean>) d -> id.getText().contains(text));
        return id;
    }

    public MobileElement waitForElementToBeDisabled(AppiumDriver driver, MobileElement id) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(id)));
        return id;
    }

    public boolean isElementPresent(AppiumDriver driver, MobileElement id) {
        try {
            wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(id));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }
}
