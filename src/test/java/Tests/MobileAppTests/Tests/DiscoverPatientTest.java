package Tests.MobileAppTests.Tests;

import Tests.MobileAppTests.Pages.HomePage;
import Tests.MobileAppTests.Utils.BaseDriver;
import org.testng.annotations.Test;

public class DiscoverPatientTest extends BaseDriver {

    @Test
    public void DiscoverPatientTest() {
        new HomePage(driver).clickAddNewProvider().searchAndSelectProvider("Tata").clickConfirmProvider();
    }

    @Test
    public void RegistrationTest() {
        new HomePage(driver).clickAddNewProvider().searchAndSelectProvider("Tata").clickConfirmProvider();
    }
}
