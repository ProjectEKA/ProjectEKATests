package Tests.HIUTests.pages;

import Tests.HIUTests.objects.ConsentRequestPageObjects;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ConsentRequestPage extends ConsentRequestPageObjects {

    private WebDriverWait wait;

    public ConsentRequestPage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);

    }

    public ConsentRequestPage clickOnNewRequest() throws InterruptedException {
        Thread.sleep(2000);
        checkVisibility(RequestButton);
        RequestButton.click();
        return this;

    }

    public ConsentRequestPage enterPatientName() throws InterruptedException {

        checkVisibility(PatientName);
        PatientName.sendKeys(obj.getProperty("ConsentRequestPage.enterPatientName"));
        return this;

    }

    public ConsentRequestPage clickOnSearch() {

        checkVisibility(Search);
        Search.click();
        return this;

    }

    public ConsentRequestPage selectOptions() {

        checkVisibility(Options);
        Options.click();
        return this;
    }

    public ConsentRequestPage submitRequest() throws InterruptedException {

        checkVisibility(SubmitButton);
        SubmitButton.click();
        Thread.sleep(2000);
        return this;

    }
}
