package Tests.MobileAppTests.Flows;


import Tests.MobileAppTests.Objects.BasePageObjects;
import Tests.MobileAppTests.Utils.BaseDriver;
import Tests.MobileAppTests.Utils.IRunner;
import io.appium.java_client.AppiumDriver;

public class NCGFlow extends BaseDriver implements IRunner {

    public NCGFlow(AppiumDriver driver) {
        System.out.println("In NCG flow");
    }

    @Override
    public void moveSplashScreens(BasePageObjects basePageObjects) {

    }
}
