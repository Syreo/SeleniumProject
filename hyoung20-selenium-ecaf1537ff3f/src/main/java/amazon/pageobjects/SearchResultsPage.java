package amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Search Results Page Object for Amazon.com
 * @author hyoung
 *
 */
public class SearchResultsPage extends PageBase{

	public static final By GIFT_CARD_LINK_100_200 = By.xpath("//*[@id='ref_2661611011']//*[contains(text(), '$100 to $200')]");
	public static final By GIFT_CARD_EMAIL = By.xpath("//*[@id='ref_2740963011']//*[contains(text(), 'E-mail')]");
	public static final By GIFT_CARD_MAIL = By.xpath("//*[@id='ref_2740963011']//*[contains(text(), 'Mail')]");
	public static final By GIFT_CARD_PRINT = By.xpath("//*[@id='ref_2740963011']//*[contains(text(), 'Print at Home')]");
	public static final By SEARCH_RESULTS_TITLE = By.xpath("//*[@id='breadCrumb']");
	
	
	 public SearchResultsPage(WebDriver driver) {
		super(driver);
		URL = "http://www.amazon.com";
	}

	public void getFirstResult(String searchItem){
		URL = "http://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords="+searchItem+"";
		driver.navigate().to(URL);
		driver.findElement(By.xpath("//*[@id='result_1']//*[@class='newaps']//a")).click();
		 
	}
	
	public SearchResultsPage getReviews(String productURL){
		WebDriverWait wait = new WebDriverWait(driver,5);
		URL = productURL;
		driver.navigate().to(productURL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='revSAR']")));
		driver.findElement(By.xpath("//*[@id='revSAR']")).click();
		return new SearchResultsPage(driver);
	}
	

}
