package Tests.HIUTests.testcase;

import Tests.HIUTests.pages.NCGHomepage;
import Tests.HIUTests.wrappers.ProjectWrappers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class RequestFormTest extends ProjectWrappers {

    @BeforeClass
    public void beforeClass() {

        description = "To ensure functionality breakdown";
        browser = "chrome";
    }

    @Test
    public void sendRequest() throws InterruptedException {

        new NCGHomepage(driver).enterUsername().enterPassword().clickOnSignin().clickOnNewRequest().enterPatientName().clickOnSearch().selectOptions().submitRequest();


        //Assert -  Consent Request sent successfully message
       boolean result= driver.getPageSource().contains(obj.getProperty("success"));
       Assert.assertTrue(result,"true");

    }

}
