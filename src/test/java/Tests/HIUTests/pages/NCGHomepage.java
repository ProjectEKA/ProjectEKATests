package Tests.HIUTests.pages;

import Tests.HIUTests.objects.NCGHomepageObjects;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NCGHomepage extends NCGHomepageObjects {

    private WebDriverWait wait;

    public NCGHomepage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);

    }

    public NCGHomepage enterUsername() {

        checkVisibility(username);
        username.sendKeys(obj.getProperty("NCGHomepage.name"));
        return this;
    }

    public NCGHomepage enterPassword() {

        checkVisibility(password);
        password.sendKeys(obj.getProperty("NCGHomepage.password"));
        return this;
    }

    public ConsentRequestPage clickOnSignin() throws InterruptedException {

        checkVisibility(signinButton);
        signinButton.click();
        Thread.sleep(1000);
        return new ConsentRequestPage(driver);

    }

}
