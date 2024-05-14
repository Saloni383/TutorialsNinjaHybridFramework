package com.tutorials.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.utils.Utilities;

public class base {
	WebDriver driver;
	public Properties p;
	public Properties dataProp;
	

	public void loadPropertiesFiles() throws IOException {
		p= new Properties();
		File propFile= new File("C:\\Users\\saloni.kaushik\\HybridTestNGFramework\\TutorialsNinjaProject\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(propFile);
		p.load(fis);
		System.out.println(p.getProperty("browser"));
		System.out.println(p.getProperty("url"));
		
		dataProp = new Properties();
		File dataPropFile = new File("C:\\Users\\saloni.kaushik\\HybridTestNGFramework\\TutorialsNinjaProject\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		FileInputStream fis2 = new FileInputStream(dataPropFile);
		dataProp.load(fis2);
		
		
		
	}
	public WebDriver initializeBrowserAndOpenApplicationURL(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		driver.get(p.getProperty("url"));
		
		return driver;

	}
}
