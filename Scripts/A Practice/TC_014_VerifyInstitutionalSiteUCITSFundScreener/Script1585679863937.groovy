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
	'Navigate to Institutional Site UCITS page'
    CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.InstitutionalPath + "/ucits/other/")
	WebUI.waitForPageLoad(10)
	
	'Accept the initial agreement form'
	uik.getCurrentSessionDriver().findElement(By.xpath('//button[text()=\'I agree\']')).click()
	uik.waitForDocLoad()
	
	'Accept the UCITS agreement form'
	uik.getCurrentSessionDriver().findElement(By.xpath('//button[text()=\'I agree\']')).click()
	uik.waitForDocLoad()
	
  	'Verify page breadcrumb'
	WebUI.verifyElementPresent(findTestObject('Institutional/UCITSBreadcrumb'), 20)

	'Verify UCITS prod info links launched from UCITS screener page'
	CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('Institutional/UCITSTable'),
		  'href', '')

}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}

WebUI.comment('Test Case Ended')
