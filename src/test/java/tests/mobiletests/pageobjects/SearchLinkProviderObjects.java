package tests.mobiletests.pageobjects;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;

public class SearchLinkProviderObjects {

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/sv_provider")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/sv_provider")
  public MobileElement providerInput;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tv_provider_name")
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/provider_name")
  public MobileElement providerName;

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_mobile_number")
  public MobileElement verifiedMobileNo;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_search")
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_search")
  public MobileElement confirmProvider;

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/lbl_mobile_number")
  public MobileElement allRecordsLinkedText;
}
