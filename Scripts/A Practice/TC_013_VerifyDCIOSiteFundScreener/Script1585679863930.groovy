import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import customKeywords.PortalTestingKeywords as ptk
import customKeywords.UIKeywords as uik
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By

try{
	'Launching Individual website'
	CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.DCIOPath)
 
	 'Navigate to Fund screener page'
 	CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Funds')

 	'Email login'
	  uik.getCurrentSessionDriver().findElement(By.xpath('//input[@id=\'primaryEmailInput\']')).sendKeys(GlobalVariable.AdvisorEmail)
	  uik.getCurrentSessionDriver().findElement(By.xpath('//button[@id=\'submit-email-form\']')).click()
	  sleep(5000)
	  WebUI.waitForPageLoad(20)
 
	'Verify page breadcrumb'
	WebUI.verifyElementPresent(findTestObject('DCIO/FundScreenerBreadcrumb'), 20)

	'Verify fund links on fund screener table and capture the response code of all Prod info pages'
	CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('DCIO/FundScreenerTable'),
		  'href', '')

}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}

WebUI.comment('Test Case Ended')
