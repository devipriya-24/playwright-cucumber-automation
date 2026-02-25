package stepdefinitions;

import org.json.JSONObject;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Loginpage;
import pages.Resourcespage;
import utils.JsonReader;

public class Resourcessteps {
	
	Resourcespage resourcespage;
	Loginsteps loginsteps;
	public Resourcessteps() {
        resourcespage = new Resourcespage(Hooks.page);
    }
	
	
	@When("user clicks on Resources menu")
	public void user_clicks_on_resources_menu() {
		resourcespage.clickResources();
	}
	@When("user clicks on Add New button")
	public void user_clicks_on_add_new_button() {
		resourcespage.clickAddnew();
	}
	@When("user enters resource details")
	public void user_enters_resource_details() {
		
		JSONObject data = JsonReader.getTestData();

		
		resourcespage.enterResourcesdetails(
				data.getString("name"),
	            data.getString("email"),
	            data.getString("phoneNumber"),
	            data.getString("department"),
	            data.getString("role"),
	            data.getString("designation"),
	            data.getString("yearsOfExperience"),
	            data.getString("skill"),
	            data.getString("password"),
	            data.getString("confirmPassword")
				);
		
	}
	@When("user clicks on Add button")
	public void user_clicks_on_add_button() {
		resourcespage.clickadd();
		
	}
	@Then("resource should be created successfully")
	public void resource_should_be_created_successfully() {
		resourcespage.verifymsg();
	}


}
