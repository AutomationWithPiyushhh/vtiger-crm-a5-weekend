package object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OrgPage {
	public OrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
