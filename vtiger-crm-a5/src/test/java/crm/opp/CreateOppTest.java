package crm.opp;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOppTest {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		FileUtility fUtil = new FileUtility();
		
		String URL = fUtil.getDataFromJSONFile("url");
		String USERNAME = fUtil.getDataFromJSONFile("un");
		String PASSWORD = fUtil.getDataFromJSONFile("pwd");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Open the browser
		driver.get(URL);

//		LoginPage
		driver.findElement(By.xpath("//input[@name=\"user_name\"]")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name=\"user_password\"]")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id=\"submitButton\"]")).click();
		System.out.println("Successfully login");

		// Create opportunities
//		HomePage
		driver.findElement(By.linkText("Opportunities")).click();
		
//		OppPage
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();

//		opp 1 0
		String Opportunity =  fUtil.getDataFromExcelFile("opp", 1, 0);
		
		String NextStep = "Playwright";
		String LeadSource = "Employee";
		String SalesStage = "Needs Analysis";
		String RelatedTo = "Accenture";
		String Description = "Successfully saved the details";

		// fill the details
//		CreateOppPage
		driver.findElement(By.xpath("//input[@name=\"potentialname\"]")).sendKeys(Opportunity);
		driver.findElement(By.xpath("//input[@name=\"nextstep\"]")).sendKeys(NextStep);
		Select select = new Select(driver.findElement(By.xpath("//select[@name=\"leadsource\"]")));
		select.selectByValue(LeadSource);
		Select select1 = new Select(driver.findElement(By.xpath("//select[@name=\"sales_stage\"]")));
		select1.selectByValue(SalesStage);
		driver.findElement(By.xpath("//input[@value=\"U\"]")).click();
		Select se = new Select(driver.findElement(By.xpath("//select[@name=\"assigned_user_id\"]")));
		// se.selectByValue("ACOE Fireflink");

		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		driver.findElement(By.xpath("//input[@name=\"related_to_display\"]")).sendKeys(RelatedTo);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@name=\"description\"]")).sendKeys(Description);

		// save the deatils
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]' and not(contains(@style,'display:none'))])[1]"))
				.click();
		System.out.println("Successfully saved the Opportunities details");
		
		// Verification
//		VerifyOppPage
		String actOpportunity = driver.findElement(By.xpath("//span[@id=\"dtlview_Opportunity Name\"]")).getText();
		String actNextStep = driver.findElement(By.xpath("//span[@id=\"dtlview_Next Step\"]")).getText();
		String actLeadSource = driver.findElement(By.xpath("//span[@id=\"dtlview_Lead Source\"]")).getText();
		String actSalesStage = driver.findElement(By.xpath("//span[@id=\"dtlview_Sales Stage\"]")).getText();
		String actAssignedTo = driver.findElement(By.xpath("//span[@id=\"dtlview_Assigned To\"]")).getText();
		
		String actDescription = driver.findElement(By.xpath("//span[@id=\"dtlview_Description\"]")).getText();

		if (actOpportunity.equals(Opportunity) && actNextStep.equals(NextStep) && actLeadSource.equals(LeadSource)
				&& actSalesStage.equals(SalesStage) && actDescription.equals(Description)) {
			System.out.println("Opport created successfully !!!");
		} else {
			System.out.println("Failed!!!....");
		}

//		logout
//		HomePage
//		profile icon
//		signout link
		
		Thread.sleep(10000);
		driver.quit();

	}

}