package com.reviewTestNG01;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utils.CommonMethods;
import com.utils.Constants;

public class HWTestNG extends CommonMethods {
	
	/*HRMS Add Employee: 

Open chrome browser
Go to “http://166.62.36.207/humanresources/symfony/web/index.php/auth/login”
Login into the application
Add 5 different Employees and create login credentials by providing: 
First Name
Last Name
Username
Password
Provide Employee First and Last Name
Verify Employee has been added successfully and take screenshot (you must have 5 different screenshots)
Close the browser
Specify group for this test case, add it into specific suite and execute from xml file.*/





  @BeforeMethod
   
	public void login() throws InterruptedException {
		setUp("chrome", Constants.HRMS_URL);
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.name("Submit")).click();
		 driver.findElement(By.linkText("PIM")).click();
		  driver.findElement(By.linkText("Add Employee")).click();
	  
  }
  
  
  @AfterMethod
  
  public void closeDown() {
//	  driver.quit();
  }
  
  @Test(priority=1,dataProvider="getData")
  
  public void login(String firstName, String lastName, String username, String password) throws InterruptedException{
	  driver.findElement(By.id("firstName")).sendKeys(firstName);
	  driver.findElement(By.id("lastName")).sendKeys(lastName);
	  driver.findElement(By.id("chkLogin")).click();
	  driver.findElement(By.id("user_name")).sendKeys(username);
	  driver.findElement(By.id("user_password")).sendKeys(password);
	  driver.findElement(By.id("re_password")).sendKeys(password);
	  driver.findElement(By.id("btnSave")).click();
	 String empFirstName= driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute(firstName);
	 String empLastName=driver.findElement(By.id("personal_txtEmpLastName")).getAttribute(lastName);
	 System.out.println("Employee first name: "+ empFirstName+" Employee last name: "+empLastName);
	 
	 WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("personal[txtEmployeeId]")));
		
	 
	 boolean empId= driver.findElement(By.id("personal[txtEmployeeId]")).isDisplayed();
	 Assert.assertTrue(empId, "Employee account is not successfuly created");
	 TakesScreenshot ts=(TakesScreenshot)driver;
	 File screen=ts.getScreenshotAs(OutputType.FILE);
	 
	 try {
			//copy file to the specified destination and give name and extension
			FileUtils.copyFile(screen, new File("screenshots/HRMS/addEmp.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  
  @DataProvider
	public Object[][] getData(){
		
		Object[][] data= {
				{"Ali", "Veli", "Aliveli", "Aliveli@123889"},
				{"John", "Doe", "JohnDoe", "Johndoe@123495"},
				{"Dave", "Sun" , "davesun", "Davesun@39838484"},
				{"Ryan", "Dad" , "ryandad", "ryanDad@3848499"},
				{"Justin", "Case" , "jcase", "Justin@834837839!"}
		};
		return data;
//		
//		String actualEmpId=driver.findElement(By.id("personal_txtEmployeeId")).getAttribute("value");
//        Assert.assertEquals(actualEmpId, empId, "Employee ID did not match");
  }
  
  
  
}

