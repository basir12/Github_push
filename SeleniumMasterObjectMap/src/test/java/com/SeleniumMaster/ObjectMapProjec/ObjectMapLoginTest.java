package com.SeleniumMaster.ObjectMapProjec;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ObjectMapLoginTest {

	public WebDriver driver;
	public ObjectMap objmap;

	@BeforeTest
	public void setUp() throws Exception {
		// Create a new instance of the Firefox driver
		System.setProperty("webdriver.chrome.driver", "src/test/resources/Browser/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(
				"https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		Thread.sleep(3000);
	}

	@Test
	public void login() throws Exception {

		// Get current working directory
		String workingDir = System.getProperty("user.dir");

		// Get object map file
		ObjectMap objmap = new ObjectMap("ObjectPropertiesFile/objectMap.properties");

		// Get the username element
		WebElement username = driver.findElement(objmap.getLocator("Username_field"));
		username.clear();
		username.sendKeys("mina.wr@gmail.com");

		driver.findElement(objmap.getLocator("Next_Field")).click();
		Thread.sleep(3 * 1000);

		// Get the password element
		WebElement password = driver.findElement(objmap.getLocator("Password_field"));
		password.sendKeys("basir");

		Thread.sleep(3000);
		// Click on the login button
		driver.findElement(objmap.getLocator("Next_button")).click();

		// Assert the user login by checking the Online user
		// WebElement onlineuser=driver.findElement(objmap.getLocator("online_user"));
		// assertEquals("Online users", onlineuser.getText());

	}

	@AfterTest
	public void tearDown() throws Exception {
		// Close the browser
		try {
			Thread.sleep(3000);
			driver.close();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
