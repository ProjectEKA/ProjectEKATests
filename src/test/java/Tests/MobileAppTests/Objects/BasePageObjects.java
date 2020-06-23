package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BasePageObjects {

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_next")
    public MobileElement nextButton;
}
