package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class OTPPageObjects {
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_continue")
    public MobileElement continueButton;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_otp")
    public MobileElement otpField;
}
