package com.demo.tests;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
	static ExtentTest test;
	static ExtentReports extent;
	@BeforeTest
	public void setUp() {
		baseURI = "https://reqres.in/api";
	}

	@BeforeSuite
	public static void configReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("report/index.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("API Automation Report");
		spark.config().setReportName("ExtentReports");
		extent.attachReporter(spark);
	}
	@AfterSuite
	public static void flushReport() {
		extent.flush();
	}
	public static void createTest(String testName, String testType) {
		test = extent.createTest(testName)
				.assignCategory(testType).assignAuthor("Otis")
				.assignAuthor("Gibson").assignDevice("Firefox 61");
	}
}