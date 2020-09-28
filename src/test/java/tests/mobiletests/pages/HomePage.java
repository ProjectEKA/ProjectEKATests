package tests.mobiletests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tests.mobiletests.pageobjects.HomePageObjects;
import tests.mobiletests.utils.WaitUtils;

public class HomePage extends WaitUtils {

  HomePageObjects homePageObjects;

  public HomePage(AppiumDriver driver) {
    super(driver);
    homePageObjects = new HomePageObjects();
    PageFactory.initElements(new AppiumFieldDecorator(driver), homePageObjects);
  }

  public SearchLinkProviderPage clickAddNewProvider() {
    waitForElement(homePageObjects.addProvider).click();
    return new SearchLinkProviderPage(driver);
  }

  public String getPatientName() {
    return refreshAndwaitForElementToBeVisible(homePageObjects.patientName).getText();
  }

  public HomePage navigateToConsentsTab() {
    waitForElement(homePageObjects.consentTab).click();
    waitForTextToAppear(homePageObjects.requestedDate);
    return this;
  }

  public ConsentArtifactPage clickConsent() {
    homePageObjects.nameInConsent.click();
    return new ConsentArtifactPage(driver);
  }

  public String getSnackBarText() {
    return waitForTextToAppear(homePageObjects.snackBar).getText();
  }

  public boolean isRequestedDateDisplayed() {
    return isElementPresent(homePageObjects.requestedDate);
  }

  public boolean validateHomePage() {
    return isElementPresent(homePageObjects.consentTab);
  }
}
