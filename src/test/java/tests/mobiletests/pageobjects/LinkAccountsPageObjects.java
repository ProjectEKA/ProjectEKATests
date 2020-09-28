package tests.mobiletests.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LinkAccountsPageObjects {
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_patient_name")
    public MobileElement patientName;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_search")
    public MobileElement linkSelected;

}
