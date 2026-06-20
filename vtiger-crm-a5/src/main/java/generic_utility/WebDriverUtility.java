package generic_utility;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WebDriverUtility {

	WebDriver driver;

	public WebDriverUtility(WebDriver driver) {
		this.driver = driver;
	}

	public void switchToWindowByTitle(String partialTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(partialTitle)) {
				break;
			}
		}
	}

	public void switchToWindowByUrl(String partialUrl) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			if (driver.getCurrentUrl().contains(partialUrl)) {
				break;
			}
		}
	}

	public void switchToParentWindow(String pid) {
		driver.switchTo().window(pid);
	}
}
