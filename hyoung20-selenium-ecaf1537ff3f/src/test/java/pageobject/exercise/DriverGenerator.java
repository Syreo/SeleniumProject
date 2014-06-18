package pageobject.exercise;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
* DriverGenerator generates WebDrivers. The purpose is so that DriverGenerators
* can be stored in a list instead of actual WebDrivers, which then allows the
* WebDrivers to be instantiated one at a time.
*/
public abstract class DriverGenerator {
	 private static final String DEFAULT_HUB_URL = "http://localhost:4444/wd/hub";
       /**
       * Creates a WebDriver.
       * 
        * @return a WebDriver.
       */
       public abstract WebDriver generate();

           public static class Firefox extends DriverGenerator {
               /**
               * @see {@link WebDriver#generate()}
               */
               @Override
               public WebDriver generate() {
                      WebDriver driver = null;
                      try {
                            driver = new RemoteWebDriver(new URL(DEFAULT_HUB_URL), DesiredCapabilities.firefox());
                      } catch (MalformedURLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                      }
                      return driver;
               }

        }

           
           public static class Chrome extends DriverGenerator {
               /**
               * @see {@link WebDriver#generate()}
               */
               @Override
               public WebDriver generate() {
                      WebDriver driver = null;
                      
                      try {
                            driver = new RemoteWebDriver(new URL(DEFAULT_HUB_URL), DesiredCapabilities.chrome());
                      } catch (MalformedURLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                      }
                      return driver;
               }

        }

           
    }




