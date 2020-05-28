package Tests.MobileAppTests.Objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class RegistrationPageObjects {
    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_continue")
    public MobileElement continueButton;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_mobile_number")
    public MobileElement mobileNo;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_username")
    public MobileElement userName;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_password")
    public MobileElement password;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_confirm_password")
    public MobileElement confirmPassword;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_name")
    public MobileElement patientName;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/gender_chip_male")
    public MobileElement genderMale;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/gender_chip_female")
    public MobileElement genderFemale;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_dob")
    public MobileElement dateOfBirth;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/spinner_yob")
    public MobileElement yearOfBirth;

    @AndroidFindBy(className = "android.widget.CheckedTextView")
    public List<MobileElement> dropDownList;

    @AndroidFindBy(id = "android:id/prev")
    public MobileElement calendarPrev;

    @AndroidFindBy(xpath = "//android.view.View[@index='10']")
    public MobileElement date;

    @AndroidFindBy(id = "android:id/button1")
    public MobileElement okCalendar;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_register")
    public MobileElement registerButton;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_confirm_registration")
    public MobileElement confirmRegisterButton;

    @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_cm_id_info")
    public MobileElement confirmationMessage;
}
