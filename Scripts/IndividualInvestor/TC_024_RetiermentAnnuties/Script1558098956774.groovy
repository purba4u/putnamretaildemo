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
CustomKeywords.'customKeywords.PortalUIKeywords.loadUrl'(GlobalVariable.ENV + GlobalVariable.RetiermentAnnuties)

WebUI.verifyTextPresent('Annuities', false)

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Annuities - Putnam Investments')

CustomKeywords.'customKeywords.PortalKeywords.verifyTabs'(['Allstate', 'Talcott', 'Variable Trust'])

CustomKeywords.'customKeywords.PortalKeywords.verifyNewTabLaunches'('Prospectuses | SAI', 'Variable Trust Diversified Income Trust Documentation - Putnam Investments')

CustomKeywords.'customKeywords.PortalKeywords.verifyNewTabLaunches'('June', 'Putnam VT Diversified Income Fund Semi-Annual Report - VTSA028-a8a3b182ee108337f0ba3fbd4fdb2a74.pdf')

CustomKeywords.'customKeywords.PortalKeywords.navigateToTab'('Talcott')

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Fixed Accumulation Feature')

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Annuities - Fixed Accumulation Feature - Putnam Investments')

WebUI.navigateToUrl(GlobalVariable.ENV + GlobalVariable.RetiermentAnnuties)
CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()
CustomKeywords.'customKeywords.PortalKeywords.navigateToTab'('Allstate')

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Putnam Allstate Advisor')

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Annuities Pricing - Putnam Investments')

WebUI.navigateToUrl(GlobalVariable.ENV + GlobalVariable.RetiermentAnnuties)
CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()
CustomKeywords.'customKeywords.PortalKeywords.navigateToTab'('Allstate')

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Putnam Allstate Advisor Plus')

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Annuities Pricing - Putnam Investments')

WebUI.navigateToUrl(GlobalVariable.ENV + GlobalVariable.RetiermentAnnuties)
CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()
CustomKeywords.'customKeywords.PortalKeywords.navigateToTab'('Allstate')

CustomKeywords.'customKeywords.PortalUIKeywords.navigateToPath'('Putnam Allstate Advisor Preferred')

CustomKeywords.'customKeywords.PortalKeywords.verifyPageTitle'('Annuities Pricing - Putnam Investments')

WebUI.navigateToUrl(GlobalVariable.ENV + GlobalVariable.RetiermentAnnuties)

CustomKeywords.'customKeywords.PortalUIKeywords.waitForDocLoad'()

CustomKeywords.'customKeywords.PortalKeywords.navigateToTab'('Allstate')

CustomKeywords.'customKeywords.PortalKeywords.verifyNewTabLaunches'('Allstate Annuity NY Price Sheet', 'Allstate Annuity NY Price Sheet - allstate_ny_prices-')

//WebUI.closeBrowser()
}
catch (Exception ex) {
	KeywordUtil.markFailed(ex.getMessage())
}
