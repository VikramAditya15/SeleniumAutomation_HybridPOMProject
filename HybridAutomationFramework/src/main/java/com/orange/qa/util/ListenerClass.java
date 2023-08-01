package com.orange.qa.util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orange.qa.base.TestBase;

public class ListenerClass extends TestBase implements ITestListener {

	ExtentSparkReporter htmlReporter;
	ExtentTest test;
	ExtentReports reports;

	public void configureReport() {
		
		String testReportPath=System.getProperty("user.dir") + "/src/test/resources/TestReports/ExtentHTMLReport.html";
		System.out.println("Report Path : "+testReportPath);
		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/src/test/resources/TestReports/ExtentHTMLReport.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);

		// add config values
		reports.setSystemInfo("machine", "Mymac");
		reports.setSystemInfo("OS :", "Mac");
		reports.setSystemInfo("Browser :", "Chrome");
		reports.setSystemInfo("Username", "Vikram");

		// Configuration for look of Report
		htmlReporter.config().setDocumentTitle("Extent Report Demo :");
		htmlReporter.config().setReportName("Extent Report :");
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	public ListenerClass() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void onStart(ITestContext Result) {
		System.out.println("Starting the Test Suite : " + Result.getName());
		configureReport();
	}

	public void onFinish(ITestContext Result) {
		System.out.println("Ending the Test Suite : " + Result.getName());
		reports.flush();
	}

	public void onTestFailure(ITestResult Result) {
		System.out.println("Failed Test Method Name : " + Result.getName());
		try {
			test = reports.createTest(Result.getName());
			TestUtil.takeScreenshot(Result.getName());
			test.log(Status.FAIL, MarkupHelper.createLabel(Result.getName(), ExtentColor.RED));
			test.fail(Result.getThrowable());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult Result) {
		System.out.println("Skipped Test Method Name : " + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(Result.getName(), ExtentColor.YELLOW));
	}

	public void onTestSuccess(ITestResult Result) {
		System.out.println("Success Test Method Name : " + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(Result.getName(), ExtentColor.GREEN));
	}

	public void onTestStart(ITestResult Result) {
		System.out.println("On start Test Method  : " + Result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

}
