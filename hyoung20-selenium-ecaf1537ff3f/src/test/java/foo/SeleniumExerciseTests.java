package foo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumExerciseTests {


	
	@Test
	public void test() {
		WebDriver wb = new FirefoxDriver();
		wb.get("http://http://www.seleniumhq.org/");
	}
	
	@Test
	public void testClickLink(){
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.seleniumhq.org/");
		WebElement inputs = wb.findElement(By.xpath("//*[@href='/download/']"));
		inputs.click();
		wb.quit();
	}
	
	@Test
	public void testSearchBox(){
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.seleniumhq.org/");
		WebElement element = wb.findElement(By.id("q"));
		element.sendKeys("info");
		WebElement goButton = wb.findElement(By.id("submit"));
		assertEquals(goButton.getAttribute("value"), "Go");
		wb.quit();
		
	}
	
	@Test
	public void testMultiplesByXPath(){
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.seleniumhq.org/");
		List<WebElement>inputs = wb.findElements(By.xpath("//*"));
		inputs.get(0).click();	
		wb.quit();
	}
	
	@Test
	public void testTwitterThirdPost(){
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.twitter.com/towelthetank");
		List<WebElement>inputs = wb.findElements(By.xpath("//*[@class='js-stream-item stream-item stream-item expanding-stream-item']"));
		String post = inputs.get(2).getAttribute("data-item-id");
		List<WebElement> element = wb.findElements(By.xpath("//*[@data-tweet-id='"+post+"']//*[@class='expand-action-wrapper']"));
		element.get(0).click();		
	}
	
	/*
	 * Activity test for correct test on website
	 */
	@Test
	public void testCorrectText(){
		WebDriver wb = new FirefoxDriver();
		wb.get("http://games.adultswim.com/zombocalypse-2-action-online-game.html");
		WebElement header = wb.findElement(By.xpath("//*[@id='most-popular-games-column-1']/li[2]/a[2]/h3"));
		assertEquals("Robot Unicorn Attack", header.getText());
		wb.quit();
	}
	
	
	
	@Test
	public void testAdultSwimCards(){
		WebDriver wb = new FirefoxDriver();
		wb.get("http://www.adultswim.com");
		WebElement pageOne = wb.findElement(By.xpath("//a[contains(@href,'harvey-birdman')]"));
		pageOne.click();
		WebElement PageTwo = wb.findElement(By.xpath("//li[@class='break']//img[contains(@src, 'death-by-chocolate')]"));
		PageTwo.click();
		WebElement PageThree = wb.findElement(By.xpath("//*[@id='square-foot-contain']//a[contains(text(), 'Games')]"));
		PageThree.click();
		WebDriverWait wait = new WebDriverWait(wb, 1000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='zombocalypse-2']")));
		WebElement PageFour = wb.findElement(By.xpath("//*[@id='zombocalypse-2']"));
		PageFour.click();
		WebElement PageFive = wb.findElement(By.xpath("//*[@id='most-popular-games-column-1']/li[2]/a[2]/h3"));
		PageFive.click();
		WebElement PageSix = wb.findElement(By.xpath("//*[@id='most-popular-games-column-1']/li[2]/a[2]/h3"));
		assertEquals("Robot Unicorn Attack", PageSix.getText());
		wb.quit();
	}

}
