package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Desktop;
import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportClass implements ITestListener {
	public ExtentSparkReporter sparkReporter; // UI of the report.
	public ExtentReports reports; // Populate common info on the report.
	public ExtentTest test; // Creating test case and update status of the test methods.
	public String reportName;

	@Override
	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-a").format(new Date());
		reportName = context.getName()+"_"+timestamp+".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\"+reportName);
		sparkReporter.config().setDocumentTitle("User API Automation Report");
		sparkReporter.config().setReportName("Functional Test");
		sparkReporter.config().setTheme(Theme.STANDARD);

		reports = new ExtentReports();
		reports.setSystemInfo("Operating System", "Windows11");
		reports.setSystemInfo("Application Name", "User API");
		reports.setSystemInfo("Environment", "QA");
		reports.setSystemInfo("User Name", System.getProperty("user.name"));
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			reports.setSystemInfo("Groups", includedGroups.toString());
		}
		reports.attachReporter(sparkReporter);
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
		
/*		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\"+reportName;
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
*/		

/*
		try { 
		 	URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);
		 	ImageHtmlEmail email = new ImageHtmlEmail(); 
		 	email.setDataSourceResolver(new DataSourceUrlResolver(url)); 
		 	email.setHostName("smtp.googlemail.com");
		 	email.setSmtpPort(465); 
		 	email.setAuthenticator(new DefaultAuthenticator("jejurkar.mahesh@gmail.com", "JMahesh@1990"));
		 	email.setSSLOnConnect(true); 
		 	email.setFrom("jejurkar.mahesh@gmail.com");
		 	email.setSubject("Test Result");
		 	email.setMsg("Please find attached report.");
		 	email.addTo("maheshmjejurkar@gmail.com");
		 	email.attach(url,"Extent Report","Please check report"); 
		 	email.send();
		 }
		catch(Exception e){ 
		 	e.printStackTrace(); 
		}
*/		 
		 
	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got executed successfully.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got failed.");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " got skipped.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
}
