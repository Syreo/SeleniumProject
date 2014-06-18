package amazon.pageobjects;


import java.io.UnsupportedEncodingException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends PageBase{

	/**
	 * Homepage of Amazon.com
	 */
	
	private static final String HOMEPAGE_URL = "http://www.amazon.com";
	public static final By DAILY_DEALS_LOCATOR = By.xpath("//*[@class='nav-xs-link ']//a[contains(text(), 'Deals')]");
	public static final By LOG_OUT_BUTTON_LOCATOR = By.xpath("//*[@id='nav-item-signout']");
	public static final By SIGN_IN_LABEL = By.xpath("//*[@id='nav-signin-text']");
	public static final By ACCOUNT_BUTTON = By.xpath("//*[@id='nav-your-account']");
	public static final By DAILY_DEALS_LABEL = By.xpath("//*[@class='gbh1-bold']");
	public static final By CART_COUNT_LABEL = By.xpath("//*[@id='nav-cart-count']");
	public static final By SEARCH_BUTTON = By.xpath("//*[@class='nav-submit-input'][@value='Go']");
	public static final By SEARCH_FIELD = By.xpath("//*[@id='twotabsearchtextbox']");
	public static final By GIFT_CARD_LINK = By.xpath("//*[@id='nav-cross-shop-links']//a[contains(@href, 'gift-cards')]");
	public static final By HELP_LINK = By.xpath("//*[@id='nav-cross-shop-links']//a[contains(@href, 'Help')]");
	public static final By SEARCH_DROP_DOWN_BOX = By.xpath("//*[@id='nav-search-in']");
	public static final By SEARCH_AMAZOM_INSTANT = By.xpath("//*[@id='searchDropdownBox']//*[@value='search-alias=instant-video']");
	public static final By JAPAN_LINK = By.xpath("//*[@class='nav_a' and contains(text(), 'Japan')]");
	public static final By JAPAN_FOOTER_TEST = By.xpath("//*[@class='nav_first']//*[contains(@href, 'footer')]");
	public static final By SELL_LINK = By.xpath("//*[@id='nav-cross-shop-links']//*[@class='nav_a'][contains(@href, 'seller-account')]");
	public static final By SHOP_BY_DEPARTMENT_MENU = By.xpath("//*[@id='nav_cats']");
	public static final By TRY_PRIME_LINK = By.xpath("//*[@id='nav-prime-ttt']");
	public static final By PRIME_GET_STARTED_LINK = By.xpath("//*[@class='nav-npt-a']");
	public static final By VIEW_CART_BUTTON = By.xpath("//*[@id='nav-cart']");
	
	public HomePage(WebDriver driver) {
		super(driver);
		URL = HOMEPAGE_URL;
	}

	public HomePage logout(){
		driver.findElement(LOG_OUT_BUTTON_LOCATOR).click();
		return new HomePage(driver);
	}
	

	
	public boolean logIn(String email, String password){
		WebDriverWait wait = new WebDriverWait(driver, 2);
		boolean isLoginSuccessful = false;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_LABEL)).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(SignInPage.EMAIL_LOCATOR)).sendKeys(email);
			wait.until(ExpectedConditions.visibilityOfElementLocated(SignInPage.PASSWORD_LOCATOR)).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(SignInPage.SUMBIT_BUTTON_LOCATOR)).click();
			isLoginSuccessful = true;
		}catch(Exception e){
			//TODO screenshot
		}
		return isLoginSuccessful;
	}
	


	public String japaneseChars() throws UnsupportedEncodingException{
		WebDriverWait wait = new WebDriverWait(driver, 2);
		 List<WebElement>elementList = null;
		 elementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(JAPAN_FOOTER_TEST));
		 byte[] test = elementList.get(0).getText().getBytes("UTF-8");
		return null;
	}


}
