package com.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utils.CommonMethods;
import com.utils.Constants;

public class HW1 extends CommonMethods {
	
	/*TC 1: HRMS Application Login: 

Open chrome browser
Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
Enter valid username and password
Click on login button
Then verify Syntax Logo is displayed
Close the browser

TC 2: HRMS Application Negative Login: 

Open chrome browser
Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
Enter valid username
Leave password field empty
Verify error message with text “Password cannot be empty” is displayed.*/
	
	@BeforeMethod
	public void openAndNavigate() {
		setUp("chrome", Constants.HRMS_URL);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void logoValidation() {
		boolean isDisplayed = driver.findElement(By.xpath("//div[@id='divLogo']/img")).isDisplayed();
		Assert.assertTrue(isDisplayed, "Syntax logo is not displayed");
		if (isDisplayed) {
			System.out.println("test pass");
		} else {
			System.out.println("test fails");
		}
	}
	
	//priority number will determine the order of test cases
	//dependsOnMethods={"nameofMethod"}==> it will run depending on other test case
	//enabled=false will skip that test case
	
	
	@Test(priority=2, dependsOnMethods= {"logoValidation"}, enabled=false)
	public void validLogin() throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(3000);
		WebElement aa=driver.findElement(By.id("spanMessage"));
		String errorMessage= aa.getText();
		if (errorMessage.equals("Password cannot be empty")) {
			System.out.println("Correct error message is displayed");
		}else {
			System.err.println("invalid error message");
		}
	}
	

}
