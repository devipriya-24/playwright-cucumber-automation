package hooks;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import utils.configReader;

public class Hooks {
	
	public static Playwright playwright;
	public static Browser browser;
	public static BrowserContext context;  
	public static Page page;

	@BeforeAll
	public static void setup() {
		
		String browserName = configReader.getProperty("browser");
	    boolean headless = Boolean.parseBoolean(configReader.getProperty("headless"));
	    int slowMo = Integer.parseInt(configReader.getProperty("slowmo"));
		
		
		
		 playwright=Playwright.create();
		 
		 BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
		    options.setHeadless(headless);
		    options.setSlowMo(slowMo);
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
	
	@AfterAll
	public static void tearDown() {
		browser.close();
		playwright.close();
	}
	
	
}
