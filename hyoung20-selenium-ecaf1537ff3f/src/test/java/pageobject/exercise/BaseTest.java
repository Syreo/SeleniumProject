package pageobject.exercise;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class BaseTest {

       //Note: you will probably need to change this String on you local machine.
       public static final String CHROME_PATH = "C:\\Build\\WebDrivers\\chromedriver.exe";
       private static final String PATH = "C:\\Windows\\chromedriver.exe";
       public static final String CHROME_KEY = "webdriver.chrome.driver";
       
       public static final String DATA_PROVIDER = "Generator";

       // This function will provide the parameter data
       @DataProvider(name = DATA_PROVIDER)
       public Object[][] parameterIntTestProvider() {
              return new Object[][] { { new DriverGenerator.Firefox() },  {new DriverGenerator.Chrome() } };
       }
       
       // It's important that this is @BeforeClass, not @BeforeMethod!
       @BeforeClass
       public void beforeClass() {
              System.setProperty(CHROME_KEY, PATH);
       }
}
