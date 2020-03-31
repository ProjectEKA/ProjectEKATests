package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SearchLinkProviderObjects {

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/sv_provider")
    public MobileElement providerInput;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/provider_name")
    public MobileElement providerName;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_mobile_number")
    public MobileElement verifiedMobileNo;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_search")
    public MobileElement confirmProvider;
}
