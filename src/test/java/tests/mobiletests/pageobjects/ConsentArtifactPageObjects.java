package tests.mobiletests.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ConsentArtifactPageObjects {

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_deny")
  public MobileElement denyButton;

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_grant")
  public MobileElement grantButton;

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_edit")
  public MobileElement editButton;
}
