package tests.mobiletests.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BasePageObjects {
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_skip")
  public MobileElement skipButton;
}
