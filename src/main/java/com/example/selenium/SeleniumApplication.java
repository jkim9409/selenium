package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class SeleniumApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SeleniumApplication.class, args);
		System.setProperty("webdriver.chrome.drive", "C:\\Users\\ntuple\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://www.rahulshettyacademy.com/locatorspractice/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());

		//this will close one window (tab)
//		driver.close();

		//this will close all windows (all tabs)
//		driver.quit();

		driver.findElement(By.id("inputUsername")).sendKeys("Junho");
		driver.findElement(By.name("inputPassword")).sendKeys("1234");
		driver.findElement(By.name("chkboxOne")).click();
		driver.findElement(By.name("chkboxTwo")).click();
		driver.findElement(By.cssSelector("button.signInBtn")).click();
		//We need to wait a little until it shows login error
		//Many Wait Mechanism
		//Implicit Wait ( will set up max time out until specific event )
		//Will have to use manage() right after you create driver
		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());

		//If the error message matches what is expected, do the next action.s
		driver.findElement(By.linkText("Forgot your password?")).click();

		//wait a little for page to render completely
		Thread.sleep(1000);


		//Xpath practice
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Junho");

		//		Following two lines will do the same
		//		driver.findElement(By.xpath("//input[@type='text'][2]")).sendKeys("junho@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("junho@gmail.com");

		//		Following two lines will do the same
		//		driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("010-1234-5678");
		driver.findElement(By.cssSelector("input[type='text']:nth-child(4)")).sendKeys("010-1234-5678");

		//		Following two lines will do the same
//		driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();

		System.out.println("reset button clicked");

//		Following two lines will do the same
		System.out.println(driver.findElement(By.cssSelector("p.infoMsg")).getText());
		System.out.println(driver.findElement(By.cssSelector("form p")).getText());

		String message = driver.findElement(By.cssSelector("p.infoMsg")).getText();
		String password = extractPassword(message);
		System.out.println("password = " + password);

		driver.findElement(By.cssSelector("button.go-to-login-btn")).click();

		//wait a little for page to render completely
		Thread.sleep(1000);

		driver.findElement(By.id("inputUsername")).sendKeys("Junho");
		driver.findElement(By.name("inputPassword")).sendKeys(password);
		driver.findElement(By.cssSelector("button.signInBtn")).click();
	}

	private static String extractPassword(String message) {
		Pattern pattern = Pattern.compile("'(.*?)'");
		Matcher matcher = pattern.matcher(message);

		if (matcher.find()) {
			return matcher.group(1);
		}

		return null; // or throw an exception if password not found
	}

}
