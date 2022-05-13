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
import customKeywords.PortalTestingKeywords as p
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
WebUI.comment('Test Case Starts')
try{
org.openqa.selenium.interactions.Actions

p.loginToPortal(GlobalVariable.individualHomePage, 'individual', GlobalVariable.indUserName, GlobalVariable.IndPassword)

WebUI.waitForPageLoad(2)

WebUI.mouseOver(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/elShowAccounts'))

Thread.sleep(1000)

WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/elShowAccounts'))

CustomKeywords.'customKeywords.PortalKeywords.verifyDailyPricePageTable'(findTestObject('IndividualInvestor/PageSpecificObjects/DaikyStatementPage/elClientName1'), 
    'dailystatement_1', 'dailystatement_2')

}catch (Exception ex) {
    println('exception occured' + ex.getMessage())


}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())

}
WebUI.comment('Test Case Ends')

