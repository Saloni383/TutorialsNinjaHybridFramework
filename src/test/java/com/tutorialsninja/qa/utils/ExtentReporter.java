package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport = new ExtentReports();
		File extentReporterFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\ExtentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReporterFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Test Automation");
		sparkReporter.config().setDocumentTitle("TN Automoation Report");
		sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		Properties p = new Properties();
		File extentFile = new File(
				"C:\\Users\\saloni.kaushik\\HybridTestNGFramework\\TutorialsNinjaProject\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(extentFile);
			p.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL", p.getProperty("url"));
		extentReport.setSystemInfo("Browser ", p.getProperty("browser"));
		extentReport.setSystemInfo("Name of user ", p.getProperty("Name"));
		extentReport.setSystemInfo("Email of user ", p.getProperty("validEmail"));
		extentReport.setSystemInfo("Operating System ", System.getProperty("os.name"));
		extentReport.setSystemInfo("User of system ", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version ", System.getProperty("java.version"));

		return extentReport;

	}

}
