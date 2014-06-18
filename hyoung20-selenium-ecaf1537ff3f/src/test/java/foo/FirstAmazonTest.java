package foo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstAmazonTest {
	private static final String HARRY_POTTER = "\"Harry Potter\"";
	
	/**
	 * First try at Selenium Tests for Amanzon.com
	 */
	@Test
	public void careerLinkTest() {
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.amazon.com/");
		WebElement careerLink = wb.findElement(By.xpath("//*[contains(@href, 'gp/jobs')]"));
		careerLink.click();
		WebElement searchText = wb.findElement(By.xpath("//*[@id='search-query-bar']//h2"));
		assertEquals(searchText.getText(), "Search for Jobs at Amazon");
		
	}
	
	@Test
	public void sellLinkTest() {
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.amazon.com/");
		WebElement sellLink = wb.findElement(By.xpath("//*[@class='nav_a'][text()='Sell on Amazon']"));
		sellLink.click();
		WebElement searchText = wb.findElement(By.xpath("//*[@class='no-margin']//*[text()='Sell on Amazon']"));
		assertEquals(searchText.getText(), "Sell on Amazon");
		
	}
	
	@Test
	public void BookLinkTest() {
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.amazon.com/");
		WebElement all = wb.findElement(By.xpath("//*[@id='nav-shop-all-button']"));
		all.click();
		WebElement sellLink = wb.findElement(By.xpath("//*[@id='nav_cat_7']"));
		sellLink.click();
		WebElement searchText = wb.findElement(By.xpath("//*[@class='nav_a nav_item'][text()='Books']"));
		assertEquals(searchText.getText(), "Books");
		searchText.click();
		WebElement bestBookOfYear = wb.findElement(By.xpath("//*[@id='leftNav']//a[text()='Best Books of the Year']"));
		assertEquals(bestBookOfYear.getText(), "Best Books of the Year");
	}

	@Test
	public void searchBoxTest() {
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.amazon.com/");
		WebElement all = wb.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
		all.sendKeys("Harry Potter");
		WebElement goButton = wb.findElement(By.xpath("//*[@class='nav-submit-input'][@value='Go']"));
		goButton.click();
		WebElement sellLink = wb.findElement(By.xpath("//*[@id='breadCrumb']"));
		assertEquals(sellLink.getText(), HARRY_POTTER);
	}
	
	
	


}
