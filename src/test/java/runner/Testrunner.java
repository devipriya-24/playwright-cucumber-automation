package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features="src/test/resources/features",
		glue={"stepdefinitions", "hooks"},
		plugin= {"pretty","html:target/cucumber-report.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
			//	tags=" @Login OR  @resources",
		tags="@ProjectCreation",
		dryRun=false,monochrome=true	
			)
public class Testrunner {
	

}
