package com.example.base;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SeleneseCommandExecutor;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.server.SeleniumServer;

import com.example.extended.TestVars;
import com.thoughtworks.selenium.BrowserConfigurationOptions;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
*
* @author: PMAC
* Date: 25-Apr-2011
*/

public class TestUtils {
	
	public static void init() {
		ThreadLocalSelenium.setTestVars(new TestVars());
	}

    public static void setUp(String browser) throws Exception {
    	
    	// public String browserString = "*googlechrome";
    	//public String browserString = "*safari";
    	//public String browserString = "*firefox";
    	//public String browserString = "*chrome";
    	//public String browserString = "*iexploreproxy";
    	//public String browserString = "*iexplore";
    	//public String browserString = "*iehta";
    	
    	if (browser.toLowerCase().contains("firefox")) {
    		
        	ThreadLocalSelenium.getTestVars().driver = new FirefoxDriver();
        	ThreadLocalSelenium.getTestVars().selenium = new WebDriverBackedSelenium(ThreadLocalSelenium.getTestVars().driver, ThreadLocalSelenium.getTestVars().BASE_URL);
        	ThreadLocalSelenium.getTestVars().driver.get(ThreadLocalSelenium.getTestVars().BASE_URL);
        	
            //_ffp = new FirefoxProfile();
            //_ffp.AcceptUntrustedCertificates = true;
            //_driver = new FirefoxDriver(_ffp);

        	
    	} else if (browser.toLowerCase().contains("ie")) {
    		
    		ThreadLocalSelenium.getTestVars().useSelenium = true;
    		
        	if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
        		
        		ThreadLocalSelenium.getTestVars().seleniumServer = new SeleniumServer();
        					
				ThreadLocalSelenium.getTestVars().seleniumServer.start();
            	
        		ThreadLocalSelenium.getTestVars().selenium = new DefaultSelenium("localhost", 4444, "*iexplore", "http://www.google.com/");
        		ThreadLocalSelenium.getTestVars().selenium.start();
        		ThreadLocalSelenium.getTestVars().selenium.windowMaximize();
        		ThreadLocalSelenium.getTestVars().selenium.windowFocus();  //stupid IE
        		ThreadLocalSelenium.getTestVars().selenium.open(ThreadLocalSelenium.getTestVars().BASE_URL);
        		
        	} else {
        		
            	ThreadLocalSelenium.getTestVars().driver = new InternetExplorerDriver();
            	ThreadLocalSelenium.getTestVars().selenium = new WebDriverBackedSelenium(ThreadLocalSelenium.getTestVars().driver, ThreadLocalSelenium.getTestVars().BASE_URL);
            	ThreadLocalSelenium.getTestVars().driver.get(ThreadLocalSelenium.getTestVars().BASE_URL);
            	
        	}

        	
    	} else if (browser.toLowerCase().contains("safari")) {
    		
    		System.out.println("OS: " + System.getProperty("os.name"));
    		//Safari not supported on Windows 7, so we need to kill the test quick so it doesn't hang if this is on Windows
    		if (System.getProperty("os.name").toLowerCase().contains("win")) {
    			throw new Exception("Safari not Supported on Windows");
    		}
    		
    		ThreadLocalSelenium.getTestVars().useSelenium = true;
    		
        	if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
        		
        		ThreadLocalSelenium.getTestVars().seleniumServer = new SeleniumServer();
        		ThreadLocalSelenium.getTestVars().seleniumServer.start();
            	
        		ThreadLocalSelenium.getTestVars().selenium = new DefaultSelenium("localhost", 4444, "*safariproxy", "http://www.google.com/");
        		ThreadLocalSelenium.getTestVars().selenium.start();
        		ThreadLocalSelenium.getTestVars().selenium.windowMaximize();
        		ThreadLocalSelenium.getTestVars().selenium.open(ThreadLocalSelenium.getTestVars().BASE_URL);
        		
        	} else {
        		
        		Selenium sel = new DefaultSelenium("localhost", 4444, "*safari", ThreadLocalSelenium.getTestVars().BASE_URL);
        		CommandExecutor executor = new SeleneseCommandExecutor(sel);
        		DesiredCapabilities dc = new DesiredCapabilities();
        		ThreadLocalSelenium.getTestVars().driver = new RemoteWebDriver(executor, dc);
            	ThreadLocalSelenium.getTestVars().selenium = new WebDriverBackedSelenium(ThreadLocalSelenium.getTestVars().driver, ThreadLocalSelenium.getTestVars().BASE_URL);
            	ThreadLocalSelenium.getTestVars().driver.get(ThreadLocalSelenium.getTestVars().BASE_URL);
            	
        	}
    		

    	} else if (browser.toLowerCase().contains("chrome")) {
    		
    		ThreadLocalSelenium.getTestVars().useSelenium = true;

        	if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
        		
        		ThreadLocalSelenium.getTestVars().seleniumServer = new SeleniumServer();
        		ThreadLocalSelenium.getTestVars().seleniumServer.start();
            	
        		ThreadLocalSelenium.getTestVars().selenium = new DefaultSelenium("localhost", 4444, "*googlechrome", "http://www.google.com/");
        		
        		BrowserConfigurationOptions bco = new BrowserConfigurationOptions();
        		ThreadLocalSelenium.getTestVars().selenium.start(bco.setCommandLineFlags("--disable-web-security"));
        		ThreadLocalSelenium.getTestVars().selenium.windowMaximize();
        		ThreadLocalSelenium.getTestVars().selenium.open(ThreadLocalSelenium.getTestVars().BASE_URL);
        		
        	} else {
        		
            	ThreadLocalSelenium.getTestVars().driver = new ChromeDriver();
            	ThreadLocalSelenium.getTestVars().selenium = new WebDriverBackedSelenium(ThreadLocalSelenium.getTestVars().driver, ThreadLocalSelenium.getTestVars().BASE_URL);
            	ThreadLocalSelenium.getTestVars().driver.get(ThreadLocalSelenium.getTestVars().BASE_URL);
            	
        	}

    	}


    }
    
    public static void getPrintPageSource(String window) {
		
    	if (window.equals("frame")) {
    		System.out.println("Page Source: " + ThreadLocalSelenium.getTestVars().frame.getPageSource());
    	} else {
    		System.out.println("Page Source: " + ThreadLocalSelenium.getTestVars().driver.getPageSource());
    	}
    	
    	
    }
    
    public static void getPrintTagName(String tagName) {
    	System.out.println(tagName + ": " + ThreadLocalSelenium.getTestVars().frame.findElement(By.tagName(tagName)).getAttribute("name"));
    }
    
    public static void tearDown() {
    	
    	if (ThreadLocalSelenium.getTestVars().useSelenium == true) {
    		
    		ThreadLocalSelenium.getTestVars().selenium.stop();
    		ThreadLocalSelenium.getTestVars().seleniumServer.stop();
    		ThreadLocalSelenium.setTestVars(null);
    		
    	} else {
        	ThreadLocalSelenium.getTestVars().driver.quit();
        	System.out.println("exit driver");
        	
        	if (ThreadLocalSelenium.getTestVars().selenium != null) {
        		System.out.println("exit selenium");
        		//ThreadLocalSelenium.set(null);
        		//ThreadLocalSelenium.setTestVars(null);
        		ThreadLocalSelenium.getTestVars().selenium.stop();
        		System.out.println("exited selenium");
        	}
        	
        	if (ThreadLocalSelenium.getTestVars().frame != null) {
        		System.out.println("exit frame");
        		ThreadLocalSelenium.getTestVars().frame.quit();
        		System.out.println("exited frame");
        	}
    	}

    }
    
    public static void getFrames() {
    	
    	List<WebElement> frames = ThreadLocalSelenium.getTestVars().driver.findElements(By.tagName("iframe"));
    	for (WebElement frame : frames) {
    	    System.out.println(String.format("Class is: %s", frame.getClass()));
    	    System.out.println(String.format("Name is: %s", frame.getAttribute("name")));   	    
    	}
    	
    }
    
    public static void getInputs(String window) {
    	
    	List<WebElement> inputs = null;
    		
    	if (window.equals("frame")) {
    		inputs = ThreadLocalSelenium.getTestVars().frame.findElements(By.xpath("//input"));
    		System.out.println("finding inputs");
    	} else {
    		inputs = ThreadLocalSelenium.getTestVars().driver.findElements(By.xpath("//input"));
    	}
    	
    	for (WebElement input : inputs) {
    	    System.out.println(String.format("Class is: %s", input.getClass()));
    	    System.out.println(String.format("Name is: %s", input.getAttribute("name")));   	    
    	}
    	
    }
    
    public static void getLinks(String window) {
    	
    	List<WebElement> links = null;
		
    	if (window.equals("frame")) {
    		links = ThreadLocalSelenium.getTestVars().frame.findElements(By.tagName("a"));
    	} else {
    		links = ThreadLocalSelenium.getTestVars().driver.findElements(By.tagName("a"));
    	}
    	
    	for (WebElement link : links) {
    	    System.out.println(String.format("Text is: %s", link.getText()));    	    
    	}
    	
    }
    
    public static void getBody(String window) {
    	
    	List<WebElement> body = null;
    		
    	if (window.equals("frame")) {
    		body = ThreadLocalSelenium.getTestVars().frame.findElements(By.tagName("body"));
    	} else {
    		body = ThreadLocalSelenium.getTestVars().driver.findElements(By.tagName("body"));
    	}
    	
    	for (WebElement bo : body) {
    	    System.out.println(String.format("id is: %s", bo.getAttribute("id")));   	    
    	}
    	
    }
    
    public static void getTitle(String window) {
    	
    	List<WebElement> titles = null;
    		
    	if (window.equals("frame")) {
    		titles = ThreadLocalSelenium.getTestVars().frame.findElements(By.tagName("title"));
    	} else {
    		titles = ThreadLocalSelenium.getTestVars().driver.findElements(By.tagName("title"));
    	}
    	
    	for (WebElement title : titles) {
    	    System.out.println(String.format("text is: %s", title.getText()));   	    
    	}
    	
    }
    
    public static void getForms(String window) {
    	
    	List<WebElement> forms = null;
    		
    	if (window.equals("frame")) {
    		forms = ThreadLocalSelenium.getTestVars().frame.findElements(By.tagName("form"));
    	} else {
    		forms = ThreadLocalSelenium.getTestVars().driver.findElements(By.tagName("form"));
    	}
    	
    	for (WebElement form : forms) {
    	    System.out.println(String.format("text is: %s", form.getAttribute("id")));   	    
    	}
    	
    }
    
    public static void pause(final int timeInMilliseconds) {
    	
    	try {
    		Thread.sleep(timeInMilliseconds);
    	} catch (InterruptedException ex) {
    		System.out.println(ex.getMessage());
    	}
    	
    }

}