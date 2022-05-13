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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
try{
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.WhitePaperPath)

WebUI.verifyTextPresent('Expert views on issues shaping the investment landscape', false)

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('White Papers - Putnam Investments')

CustomKeywords.'customKeywords.PortalKeywords.verifyTabs'(['Asset allocation', 'Fixed income', 'Equity', 'Outlook'
        , 'All'])

CustomKeywords.'customKeywords.PortalKeywords.verifyDocJsflink'(findTestObject('IndividualInvestor/PageSpecificObjects/Retirement_IndInvestor/WhitePaper_FixedIncomeOutlook'),".pdf")

//WebUI.closeBrowser()

}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}