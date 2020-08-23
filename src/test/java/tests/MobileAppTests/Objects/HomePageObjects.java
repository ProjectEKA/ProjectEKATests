package tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class HomePageObjects {

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/fab")
    public MobileElement addProvider;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_patient_name")
    public MobileElement patientName;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "in.ndhm.phr.debug:id/action_consents")
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/action_consents")
    public MobileElement consentTab;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_requested_date")
    public MobileElement requestedDate;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_requester_name")
    public MobileElement nameInConsent;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/snackbar_text")
    public MobileElement snackBar;
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/sp_request_filter")
    public MobileElement requestFilter;



}
