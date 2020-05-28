package Tests.MobileAppTests.Tests;

import Tests.APITests.APIUtils.APIUtils;
import Tests.MobileAppTests.Pages.HomePage;
import Tests.MobileAppTests.Pages.LoginPage;
import Tests.MobileAppTests.Pages.OTPPage;
import Tests.MobileAppTests.Pages.RegistrationPage;
import Tests.MobileAppTests.Utils.AppUtility;
import Tests.MobileAppTests.Utils.BaseDriver;
import Tests.MobileAppTests.Utils.Patient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseDriver {

    @Test
    public void registerLinkCareContext() {
        String userName = "TestUser" + AppUtility.generateRandomNo();
        Patient patient = new Patient("Hina Patel", "8888888888", "female");
        HomePage homePage = new LoginPage(driver).navigateToCreateAccount().enterContactNoAndContinue(patient.getMobile())
                .enterOTP(new RegistrationPage(driver)).enterUserDetails(patient, userName)
                .searchAndSelectProvider("Max").clickConfirmProvider().linkCareContext().enterOTP(new HomePage(driver));

        Assert.assertTrue(homePage.getPatientName().length() > 0, "Patient Name not displayed on Link Provider screen");
    }

    @Test
    public void grantConsent() {
        String userName = "TestUser" + AppUtility.generateRandomNo();
        Patient patient = new Patient("John  Doe", "9999999999", "male");
        HomePage homePage = new LoginPage(driver).navigateToCreateAccount().enterContactNoAndContinue("9999999999")
                .enterOTP(new RegistrationPage(driver)).enterUserDetails(patient, userName)
                .searchAndSelectProvider("Max").clickConfirmProvider().linkCareContext()
                .enterOTP(new HomePage(driver));

        new APIUtils().createConsent(userName + "@ncg"); //Create Consent Request

        String snackBarText = homePage.navigateToConsentsTab().clickConsent().grantConsent()
                .enterPin(new OTPPage(driver), "Create")
                .enterPin(new HomePage(driver), "Confirm").getSnackBarText();

        Assert.assertEquals(snackBarText, "Consent Granted Successfully");
    }
}
