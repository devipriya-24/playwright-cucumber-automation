package hooks;

import com.microsoft.playwright.Browser;
import java.io.ByteArrayInputStream;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utils.configReader;

public class Hooks {
	
	public static Playwright playwright;
	public static Browser browser;
	public static BrowserContext context;  
	public static Page page;

	@BeforeAll
	public static void setup() {
		playwright=Playwright.create();
		
		String browserName = configReader.getProperty("browser");
	    //boolean headless = Boolean.parseBoolean(configReader.getProperty("headless"));
		/*
		 * browser = playwright.chromium().launch( new BrowserType.LaunchOptions() );
		 */
	    int slowMo = Integer.parseInt(configReader.getProperty("slowmo"));
		
		
		
		 
		 
		 BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
		    //options.setHeadless(headless);
		    options.setSlowMo(slowMo);
		    options.setHeadless(true);
		   // options.setArgs(java.util.Arrays.asList("--start-maximized"));
		 
		// browser=playwright.chromium().launch(new
				                          // BrowserType.LaunchOptions().setHeadless(false)
				                          // .setSlowMo(1000));
		//context=browser.newContext();
		//page=context.newPage();
		 if (browserName.equalsIgnoreCase("chromium")) {
		        browser = playwright.chromium().launch(options);
		    } else if (browserName.equalsIgnoreCase("firefox")) {
		        browser = playwright.firefox().launch(options);
		    } else {
		        browser = playwright.webkit().launch(options);
		    }

		    context = browser.newContext();
		    page = context.newPage();
		    
		   // page.navigate(configReader.getProperty("baseurl"));
	}
	
	public static void attachScreenshot(String name) {
	    byte[] screenshot = page.screenshot();
	    Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
	}
	@After
	public void tearDown(Scenario scenario) {

	    if (scenario.isFailed()&& page != null) {

	        byte[] screenshot = page.screenshot();
	        Allure.addAttachment(
	            scenario.getName(),
	            new ByteArrayInputStream(screenshot)
	        );
	    }
	}

	
	@AfterAll
	public static void tearDown() {
		if (browser != null) {
	        browser.close();
	    }
	    if (playwright != null) {
	        playwright.close();
	    }
	}
	
	
}
