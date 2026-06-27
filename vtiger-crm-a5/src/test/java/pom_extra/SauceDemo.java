package pom_extra;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemo {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("https://www.saucedemo.com/");


//		WebElement un = driver.findElement(By.id("user-name"));
//		WebElement pwd = driver.findElement(By.id("password"));
//		WebElement loginBtn = driver.findElement(By.id("login-button"));

		LoginPage lp = new LoginPage(driver);
		
		WebElement un = lp.getUn();
		WebElement pwd = lp.getPwd();

		driver.navigate().refresh();

//		utilization
		un.sendKeys("standard_user");
		pwd.sendKeys("secret_sauce");
		lp.getLoginBtn().click();

		HomePage hp = new HomePage(driver);
				
//		WebElement appLogo = driver.findElement(By.className("app_logo"));
		WebElement appLogo = hp.getAppLogo();
		
		if (appLogo.isDisplayed()) {
			System.out.println("logged in successfully !!!");
		}
		
		Thread.sleep(5000);
		driver.quit();

	}

	
}
