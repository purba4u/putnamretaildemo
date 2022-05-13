import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import customKeywords.UIKeywords as uik
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities

import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import java.nio.file.Path
import java.nio.file.Paths
/**
 * 
 * @author NaveenkumarB
 *
 */

class BaseTest {
	
	private static Path reportFolder= null;
	private static String reportFolderPath = null;
	private static String screenShotFolderName = null;
	private static String cleanupBatchFile = RunConfiguration.getProjectDir()+'/Data Files/Cleanup.bat'
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		WebUI.openBrowser(GlobalVariable.individualHomePage);
		WebUI.maximizeWindow();
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseVariables()
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		
		if(testCaseContext.getTestCaseStatus() == 'Failed'){
			WebUI.takeScreenshot(reportFolderPath+File.separator+screenShotFolderName+File.separator+testCaseContext.getTestCaseId()+".png")		
		}
		println testCaseContext.getTestCaseId() +" with status "+testCaseContext.getTestCaseStatus()
		WebUI.closeBrowser();
	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		String lockScript = RunConfiguration.getProjectDir()+'/Data Files/lock.bat'		
		Runtime.runtime.exec(cleanupBatchFile)
		Runtime.runtime.exec(lockScript)
		reportFolder = Paths.get(RunConfiguration.getReportFolder())
		reportFolderPath = reportFolder.toString()
		reportFolderPath = reportFolderPath.replace("\\",File.separator)	
		screenShotFolderName = reportFolder.getFileName()
		//WebUI.comment("${reportFolderPath}")	
	}


	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {		
		Runtime.runtime.exec(cleanupBatchFile)
	}

}