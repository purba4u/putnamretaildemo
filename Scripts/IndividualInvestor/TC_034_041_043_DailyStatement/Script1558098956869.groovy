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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import customKeywords.UIKeywords as uik
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import customKeywords.PortalTestingKeywords as p
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
org.openqa.selenium.interactions.Actions

WebUI.comment('Test Case Started')
try{
p.loginToPortal(GlobalVariable.individualHomePage, "individual", GlobalVariable.indUserName, GlobalVariable.IndPassword)
WebUI.waitForPageLoad(2)

Thread.sleep(2)

WebUI.mouseOver(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/elShowAccounts'))

WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/elShowAccounts'))

CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

CustomKeywords.'customKeywords.PortalKeywords.validateDailyPricing'(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/AccountSummary'))

}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}
WebUI.comment('Test Case Ended')