package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPageObjects {
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_cm_id")
    public MobileElement userName;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_next")
    public MobileElement nextButton;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_password")
    public MobileElement password;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_login")
    public MobileElement loginButton;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_register")
    public MobileElement registerButton;
}


