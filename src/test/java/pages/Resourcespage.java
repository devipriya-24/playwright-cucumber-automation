package pages;

import static org.junit.Assert.assertThat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.PressSequentiallyOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.LoadState;

import java.io.ByteArrayInputStream;

import io.qameta.allure.Allure;

public class Resourcespage {
	
	private Page page;
	
	
	public Resourcespage(Page page) {
		this.page=page;
	
	}
	
	
	private String resourcebtn="//li[@role='menuitem']//span[text()='Resource']";
	private String addnew="//button[@type='button']//span[text()='Add Resource']";
	private String name="//input[@id='name']";
	private String mail="//input[@id='email']";
	private String phonenumber="//input[@id='phoneNumber']";
	//department
	private String department="//input[@id='department']";
	private String departoption="//div[contains(@class,'ant-select-item-option-content') and text()='IT']";
	//role
	private String role="//input[@id='role']";
	private String roleopt="//div[contains(@class,'ant-select-item-option-content') and text()='Project Resource']";
	//designation
	private String designation="//input[@id='designation']";
	private String desigoption="//div[contains(@class,'ant-select-item-option-content') and normalize-space()='%s']";
	
	private String exp="//input[@id='yearsOfExperience']";
	
	//private String skills= "//label[normalize-space()='Skills']/following::input[1]";
	
	private String skillsContainer = "//*[contains(text(),'Skills')]/following::div[contains(@class, 'ant-select-selector')][1]";
	private String skillsInput = "//*[contains(text(),'Skills')]/following::input[1]";
	
	private String password="//input[@id='password']";
	private String confirmpass="//input[@id='confirmPassword']";
	private String Readaccess="//input[@value='Read']";
	private String clickadd="//button[@type='submit']";
	private String toastmsg="//div[contains(@class, 'ant-message')]//span[contains(text(), 'User registered successfully')]";
	
	public void clickResources() {
		//page.locator(resourcebtn).waitFor();
		String expectedUrl = "https://projectpulseqa.gsussoftwares.com/projects";
		try {
	        // This waits up to 10 seconds for the URL to be exactly what you specified
	        page.waitForURL(expectedUrl, new Page.WaitForURLOptions().setTimeout(10000));
	        System.out.println("Navigation confirmed: User is on the Projects page.");
	        page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
	    } catch (Exception e) {
	        throw new RuntimeException("Login failed or navigation to projects page timed out! Current URL: " + page.url());
	    }
		Locator resBtn = page.locator(resourcebtn);
		resBtn.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
		resBtn.click(new Locator.ClickOptions().setForce(true));
	
	}
	
	public void clickAddnew() {
		page.click(addnew);
	}
	
	public void selectdepart(String departvalue) {
		page.click(department);
		page.click(String.format(departoption, departvalue));
	}
	
	public void selectDesignation(String designationValue) {
		page.click(designation);
		
		
		
		String optionXpath = String.format(desigoption, designationValue);
	    Locator option = page.locator(optionXpath);
	    
	    try {
	    option.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
	    option.scrollIntoViewIfNeeded();
		
	    option.click(new Locator.ClickOptions().setForce(true));
	    System.out.println("Successfully selected Designation: " + designationValue);
	    }catch(Exception e) {
	    	System.out.println("Click failed, attempting Keyboard Enter for: " + designationValue);
	        page.keyboard().press("Enter");
	    }
	    System.out.println("Selected Designation: " + designationValue);
	}
	
	
	public void selectrole(String rolevalue) {
		page.click(role);
		page.click(String.format(roleopt, rolevalue));
		page.locator(role).scrollIntoViewIfNeeded();
	
	}
	
	public void selectSkill(String skillName) {
        // 1. Locate and Scroll to the container
        Locator container = page.locator(skillsContainer);
        container.scrollIntoViewIfNeeded();
        
        // 2. Click to focus (using force because AntD has overlapping layers)
        container.click(new Locator.ClickOptions().setForce(true));

        // 3. Type the skill name to filter the list
        Locator sInput = page.locator(skillsInput);
        sInput.pressSequentially(skillName, new PressSequentiallyOptions().setDelay(100));

      
        // 5. Optional: Press Escape to blur the field so the dropdown disappears
        page.keyboard().press("Enter");
        page.waitForTimeout(500);
    }
	
	public void enterResourcesdetails(String nameval,
									  String mailval,
									  String numberval,
									  String departVal,
									  String roleVal,
									  String designationVal,
									  String expval,
									  String skillval,
									  String passval,
									  String confirmpassval ) {
		page.fill(name, nameval);
		page.fill(mail, mailval);
		page.fill(phonenumber, numberval);
		
		selectdepart(departVal);
		selectrole(roleVal);
		selectDesignation(designationVal);
		
		// scroll to Year Of Experience field
		
		page.locator(exp)
	    .evaluate("el => el.scrollIntoView({behavior: 'smooth', block: 'center'})");

	    page.fill(exp, expval);
	
	 // --- HANDLING MULTIPLE SKILLS ---
	    if (skillval != null && !skillval.isEmpty()) {
	        String[] skillsArray = skillval.split(",");
	        for (String individualSkill : skillsArray) {
	            selectSkill(individualSkill.trim()); // .trim() removes spaces after commas
	        }
	    }
		
		page.locator(password).scrollIntoViewIfNeeded();
		page.fill(password, passval);
		
		page.locator(confirmpass).scrollIntoViewIfNeeded();
		page.fill(confirmpass, confirmpassval);
		
		page.click(Readaccess);
		page.waitForTimeout(500);
		
		
		
	}
	
	public void clickadd() {
		Locator addButton = page.locator(clickadd);
	    
	    // 1. Scroll the button into center view
		addButton.evaluate("el => el.scrollIntoView({behavior: 'smooth', block: 'center'})");
		addButton.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
	    
	    // 2. Click the button
	    // Using force:true helps if there are overlapping UI elements
	    addButton.click(new Locator.ClickOptions().setForce(true));
	    page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
	}
	    // 3. Small pause to let the toast message trigger
	 //   page.waitForTimeout(500);
		
		
		
		//page.click(clickadd);
		//page.waitForTimeout(300);
	
		
	public void verifymsg() {
		try {
			Locator successToast = page.locator(toastmsg);
			assertThat(successToast).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
			System.out.println("Success message found: " + successToast.textContent());
		Allure.step("SUCCESS: Resource was added and toast message displayed.");
		
		}catch(AssertionError e) {
			Allure.addAttachment("Failed_Toast_Screenshot", 
			        new ByteArrayInputStream(page.screenshot()));
			throw e;
		}
	}

}
