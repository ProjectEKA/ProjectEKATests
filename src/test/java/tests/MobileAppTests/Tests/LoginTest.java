package tests.MobileAppTests.Tests;

import tests.apitests.helpers.PropertiesCache;
import tests.MobileAppTests.Pages.LoginPage;
import tests.MobileAppTests.Utils.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseDriver {

    String userName = PropertiesCache.getInstance().getProperty("mobileUser");
    String password = PropertiesCache.getInstance().getProperty("mobilePassword");

    @Test
    public void loginUser() {

        boolean status = new LoginPage(driver).loginUser(userName, password).validateHomePage();
        Assert.assertTrue(status);
    }
}
