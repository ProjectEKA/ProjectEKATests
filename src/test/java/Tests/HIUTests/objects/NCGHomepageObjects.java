package Tests.HIUTests.objects;

import Tests.HIUTests.wrappers.GenricWrappers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NCGHomepageObjects extends GenricWrappers {

    @FindBy(id = "userName")
    public static WebElement username;

    @FindBy(id = "password")
    public static WebElement password;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/main/div[1]/form/button")
    public static WebElement signinButton;

}
