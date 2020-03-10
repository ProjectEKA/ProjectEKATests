package Tests.MobileAppTests.Tests;

import Tests.MobileAppTests.Pages.LoginPage;
import Tests.MobileAppTests.Pages.RegistrationPage;
import Tests.MobileAppTests.Utils.BaseDriver;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseDriver {
    @Test
    public void RegistrationTest() {
        new LoginPage(driver).navigateToCreateAccount().enterContactNoAndContinue();
    }
}
