package tests.mobiletests.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPageObjects {
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/et_cm_id")
  public MobileElement userName;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/et_password")
  public MobileElement password;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_next")
  public MobileElement loginButton;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_register")
  public MobileElement registerButton;

  @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
  public MobileElement allowPermission;
}
