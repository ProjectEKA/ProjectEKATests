package tests.mobiletests.utils;

import tests.mobiletests.pageobjects.BasePageObjects;
import tests.mobiletests.pageobjects.LoginPageObjects;

public interface IRunner {
  void moveSplashScreens(BasePageObjects basePageObjects);

  void login(LoginPageObjects loginPageObjects, String username, String password);
}
