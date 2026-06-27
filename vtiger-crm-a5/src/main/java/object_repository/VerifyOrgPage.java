package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class VerifyOrgPage {
	public VerifyOrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
