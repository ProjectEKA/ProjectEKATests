package Tests.HIUTests.testcase;

import Tests.HIUTests.pages.NCGHomepage;
import Tests.HIUTests.wrappers.ProjectWrappers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class HIULoginTest extends ProjectWrappers {

    @BeforeClass
    public void beforeClass() {

        description = "To ensure functionality breakdown";
        browser = "chrome";
    }

    @Test
    public void loginUser() throws InterruptedException {

        new NCGHomepage(driver).enterUsername().enterPassword().clickOnSignin();

    }

}
