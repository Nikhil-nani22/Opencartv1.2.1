package utilities;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	// ===================== ON START =====================
	@Override
	public void onStart(ITestContext testContext) {
		System.out.println(">>> EXTENT onStart() EXECUTED <<<");

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp

		repName = "Test-Report-" + timeStamp + ".html";
		String reportDirPath = System.getProperty("user.dir") + "\\reports";

		File reportDir = new File(reportDirPath);
		if (!reportDir.exists()) {
			reportDir.mkdirs();
		}

		String reportPath = reportDirPath + "\\" + repName;
		sparkReporter = new ExtentSparkReporter(reportPath);

		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of the report
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		// Parameters from testng.xml
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();

		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	// ===================== ON TEST SUCCESS =====================
	@Override
	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	// ===================== ON TEST FAILURE =====================

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// ===================== ON TEST SKIPPED =====================

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	// ===================== ON FINISH =====================
	@Override
	public void onFinish(ITestContext testContext) {
		System.out.println(">>> EXTENT onFinish() EXECUTED <<<");

		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;

		File extentReport = new File(pathOfExtentReport);

		// Open report automatically you should comment if you're running with Jenkins
		/*
		 * try { Desktop.getDesktop().browse(extentReport.toURI()); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */
		// when running from jenkins use this

		System.out.println("Extent report generated at: " + extentReport.getAbsolutePath());

		// (Optional) Email code block – kept as-is from your notes
		try {
			URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

			/*
			 * ImageHtmlEmail email = new ImageHtmlEmail(); email.setDataSourceResolver(new
			 * DataSourceUrlResolver(url)); email.setHostName("smtp.googlemail.com");
			 * email.setSmtpPort(465); email.setAuthenticator( new
			 * DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
			 * email.setSSLOnConnect(true); email.setFrom("pavanoltraining@gmail.com");
			 * email.setSubject("Test Results");
			 * email.setMsg("Please find attached Report...");
			 * email.addTo("pavankumar.busyqa@gmail.com"); email.send();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
