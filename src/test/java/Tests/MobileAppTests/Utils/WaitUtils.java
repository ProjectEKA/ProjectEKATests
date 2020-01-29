package Tests.MobileAppTests.Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
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
}
