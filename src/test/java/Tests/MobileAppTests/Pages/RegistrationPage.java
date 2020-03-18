package Tests.MobileAppTests.Pages;

import Tests.MobileAppTests.Objects.RegistrationPageObjects;
import Tests.MobileAppTests.Utils.WaitUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

public class RegistrationPage {
    RegistrationPageObjects registrationPageObjects;
    AppiumDriver driver;

    public RegistrationPage(AppiumDriver driver) {
        this.driver = driver;
        registrationPageObjects = new RegistrationPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), registrationPageObjects);
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.continueButton);
    }

    public RegistrationPage enterContactNoAndContinue() {
        registrationPageObjects.mobileNo.sendKeys("9999999999");
        registrationPageObjects.continueButton.click();
        return this;
    }

    public RegistrationPage enterOTP() {
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.otpField).sendKeys("666666");
        ;
        registrationPageObjects.continueButton.click();
        return this;
    }

    public RegistrationPage enterUserDetails() {
        new WaitUtils().waitForElementToBeVisible(driver, registrationPageObjects.userName).sendKeys("TestUser" + generateRandomNo());
        registrationPageObjects.password.sendKeys("Test@123");
        registrationPageObjects.firstName.sendKeys("Test");
        registrationPageObjects.lastName.sendKeys("Test");

        verticalSwipe();


        registrationPageObjects.genderMale.click();
        registrationPageObjects.dateOfBirth.click();

        registrationPageObjects.calendarPrev.click();
        registrationPageObjects.date.click();
        registrationPageObjects.okCalendar.click();
        registrationPageObjects.registerButton.click();


        WebDriverWait wait = new WebDriverWait(driver, 20);

        final String toastText = "Catch me if you can!";

        wait.until(toastMatches(toastText, false));


        return this;
    }

    private String generateRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return String.valueOf(10000 + r.nextInt(20000));
    }

    private void verticalSwipe() {


        Point source = registrationPageObjects.firstName.getCenter();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 0);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                source.x / 2, source.y));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(20),
                PointerInput.Origin.viewport(),
                source.x / 2, source.y - 800));//Move the pointer up

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    public static ExpectedCondition<Boolean> toastMatches(String matchText, Boolean isRegexp) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                ImmutableMap<String, Object> args = ImmutableMap.of(
                        "text", matchText,
                        "isRegexp", isRegexp
                );
                return (Boolean) ((JavascriptExecutor)driver).executeScript("mobile: isToastVisible", args);
            }

            @Override
            public String toString() {
                return "toast to be present";
            }
        };
    }


}
