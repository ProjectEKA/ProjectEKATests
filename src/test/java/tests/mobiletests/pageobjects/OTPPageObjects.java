package tests.mobiletests.pageobjects;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;

public class OTPPageObjects {
  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_continue")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_create_pin")
  public MobileElement continueButton;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_otp")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/et_otp")
  public MobileElement otpField;

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_pin")
  public MobileElement consentPin;

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/lbl_enter_otp")
  public MobileElement consentPinLabel;
}
