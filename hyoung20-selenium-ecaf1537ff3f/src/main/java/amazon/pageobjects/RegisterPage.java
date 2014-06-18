package amazon.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Register Page Object for Amazon.com
 * @author hyoung
 *
 */
public class RegisterPage extends PageBase{
public static final By PASSWORD = By.xpath("//*[@id='ap_password']");
public static final By PASSWORD_CHECK = By.xpath("//*[@id='ap_password_check']");
public static final By EMAIL = By.xpath("//*[@id='ap_email']");
public static final By EMAIL_CHECK = By.xpath("//*[@id='ap_email_check']");
public static final By NAME = By.xpath("//*[@id='ap_customer_name']");
public static final By REGISTER_BUTTON = By.xpath("//*[@id='continue-input']");

	public RegisterPage(WebDriver driver) {
		super(driver);
		 URL = "https://www.amazon.com/ap/register?ie=UTF8&openid.pape.max_auth_age=0&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&pageId=usflex&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&create=1&openid.assoc_handle=usflex&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fcss%2Fhomepage.html%3Fie%3DUTF8%26ref_%3Dgno_yam_ya";
	}

	
	public boolean registerNewUser(String email, String password, String firstName, String lastName){
		boolean isSuccessful = false;
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL)).sendKeys(email);
			wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_CHECK)).sendKeys(email);
			wait.until(ExpectedConditions.visibilityOfElementLocated(NAME)).sendKeys(firstName+" "+lastName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD)).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_CHECK)).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON)).click();
			isSuccessful = true;
		}catch(Exception e){
			//TODO put screenshot here
		}
		
		return isSuccessful;
	}
	
	
}
