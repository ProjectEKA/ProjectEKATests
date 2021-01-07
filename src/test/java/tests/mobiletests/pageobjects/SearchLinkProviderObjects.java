package tests.mobiletests.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SearchLinkProviderObjects {

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/sv_provider")
  public MobileElement providerInput;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tv_provider_name")
  public MobileElement providerName;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_search")
  public MobileElement confirmProvider;
}
