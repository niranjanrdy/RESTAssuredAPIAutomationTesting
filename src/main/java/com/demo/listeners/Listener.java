package com.demo.listeners;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.demo.reports.ExtentReportManager;
public class Listener implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		ExtentReportManager.configReport();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReportManager.flushReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReportManager.createTest(result.getMethod().getMethodName(), "Regression");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReportManager.test.pass(result.getMethod().getMethodName()+" is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReportManager.test.fail(result.getMethod().getMethodName()+" is failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReportManager.test.skip(result.getMethod().getMethodName()+" is skipped");
	}

}
