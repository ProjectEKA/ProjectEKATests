package Tests.HIUTests.wrappers;

import org.testng.annotations.*;


import java.io.IOException;

public class ProjectWrappers extends GenricWrappers {

    // contains 8 testng methods
    public String excelSheetName, description;
    public String browser = "chrome";

    @BeforeSuite
    public void beforeSuit() {
        //startReport();

    }

    @BeforeTest
    public void beforeTest() {
        loadObject();

    }

    @BeforeMethod
    public void beforeMethod() {

        getBrowser(browser, "https://ncg-dev.projecteka.in/hiu#/hiu/login");
    }

    @AfterMethod
    public void afterMethod() {

        driver.close();
    }

    @AfterClass
    public void afterClass() {
        //endTest();
    }

    @AfterTest
    public void afterTest() {

        unloadObject();
    }

    @AfterSuite
    public void afterSuit() {
        //endReport();
    }

	/*@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return DataProviderFromExcel.getData(excelSheetName);

	}
	*/


}
