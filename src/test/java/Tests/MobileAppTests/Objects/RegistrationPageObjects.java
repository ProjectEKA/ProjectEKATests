package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RegistrationPageObjects {
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_continue")
    public MobileElement continueButton;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_mobile_number")
    public MobileElement mobileNo;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_username")
    public MobileElement userName;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_password")
    public MobileElement password;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_first_name")
    public MobileElement firstName;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_last_name")
    public MobileElement lastName;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/chip_male")
    public MobileElement genderMale;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_dob")
    public MobileElement dateOfBirth;

    @AndroidFindBy(id = "android:id/prev")
    public MobileElement calendarPrev;

    @AndroidFindBy(xpath = "//android.view.View[@index='10']")
    public MobileElement date;

    @AndroidFindBy(id = "android:id/button1")
    public MobileElement okCalendar;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_register")
    public MobileElement registerButton;
}
