package amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelpPage extends PageBase{

	public static final By HELP_TOPICS = By.xpath("//*[@id='other-areas-content']//ul[@class='nav-topics']//a");
	public static final By CONTACT_US_BUTTON = By.xpath("//*[@id='displayLink']//a[@class='amzn-btn btn-prim-med-ra']");
	public static final By CONTACT_US_HELP_SELECTIONS = By.xpath("//*[@class='cs-step-start']//a[@class='origin anchor-radio-btn']");
	
	public HelpPage(WebDriver driver) {
		super(driver);
		
	}
}
