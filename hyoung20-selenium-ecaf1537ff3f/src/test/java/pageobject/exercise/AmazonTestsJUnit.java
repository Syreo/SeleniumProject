//package pageobject.exercise;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.commons.io.FileUtils;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
///**
// * Amazon Tests using JUnit
// * @author hyoung
// *
// */
//public class AmazonTestsJUnit {
//
//	private static WebDriver wb;
//	private static final String PASSWORD = "Password12!";
//	private static final String FIRST_NAME = "Lula";
//	private static final String LAST_NAME = "Jenkins";
//	private static String email = "";
//	private static final String ITEM = "Harry Potter";
//	private static final String FORMATTED_ITEM = "\""+ITEM+"\"";
//	private static final String SEARCH_ITEM = "Harry Potter";
//	private static final String PATH = "C:\\Users\\Hyoung\\workspace\\chromedriver_win32\\chromedriver.exe";
//	private static String RESULTS_URL = "";
//	
//	
//	
//	@BeforeClass
//    public static void initializeClass(){
//		
//		wb = new FirefoxDriver();
//     
//    }
//	
//	
//	@Test
//	public void testLogIn(){
//		HomePage hPage = new HomePage(wb);
//		SignInPage sPage = new SignInPage(wb);
//		hPage.open();
//		hPage.pressSignInButton();
//		sPage.enterEmail("fakeEmailForTest@Test.com");
//		sPage.enterPassword("Password12!");
//		sPage.submitForm();
//		assertEquals(wb.findElement(By.xpath("//*[@id='your-orders']")).getText(), "Your Orders");
//	}
//	
//	@Test
//	public void testSignInPage() {
//		HomePage hPage = new HomePage(wb);
//		hPage.open();
//		hPage = hPage.pressSignInButton();
//		assertEquals(wb.findElement(By.xpath("//*[@id='ap_signin1a_pagelet_title']/h1")).getText(), "Sign In");
//		
//	}
//
//	@Test
//	public void testSignUp(){
//		email = getRandomEmail();
//		SignInPage sPage = new SignInPage(wb);
//		sPage.open();
//		sPage.clickNewUserButton();
//		sPage.enterEmail(email);
//		sPage.submitForm();
//		assertEquals(wb.findElement(By.xpath("//*[@id='ap_register1a_pagelet_title']/h1")).getText(), "Registration");
//		
//	}
//
//	
//	@Test
//	public void testRegistration() {
//		email = getRandomEmail();
//		RegisterPage rPage = new RegisterPage(wb);
//		rPage.open();
//		rPage.enterPassword(PASSWORD);
//		rPage.enterEmail(email);
//		rPage.entryName(FIRST_NAME, LAST_NAME);
//		rPage.submitRegistration();
//		assertEquals(wb.findElement(By.xpath("//*[@id='nav-signin-title']")).getText(), "Hello, "+FIRST_NAME);
//		
//	}
//	
//	
//	@Test
//	public void testSearch(){
//		HomePage hPage = new HomePage(wb);
//		hPage.open();
//		hPage.typeInCheckBox(ITEM);
//		hPage.submitSearch();
//		assertEquals(wb.findElement(By.xpath("//*[@id='breadCrumb']")).getText(), FORMATTED_ITEM);
//	}
//
//	@Test
//	public void getSearchResultsAndReviews(){
//		SearchResultsPage resultsPage = new SearchResultsPage(wb);
//		resultsPage.getFirstResult(SEARCH_ITEM);
//		RESULTS_URL = wb.getCurrentUrl();
//		assertTrue(wb.getTitle().contains(SEARCH_ITEM));
//		System.out.println(RESULTS_URL);
//		resultsPage.getReviews(RESULTS_URL);
//		assertEquals(wb.findElement(By.xpath("//*[@class='crVS crLeft']")).getText(), "The most helpful favorable review");
//		
//	}
//
//	
//	
//	public String getRandomEmail(){
//		int random = 0 + (int)(Math.random() * ((10000 - 0) + 1));
//		String email = "fakeyfake"+random+"@fake.com";
//		return email;
//	}
//	
//}
