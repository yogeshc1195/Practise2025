package Com.TCS;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebShop {

	WebDriver driver;
	
	@BeforeSuite
	public void openBrowser()
	{
		
		System.out.println("Opening Browser In Processs");
		System.setProperty("webdriver.chrome.driver","D:\\TESTING REQUIRED APPS JARS\\Chrome 131\\chromedriver.exe");
		driver=new ChromeDriver();
		
		
	}
	
	@BeforeTest
	public void URL()
	{
		System.out.println("Entering URL");
		driver.get("http://demowebshop.tricentis.com/");
		driver.manage().timeouts().pageLoadTimeout(1000,TimeUnit.SECONDS);
	}
	
	@BeforeClass
	public void Maximize()
	{
		System.out.println("Maximize the Browser");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeSS() throws IOException
	{
		System.out.println("Taking screenshot before Login");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File("D:\\Testing Workspace"));
		
		Set<Cookie> c=driver.manage().getCookies();
		for(Cookie co:c) {
			System.out.println("COOKIE : " + co.getName());
		}
		
	}
	@Test
	public void logIn()
	{
		System.out.println("LOG IN INPROGRESS");
		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		driver.findElement(By.xpath("//input[@class=\"email\"]")).sendKeys("Ashishnawa@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"Password\"]")).sendKeys("RANVEER");
		driver.findElement(By.xpath("//div[@class=\"buttons\"]/child::input[@type=\"submit\"]")).click();
	}
@AfterMethod	
public void loginSuccess()
{
	System.out.println("VALIDATE THE LOGIN");
	WebElement actual=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a"));
	
	String expected= "Ashishnawa@gmail.com";
	assertEquals(actual.getText(), expected);
			if(actual.getText().equals(expected))
			{
				System.out.println("LOGIN SUCCESS");
			}
				else {
					System.out.println("LOGIN FAILED");
				}
		}
	@AfterClass
	public void screenshot() throws IOException
	{
		File srcc=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(srcc,new File("D:\\Testing Workspace"));
		
		driver.manage().deleteAllCookies();
		
	}
	@AfterTest
	public void done()
	{
		System.out.println("OVER");
	}
	@AfterSuite
	public void close()
	{
		System.out.println("Browser closed");
		driver.close();
	}
	
	
}
