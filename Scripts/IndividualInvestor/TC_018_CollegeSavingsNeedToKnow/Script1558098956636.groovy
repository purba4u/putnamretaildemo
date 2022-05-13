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
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.CollegeSavingsNeedToKnow)

WebUI.verifyTextPresent('FIND YOUR ANSWERS', false)

WebUI.verifyTextPresent('What you need to know about 529 plans', false)

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('529 Plan - Putnam Investments')

WebUI.verifyTextPresent('How much will I need to save?', false)

WebUI.verifyTextPresent('Try our college savings calculator.', false)

WebUI.verifyTextPresent('Learn about the exclusive scholarship program for Nevada residents.', false)

WebUI.verifyTextPresent('Should I choose my state\'s 529 plan or another state\'s?', false)

WebUI.verifyTextPresent('How do I open a 529 account?', false)

WebUI.verifyTextPresent('Are there contribution limits to a 529 plan?', false)

WebUI.verifyTextPresent('Who can invest in a 529 plan?', false)

WebUI.verifyTextPresent('Can I change a 529 plan beneficiary?', false)

WebUI.verifyTextPresent('Who controls the 529 account?', false)

WebUI.verifyTextPresent('Where can I use my 529 account?', false)

}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}