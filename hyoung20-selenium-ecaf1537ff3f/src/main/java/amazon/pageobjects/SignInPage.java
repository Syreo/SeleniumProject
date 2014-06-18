package amazon.pageobjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Sign In Page Object for Amazon.com
 * @author hyoung
 *
 */
public class SignInPage extends PageBase{
	public static final By EMAIL_LOCATOR = By.xpath("//*[@id='ap_email']");
	public static final By PASSWORD_LOCATOR = By.xpath("//*[@id='ap_password']");
	public static final By NEW_USER_RADIO_BUTTON_LOCATOR = By.xpath("//*[@id='ap_signin_create_radio']");
	public static final By SUMBIT_BUTTON_LOCATOR = By.xpath("//*[@id='signInSubmit-input']");
	public static final By SIGN_IN_LABEL = By.xpath("//*[@id='ap_signin1a_pagelet_title']");
	public static final By ERROR_MESSAGE = By.xpath("//*[@id='message_error']");
	public static final By SKIP_SIGN_IN_BUTTON = By.xpath("//*[@id='cs-pagelet']//a[@class='amzn-btn btn-sec-sml']");
	
	
	
	public SignInPage(WebDriver driver) {
		super(driver);
		URL = "https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fcss%2Fhomepage.html%3Fie%3DUTF8%26ref_%3Dgno_yam_ya";
	}

	
	

}
