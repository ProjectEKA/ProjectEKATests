package tests.mobiletests.pageobjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class RegistrationPageObjects {
  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_continue")
  public MobileElement continueButton;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/edit_text_mobile_number")
  public MobileElement mobileNo;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tv_proceed_without_aadhaar")
  public MobileElement proceedWithoutAadhaar;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tv_health_id")
  public MobileElement cmID;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/actv_district")
  public MobileElement password;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/et_confirm_password")
  public MobileElement confirmPassword;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tiet_first_name")
  public MobileElement firstName;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/tiet_last_name")
  public MobileElement lastName;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/gender_chip_male")
  public MobileElement genderMale;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/gender_chip_female")
  public MobileElement genderFemale;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/actv_year")
  public MobileElement yearOfBirth;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/actv_state")
  public MobileElement state;

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

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_register")
  public MobileElement registerButton;

  @AndroidFindBy(id = "in.ndhm.phr.debug:id/btn_confirm_registration")
  public MobileElement confirmRegisterButton;

}
