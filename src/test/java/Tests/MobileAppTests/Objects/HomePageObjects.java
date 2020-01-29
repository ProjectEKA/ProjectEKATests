package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePageObjects {

    @AndroidFindBy(id = "in.org.projecteka.jataayu.debug:id/fab")
    public MobileElement addProvider;
    @AndroidFindBy(id = "in.org.projecteka.jataayu.debug:id/tv_patient_name")
    public MobileElement patientName;

}
