package amazon.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DealPage extends PageBase{

	public static final By ADD_DEAL_TO_CART_BUTTON = By.xpath("//*[@id='dealActionButton']//img[contains(@src, 'add-to-cart')]"); 
	public static final By DAILY_DEALS_LABEL = By.xpath("//*[@class='gbh1-bold']");
	public static final By PROCEED_TO_CHECKOUT_BUTTON = By.xpath("//*[@id='hlb-next-steps']//a[@class='hucSprite s_checkout hlb-checkout-button']");
	public static final By DAILY_DEALS_WITH_CART_BUTTON = By.xpath("//*[contains(@id, 'deals-featured')]//li//*[@id='dealActionButton']//img[contains(@src, 'add-to-cart')]");
	public static final By DAILY_DEALS_CART_POPUP = By.xpath("//*[@class='cart-popover-label']");
	public static final By DAILY_DEAL_CART_POPUP_BUTTON = By.xpath("//*[@class='inner-cart-popover-table']//img");
	public static final By RIGHT_ARROW = By.xpath("//*[@id='rightShovelBg']");
	public static final By CLOSE_POPUP = By.xpath("//*[@class='ap_closebutton']");
	public static final By EDIT_CART_BUTTON = By.xpath("//*[@class='hucSprite s_editCart hlb-cart-button']");
	public static final By REGULAR_DEAL_ITEMS = By.xpath("//*[contains(@id, 'deals-bd')]//li//*[@id='dealActionButton']//img[contains(@src, 'add-to-cart')]");
	public static final By LIGHTNING_DEALS_LABEL = By.xpath("//*[contains(@id, 'deals-featured')]//div[@class='gbh2cont']");
	public static final By POPUP_EXIT_BUTTON = By.xpath("//*[@class='ap_closebutton']");
	
	
	
	public DealPage(WebDriver driver){
		super(driver);
		URL = "http://www.amazon.com/gp/goldbox/ref=cs_top_nav_gb27";
	}
	
	public boolean clickOnDailyDeal(){
		WebDriverWait wait = new WebDriverWait(driver, 2);
		List<WebElement>dailyDeals = new ArrayList<WebElement>();
		boolean isSuccessful = false;
		try{
			while(dailyDeals.size() < 1){
				dailyDeals = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DAILY_DEALS_WITH_CART_BUTTON));
				WebElement rightArrow = wait.until(ExpectedConditions.visibilityOfElementLocated(RIGHT_ARROW));
				rightArrow.click();
			}
			dailyDeals.get(0).click();
		}catch(Exception e){
			
		}
		
		return isSuccessful;
		}
	
	
}
