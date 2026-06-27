package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPage {
	public CreateOrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
