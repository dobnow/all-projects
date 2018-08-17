package com.electrical;

import com.util.Constants;
import com.util.TestUtil;
import com.util.Xls_Reader;
import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.support.PageFactory;
import com.pages.ElectricalDashboardPage;
import com.pages.ElectricalPage;
import com.relevantcodes.extentreports.LogStatus;
import com.base.TestBase;

public class ElOther extends TestBase {
	Xls_Reader xlsx = new Xls_Reader(Constants.testCasesesEctrical);
	String testname = "ElOther";
	
	@BeforeSuite
	public void BeforeSuite() {
		initConfigurations();
	}

	@BeforeMethod
	public void init() {
		initDriver();
		getEnvironmentDetails();
	}

	@AfterMethod
	public void quit() {
//		quitDriver();
	}

	@AfterSuite
	public void closeChromeDriver() {
		killDriver();
	}
	
	@AfterClass
	public void setChrome() {
		setConfigBrowser("Chrome");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(testname, xlsx);
	}

	@Test(priority = 0, dataProvider = "getTestData", invocationCount = 1)
	public void Portal(Hashtable<String, String> data) {
		if (!TestUtil.isExecutable(testname, xlsx) || data.get("Runmode").equals("N"))
			throw new SkipException("Skipping the test");
		test = rep.startTest(data.get("description"));
		test.log(LogStatus.INFO, data.get("description"));
		test = rep.startTest("Test Data");
		test.log(LogStatus.INFO, data.toString());
		System.out.println("BEGIN " + convertedTimestamp() + " **************** " + data.get("description"));
		ElectricalDashboardPage dash = PageFactory.initElements(driver, ElectricalDashboardPage.class);
		ElectricalPage el = PageFactory.initElements(driver, ElectricalPage.class);
		
		
		loginToPortal(user);
		dash.jobFiling(data.get("work_type"));
		el.locationInfo(data.get("address"),data.get("description"),data.get("calendar"),data.get("joint_venue"),data.get("use_type"));
		el.applicantInfo(data.get("applicant_info"));
		el.ownerInfo(data.get("owner_info"));
		el.additionalInfo(data.get("additional_info"));
		el.saveGI(data.get("save_gi"));
		el.scopeOfWork(data.get("sow"));
		
		
		
		
/*		filterJob(user);
		el.scopeOfWork(data.get("sow"));
		
		loginToPortal(user);
		dash.jobFiling(data.get("work_type"));
		el.locationInfo(data.get("address"),data.get("description"),data.get("calendar"),data.get("joint_venue"),data.get("use_type"));
		el.applicantInfo(data.get("applicant_info"));
		el.ownerInfo(data.get("owner_info"));
		el.additionalInfo(data.get("additional_info"));
		el.saveGI(data.get("save_gi"));
		el.scopeOfWork(data.get("sow"));
		el.uploadDocuments(data.get("documents"));
		el.signatures(data.get("sign"));
		el.previewToFile(data.get("preview_to_file"));*/
		successMessage(data.get("description"));
		
	}

}