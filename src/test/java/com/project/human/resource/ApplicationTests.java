package com.project.human.resource;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.openqa.selenium.WebDriver;

import java.net.URL;


@SpringBootTest
class ApplicationTests {
	private WebDriver driver;

	@Test
	void contextLoads() {
		System.out.println("Hello!!");
		try {

			ChromeOptions options = new ChromeOptions();

            URL url = new URL("http://localhost:4444/wd/hub");

			driver = new RemoteWebDriver(url, options);

            driver.get("https://opensource-demo.orangehrmlive.com/");

            System.out.println("Title details "+driver.getTitle());

            driver.quit();
/*
		    WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver", "C:\\Users\\Akhil\\Desktop\\Selenium\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

			//Page synchronization
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			driver.get("http://www.edureka.co");
			*/

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


}
