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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import customKeywords.PortalTestingKeywords as ptk
import customKeywords.UIKeywords as uik
import internal.GlobalVariable as GlobalVariable

try {
    ptk.loginToPortal(GlobalVariable.individualHomePage, 'individual', GlobalVariable.indUserName, GlobalVariable.IndPassword)

    uik.navigateToPath('Balance and activity alerts')

    WebUI.verifyTextPresent('Receive balance alerts by email?', false)

    WebUI.verifyTextPresent('Receive activity alerts by email?', false)

	WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/tgBalanceAlerts'))
	
	WebUI.click(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/btnSaveChanges'))
//	WebUI.waitForPageLoad(10)
//	
//	WebUI.verifyTextPresent('Your alert preferences have been updated.',false)
	WebUI.waitForElementPresent(findTestObject('IndividualInvestor/PageSpecificObjects/updateProfile/lblSucessMsg'), 10)
	
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}

