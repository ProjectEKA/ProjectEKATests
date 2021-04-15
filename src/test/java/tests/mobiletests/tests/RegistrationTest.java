package tests.mobiletests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.apitests.consentmanager.utils.ConsentRequest;
import tests.mobiletests.pages.HomePage;
import tests.mobiletests.pages.LoginPage;
import tests.mobiletests.pages.OTPPage;
import tests.mobiletests.pages.RegistrationPage;
import tests.mobiletests.utils.AppUtility;
import tests.mobiletests.utils.BaseDriver;
import tests.mobiletests.utils.Patient;

public class RegistrationTest extends BaseDriver {

  //@Test(enabled = false)
  public void registerUser() {
    String userName = "TestUser" + AppUtility.generateRandomNo();
    Patient patient =
        Patient.builder()
            .firstName("Hina")
            .lastName("Patel")
            .mobile("8888888888")
            .gender("female")
            .build();
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

  //@Test(enabled = false)
  public void grantConsent() {
    String userName = "TestUser" + AppUtility.generateRandomNo();
    Patient patient =
        Patient.builder()
            .firstName("John")
            .lastName("Doe")
            .mobile("9999999999")
            .gender("male")
            .build();
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
