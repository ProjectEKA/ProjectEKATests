package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;

public class SearchLinkProviderObjects {

    @AndroidFindBy(id = "in.org.projecteka.jataayu.debug:id/sv_provider")
    public MobileElement providerInput;
    @AndroidFindBy( id = "in.org.projecteka.jataayu.debug:id/provider_name")
    public MobileElement providerName;
    @AndroidFindBy( id = "in.org.projecteka.jataayu.debug:id/tv_mobile_number")
    public MobileElement verifiedMobileNo;
    @AndroidFindBy( id = "in.org.projecteka.jataayu.debug:id/btn_search")
    public MobileElement confirmMobileNo;
}
