package tests.mobiletests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tests.mobiletests.pageobjects.BasePageObjects;
import tests.mobiletests.utils.WaitUtils;

public class BasePage extends WaitUtils {

  BasePageObjects basePageObjects;

  public BasePage(AppiumDriver driver) {
    super(driver);
    basePageObjects = new BasePageObjects();
    PageFactory.initElements(new AppiumFieldDecorator(driver), basePageObjects);
  }

  public LoginPage moveSplashScreens() {
    System.out.println("INSIDE MOVE SPLASH SCREEN"+runner);
    runner.moveSplashScreens(basePageObjects);
    return new LoginPage(driver);
  }
}
