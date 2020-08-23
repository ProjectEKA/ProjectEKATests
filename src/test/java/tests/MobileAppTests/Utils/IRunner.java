package tests.MobileAppTests.Utils;

import tests.MobileAppTests.Objects.BasePageObjects;
import tests.MobileAppTests.Objects.LoginPageObjects;

public interface IRunner {
    void moveSplashScreens(BasePageObjects basePageObjects);
    void login(LoginPageObjects loginPageObjects, String username, String password);
}
