package Tests.HIUTests.wrappers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GenricWrappers implements wrappers {

    public static RemoteWebDriver driver;

    public static WebDriverWait wait;
    public static Properties obj;

    /* ############################ Property file Method ############################## */

    public void loadObject() {

        obj = new Properties();
        try {
            obj.load(new FileInputStream("./src/test/resources/config.properties"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void unloadObject() {

        obj = null;
    }

    /* ############################ chrome to launch automatically ############################## */


    public void getBrowser(String browser, String url) {


        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(url);

    }



    /* ############################ Take snapshot Methods ############################## */
	/*
	@Override
	public long takeSnap() {

		long snap = System.currentTimeMillis();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./screenshot/" + snap + ".png"));
		} catch (IOException e) {
		
			e.printStackTrace();
		}catch (ScreenshotException e) {
			reportLog("fail", "error occured");
		} catch (NotFoundException e) {
			reportLog("fail", "error occured");
		
		} catch (WebDriverException e) {
			reportLog("fail", "error occured");
		} 

		return snap;
	}
	
	*/
    /* ############################ Launch Browser options method ############################## */

    public void invokeApp(String browser, String url) {


        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "./driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.get(url);


    }

    /* ############################ close browser Method ############################## */

    public void closeAllBrowsers() {

        driver.quit();


    }

    /* ############################ Common  Methods to click and enter in text box and buttons############################## */

    public void clickById(String id) {


        driver.findElementById(id).click();


    }

    public void clickByXpath(String xpath) {

        driver.findElementByXPath(xpath).click();

    }

    public void enterById(String id, String data) {

        driver.findElementById(id).sendKeys(data);

    }

    public void enterByXpath(String xpath, String data) {

        driver.findElementByXPath(xpath).sendKeys(data);

    }

    /* ############################ switch window Methods ############################## */

    public void switchLastWindow() {

        Set<String> allwindow = driver.getWindowHandles();

        for (String next : allwindow) {
            driver.switchTo().window(next);

        }
    }

    /* ############################ Static & page wait Methods ############################## */

    public void implicitTime() {

        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public long threadWait(long time) throws InterruptedException {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;

    }


    /* ############################ Common wait Methods ############################## */

    public static void waitObjectAppearByHomeId(String idValue) {

        wait = new WebDriverWait(driver, 800);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(idValue)));


    }

    public static void waitObjectAppearById(String idValue) {

        wait = new WebDriverWait(driver, 180);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(idValue)));


    }

    public static void waitObjectAppearByIdAvailable(String idValue) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idValue)));


    }

    public static void waitObjectAppearByXpath(String xpathValue) {

        wait = new WebDriverWait(driver, 180);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathValue)));


    }

    public static void waitObjectAppearByXpathVisible(String xpathValue) {

        wait = new WebDriverWait(driver, 180);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathValue)));


    }

    /* ############################ check visibility method ############################## */

    //To check element is visible in the page or not

    public void checkVisibility(WebElement element) {
        wait = new WebDriverWait(driver, 15, 50);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /* ############################ Assession method ############################## */

    public void loginAssert(String title) throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().trim().equals(title), "User logged in successfully!! ");
        System.out.println("User logged in successfully");
    }

    public void requestAssert(String text) throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertTrue( driver.getPageSource().contains(text), "Consent Request sent successfully!! ");
        System.out.println("User logged in successfully");
    }

}



