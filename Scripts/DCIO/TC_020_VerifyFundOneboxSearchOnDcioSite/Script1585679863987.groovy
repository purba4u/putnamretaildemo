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
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.DCIOPath)

WebUI.verifyTextPresent('DC INVESTMENT ONLY', false)

uik.getCurrentSessionDriver().findElement(By.xpath('//a[@class=\'nav-link start-search\']')).click()
WebUI.delay(2)
uik.getCurrentSessionDriver().findElement(By.xpath('//span[text()=\'What are you looking for?\']//parent::label/preceding-sibling::input')).sendKeys("retirement trust")
uik.getCurrentSessionDriver().findElement(By.xpath('//button[text()=\'Search\']')).click()

sleep(5000)
WebUI.waitForPageLoad(20)

'Confirm if the Fund Onebox is appearing'
TestObject FundOneBox= findTestObject('DCIO/SearchFundOneBox')

if (WebUI.waitForElementPresent(FundOneBox, 10)) {
		KeywordUtil.markPassed('Fund Onebox is present.')
	} else {
		KeywordUtil.markFailed('Fund Onebox is not appearing.')
	}
	
	'Verify all links within FundOnebox'
	CustomKeywords.'customKeywords.PortalUIKeywords.verifyForBrokenLinks'(findTestObject('DCIO/SearchFundOneBoxContainer'),
			'href', '')
		
	'Verify Fund Share class in Fund Onebox'
	def FundName1= WebUI.getText(findTestObject('DCIO/SearchFundOneBoxFundName'))
	if (FundName1.contains('I shares')) {
		KeywordUtil.markPassed('Retirement trust Fund in Fund Onebox with Share Class I')
	} else {
		KeywordUtil.markFailed('Retirement trust Fund in Fund Onebox not with Share Class I')
	}

'Verify for other funds with Y share as default'	
	uik.getCurrentSessionDriver().findElement(By.xpath('//a[@class=\'nav-link start-search\']')).click()
	WebUI.delay(2)
	uik.getCurrentSessionDriver().findElement(By.xpath('//span[text()=\'What are you looking for?\']//parent::label/preceding-sibling::input')).sendKeys("Income Fund")
	uik.getCurrentSessionDriver().findElement(By.xpath('//button[text()=\'Search\']')).click()
	
	sleep(5000)
	WebUI.waitForPageLoad(20)
	
	'Verify Fund Share class in Fund Onebox'
	def FundName2= WebUI.getText(findTestObject('DCIO/SearchFundOneBoxFundName'))
	if (FundName2.contains('Y shares')) {
		KeywordUtil.markPassed('Fund in Fund Onebox with Share Class Y')
	} else {
		KeywordUtil.markFailed('Fund in Fund Onebox not with Share Class Y. Jira defect RS-265.')
	}
	
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}