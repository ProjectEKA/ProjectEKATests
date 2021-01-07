package tests.mobiletests.pageobjects;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;

public class OTPPageObjects {
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_create_pin")
  public MobileElement continueButton;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/et_otp")
  public MobileElement otpField;
}