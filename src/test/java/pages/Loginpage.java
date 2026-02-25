package pages;

import com.microsoft.playwright.Page;

public class Loginpage {
	
private Page page;

public Loginpage(Page page) {
	this .page=page;
}

//Locators

private String username="//input[@id='email']";
private String password="//input[@id='password']";
private String loginbtn="//button[@type='submit']";


//Actions
public void navigate(String url) {
	page.navigate(url);
}

public void enterusername(String user) {
	page.fill(username, user);
}

public void enterpassword(String pass) {
	page.fill(password, pass);
}

public void clickLogin() {
	page.click(loginbtn);
}

}
