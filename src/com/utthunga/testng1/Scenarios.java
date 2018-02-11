package com.utthunga.testng1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Scenarios {
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
	}
	 static WebDriver driver;
	 FirefoxOptions opt;
	@Test(enabled=true,priority=1)
	public void register() 
	{
		WebElement reg = driver.findElement(By.xpath("//span[text()='Register']"));
		reg.click();
		WebElement fname = driver.findElement(By.id("firstname"));
		fname.clear();
		fname.sendKeys("Natarajan");
		WebElement lname = driver.findElement(By.name("lastname"));
		lname.clear();
		lname.sendKeys("Ramanathan");
		WebElement email = driver.findElement(By.name("email"));
		email.clear();
		email.sendKeys("natarajan.ramanathan93@gmail.com");
		WebElement ctype = driver.findElement(By.name("company_type"));
		Select ctype_select = new Select(ctype);
		ctype_select.selectByValue("selling");
		WebElement role = driver.findElement(By.name("individual_role"));
		Select role_select = new Select(role);
		role_select.selectByVisibleText("Technical/developer");
		WebElement pass = driver.findElement(By.xpath("//input[@type='password']"));
		pass.clear();
		pass.sendKeys("Welcome123");
		WebElement conf_pass = driver.findElement(By.xpath("//input[@title='Confirm Password']"));
		conf_pass.clear();
		conf_pass.sendKeys("Welcome123");
		By captcha_box = By.className("recaptcha-checkbox-checkmark");
		frameclick(captcha_box);
		WebElement agree = driver.findElement(By.id("agree_terms"));
		agree.click();
		agree.submit();
		try {
		Assert.assertTrue(driver.findElement(By.className("error-msg")).isDisplayed());
		}catch(AssertionError e)
		{
			Reporter.log(e.getMessage());
			Assert.fail();
		}
		
		String exp="Captcha not valid.";
		String act=driver.findElement(By.className("error-msg")).getText();
		try {
			Assert.assertEquals(act, exp);
		}catch(AssertionError e)
		{
			Reporter.log(e.getMessage());
			Assert.fail();
		}
		driver.quit();
	}
	
	public static void frameclick(By loc)
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		if(frames.isEmpty())
		{
			frames = driver.findElements(By.tagName("frame"));
		}
		List<WebElement> elements;
		for(WebElement frame:frames)
		{
			driver.switchTo().frame(frame);
			elements = driver.findElements(loc);
			if(!elements.isEmpty())
			{
				driver.findElement(loc).click();
				driver.switchTo().defaultContent();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				break;
			}
			driver.switchTo().defaultContent();
		}
	}
	
@Test(priority=2)
public void login() 
{
		
	WebElement email_id = driver.findElement(By.name("login[username]"));
	email_id.clear();
	email_id.sendKeys("Mahesh.tk@gmail.com");
	WebElement pwd = driver.findElement(By.name("login[password]"));
	pwd.clear();
	pwd.sendKeys("password@123");
	WebElement btn = driver.findElement(By.id("send2"));
	btn.click();
	
	String exp="Invalid login or password.";
	String act=driver.findElement(By.className("error-msg")).getText();
	try {
		Assert.assertEquals(act, exp);
	}catch(AssertionError e)
	{
		Reporter.log(e.getMessage());
		Assert.fail();
	}
	
}
@BeforeMethod
public void launch() {
	
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("http://magento.com");
	WebElement my_acc = driver.findElement(By.linkText("MY ACCOUNT"));
	my_acc.click();	
}

@AfterMethod
public void close() {
	driver.quit();
}


}



