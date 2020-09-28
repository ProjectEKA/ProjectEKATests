package tests.mobiletests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.apitests.helpers.PropertiesCache;
import tests.mobiletests.pages.LoginPage;
import tests.mobiletests.utils.BaseDriver;

public class LoginTest extends BaseDriver {

  String userName = PropertiesCache.getInstance().getProperty("mobileUser");
  String password = PropertiesCache.getInstance().getProperty("mobilePassword");

  @Test
  public void loginUser() {

    boolean status = new LoginPage(driver).loginUser(userName, password).validateHomePage();
    Assert.assertTrue(status);
  }
}
