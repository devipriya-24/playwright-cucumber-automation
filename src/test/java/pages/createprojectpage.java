package pages;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class createprojectpage {
	private Page page;
	

public createprojectpage(Page page) {
	this.page=page;
}

    private String uploadimage="//button[@type='button']//span[text()='Upload image']";
	private String projectmenu="//li[@role='menuitem']//span[text()='Projects']";
	private String Addprojectbtn="//button[@type='button']//span[text()='Add Project']";
	private String projectname="//input[@id='projectName']";
	private String projecttype="//input[@id='projectType' and @role='combobox']";
	//private String summary="//textarea[@id='summary']";
	private String summary = "#summary";
	private String startdate="//input[@id='startDate']";
	private String enddate="//input[@id='dueDate']";
	private String status="//input[@id='status']";
	private String clientdetails="//input[@id='clientId']";
	private String assigne="//input[@id='assignees_0_userId']";
			// "//span[text()='Assignee']/following::div[contains(@class,'ant-select-selector')][1]";
	private String budcost="//input[@type='text' and @id='assignees_0_budget']";
	private String addbtn="//button[@type='submit']//span[text()='Add']";
	
	//Actions
	
	public void click_uploadimage(String relativePath) {
		Path path = Paths.get("src", "test", "resources", "testdata", relativePath);
		System.out.println("Uploading from: " + path.toAbsolutePath()); // Debug

	    //page.setInputFiles("input[type='file']", path.toAbsolutePath());
		//page.click(uploadimage);
	    page.setInputFiles("input[type='file']", path.toAbsolutePath());//upload
		//String fullPath = "src/test/resources/testdata/" + relativePath;
	   // page.setInputFiles("input[type='file']", Paths.get(fullPath));
	    
	}
	
	public void click_projectmenu()  {
		page.click(projectmenu);	
		}
	
	public void click_Addprojectbtn() {
		page.click(Addprojectbtn);
	}

public void enter_projectname(String projectName) {
	page.fill(projectname , projectName );
}
public void enter_projecttype(String projectType)
{
	page.click(projecttype);
	 page.waitForSelector("//div[contains(@class,'ant-select-item-option')]");
	 
	 page.locator("//div[@title='" + projectType + "']").scrollIntoViewIfNeeded();
	 page.locator("//div[@title='" + projectType + "']").click();
	//page.keyboard().press("Enter");  
}
public void enter_summary(String summarytxt) {
	
	page.fill(summary,summarytxt );
}
public void enter_startdate(String startDate) {

	String[] parts = startDate.split("-");
	// String year = parts[0];
	 int targetYear = Integer.parseInt(parts[0]);
	    int targetMonth = Integer.parseInt(parts[1]);
	    String day = String.valueOf(Integer.parseInt(parts[2]));
	    
		page.click(startdate);
		 while (true) {

		        int displayedYear = Integer.parseInt(
		                page.locator(".ant-picker-year-btn").innerText().trim());

		        if (displayedYear == targetYear) {
		            break;
		        }

		        if (displayedYear < targetYear) {
		            page.click(".ant-picker-header-super-next-btn");   // >>
		        } else {
		            page.click(".ant-picker-header-super-prev-btn");   // <<
		        }
		    }
		  while (true) {

		        String displayedMonthText = page.locator(".ant-picker-month-btn")
		                .innerText().trim();

		        int displayedMonth = convertMonthToNumber(displayedMonthText);

		        if (displayedMonth == targetMonth) {
		            break;
		        }

		        if (displayedMonth < targetMonth) {
		            page.click(".ant-picker-header-next-btn");  // >
		        } else {
		            page.click(".ant-picker-header-prev-btn");  // <
		        }
		    }
	    

page.locator(".ant-picker-cell-in-view")
.locator("text='" + day + "'")
.first()
.click();
}
private int convertMonthToNumber(String month) {
    switch (month) {
        case "Jan": return 1;
        case "Feb": return 2;
        case "Mar": return 3;
        case "Apr": return 4;
        case "May": return 5;
        case "Jun": return 6;
        case "Jul": return 7;
        case "Aug": return 8;
        case "Sep": return 9;
        case "Oct": return 10;
        case "Nov": return 11;
        case "Dec": return 12;
    }
    return 0;
}

public void enter_enddate(String endDate) {
	
	String[] parts = endDate.split("-");
	
	// String year = parts[0];
	 int targetYear = Integer.parseInt(parts[0]);
	    int targetMonth = Integer.parseInt(parts[1]);
	    String day = String.valueOf(Integer.parseInt(parts[2]));
	    
		page.click(enddate);
		
		// Wait for calendar popup
	    Locator calendar = page.locator(".ant-picker-panel:visible");
	    calendar.waitFor();
	    
	    int yearSafety = 0;
	    while (yearSafety < 20) {

	        int displayedYear = Integer.parseInt(
	                calendar.locator(".ant-picker-year-btn").innerText().trim());

	        if (displayedYear == targetYear) {
	            break;
	        }

	        if (displayedYear < targetYear) {
	            calendar.locator(".ant-picker-header-super-next-btn").click();  // >>
	        } else {
	            calendar.locator(".ant-picker-header-super-prev-btn").click();  // <<
	        }

	        yearSafety++;
	    }
	    
	    int monthSafety = 0;
	    while (monthSafety < 12) {

	        String displayedMonthText =
	                calendar.locator(".ant-picker-month-btn").innerText().trim();

	        int displayedMonth = convertMonthToNumber(displayedMonthText);

	        if (displayedMonth == targetMonth) {
	            break;
	        }

	        if (displayedMonth < targetMonth) {
	            calendar.locator(".ant-picker-header-next-btn").click();  // >
	        } else {
	            calendar.locator(".ant-picker-header-prev-btn").click();  // <
	        }

	        monthSafety++;
	    }

	    calendar.locator(".ant-picker-cell-in-view")
        .locator("text='" + day + "'")
        .first()
        .click();  
		
}
public void enter_status(String statustxt) {
	page.click(status);
	
	 Locator dropdown = page.locator(".ant-select-dropdown:visible");
	    dropdown.waitFor();
	    
	    // Click the visible matching option
	    dropdown.locator(".ant-select-item-option-content",
	            new Locator.LocatorOptions().setHasText(statustxt))
	            .first()
	            .click();
	// page.locator("//div[@title='" + statustxt + "']").scrollIntoViewIfNeeded();
	// page.locator("//div[@title='" + statustxt + "']").click();
}
public void enter_clientdetails(String Client) 
{
	page.click(clientdetails);
	
	Locator dropdown = page.locator(".ant-select-dropdown:visible");
    dropdown.waitFor();

    Locator option = dropdown.locator("div.ant-select-item-option[title='" + Client + "']");
   
    
    option.scrollIntoViewIfNeeded();
    option.click();
    
	//page.waitForSelector("//div[contains(@class,'ant-select-item-option-content')]");
	//page.locator("//div[@title='" +Client+"']").scrollIntoViewIfNeeded();
	//page.locator("//div[@title='"+Client+"']").click();
}
public void enter_asignee(String assigneetxt) {
	page.click(assigne);
	
	 Locator dropdown = page.locator(".ant-select-dropdown:visible");
	    dropdown.waitFor();

	    dropdown.getByText(assigneetxt).first().click();
}
public void enter_budgetcost(String budget) {
	page.fill( budcost, budget);
	
}
public void click_addbtn() {
	page.click(addbtn);
	
}
}
