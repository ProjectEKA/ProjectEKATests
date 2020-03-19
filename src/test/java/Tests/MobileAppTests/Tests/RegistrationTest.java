package Tests.MobileAppTests.Tests;

import Tests.MobileAppTests.Pages.LoginPage;
import Tests.MobileAppTests.Utils.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseDriver {
    @Test
    public void RegistrationTest() {
        String patientName = new LoginPage(driver).navigateToCreateAccount().enterContactNoAndContinue().enterOTP().enterUserDetails()
                .searchAndSelectProvider("Max").clickConfirmProvider().getPatientName();

        Assert.assertTrue(patientName.length() > 0, "Patient Name not displayed on Link Provider screen");
    }
}
