package Tests.MobileAppTests.Utils;

import Tests.MobileAppTests.Objects.BasePageObjects;
import Tests.MobileAppTests.Objects.LoginPageObjects;

public interface IRunner {
    void moveSplashScreens(BasePageObjects basePageObjects);
    void login(LoginPageObjects loginPageObjects, String username, String password);
}
