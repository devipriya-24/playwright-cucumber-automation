package stepdefinitions;



import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Loginpage;
import utils.configReader;

public class Loginsteps {
	
	//Page page=Hooks.page;
	//Loginpage loginpage=new Loginpage(page);
	 Loginpage loginpage;
	 
	@Given("user launches the application")
	public void user_launches_the_application() {
		
	  loginpage=new Loginpage(Hooks.page);
	  String url=configReader.getProperty("baseUrl");
		loginpage.navigate(url);
	}

	@When("user enters valid username and password")
	public void user_enters_valid_username_and_password() {
	   
		String username=configReader.getProperty("username");
		String password=configReader.getProperty("password");
		
		
		loginpage.enterusername(username);
		loginpage.enterpassword(password);
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
	  loginpage.clickLogin();
		
	}

	@Then("user should be redirected to dashboard")
	public void user_should_be_redirected_to_dashboard() {
	   // loginpage.navigate(" https://projectpulseqa.gsussoftwares.com/projects");
		
		String expectedurl=configReader.getProperty("dashboardUrl");
		  Hooks.page.waitForURL(expectedurl);

		    String actualUrl = Hooks.page.url();
		    
		    if (!actualUrl.equals(expectedurl)) {
		        throw new AssertionError(
		                "Expected: " + expectedurl + 
		                " but found: " + actualUrl);
		    }

		    System.out.println("User successfully redirected to dashboard.");
		    
		    
	}



}
