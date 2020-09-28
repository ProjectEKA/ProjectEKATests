package tests.mobiletests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.mobiletests.pages.HomePage;
import tests.mobiletests.pages.LoginPage;
import tests.mobiletests.pages.OTPPage;
import tests.mobiletests.pages.RegistrationPage;
import tests.mobiletests.utils.AppUtility;
import tests.mobiletests.utils.BaseDriver;
import tests.mobiletests.utils.Patient;
import tests.apitests.consentmanager.utils.ConsentRequest;

public class RegistrationTest extends BaseDriver {

  @Test
  public void registerLinkCareContext() {
    String userName = "TestUser" + AppUtility.generateRandomNo();
    Patient patient = new Patient("Hina Patel", "8888888888", "female");
    HomePage homePage =
        new LoginPage(driver)
            .navigateToCreateAccount()
            .enterContactNoAndContinue(patient.getMobile())
            .enterOTP(new RegistrationPage(driver))
            .enterUserDetails(patient, userName)
            .clickAddNewProvider()
            .searchAndSelectProvider("Max")
            .clickConfirmProvider()
            .linkCareContext()
            .enterOTP(new HomePage(driver));

    Assert.assertTrue(
        homePage.getPatientName().length() > 0,
        "Patient Name not displayed on Link Provider screen");
  }

  @Test
  public void grantConsent() {
    String userName = "TestUser" + AppUtility.generateRandomNo();
    Patient patient = new Patient("John  Doe", "9999999999", "male");
    HomePage homePage =
        new LoginPage(driver)
            .navigateToCreateAccount()
            .enterContactNoAndContinue("9999999999")
            .enterOTP(new RegistrationPage(driver))
            .enterUserDetails(patient, userName)
            .clickAddNewProvider()
            .searchAndSelectProvider("Max")
            .clickConfirmProvider()
            .linkCareContext()
            .enterOTP(new HomePage(driver));

    new ConsentRequest().createConsent(userName + "@ncg"); // Create Consent Request

    String snackBarText =
        homePage
            .navigateToConsentsTab()
            .clickConsent()
            .grantConsent()
            .enterPin(new OTPPage(driver), "Create")
            .enterPin(new HomePage(driver), "Confirm")
            .getSnackBarText();

    Assert.assertEquals(snackBarText, "Consent Granted Successfully");
  }
}
