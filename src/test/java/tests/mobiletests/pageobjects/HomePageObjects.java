package tests.mobiletests.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePageObjects {
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/action_accounts")
  public MobileElement providerScreenPane;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/fab")
  public MobileElement addProvider;

  @AndroidFindBy(id = "")
  public MobileElement patientName;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/action_consents")
  public MobileElement consentTab;

  @AndroidFindBy(id = "")
  public MobileElement requestedDate;

  @AndroidFindBy(id = "")
  public MobileElement nameInConsent;

  @AndroidFindBy(id = "")
  public MobileElement snackBar;

  @AndroidFindBy(id = "")
  public MobileElement requestFilter;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_agree")
  public MobileElement agreeTermsAndConditions;
}
