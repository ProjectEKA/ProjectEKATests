package tests.mobiletests.tests;

import tests.mobiletests.pages.HomePage;
import tests.mobiletests.pages.LoginPage;
import tests.mobiletests.utils.BaseDriver;
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
