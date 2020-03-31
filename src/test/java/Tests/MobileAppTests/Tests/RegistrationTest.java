package Tests.MobileAppTests.Tests;

import Tests.APITests.APIUtils.APIUtils;
import Tests.MobileAppTests.Pages.LoginPage;
import Tests.MobileAppTests.Utils.AppUtility;
import Tests.MobileAppTests.Utils.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseDriver {
    @Test
    public void RegistrationTest() {

        String userName = "TestUser" + AppUtility.generateRandomNo();

        new APIUtils().createConsent(userName+"@ncg"); //Create Consent Request

        String patientName = new LoginPage(driver).navigateToCreateAccount().enterContactNoAndContinue().enterOTP().enterUserDetails(userName)
                .searchAndSelectProvider("Max").clickConfirmProvider().getPatientName();


        new LoginPage(driver).navigateToCreateAccount().enterContactNoAndContinue().enterOTP().enterUserDetails(userName)
                .searchAndSelectProvider("Max").clickConfirmProvider().linkCareContext();

        Assert.assertTrue(patientName.length() > 0, "Patient Name not displayed on Link Provider screen");
    }
}
