package amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends PageBase{

	public static final By CART_ITEMS = By.xpath("//*[@class='item-list']//*[@class='cart-item']");
	public static final By DELETE_CART_ITEM = By.xpath("//*[@class='app-button-small'][contains(text(), 'Delete')]");
	public static final By ITEM_PRICES = By.xpath("//*[@id='cart-active-items']//*[@class='cart-item']//*[@class='ourprice']");
	public static final By CART_SUBTOTAL = By.xpath("//*[@id='cart-subtotal']");
	public static final By CART_DELETE_MESSAGE = By.xpath("//*[@class='message']//*[@style='']");
	
	
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		
	}

}
