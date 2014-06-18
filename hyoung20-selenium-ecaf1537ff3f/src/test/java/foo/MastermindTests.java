package foo;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MastermindTests {

	/**
	 * Selenium tests for Mastermind game
	 */
	private static final String RED = "//img[@src='images/color_1.gif']";
	private static final String GREEN = "//img[@src='images/color_2.gif']";
	private static final String PURPLE = "//img[@src='images/color_3.gif']";
	private static final String YELLOW = "//img[@src='images/color_4.gif']";
	private static final String BROWN = "//img[@src='images/color_5.gif']";
	private static final String ORANGE = "//img[@src='images/color_6.gif']";
	private static final String BLACK = "//img[@src='images/color_7.gif']";
	private static final String WHITE = "//img[@src='images/color_8.gif']";
	private static final String CHECK_BUTTON = "//*[contains(@id, 'Btn')][@style='visibility: visible;' or @style='visibility:show;']";
	private List<String>colorList = new ArrayList<String>();
	private int max = 7;
	private static final String SRC = "src";
	private static final String URL = "http://www.web-games-online.com/mastermind/index.php";
	
	
	@Test
	public void testMove(){
		WebDriver wb = new FirefoxDriver();
		wb.get(URL);
		int count = 10;
		while(count != 0){
		max = 7;
		colorList = new ArrayList<String>();
		setUpArray();
		WebElement firstColor = wb.findElement(By.xpath(randomColor()));
		WebElement secondColor = wb.findElement(By.xpath(randomColor()));
		WebElement thirdColor = wb.findElement(By.xpath(randomColor()));
		WebElement fourthColor = wb.findElement(By.xpath(randomColor()));
		WebElement Row10Col1 = wb.findElement(By.xpath("//*[@id='Row"+count+"']//td[1]//img"));
		WebElement Row10Col2 = wb.findElement(By.xpath("//*[@id='Row"+count+"']//td[2]//img"));
		WebElement Row10Col3 = wb.findElement(By.xpath("//*[@id='Row"+count+"']//td[3]//img"));
		WebElement Row10Col4 = wb.findElement(By.xpath("//*[@id='Row"+count+"']//td[4]//img"));
		WebElement CheckButton = wb.findElement(By.xpath(CHECK_BUTTON));
		firstColor.click();
		Row10Col1.click();
		secondColor.click();
		Row10Col2.click();
		thirdColor.click();
		Row10Col3.click();
		fourthColor.click();
		Row10Col4.click();
		CheckButton.click();
		if(isAlertPresent(wb)){
			break;
		}
		assertTrue(Row10Col1.getAttribute(SRC).contains(firstColor.getAttribute(SRC)));
		assertTrue(Row10Col2.getAttribute(SRC).contains(secondColor.getAttribute(SRC)));
		assertTrue(Row10Col3.getAttribute(SRC).contains(thirdColor.getAttribute(SRC)));
		assertTrue(Row10Col4.getAttribute(SRC).contains(fourthColor.getAttribute(SRC)));
		count--;
		}
		
	}
	
	
	public String randomColor(){
		int random = 0 + (int)(Math.random() * ((max - 0) + 1));
		String randomColor = colorList.get(random);
		colorList.remove(random);
		max = max - 1;
		
		return randomColor;
	}
	
	public void setUpArray(){
		colorList.add(RED);
		colorList.add(GREEN);
		colorList.add(PURPLE);
		colorList.add(YELLOW);
		colorList.add(BROWN);
		colorList.add(ORANGE);
		colorList.add(BLACK);
		colorList.add(WHITE);
	}
	
	


	public boolean isAlertPresent(WebDriver wb){
	    try
	    {
	        Alert alert = wb.switchTo ().alert ();
	        //alert is present
	        System.out.println(alert.getText());
	        alert.accept(); 
	        return true;
	    }
	    catch (NoAlertPresentException n)
	    {
	        //Alert isn't present
	        return false; 
	    }
	
}
}
