package foo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorTest {
/**
 * Selenium Tests for Google calculator
 */
	private static final String SEVEN_LOCATOR = "//*[@id = 'cwbt13']//*[@class= 'cwbts']";
	private static final String PLUS_LOCATOR = "//*[@id = 'cwbt46']//*[@class= 'cwbts']";
	private static final String EQUALS_LOCATOR = "//*[@id = 'cwbt45']//*[@class= 'cwbts']";
	private static final String INPUT_BOX_LOCATOR = "//*[@id='cwos']";
	private static final String CLEAR_LOCATOR = "//*[@id = 'cwclrbtnCE']";
	private static final String SEVEN = "7";
	private static final String FOURTEEN = "14";
	private static final String WEBPAGE_URL = "http://www.google.com/#q=calculator";
	private static final String ZERO = "0";
	
	/*
	 * Gets the Seven button element
	 */
	@Test
	public void testClickSeven() {
		
		WebDriver wb = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(wb, 1000);
		wb.get(WEBPAGE_URL);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(SEVEN_LOCATOR)));
		WebElement elementSeven = wb.findElement(By.xpath(SEVEN_LOCATOR));
		assertEquals(SEVEN, elementSeven.getText());
		wb.quit();
	}
	/*
	 * Tests that the input box displays the numbers correctly
	 */
	
	@Test
	public void testInputBox() {
		
		WebDriver wb = new FirefoxDriver();
		wb.get(WEBPAGE_URL);
		WebElement elementInputBox = wb.findElement(By.xpath(INPUT_BOX_LOCATOR));
		WebElement elementSeven = wb.findElement(By.xpath(SEVEN_LOCATOR));
		elementSeven.click();
		assertEquals(SEVEN, elementInputBox.getText());
		wb.quit();
	}
	/*
	 * Tests the addition functionality
	 */
	@Test
	public void testAddition() {
		WebDriver wb = new FirefoxDriver();
		wb.get(WEBPAGE_URL);
		WebElement elementInputBox = wb.findElement(By.xpath(INPUT_BOX_LOCATOR));
		WebElement elementSeven = wb.findElement(By.xpath(SEVEN_LOCATOR));
		WebElement elementPlus = wb.findElement(By.xpath(PLUS_LOCATOR));
		WebElement elementEquals = wb.findElement(By.xpath(EQUALS_LOCATOR));
		elementSeven.click();
		elementPlus.click();
		elementSeven.click();
		elementEquals.click();
		assertEquals(FOURTEEN, elementInputBox.getText());
		wb.quit();
	}
	
	/*
	 * Tests that the clear button clears correctly
	 */
	
	@Test
	public void testClearInput(){
		WebDriver wb = new FirefoxDriver();
		wb.get(WEBPAGE_URL);
		WebElement elementInputBox = wb.findElement(By.xpath(INPUT_BOX_LOCATOR));
		WebElement elementSeven = wb.findElement(By.xpath(SEVEN_LOCATOR));
		WebElement elementPlus = wb.findElement(By.xpath(PLUS_LOCATOR));
		WebElement elementClear = wb.findElement(By.xpath(CLEAR_LOCATOR));
		elementSeven.click();
		elementPlus.click();
		elementClear.click();
		assertTrue(elementInputBox.getText().equals(SEVEN));
		elementClear.click();
		assertTrue(elementInputBox.getText().equals(ZERO));
		wb.quit();
	}

}
