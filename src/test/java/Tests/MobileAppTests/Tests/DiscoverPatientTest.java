package Tests.MobileAppTests.Tests;

import Tests.MobileAppTests.Pages.HomePage;
import Tests.MobileAppTests.Pages.LoginPage;
import Tests.MobileAppTests.Utils.BaseDriver;
import org.testng.annotations.Test;

public class DiscoverPatientTest extends BaseDriver {

    @Test
    public void DiscoverPatientTest() {
        new LoginPage(driver).loginUser().clickAddNewProvider().searchAndSelectProvider("Tata").clickConfirmProvider();
    }

    @Test(enabled=false) //Disabled as the test user is not there yet for this flow
    public void RegistrationTest() {
        new HomePage(driver).clickAddNewProvider().searchAndSelectProvider("Tata").clickConfirmProvider();
    }
}
