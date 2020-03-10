package Tests.MobileAppTests.Tests;

import Tests.APITests.APIUtils.PropertiesCache;
import Tests.MobileAppTests.Pages.LoginPage;
import Tests.MobileAppTests.Utils.BaseDriver;
import org.testng.annotations.Test;

public class LoginTest extends BaseDriver {

    @Test
    public void loginUser() {

        String userName = PropertiesCache.getInstance().getProperty("user");
        String password = PropertiesCache.getInstance().getProperty("password");

        new LoginPage(driver).loginUser(userName, password);
    }
}
