package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePageObjects {

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/fab")
    public MobileElement addProvider;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_patient_name")
    public MobileElement patientName;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/action_consents")
    public MobileElement consentTab;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_requested_date")
    public MobileElement requestedDate;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_requester_name")
    public MobileElement nameInConsent;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/snackbar_text")
    public MobileElement snackBar;

}
