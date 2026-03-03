package stepdefinitions;

import org.json.JSONObject;



import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.createprojectpage;
import utils.JsonReader;

public class createprojectsteps {
	
	createprojectpage createproject;
	
	
	
	@When("user clicks on Project menu")
	public void user_clicks_on_project_menu() {
		createproject = new createprojectpage(Hooks.page);
		createproject.click_projectmenu();
	}
	
	@When("user clicks on Add New Project button")
	public void user_clicks_on_add_new_project_button() {
	   
		createproject.click_Addprojectbtn();
	}
	
	@When("user enters project details")
	public void user_enters_project_details() {
	   
		JSONObject fullData = JsonReader.getTestData("createprojectdata.json");
        JSONObject projectData = fullData.getJSONObject("validProject");
       // try {
        String imagePath = projectData.getString("projectImagePath");
        createproject.click_uploadimage(imagePath);
        createproject.enter_projectname(projectData.getString("projectName"));
        createproject.enter_projecttype(projectData.getString("projectType"));
        createproject.enter_summary(projectData.getString("summarytxt"));
        createproject.enter_startdate(projectData.getString("startDate"));
        createproject.enter_enddate(projectData.getString("endDate"));
        createproject.enter_status(projectData.getString("status"));
        createproject.enter_clientdetails(projectData.getString("Client"));
        createproject.enter_asignee(projectData.getString("assigneetxt"));
        createproject.enter_budgetcost(projectData.getString("budget"));
      //  }catch(Exception e) {
        //	 Hooks.attachScreenshot("Failure while entering project details");
//
        //     throw new RuntimeException("Project creation failed due to: " + e.getMessage(), e);
      //  }
	}
	
	@Then("project should be created successfully")
	public void project_should_be_created_successfully() {
		createproject.click_addbtn();
		
	}


}
