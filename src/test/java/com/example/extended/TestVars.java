package com.example.extended;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.SeleniumServer;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.Selenium;



public class TestVars {
	
	public static final String TestConfiguration = null;
	
	public Selenium selenium = null;
	public WebDriver driver = null;
	public WebDriver frame = null;
	public Select select = null;
	public WebElement element = null;
	
	public boolean useWebdriver = false;
	public boolean useSelenium = false;

    public SeleniumServer seleniumServer = null;
	
	public String BASE_URL = "http://www.google.com";
	
	public String seleniumHost = "localhost";
	public int seleniumPort = 4444;
	public String browser = "";

	/**
	 * Static on purpose as we increment this globally.
	 */
	private static int nextId = 1;

	public static String ConfigFile;

	public Date date;

	public boolean debug = true;

	public final String MAX_WAIT_TIME_IN_MS = "90000";









}
