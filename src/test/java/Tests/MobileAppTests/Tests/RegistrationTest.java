package Tests.MobileAppTests.Tests;

import Tests.APITests.APIUtils.APIUtils;
import Tests.MobileAppTests.Pages.HomePage;
import Tests.MobileAppTests.Pages.LoginPage;
import Tests.MobileAppTests.Pages.OTPPage;
import Tests.MobileAppTests.Pages.RegistrationPage;
import Tests.MobileAppTests.Utils.AppUtility;
import Tests.MobileAppTests.Utils.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseDriver {

    @Test
    public void registerLinkCareContext() {
        String userName = "TestUser" + AppUtility.generateRandomNo();

        HomePage homePage = new LoginPage(driver).navigateToCreateAccount().enterContactNoAndContinue().enterOTP(new RegistrationPage(driver)).enterUserDetails(userName)
                .searchAndSelectProvider("Max").clickConfirmProvider().linkCareContext().enterOTP(new HomePage(driver));

        Assert.assertTrue(homePage.getPatientName().length() > 0, "Patient Name not displayed on Link Provider screen");
    }

    @Test
    public void grantConsent() {
        String userName = "TestUser" + AppUtility.generateRandomNo();

        HomePage homePage = new LoginPage(driver).navigateToCreateAccount().enterContactNoAndContinue().enterOTP(new RegistrationPage(driver)).enterUserDetails(userName)
                .searchAndSelectProvider("Max").clickConfirmProvider().linkCareContext()
                .enterOTP(new HomePage(driver));

        new APIUtils().createConsent(userName + "@ncg"); //Create Consent Request

        boolean filterDisplayed = homePage.navigateToConsentsTab().clickConsent().grantConsent()
                .enterPin(new OTPPage(driver), "Create")
                .enterPin(new HomePage(driver), "Confirm").isRequestedDateDisplayed() ;

        Assert.assertTrue(filterDisplayed,"Not navigated to HomePage" );
    }
}
