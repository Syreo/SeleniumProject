package pageobject.exercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import amazon.pageobjects.CartPage;
import amazon.pageobjects.DealPage;
import amazon.pageobjects.HelpPage;
import amazon.pageobjects.HomePage;
import amazon.pageobjects.PageBase;
import amazon.pageobjects.RegisterPage;
import amazon.pageobjects.SearchResultsPage;
import amazon.pageobjects.SellPage;
import amazon.pageobjects.SignInPage;


/**
 * Amazon Selenium Tests with TestNG 
 * @author hyoung
 *
 */


public class AmazonSeleniumTest extends BaseTest{

	private static final String TEST_EMAIL = "fakeEmailForTest@Test.com";
	private static final String TEST_PASSWORD = "Password12!";
	private static final String TEST_FIRSTNAME = "Lula";
	private static final String TEST_LASTNAME = "Jenkins";
	private static String email = "";
	private static final String ITEM = "Harry Potter";
	private static final String FORMATTED_ITEM = "\""+ITEM+"\"";
	private static final String SEARCH_ITEM = "Harry Potter";
	private static String RESULTS_URL = "";
	private static final String IP_ADDRESS= "192.168.0.57";
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testIpAddress(DriverGenerator generator) throws MalformedURLException{
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://"+IP_ADDRESS+":4444/wd/hub"), cap);
		
		String ipAddress = PageBase.getIPOfNode(driver);
		System.out.println("IP Address: "+ ipAddress);
		assertTrue(ipAddress.equals(IP_ADDRESS));
		driver.quit();
	}
	

