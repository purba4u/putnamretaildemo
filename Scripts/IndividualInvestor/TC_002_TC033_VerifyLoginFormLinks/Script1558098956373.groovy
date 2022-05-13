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
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.IndividualPath)

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Login')

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Need a Password')

CustomKeywords.'customKeywords.PortalKeywords.verifyPopupFormsLaunching'('Password registration', true)

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Login')

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Forgot your User ID')

CustomKeywords.'customKeywords.PortalKeywords.verifyPopupFormsLaunching'('Forgot your User ID?', true)

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Login')

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Forgot your Password')

CustomKeywords.'customKeywords.PortalKeywords.verifyPopupFormsLaunching'('Password hint', true)

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Login')

CustomKeywords.'customKeywords.PortalKeywords.verifyNewTabLaunches'('Putnam Talcott Resolution contract holders', 
    'Annuity Service Center')

CustomKeywords.'customKeywords.PortalKeywords.verifyNewTabLaunches'('Company processing', 'Login')

//WebUI.closeBrowser()

}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}