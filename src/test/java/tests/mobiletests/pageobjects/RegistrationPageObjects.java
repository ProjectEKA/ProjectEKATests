package tests.mobiletests.pageobjects;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import java.util.List;

public class RegistrationPageObjects {
  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_continue")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_continue")
  public MobileElement continueButton;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tie_mobile_number")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/edit_text_mobile_number")
  public MobileElement mobileNo;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tv_proceed_without_aadhaar")
  public MobileElement proceedWithoutAadhaar;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_cmid")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tv_health_id")
  public MobileElement cmID;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_password")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/actv_district")
  public MobileElement password;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_confirm_password")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/et_confirm_password")
  public MobileElement confirmPassword;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/et_name")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tiet_first_name")
  public MobileElement firstName;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tiet_last_name")
  public MobileElement lastName;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/gender_chip_male")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/gender_chip_male")
  public MobileElement genderMale;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/gender_chip_female")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/gender_chip_female")
  public MobileElement genderFemale;

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_dob")
  public MobileElement dateOfBirth;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/spinner_yob")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/actv_year")
  public MobileElement yearOfBirth;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/actv_state")
  public MobileElement state;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/actv_district")
  public MobileElement district;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/cb_link_all_providers")
  public MobileElement userInformationAgreement;

  @AndroidFindBy(className = "android.widget.CheckedTextView")
  public List<MobileElement> dropDownList;

  @AndroidFindBy(id = "android:id/prev")
  public MobileElement calendarPrev;

  @AndroidFindBy(xpath = "//android.view.View[@index='10']")
  public MobileElement date;

  @AndroidFindBy(id = "android:id/button1")
  public MobileElement okCalendar;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_register")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_register")
  public MobileElement registerButton;

  @HowToUseLocators(androidAutomation = ALL_POSSIBLE)
  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/btn_confirm_registration")
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_confirm_registration")
  public MobileElement confirmRegisterButton;

  @AndroidFindBy(id = "in.projecteka.jataayu.debug:id/tv_cm_id_info")
  public MobileElement confirmationMessage;
}
