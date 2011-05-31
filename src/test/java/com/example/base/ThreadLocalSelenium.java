package com.example.base;

import org.openqa.selenium.WebDriver;

import com.example.extended.TestVars;
import com.thoughtworks.selenium.Selenium;

public class ThreadLocalSelenium {
	
	private static String BrowserType = "";

	private static ThreadLocal<Selenium> slt = new ThreadLocal<Selenium>();
	private static ThreadLocal<TestVars> tv = new ThreadLocal<TestVars>();
	private static ThreadLocal<WebDriver> wd = new ThreadLocal<WebDriver>();
	
	public static Selenium get() {
		return slt.get();
	}

	public static void set(final Selenium selenium) {
		slt.set(selenium);
	}
	
	public static TestVars getTestVars() {
		return tv.get();
	}

	public static void setTestVars(final TestVars TestVars) {
		tv.set(TestVars);
	}

	public static WebDriver getWebDriver() {
		return wd.get();
	}

	public static void setWebDriver(final WebDriver webdriver) {
		wd.set(webdriver);
	}

	public static void setBrowserType(String browserType) {
		BrowserType = browserType;
	}

	public static String getBrowserType() {
		return BrowserType;
	}
	


}
