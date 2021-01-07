package tests.mobiletests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.mobiletests.pages.LoginPage;
import tests.mobiletests.utils.BaseDriver;

public class LoginTest extends BaseDriver {

  @Test
  public void loginUser() {
    boolean status = new LoginPage(driver).loginUser().agreeTermsAndConditions().validateHomePage();
    Assert.assertTrue(status);
  }
}
