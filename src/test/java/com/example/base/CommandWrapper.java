package com.example.base;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class CommandWrapper {
	
	private static final String Configuration = ThreadLocalSelenium.getTestVars().TestConfiguration;
	private static org.apache.log4j.Logger log = Logger.getLogger("DebugInfo");
	
	public static void Log(Object message)throws InterruptedException, ParseException {
		CommandWrapper.Log(String.valueOf(message), "DEBUG");
	}
	
	public static void Log(String message, String level) throws InterruptedException, ParseException {
		
    	// Reset the current config
    	LogManager.resetConfiguration();
    	// Load new config
    	PropertyConfigurator.configure("src\\log4j.properties");

		
		if (level.equals("DEBUG")) {
			log.debug(message);
		} else if (level.equals("INFO")) {
			log.info(message);
		} else if (level.equals("WARN")) {
			log.warn(message);
		} else if (level.equals("ERROR")) {
			log.error(message);
		} else if (level.equals("FATAL")) {
			log.fatal(message);
		}
						
	}
	
	public static boolean ischecked(String locator, String method) throws InterruptedException, ParseException {
		
		boolean checked = false;
		
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			checked = ThreadLocalSelenium.getTestVars().selenium.isChecked(locator);
		} else {
			
			String data = "";
			WebElement checkBox = CommandWrapper.locatorHelper(locator, method);
			data = checkBox.getAttribute("checked");
			
			if (data.equals("")) {
				checked = false;
			} else if (data.equals("disabled")) {
				checked = false;
			} else if (data.equals("checked")) {
				checked = true;
			} else if (data.equals("selected")) {
				checked = true;
			}
			
		}
		
		return checked;
				
	}
	
	public static void click(String locator, String method) throws InterruptedException, ParseException {

		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {

			locator = CommandWrapper.getSeleniumLocatorPrefix(locator, method);
			
			//verify the element is there before we try to take action.  this should catch errors earlier.
			CommandWrapper.asserttrue(ThreadLocalSelenium.getTestVars().selenium.isElementPresent(locator));
			
			ThreadLocalSelenium.getTestVars().selenium.click(locator);
		} else {
			
			WebElement clickElement = CommandWrapper.locatorHelper(locator, method);
			clickElement.click();
			
		}
		
	}
	
	public static void waitForPageToLoad(String waittime) throws InterruptedException, ParseException {
		
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			if (waittime.equals("")){
				waittime = ThreadLocalSelenium.getTestVars().MAX_WAIT_TIME_IN_MS;
			} else if (waittime.equals("30000")){
				waittime = ThreadLocalSelenium.getTestVars().MAX_WAIT_TIME_IN_MS;
			}

			ThreadLocalSelenium.getTestVars().selenium.waitForPageToLoad(waittime);
		} else {
			
		}
		

	}
	
	public static String getValue(String locator, String method) throws InterruptedException, ParseException {
		
		//TODO
		boolean wasFalse = false;
		//doesn't seem like the webdriver can get a value...
		if (ThreadLocalSelenium.getTestVars().useSelenium == false) {
			ThreadLocalSelenium.getTestVars().useSelenium = true;
			wasFalse = true;
		}
		
		
		String text = "";
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			locator = CommandWrapper.getSeleniumLocatorPrefix(locator, method);
			
			//verify the element is there before we try to take action.  this should catch errors earlier.
			CommandWrapper.asserttrue(ThreadLocalSelenium.getTestVars().selenium.isElementPresent(locator));
			
			text = ThreadLocalSelenium.getTestVars().selenium.getValue(locator);
			
		} else {
			
			WebElement elementValue = CommandWrapper.locatorHelper(locator, method);
			elementValue.getValue();

		}
		
		//TODO
		//doesn't seem like the webdriver can get a value...
		if (wasFalse == true) {
			if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
				ThreadLocalSelenium.getTestVars().useSelenium = false;
			}
		}

		return text;
				
	}

	public static String getText(String locator, String method) throws InterruptedException, ParseException {
		
		String text = "";
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			locator = CommandWrapper.getSeleniumLocatorPrefix(locator, method);
			
			//verify the element is there before we try to take action.  this should catch errors earlier.
			CommandWrapper.asserttrue(ThreadLocalSelenium.getTestVars().selenium.isElementPresent(locator));
			
			text = ThreadLocalSelenium.getTestVars().selenium.getText(locator);
			
		} else {
			
			WebElement elementText = CommandWrapper.locatorHelper(locator, method);
			elementText.getText();

		}
		
		return text;
				
	}
	
	public static void type(String locator, String value, String method) throws InterruptedException, ParseException {

		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			locator = CommandWrapper.getSeleniumLocatorPrefix(locator, method);
			
			//verify the element is there before we try to take action.  this should catch errors earlier.
			CommandWrapper.asserttrue(ThreadLocalSelenium.getTestVars().selenium.isElementPresent(locator));
			
			//IE requires this or it doesn't pick up the change. Stupid IE.
			//if (ThreadLocalSelenium.getTestVars().browser.toLowerCase().contains("ie")) {
				ThreadLocalSelenium.getTestVars().selenium.setCursorPosition(locator, "0");
			//}

			//CommandWrapper.fireEvent("//*/input[@class='pan' and @name='sfi']", "focus");
			ThreadLocalSelenium.getTestVars().selenium.type(locator, value);
			ThreadLocalSelenium.getTestVars().selenium.typeKeys(locator, value);
			//ThreadLocalSelenium.getTestVars().selenium.getCursorPosition(locator);
			
		} else {
			
			WebElement inputElement = CommandWrapper.locatorHelper(locator, method);
			inputElement.sendKeys(value);

		}

		
	}
	
	public static void assertequals(String value1, String value2) throws InterruptedException, ParseException {

		CommandWrapper.Log("assertEquals('" + value1 + "', '" + value2 + "')", "DEBUG");
		Assert.assertEquals(value1, value2);
		
	}
	
	public static void assertnotequals(String value1, String value2) throws InterruptedException, ParseException {

		boolean assertCheck = false;
		
		if (value1.equals(value2)) {
			assertCheck = true;
		}
		
		Assert.assertTrue(assertCheck == false);
	
	}
	
	public static void select(String locator, String value, String method) throws InterruptedException, ParseException {
		ThreadLocalSelenium.getTestVars().useSelenium = true;
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			locator = CommandWrapper.getSeleniumLocatorPrefix(locator, method);
			
			//verify the element is there before we try to take action.  this should catch errors earlier.
			CommandWrapper.asserttrue(ThreadLocalSelenium.getTestVars().selenium.isElementPresent(locator));
			
			ThreadLocalSelenium.getTestVars().selenium.select(locator, value);
		} else {
			
			WebElement selectElement = CommandWrapper.locatorHelper(locator, method);
			ThreadLocalSelenium.getTestVars().select = new Select(selectElement);
			
			if (value.contains("label=")) {
				value = value.replace("label=", "");
				//ThreadLocalSelenium.getTestVars().select.selectByVisibleText(value);
			}
			
			//WebElement select = driver.findElement(By.xpath("//select"));
			List<WebElement> allOptions = selectElement.findElements(By.tagName("option"));
			for (WebElement option : allOptions) {
			    System.out.println(String.format("Value is: %s", option.getValue()));
			    if (option.getValue().toLowerCase().equals(value.toLowerCase())) {
			    	option.setSelected();
			    }
			}

		}

		ThreadLocalSelenium.getTestVars().useSelenium = false;
	}
	
	public static void asserttrue(boolean statement) throws InterruptedException, ParseException {
		
		Assert.assertTrue(statement);
				
	}
	
	public static void assertfalse(boolean statement) throws InterruptedException, ParseException {

		Assert.assertFalse(statement);
				
	}

	public static String getattribute(String attribute) {

		String result = "";
		
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			result = ThreadLocalSelenium.getTestVars().selenium.getAttribute(attribute);
		} else {
			result = "";
		}
		
		return result;
	}

	public static boolean istextpresent(String text) {

		boolean result = false;
			
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			result = ThreadLocalSelenium.getTestVars().selenium.isTextPresent(text);
		} else {
			result = false;
		}
		
		return result;
	}
	
	public static void verifytrue(String statement) throws InterruptedException, ParseException {

		Assert.assertTrue(ThreadLocalSelenium.getTestVars().selenium.isTextPresent("Home Owner Login"));
				
	}
	
	public static void selectFrame(String locator) throws InterruptedException, ParseException {

		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			String xpathcount = String.valueOf(ThreadLocalSelenium.getTestVars().selenium.getXpathCount("//iframe"));
			int count = Integer.parseInt(xpathcount);
			//System.out.println("Count is: " + count);
			for (int i = 0; i < count; i++){
				//System.out.println("i is: " + i);
				String frameName = ThreadLocalSelenium.getTestVars().selenium.getAttribute("//iframe@id");
				//System.out.println(String.format("Id is: %s", frameName));
				if (frameName.toLowerCase().contains(locator.toLowerCase())) {
					ThreadLocalSelenium.getTestVars().selenium.selectFrame("index=" + i + "");
					break;
				}
				//System.out.println("i is: " + i);
				
			}
		} else {
			//with the proliferation of ifs for the driver, I decided to remove explicit references to a frame
			//ThreadLocalSelenium.getTestVars().frame = ThreadLocalSelenium.getTestVars().driver.switchTo().frame(locator);
			ThreadLocalSelenium.getTestVars().driver.switchTo().frame(locator);
		}

				
	}
    
	public static void selectParentWindow() throws InterruptedException, ParseException {

		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			ThreadLocalSelenium.getTestVars().selenium.selectFrame("relative=up");
		} else {
			ThreadLocalSelenium.getTestVars().driver.switchTo().defaultContent();
			//ThreadLocalSelenium.getTestVars().frame = null;
		}

				
	}
	
	public static void fireEvent(String locator, String event) throws InterruptedException, ParseException {

		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			ThreadLocalSelenium.getTestVars().selenium.fireEvent(locator, event);
		} else {
			//not applicable
		}

				
	}
	
	
	public static void clearInput(String locator, String method) throws InterruptedException, ParseException {

		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			locator = CommandWrapper.getSeleniumLocatorPrefix(locator, method);
			
			//verify the element is there before we try to take action.  this should catch errors earlier.
			CommandWrapper.asserttrue(ThreadLocalSelenium.getTestVars().selenium.isElementPresent(locator));
			
			ThreadLocalSelenium.getTestVars().selenium.setCursorPosition(locator, "0");
		} else {
			
			WebElement inputElement = CommandWrapper.locatorHelper(locator, method);
			inputElement.clear();

		}

		
	}
	
	public static void getUrl(String url) throws InterruptedException, ParseException {

		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			ThreadLocalSelenium.getTestVars().selenium.open(url);
		
		} else {
			
			ThreadLocalSelenium.getTestVars().driver.get(url);
		
		}

		
	}
	
	
	public static void clickVariableTextLink(String variableTextLink) throws InterruptedException, ParseException {
		
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			String xpathcount = String.valueOf(ThreadLocalSelenium.getTestVars().selenium.getXpathCount("//li/a"));
			int count = Integer.parseInt(xpathcount);
			//System.out.println("Count is: " + count);
			for (int i = 1; i < count; i++){
				//System.out.println("i is: " + i);
				String linkText = ThreadLocalSelenium.getTestVars().selenium.getAttribute("//li[" + i + "]/a@href");
				//System.out.println(String.format("Text is: %s", linkText));
				if (linkText.toLowerCase().contains(variableTextLink.toLowerCase())) {
					ThreadLocalSelenium.getTestVars().selenium.click("//li[" + i + "]/a");
					break;
				}
				//System.out.println("i is: " + i);
				
			}
		
		} else {
			
			List<WebElement> links = ThreadLocalSelenium.getTestVars().driver.findElements(By.tagName("a"));
			for (WebElement link : links) {
			    //System.out.println(String.format("Text is: %s", link.getText()));
			    if (link.getText().toLowerCase().equals(variableTextLink.toLowerCase())) {
			    	link.click();
			    	break;
			    }
			    
			}
		
		}

	}
	
	public static String getSeleniumLocatorPrefix(String locator, String method) throws InterruptedException, ParseException {

		String newLocator = "";
		
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {

			if (method.equals("xpath")) {
				newLocator = "xpath=" + locator;
			} else if (method.equals("className")) {
				newLocator = locator;
			} else if (method.equals("cssSelector")) {
				newLocator = "css=" + locator;
			} else if (method.equals("id")) {
				newLocator = "id=" + locator;
			} else if (method.equals("linkText")) {
				newLocator = "link=" + locator;
			} else if (method.equals("name")) {
				newLocator = "name=" + locator;
			} else if (method.equals("partialLinkText")) {
				newLocator = locator;
			} else if (method.equals("tagName")) {
				newLocator = locator;
			}

		}
		
		return newLocator;
		
	}
	
	public static void clickLastLink(String xPathPatternPre, String xPathPatternPost, String linkPattern) throws InterruptedException, ParseException {
		
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
			
			String xpathcount = String.valueOf(ThreadLocalSelenium.getTestVars().selenium.getXpathCount("//" + xPathPatternPre + xPathPatternPost + "[contains(@href,'" + linkPattern + "')]"));
			int count = Integer.parseInt(xpathcount);
			System.out.println("Count is: " + count);
			for (int i = 1; i < count; i++){
				System.out.println("i is: " + i);
				ThreadLocalSelenium.getTestVars().selenium.click("//" + xPathPatternPre + "[" + i + "]" + xPathPatternPost);	
				System.out.println("i is: " + i);
				
			}
		
		} else {
			
			List<WebElement> links = ThreadLocalSelenium.getTestVars().driver.findElements(By.xpath("//" + xPathPatternPre + xPathPatternPost));
			for (WebElement link : links) {
			    System.out.println(String.format("Text is: %s", link.getText()));
			    
	
				
			    if (!links.isEmpty()) {
			    	link.findElement(By.xpath("//" + xPathPatternPre + "[" + (links.size()-1) + "]" + xPathPatternPost)).click();
				}
			    
			}
		
		}

	}
	
	public static WebElement locatorHelper(String locator, String method) throws InterruptedException, ParseException {
				
		ThreadLocalSelenium.getTestVars().element = null;
		
		if (method.equals("xpath")) {
			ThreadLocalSelenium.getTestVars().element = ThreadLocalSelenium.getTestVars().driver.findElement(By.xpath(locator));
		} else if (method.equals("className")) {
			ThreadLocalSelenium.getTestVars().element = ThreadLocalSelenium.getTestVars().driver.findElement(By.className(locator));
		} else if (method.equals("cssSelector")) {
			ThreadLocalSelenium.getTestVars().element = ThreadLocalSelenium.getTestVars().driver.findElement(By.cssSelector(locator));
		} else if (method.equals("id")) {
			ThreadLocalSelenium.getTestVars().element = ThreadLocalSelenium.getTestVars().driver.findElement(By.id(locator));
		} else if (method.equals("linkText")) {
			ThreadLocalSelenium.getTestVars().element = ThreadLocalSelenium.getTestVars().driver.findElement(By.linkText(locator));
		} else if (method.equals("name")) {
			ThreadLocalSelenium.getTestVars().element = ThreadLocalSelenium.getTestVars().driver.findElement(By.name(locator));
		} else if (method.equals("partialLinkText")) {
			ThreadLocalSelenium.getTestVars().element = ThreadLocalSelenium.getTestVars().driver.findElement(By.partialLinkText(locator));
		} else if (method.equals("tagName")) {
			ThreadLocalSelenium.getTestVars().element = ThreadLocalSelenium.getTestVars().driver.findElement(By.tagName(locator));
		}
		
		return ThreadLocalSelenium.getTestVars().element;

	}
	
	public static String getTitle() throws InterruptedException, ParseException {

		String title = null;
		
		if (ThreadLocalSelenium.getTestVars().useSelenium == true) {

			title = ThreadLocalSelenium.getTestVars().selenium.getTitle();
		} else {
			
			title = ThreadLocalSelenium.getTestVars().driver.findElement(By.tagName("title")).getText();
			
		}
		
		return title;
		
	}
	
}