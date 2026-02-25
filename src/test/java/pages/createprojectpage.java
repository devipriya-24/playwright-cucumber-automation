package pages;

import com.microsoft.playwright.Page;

public class createprojectpage {
	private Page page;
	
	//
public createprojectpage(Page page) {
	this.page=page;
}
	private String projectmenu="//li[@role='menuitem']//span[text()='Projects']";
	private String Addprojectbtn="//button[@type='button']//span[text()='Add Project']";
	private String projectname="//input[@id='projectName']";
	private String projecttype="";
	private String summary="//textarea[@id='summary']";
	private String startdate="//input[@id='startDate']";
	private String enddate="//input[@id='dueDate']";
	private String status="//input[@id='status']";
	private String clientdetails="//span[text()='Client Details']/following::div[contains(@class,'ant-select-selector')][1]";
	private String assigne="//span[text()='Assignee']/following::div[contains(@class,'ant-select-selector')][1]";
	


}
