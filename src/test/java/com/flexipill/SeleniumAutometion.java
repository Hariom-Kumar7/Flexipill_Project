package com.flexipill;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumAutometion {
	WebDriver driver;
	ExtentReports er;
	ExtentTest test;
	
	@BeforeClass
	public void testreport() {
		 er = new ExtentReports(System.getProperty("user.dir")+"/Report.html");
		 test = er.startTest("Flexipill");
	}
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://flexipill-ui-new-staging.vercel.app/");
		
		
	}
	@Test(priority = 0)
	public void login() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class = 'MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall platinumrx-1o6z5ng']")).sendKeys("1111111111");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[@class = 'MuiTypography-root MuiTypography-body1 platinumrx-1bx7d16']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class = 'MuiInputBase-input MuiOutlinedInput-input platinumrx-1x5jdmq']")).sendKeys("1111");
		test.log(LogStatus.PASS, "Login functionality");
	}
	@Test(priority = 1)
	public void AddToCart() throws InterruptedException {
		/*
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class = 'MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall platinumrx-1o6z5ng']")).sendKeys("1111111111");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[@class = 'MuiTypography-root MuiTypography-body1 platinumrx-1bx7d16']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class = 'MuiInputBase-input MuiOutlinedInput-input platinumrx-1x5jdmq']")).sendKeys("1111");
		*/
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Thread.sleep(2000);
		WebElement scrollUpto = driver.findElement(By.xpath("(//p[@class = 'landing-page_landing__sectionTitle__maj1m'])[1]"));
		js.executeScript("arguments[0].scrollIntoView();", scrollUpto);
		driver.findElement(By.xpath("(//span[@class='MuiTypography-root MuiTypography-p platinumrx-8ygzv4'][normalize-space()='Add to Cart'])[1]")).click();
		driver.findElement(By.xpath("(//span[@class='MuiTypography-root MuiTypography-p platinumrx-8ygzv4'][normalize-space()='Add to Cart'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class = 'MuiBadge-badge MuiBadge-standard MuiBadge-anchorOriginTopRight MuiBadge-anchorOriginTopRightRectangular MuiBadge-overlapRectangular platinumrx-1372tkt']")).click();
		test.log(LogStatus.PASS, "AddToCart Functionality");
	}
	@Test(priority = 2)
	public void placeOrder() throws InterruptedException {
		JavascriptExecutor js2 = (JavascriptExecutor)driver;
		Thread.sleep(2000);
		WebElement scrollUpto = driver.findElement(By.xpath("//h2[@class='MuiTypography-root MuiTypography-h2 platinumrx-1almcn1']"));
		js2.executeScript("arguments[0].scrollIntoView();", scrollUpto);
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
	}
	@AfterTest
	public void close() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}
}
