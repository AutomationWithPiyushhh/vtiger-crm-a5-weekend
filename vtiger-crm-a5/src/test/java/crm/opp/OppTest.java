package crm.opp;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import generic_utility.FileUtility;
import generic_utility.JavaUtility;
import generic_utility.WebDriverUtility;

public class OppTest {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {

		FileUtility fUtil = new FileUtility();

		String URL = fUtil.getDataFromJsonFile("url");
		String USERNAME = fUtil.getDataFromJsonFile("un");
		String PASSWORD = fUtil.getDataFromJsonFile("pwd");

		// ==============================
		// Browser Setup
		// ==============================
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// ==============================
		// Login to CRM Application
		// ==============================

		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		System.out.println("Login successful");

		// ==============================
		// Navigate to Opportunities Module
		// ==============================

		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();

		// ==============================
		// Create New Opportunity
		// ==============================

		WebDriverUtility wdUtil = new WebDriverUtility(driver);

		
		// Generate unique Opportunity Name
		int randomNum = JavaUtility.generateRandomNumber();

		String expectedOpportunityName = fUtil.getDataFromExcelFile("opp", 1, 0) + randomNum;
		String expectedOrgName = fUtil.getDataFromExcelFile("opp", 1, 1);
		String expectedAmount = fUtil.getDataFromExcelFile("opp", 1, 2);
		String expectedSalesStage = fUtil.getDataFromExcelFile("opp", 1, 3);
		String expectedCloseDate = fUtil.getDataFromExcelFile("opp", 1, 4);

		// Enter Opportunity Name
		driver.findElement(By.name("potentialname")).sendKeys(expectedOpportunityName);
		driver.findElement(By.name("amount")).sendKeys(expectedAmount);
		Select relatedToType = new Select(driver.findElement(By.name("related_to_type")));
		relatedToType.selectByVisibleText("Organizations");

		// ==============================
		// Handle Organization Lookup Popup
		// ==============================

		// Capture Parent Window ID
		String parentWindow = driver.getWindowHandle();

		// Click Related Organization Lookup Icon
		driver.findElement(By.xpath("//input[@name='related_to_display']/following::img[1]")).click();

//		// Capture All Window IDs
//		Set<String> allWindows = driver.getWindowHandles();
//
//		// Switch Control to Child Window
//		for (String window : allWindows) {
//
//			driver.switchTo().window(window);
//
//			if (driver.getCurrentUrl().contains("module=Accounts")) {
//				break;
//			}
//		}
				
		wdUtil.switchToWindowByUrl("module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(expectedOrgName + Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='automationwithpiyush_0123']")).click();

		// ==============================
		// Switch Back to Parent Window
		// ==============================
		wdUtil.switchToParentWindow(parentWindow);

		Thread.sleep(2000);

		// Verify Selected Organization
		String actualSelectedOrganization = driver.findElement(By.name("related_to_display")).getAttribute("value");
		System.out.println("Selected Organization : " + actualSelectedOrganization);

		Select salesStage = new Select(driver.findElement(By.name("sales_stage")));
		salesStage.selectByVisibleText(expectedSalesStage);

		driver.findElement(By.name("closingdate")).clear();
		driver.findElement(By.name("closingdate")).sendKeys(expectedCloseDate);

		// ==============================
		// Save Opportunity
		// ==============================
		driver.findElement(By.name("button")).click();
		System.out.println("Opportunity creation form submitted");

		Thread.sleep(3000);

		// ==============================
		// Validation
		String actualOpportunityName = driver.findElement(By.className("dvHeaderText")).getText();
		if (actualOpportunityName.contains(expectedOpportunityName)) {
			System.out.println("PASS : Opportunity Created Successfully");
		} else {
			System.out.println("FAIL : Opportunity Creation Failed");
			System.out.println("Expected : " + expectedOpportunityName);
			System.out.println("Actual   : " + actualOpportunityName);
		}

		// ==============================
		// Close Browser
		// ==============================

		Thread.sleep(2000);
		driver.quit();
	}
}
