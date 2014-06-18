package amazon.pageobjects;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Base for Page Object Design Pattern
 * @author hyoung
 *
 */

public class PageBase {
	
	 protected String URL;        
     
     /** This page's WebDriver */ 
     protected WebDriver driver; 
     

     
     
     /** Constructor */ 
     public PageBase(WebDriver driver) {
             this.driver = driver; 
            
     }
     
     public void dragAndDrop(By locatorOne, By locatorTwo){
    	 WebDriverWait wait = new WebDriverWait(driver, 2);
    	 Actions builder = new Actions(driver);
    	 try {
    	 WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(locatorOne));
    	 Action act = builder.moveToElement(element1).build();
    	 act.perform();
    	 WebElement element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(locatorTwo));
    	 Action act2 = builder.moveToElement(element2).click().build();
    	 act2.perform();
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	
     }
     
     public boolean enterTextIntoElement(By locator, String text) {
 		WebDriverWait wait = new WebDriverWait(driver, 2);
 		boolean isSuccessful = false;
 		try {
 			wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
 					.clear();
 			wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
 					.sendKeys(text);
 			isSuccessful = true;
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return isSuccessful;
 	}
     
 	public boolean isLoggedIn(By locator, String expected){
		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement signIn = null;
		boolean loggedIn = false;
		try{
		signIn = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}catch(Exception e){
			e.printStackTrace();
		}
		if(!signIn.getText().equals(expected)){
			loggedIn = true;
		}
		return loggedIn;
	}
	
     
     public boolean clickElement(By locator) {
  		WebDriverWait wait = new WebDriverWait(driver, 2);
  		boolean isSuccessful = false;
  		
  		try {
  		
  			wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
  			isSuccessful = true;
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return isSuccessful;
  	}
     
     public String getElement(By locator){
    		WebDriverWait wait = new WebDriverWait(driver, 2);//TODO create constant for wait value
    		String element = null;
      		try {
      			
      			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
      		
      		} catch (Exception e) {
      			e.printStackTrace();
      		}
      		return element;
     }
     
     public String getArrayElementXPath(){
    	 return null;
     }
     
     public String getElementAttribute(By locator, String attribute){
 		WebDriverWait wait = new WebDriverWait(driver, 2);//TODO create constant for wait value
 		String element = null;
   		try {
   			
   			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(attribute);
   		
   		} catch (Exception e) {
   			//TODO screen shot
   		}
   		return element;
  }
     public List<String> getMultipleElements(By locator){
 		WebDriverWait wait = new WebDriverWait(driver, 2);
 		 List<WebElement>elementList = null;
 		 List<String>list = new ArrayList<String>();
   		try {
   			
   			elementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
   			for(int i = 0; i< elementList.size(); i++){
   				list.add(elementList.get(i).getText());
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   		return list;
  }
     
     public boolean clickWebElement(By locator){
    	 boolean isSuccessful = false;
    	 WebDriverWait wait = new WebDriverWait(driver, 2);
    	 try {
    		 wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    		 isSuccessful = true;
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 return isSuccessful;
     }
     
     

     
     /** Open the default page */ 
     public void open(){
             driver.get(URL); 
     }
     public void openURL(String URL){
    	 driver.get(URL);
     }
     
     
     /** Returns the default URL */ 
     public String getURL() {
    	String currentURL = driver.getCurrentUrl();
           return currentURL;
     }
     
     public static String getIPOfNode(RemoteWebDriver remoteDriver) { 
    	 String hostFound = null; 
    	 try { HttpCommandExecutor ce = (HttpCommandExecutor) remoteDriver.getCommandExecutor(); 
    	 String hostName = ce.getAddressOfRemoteServer().getHost(); 
    	 int port = ce.getAddressOfRemoteServer().getPort(); 
    	 HttpHost host = new HttpHost(hostName, port); 
    	 DefaultHttpClient client = new DefaultHttpClient(); 
    	 URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + remoteDriver.getSessionId()); 
    	 BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest( "POST", sessionURL.toExternalForm()); 
    	 HttpResponse response = client.execute(host, r); 
    	 JSONObject object = extractObject(response); 
    	 URL myURL = new URL(object.getString("proxyId")); 
    	 if ((myURL.getHost() != null) && (myURL.getPort() != -1)) { 
    		 hostFound = myURL.getHost(); 
    		 } 
    	 } catch (Exception e) { 
    		 System.err.println(e); 
    		 } return hostFound; 
    		 }
     
     
     private static JSONObject extractObject(HttpResponse resp) throws IOException, JSONException { 
    	 InputStream contents = resp.getEntity().getContent(); 
    	 StringWriter writer = new StringWriter(); 
    	 IOUtils.copy(contents, writer, "UTF8"); 
    	 JSONObject objToReturn = new JSONObject(writer.toString()); 
    	 return objToReturn; 
    	 }
}
