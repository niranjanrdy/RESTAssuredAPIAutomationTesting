package com.demo.extentreports;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsDemo {
	@Test
	public void extenteportTest() throws IOException {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("tests.html");
		final File CONF = new File("extent-Config.json");
		spark.loadJSONConfig(CONF);
		
//		spark.config().setTheme(Theme.DARK);
//		spark.config().setDocumentTitle("API Automation Report");
//		spark.config().setReportName("ExtentReports");
		extent.attachReporter(spark);
		
		ExtentTest test = extent.createTest("Login Test").assignAuthor("Milburn").assignCategory("Smoke").assignDevice("chrome 96");//create test node in the report
		test.pass("LOGIN TEST PASSED SUCCESSFULLY");// create a test node in the report
		test.info("URL is Loaded");
		test.info("Values Entered");
		test.pass("login test completed successfully");
		
		ExtentTest newTest = extent.createTest("Home page test").assignCategory("Regression").assignAuthor("Otis").assignAuthor("Gibson").assignDevice("Firefox 61");//create test node in the report
		newTest.pass("HOME PAGE TESTED SUCCESSFULLY");// create a test node in the report
		newTest.info("URL is Loaded");
		newTest.info("Values Entered");
		newTest.fail("Home page test failed unfortunately");
		
		extent.flush();// if this method is not called, then report is not generated with logs
	}
}
