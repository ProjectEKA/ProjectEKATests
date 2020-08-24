package tests.MobileAppTests.Tests;

import tests.MobileAppTests.Pages.HomePage;
import tests.MobileAppTests.Pages.LoginPage;
import tests.MobileAppTests.Utils.BaseDriver;
import org.testng.annotations.Test;

public class DiscoverPatientTest extends BaseDriver {

    @Test
    public void discoverPatientTest() {
        new LoginPage(driver).loginUser().clickAddNewProvider().searchAndSelectProvider("Max").clickConfirmProvider();
    }

    @Test(enabled=false) //Disabled as the test user is not there yet for this flow
    public void registrationTest() {
        new HomePage(driver).clickAddNewProvider().searchAndSelectProvider("max").clickConfirmProvider();
    }
}
