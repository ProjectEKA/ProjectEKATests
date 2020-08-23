package tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class LoginPageObjects {
    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "in.ndhm.phr.debug:id/et_cm_id")
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_cm_id")
    public MobileElement userName;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_next")
    public MobileElement nextButton;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "in.ndhm.phr.debug:id/et_password")
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_password")
    public MobileElement password;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_next")
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_login")
    public MobileElement loginButton;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_register")
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_register")
    public MobileElement registerButton;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/text_input_end_icon")
    public MobileElement showPasswordButton;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    public MobileElement allowPermission;

}


