package Tests.HIUTests.objects;

import Tests.HIUTests.wrappers.GenricWrappers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ConsentRequestPageObjects extends GenricWrappers {

    @FindBy(id = "search-field")
    public static WebElement PatientName;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/button")
    public static WebElement RequestButton;

    @FindBy(id = "search-button")
    public static WebElement Search;

    @FindBy(xpath = "/html/body/div[3]/div[3]/div/form/div[5]/div[2]/div/fieldset/div/label[1]/span[1]")
    public static WebElement Options;

    @FindBy(xpath = "/html/body/div[3]/div[3]/div/form/div[7]/button")
    public static WebElement SubmitButton;

}
