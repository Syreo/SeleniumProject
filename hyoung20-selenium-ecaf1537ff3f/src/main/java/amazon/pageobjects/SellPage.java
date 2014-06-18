package amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SellPage extends PageBase{
	public static final By PROFESSIONAL_LINK = By.xpath("//*[@id='professional']//img");
	public static final By INDIVIDUAL_LINK = By.xpath("//*[@id='individual']//img");
	public static final By INDIVIDUAL_CAN_SELL_LINK = By.xpath("//*[@id='ViewElidible1']");
	public static final By INDIVIDUAL_SELL_ITEMS_TOOLTIP = By.xpath("//*[@id='tooltip1']//*[@class='Tooltiptable']");
	public static final By INDIVIDUAL_SELL_ITEMS_CONTENTS = By.xpath("//*[@id='tooltip1']//*[@class='Tooltiptable']//td");
	
	
	
	
	public SellPage(WebDriver driver) {
		super(driver);
		
	}

	
}
