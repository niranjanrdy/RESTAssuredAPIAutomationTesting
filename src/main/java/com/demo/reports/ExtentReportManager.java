package com.demo.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	public static ExtentTest test;
	private static ExtentReports extent;

	public static void configReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("report/index.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("API Automation Report");
		spark.config().setReportName("ExtentReports");
		extent.attachReporter(spark);
	}

	public static void flushReport() {
		extent.flush();
	}

	public static void createTest(String testName, String testType) {
		test = extent.createTest(testName)
				.assignCategory(testType).assignAuthor("Otis")
				.assignAuthor("Milburn").assignDevice("Chrome 96");
	}
}
