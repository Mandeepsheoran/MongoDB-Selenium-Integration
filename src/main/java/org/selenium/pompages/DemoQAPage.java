package org.selenium.pompages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.selenium.driver.DriverSetup;

public class DemoQAPage {

	private static By usernamefield = By.xpath("//input[@id='userName']");
	private static By passwordfield = By.xpath("//input[@id='password']");
	private static By loginbutton = By.xpath("//button[@id='login']");
	private static By logoutbutton = By.xpath("//div[@class='text-right col-md-5 col-sm-12']//button[@id='submit']");


	public  void enterUserName(String username) {
		WebElement name = DriverSetup.getDriver().findElement(usernamefield);
		name.sendKeys(username);
	}
	
	public  void enterpassword(String password) {
		WebElement pass = DriverSetup.getDriver().findElement(passwordfield);
		pass.sendKeys(password);
	}
	
	
	public  void clickLogin() {
		WebElement submit = DriverSetup.getDriver().findElement(loginbutton);
		submit.click();
	}
	
	public  boolean isUserLoggedIN() {

		WebElement logout = DriverSetup.getDriver().findElement(logoutbutton);
		boolean value =logout.isDisplayed();
		return value;
	}


}