	@Test(dataProvider = DATA_PROVIDER)
	public void test(DriverGenerator generator){
		WebDriver wb = generator.generate();
		
		HomePage hPage = new HomePage(wb);
		hPage.open();
		hPage.dragAndDrop(HomePage.TRY_PRIME_LINK, HomePage.PRIME_GET_STARTED_LINK);
		String url = hPage.getURL();
		assertTrue(url.equals("http://www.amazon.com/dp/B00DBYBNEE?_encoding=UTF8&ref_=nav_tooltip_redirect"));
		wb.quit();
	}
	
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testLogIn(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		SignInPage sPage = new SignInPage(wb);
		hPage.open();
		boolean isClickGood = hPage.clickElement(HomePage.ACCOUNT_BUTTON);
		boolean isSuccessful = hPage.logIn(TEST_EMAIL, TEST_PASSWORD);
		boolean isLoggedIn = sPage.isLoggedIn(HomePage.SIGN_IN_LABEL, "Sign in");
		assertTrue(isSuccessful);
		assertTrue(isLoggedIn);
		assertTrue(isClickGood);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testSignInPage(DriverGenerator generator) {
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean isSuccessful = hPage.clickElement(HomePage.ACCOUNT_BUTTON);
		String signInText = hPage.getElement(SignInPage.SIGN_IN_LABEL);
		assertEquals(signInText, "Sign In");
		assertTrue(isSuccessful);
		wb.quit();
	}

	@Test(dataProvider = DATA_PROVIDER)
	public void testSignUp(DriverGenerator generator){
		WebDriver wb = generator.generate();
		email = getRandomEmail();
		SignInPage sPage = new SignInPage(wb);
		sPage.open();
		boolean buttonSuccessful = sPage.clickElement(SignInPage.NEW_USER_RADIO_BUTTON_LOCATOR);
		boolean enterEmailSuccess = sPage.enterTextIntoElement(SignInPage.EMAIL_LOCATOR, email);
		boolean sumbutSuccess = sPage.clickElement(SignInPage.SUMBIT_BUTTON_LOCATOR);
		assertTrue(buttonSuccessful);
		assertTrue(enterEmailSuccess);
		assertTrue(sumbutSuccess);
		wb.quit();
	}

	
	@Test(dataProvider = DATA_PROVIDER)
	public void testRegistration(DriverGenerator generator) {
		WebDriver wb = generator.generate();
		email = getRandomEmail();
		RegisterPage rPage = new RegisterPage(wb);
		rPage.open();
		boolean isSuccessful = rPage.registerNewUser(TEST_EMAIL, TEST_PASSWORD, TEST_FIRSTNAME, TEST_LASTNAME);
		boolean isLoggedIn =  rPage.isLoggedIn(HomePage.SIGN_IN_LABEL, "Sign in");
	    assertTrue(isSuccessful);
	    assertTrue(isLoggedIn);
		wb.quit();
	}
	
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testSearch(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		hPage.enterTextIntoElement(HomePage.SEARCH_FIELD, ITEM);
		hPage.clickElement(HomePage.SEARCH_BUTTON);
		assertEquals(wb.findElement(SearchResultsPage.SEARCH_RESULTS_TITLE).getText(), FORMATTED_ITEM);
		wb.quit();
	}

	@Test(dataProvider = DATA_PROVIDER)
	public void getSearchResultsAndReviews(DriverGenerator generator){
		WebDriver wb = generator.generate();
		SearchResultsPage resultsPage = new SearchResultsPage(wb);
		resultsPage.open();
		resultsPage.getFirstResult(SEARCH_ITEM);
		RESULTS_URL = wb.getCurrentUrl();
		assertTrue(wb.getTitle().contains(SEARCH_ITEM));
		resultsPage.getReviews(RESULTS_URL);
		assertEquals(wb.findElement(By.xpath("//*[@class='crVS crLeft']")).getText(), "The most helpful favorable review");//TODO Fix this
		wb.quit();
	}

	@Test(dataProvider = DATA_PROVIDER)
	public void testResizeBrowser(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		wb.manage().window().setSize(new Dimension(700, 900));
		wb.manage().window().setPosition(new Point(25,25));
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testCartCount(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		String cartCount = hPage.getElement(HomePage.CART_COUNT_LABEL);
		assertEquals("0", cartCount);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testDailyDeals(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean clickSuccess = hPage.clickElement(HomePage.DAILY_DEALS_LOCATOR);
		assertEquals("Today's Deals.", wb.findElement(HomePage.DAILY_DEALS_LABEL).getText());
		assertTrue(clickSuccess);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testDailyDealsCartButton(DriverGenerator generator){
		WebDriver wb = generator.generate();
		DealPage dPage = new DealPage(wb);
		dPage.open();
		String element = dPage.getElementAttribute(DealPage.ADD_DEAL_TO_CART_BUTTON, "title");
		assertEquals("Add to cart", element);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testAddToCartSignInPrompt(DriverGenerator generator){
		WebDriver wb = generator.generate();
		DealPage dPage = new DealPage(wb);
		dPage.open();
		boolean buttonClick = dPage.clickElement(DealPage.REGULAR_DEAL_ITEMS);
		boolean checkoutClick = dPage.clickElement(DealPage.PROCEED_TO_CHECKOUT_BUTTON);
		String element = dPage.getElement(SignInPage.SIGN_IN_LABEL);
		assertTrue(buttonClick);
		assertTrue(checkoutClick);
		assertEquals("Sign In", element);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testGiftCardOptions(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean buttonClick = hPage.clickElement(HomePage.GIFT_CARD_LINK);
		boolean linkClick = hPage.clickElement(SearchResultsPage.GIFT_CARD_LINK_100_200);
		String emailClick = hPage.getElement(SearchResultsPage.GIFT_CARD_EMAIL);
		String mailClick = hPage.getElement(SearchResultsPage.GIFT_CARD_MAIL);
		String printClick = hPage.getElement(SearchResultsPage.GIFT_CARD_PRINT);
		assertTrue(buttonClick);
		assertTrue(linkClick);
		assertEquals("E-mail", emailClick);
		assertEquals("Mail", mailClick);
		assertEquals("Print at Home", printClick);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testHelpTopics(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean helpClick = hPage.clickElement(HomePage.HELP_LINK);
		List<String>elementList = hPage.getMultipleElements(HelpPage.HELP_TOPICS);
		assertTrue(helpClick);
		assertEquals("11", String.valueOf(elementList.size()));
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testContactUsLoginPrompt(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean helpClick = hPage.clickElement(HomePage.HELP_LINK);
		boolean contactUsClick = hPage.clickElement(HelpPage.CONTACT_US_BUTTON);
		String element = hPage.getElement(SignInPage.SIGN_IN_LABEL);
		assertTrue(helpClick);
		assertTrue(contactUsClick);
		assertEquals("Sign In", element);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testPasswordErrorMessage(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean signInClick = hPage.clickElement(HomePage.ACCOUNT_BUTTON);
		hPage.enterTextIntoElement(SignInPage.EMAIL_LOCATOR, "fakeEmail12@fake.com");
		boolean submitClick = hPage.clickElement(SignInPage.SUMBIT_BUTTON_LOCATOR);
		String element = hPage.getElement(SignInPage.ERROR_MESSAGE);
		assertTrue(signInClick);
		assertTrue(submitClick);
		assertTrue(element.contains("Please enter your password."));
		wb.quit();
		
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testEmailErrorMessage(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean signInClick = hPage.clickElement(HomePage.ACCOUNT_BUTTON);
		hPage.enterTextIntoElement(SignInPage.EMAIL_LOCATOR, "fakeEmail12@fake.com");
		hPage.enterTextIntoElement(SignInPage.PASSWORD_LOCATOR, "Password");
		boolean submitClick = hPage.clickElement(SignInPage.SUMBIT_BUTTON_LOCATOR);
		String element = hPage.getElement(SignInPage.ERROR_MESSAGE);
		assertTrue(signInClick);
		assertTrue(submitClick);
		assertTrue(element.contains("There was an error with your E-Mail/Password combination. Please try again."));
		wb.quit();
		
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testFooterLink(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean linkClick = hPage.clickElement(HomePage.JAPAN_LINK);
		assertTrue(linkClick);
		assertEquals("http://www.amazon.co.jp/", wb.getCurrentUrl());
		wb.quit();
	}
	
//	@Test(dataProvider = DATA_PROVIDER)
	public void testJapaneseCharacters(DriverGenerator generator) throws UnsupportedEncodingException{
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean linkClick = hPage.clickElement(HomePage.JAPAN_LINK);
		List<String> element = hPage.getMultipleElements(HomePage.JAPAN_FOOTER_TEST);
		String japaneseRegex = "[一-龯]";
		byte[] jis = element.get(0).getBytes();
		String decoded = new String(jis, "UTF-8");
		System.out.println(decoded);
		assertTrue(linkClick);
		assertTrue(element.get(0).matches(japaneseRegex));
		
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testMyAccountSignInPrompt(DriverGenerator generator) throws UnsupportedEncodingException{
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean accountClick = hPage.clickElement(HomePage.ACCOUNT_BUTTON);
		String element =  hPage.getElement(SignInPage.SIGN_IN_LABEL);
		assertTrue(accountClick);
		assertEquals(element, "Sign In");
		wb.quit();
	}
	@Test(dataProvider = DATA_PROVIDER)
	public void testLightningDealsLabel(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean dealsClick = hPage.clickElement(HomePage.DAILY_DEALS_LOCATOR);
		String element = hPage.getElement(DealPage.LIGHTNING_DEALS_LABEL);
		assertTrue(dealsClick);
		assertEquals("Today's Lightning Deals", element);
		wb.quit();
	}
	@Test(dataProvider = DATA_PROVIDER)
	public void testGiftCardPage(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean giftCardClick = hPage.clickElement(HomePage.GIFT_CARD_LINK);
		String currentURL = wb.getCurrentUrl();
		assertTrue(giftCardClick);
		assertTrue(currentURL.contains("gift-cards"));
		wb.quit();
	}
	@Test(dataProvider = DATA_PROVIDER)
	public void testProfessionalSellButton(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean sellButton = hPage.clickElement(HomePage.SELL_LINK);
		String element = hPage.getElementAttribute(SellPage.PROFESSIONAL_LINK, "src");
		assertTrue(sellButton);
		assertTrue(element.contains("proButton"));
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testIndividualSellButton(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean sellButton = hPage.clickElement(HomePage.SELL_LINK);
		String element = hPage.getElementAttribute(SellPage.INDIVIDUAL_LINK, "src");
		assertTrue(sellButton);
		assertTrue(element.contains("individual"));
		wb.quit();
	}
	@Test(dataProvider = DATA_PROVIDER)
	public void testIndividualSellButtonPrompt(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean sellButton = hPage.clickElement(HomePage.SELL_LINK);
		boolean individualButtonClick = hPage.clickElement(SellPage.INDIVIDUAL_LINK);
		String url = wb.getCurrentUrl();
		assertTrue(sellButton);
		assertTrue(individualButtonClick);
		assertTrue(url.contains("sign-in"));
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testIndividualCanSellButtonPrompt(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean sellButton = hPage.clickElement(HomePage.SELL_LINK); 
		boolean individualButtonClick = hPage.clickElement(SellPage.INDIVIDUAL_CAN_SELL_LINK);
		List<String>toolTipList = hPage.getMultipleElements(SellPage.INDIVIDUAL_SELL_ITEMS_CONTENTS);
		assertTrue(sellButton);
		assertTrue(individualButtonClick);
		assertTrue(toolTipList.get(0).contains("Amazon Kindle"));
		assertTrue(toolTipList.get(10).contains("Tools & Home Improvement"));
		assertTrue(toolTipList.get(15).contains("Home & Garden (including Pet Supplies)"));
		assertTrue(toolTipList.get(20).contains("Everything Else"));
		wb.quit();
	}
	@Test(dataProvider = DATA_PROVIDER)
	public void testShopByDepartmentScreenSizeDisappears(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		wb.manage().window().maximize();
		String element = hPage.getElement(HomePage.SHOP_BY_DEPARTMENT_MENU);
		wb.manage().window().setSize(new Dimension(800, 800));
		String element2 = hPage.getElement(HomePage.SHOP_BY_DEPARTMENT_MENU);
		assertTrue(element != null);
		assertTrue(element2 == null);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testShopByDepartmentScreenSizeExists(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		wb.manage().window().maximize();
		String element = hPage.getElement(HomePage.SHOP_BY_DEPARTMENT_MENU);
		assertTrue(element != null);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testContactUsHelpScreenSkipSignIn(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean clickHelp = hPage.clickElement(HomePage.HELP_LINK);
		boolean clickContactUs = hPage.clickElement(HelpPage.CONTACT_US_BUTTON);
		boolean clickSkipSignIn = hPage.clickElement(SignInPage.SKIP_SIGN_IN_BUTTON);
		List<String>helpElements = hPage.getMultipleElements(HelpPage.CONTACT_US_HELP_SELECTIONS);
		assertTrue(clickHelp);
		assertTrue(clickContactUs);
		assertTrue(clickSkipSignIn);
		assertTrue(helpElements.size() == 4);
		wb.quit();
		
	}
	
	//Logged in tests below
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testAddCartPrompt(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		DealPage dPage = new DealPage(wb);
		hPage.open();
		hPage.logIn(TEST_EMAIL, TEST_PASSWORD);//TODO Redo login method to take in a locator for each field
		hPage.clickElement(SignInPage.SUMBIT_BUTTON_LOCATOR);
		hPage.clickElement(HomePage.DAILY_DEALS_LOCATOR);
		boolean dealClick = dPage.clickOnDailyDeal();
		String element = hPage.getElement(DealPage.DAILY_DEALS_CART_POPUP);
		assertTrue(dealClick);
		assertEquals("1 deal added to Cart", element);
		wb.quit();
	}
	
//	//@Test(dataProvider = DATA_PROVIDER)
//	public void testAddCartPromptClickProceed(DriverGenerator generator){
//		WebDriver wb = generator.generate();
//		HomePage hPage = new HomePage(wb);
//		hPage.open();
//		hPage.logIn(TEST_EMAIL, TEST_PASSWORD);//TODO Redo login method to take in a locator for each field
//		hPage.clickElement(SignInPage.SUMBIT_BUTTON_LOCATOR);
//		hPage.clickElement(HomePage.DAILY_DEALS_LOCATOR);
//		List<WebElement>elements = hPage.getMultipleElements(DealPage.DAILY_DEALS_WITH_CART_BUTTON);
//		boolean clickElement = hPage.clickWebElement(elements.get(0));
//		boolean clickCheckOut = hPage.clickElement(DealPage.DAILY_DEAL_CART_POPUP_BUTTON);
//		List<WebElement>cartItems = hPage.getMultipleElements(CartPage.CART_ITEMS);
//		while(cartItems.size() == 0){
//			hPage.clickElement(DealPage.RIGHT_ARROW);
//		cartItems = hPage.getMultipleElements(CartPage.CART_ITEMS);
//		}
//		hPage.clickElement(CartPage.DELETE_CART_ITEM);
//		assertTrue(clickElement);
//		assertTrue(clickCheckOut);
//		assertTrue(cartItems.size() < 2);
//		wb.quit();
//	}
//	//@Test(dataProvider = DATA_PROVIDER)
//	public void testAddTwoItemsToCartTotal(DriverGenerator generator){
//		WebDriver wb = generator.generate();
//		HomePage hPage = new HomePage(wb);
//		int total = 0;
//		hPage.open();
//		hPage.logIn(TEST_EMAIL, TEST_PASSWORD);//TODO Redo login method to take in a locator for each field
//		hPage.clickElement(SignInPage.SUMBIT_BUTTON_LOCATOR);
//		hPage.clickElement(HomePage.DAILY_DEALS_LOCATOR);
//		boolean buttonClick = hPage.clickElement(DealPage.REGULAR_DEAL_ITEMS);
//		wb.getPageSource();
//		boolean buttonClickTwo = hPage.clickElement(DealPage.REGULAR_DEAL_ITEMS);
//		boolean editCartClick = hPage.clickElement(DealPage.EDIT_CART_BUTTON);
//		List<WebElement>cartItems = hPage.getMultipleElements(CartPage.ITEM_PRICES);
//		WebElement totalPrice = hPage.getElement(CartPage.CART_SUBTOTAL);
//		System.out.println("cart items: "+ cartItems.size());
//		for(int i = 0; i < cartItems.size(); i++){
//			total = 0;
//			total += Integer.parseInt(cartItems.get(i).getText());
//		}
//		System.out.println("total: "+total+" subtotal: "+totalPrice.getText());
//		assertTrue(buttonClick);
//		assertTrue(buttonClickTwo);
//		assertTrue(editCartClick);
//		assertEquals(total, totalPrice.getText());
//	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testSearchDropDown(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean dropDownButton = hPage.clickElement(HomePage.SEARCH_DROP_DOWN_BOX);
		boolean selectDepartment = hPage.clickElement(HomePage.SEARCH_AMAZOM_INSTANT);
		String element = hPage.getElement(HomePage.SEARCH_AMAZOM_INSTANT);
		assertTrue(dropDownButton);
		assertTrue(selectDepartment);
		assertEquals("Amazon Instant Video", element);
		wb.quit();
	}
	@Test(dataProvider = DATA_PROVIDER)
	public void testDealPageExitButton(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean clickSignInButton = hPage.clickElement(HomePage.SIGN_IN_LABEL);
		boolean signIn = hPage.logIn(TEST_EMAIL, TEST_PASSWORD);
		boolean clickTodaysDeals = hPage.clickElement(HomePage.DAILY_DEALS_LOCATOR);
		boolean clickDeal = hPage.clickElement(DealPage.DAILY_DEALS_WITH_CART_BUTTON);
		boolean clickExitButton = hPage.clickElement(DealPage.POPUP_EXIT_BUTTON);
		assertTrue(clickSignInButton);
		assertTrue(signIn);
		assertTrue(clickTodaysDeals);
		assertTrue(clickDeal);
		assertTrue(clickExitButton);
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testSignInGreeting(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		hPage.open();
		boolean signIn = hPage.logIn(TEST_EMAIL, TEST_PASSWORD);
		String element = hPage.getElement(HomePage.SIGN_IN_LABEL);
		assertTrue(signIn);
		assertTrue(element.equals(TEST_FIRSTNAME));
		wb.quit();
	}
	
	@Test(dataProvider = DATA_PROVIDER)
	public void testDeleteItemMessage(DriverGenerator generator){
		WebDriver wb = generator.generate();
		HomePage hPage = new HomePage(wb);
		DealPage dPage = new DealPage(wb);
		dPage.open();
		boolean signIn = hPage.logIn(TEST_EMAIL, TEST_PASSWORD);
		boolean buttonClick = dPage.clickElement(DealPage.REGULAR_DEAL_ITEMS);
		boolean clickCart = dPage.clickElement(HomePage.VIEW_CART_BUTTON);
		boolean clickDelete = dPage.clickElement(CartPage.DELETE_CART_ITEM);
		String element = dPage.getElement(CartPage.CART_DELETE_MESSAGE);
		assertTrue(signIn);
		assertTrue(clickCart);
		assertTrue(clickDelete);
		assertTrue(element.contains("was removed from Shopping Cart."));
		wb.quit();
	}
	
	
	public String getRandomEmail(){
		int random = 0 + (int)(Math.random() * ((10000 - 0) + 1));
		String email = "fakeyfake"+random+"@fake.com";
		return email;
	}
	
	
	
}
